package cn.leithda.interceptors;

import cn.leithda.annotations.ReadOnly;
import cn.leithda.utils.DBContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author leithda
 * @version 2021/7/9
 * @desc 读写分离拦截器
 */
@Aspect
@Component
@Slf4j
public class ReadOnlyInterceptor implements Ordered {

    /**
     * 1. 根据注解进行切入处理
     */
    @Around("@annotation(readOnly)")
    public Object setRead(ProceedingJoinPoint joinPoint, ReadOnly readOnly) throws Throwable {
        try {
            DBContextHolder.slave();
            return joinPoint.proceed();
        } finally {
            //清除DbType一方面为了避免内存泄漏，更重要的是避免对后续在本线程上执行的操作产生影响
            DBContextHolder.clear();
            log.info("清除ThreadLocal线程本地变量");
        }
    }


    /**
     * 定义切入点
     */
    @Pointcut("execution(* cn.leithda.service..*.select*(..))")
    public void slavePointcut() {}

    /**
     * 2. 根据方法名进行切入处理,select开头的方法使用从库
     */
    @Around("slavePointcut()")
    public Object slave(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            DBContextHolder.slave();
            return joinPoint.proceed();
        } finally {
            //清除DbType一方面为了避免内存泄漏，更重要的是避免对后续在本线程上执行的操作产生影响
            DBContextHolder.clear();
            log.info("清除ThreadLocal线程本地变量");
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
