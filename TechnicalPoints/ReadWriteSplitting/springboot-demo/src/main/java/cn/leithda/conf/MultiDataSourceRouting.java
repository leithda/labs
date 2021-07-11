package cn.leithda.conf;

import cn.leithda.utils.DBContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created with IntelliJ IDEA.
 *
 * @author leithda
 * @version 2021/7/9
 * @desc 多数据源实现，基于特定的key路由到指定数据源，实现determineCurrentLookupKey()编写获取key的规则
 */
@Slf4j
public class MultiDataSourceRouting extends AbstractRoutingDataSource {

    /**
     * 设置数据源路由，通过该类中的determineCurrentLookupKey决定使用哪个数据源
     */
    @Override
    protected Object determineCurrentLookupKey() {
       return DBContextHolder.get();
    }
}
