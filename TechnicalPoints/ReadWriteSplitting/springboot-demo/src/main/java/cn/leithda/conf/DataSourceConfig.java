package cn.leithda.conf;

import cn.leithda.enums.DBTypeEnum;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author leithda
 * @version 2021/7/9
 * @desc 多数据源配置
 */
@Configuration
public class DataSourceConfig {
    /**
    * 主库
    * 可以使用@Primary 标志这个 Bean 如果在多个同类 Bean 候选时，该 Bean 优先被考虑。
    */
    @Bean
    @ConfigurationProperties(prefix = "mysql.datasource.master")
    public DataSource masterDataSource() {
        return new DruidDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "mysql.datasource.slave1")
    public DataSource slave1DataSource() {
        return new DruidDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "mysql.datasource.slave2")
    public DataSource slave2DataSource() {
        return new DruidDataSource();
    }

    @Bean
    public DataSource myRoutingDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                          @Qualifier("slave1DataSource") DataSource slave1DataSource,
                                          @Qualifier("slave2DataSource") DataSource slave2DataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBTypeEnum.MASTER, masterDataSource);
        targetDataSources.put(DBTypeEnum.SLAVE1, slave1DataSource);
        targetDataSources.put(DBTypeEnum.SLAVE2, slave2DataSource);
        MultiDataSourceRouting multiDataSourceRouting = new MultiDataSourceRouting();
        // 默认走主库
        multiDataSourceRouting.setDefaultTargetDataSource(masterDataSource);
        multiDataSourceRouting.setTargetDataSources(targetDataSources);
        return multiDataSourceRouting;
    }
}
