package cn.leithda.utils;

import cn.leithda.enums.DBTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 *
 * @author leithda
 * @version 2021/7/9
 * @desc 多数据源上下文
 */
@Slf4j
public class DBContextHolder {

    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();

    private static final AtomicInteger counter = new AtomicInteger(-1);

    public static void set(DBTypeEnum dbType) {
        contextHolder.set(dbType);
    }

    /**
     * 获取当前线程数据库类型
     */
    public static DBTypeEnum get() {
        return Objects.isNull(contextHolder.get()) ? DBTypeEnum.MASTER : contextHolder.get();
    }

    /**
     * 切换主库
     */
    public static void master() {
        set(DBTypeEnum.MASTER);
        log.info("切换到master");
    }

    /**
     * 切换从库
     */
    public static void slave() {
        //  轮询
        final int MAX = 9999;
        int index = counter.getAndIncrement() % 2;
        if (counter.get() > MAX) {
            counter.set(-1);
        }
        if (index == 0) {
            set(DBTypeEnum.SLAVE1);
            log.info("切换到slave1");
        } else {
            set(DBTypeEnum.SLAVE2);
            log.info("切换到slave2");
        }
    }

    /**
     * 清除变量
     */
    public static void clear() {
        contextHolder.remove();
    }

}
