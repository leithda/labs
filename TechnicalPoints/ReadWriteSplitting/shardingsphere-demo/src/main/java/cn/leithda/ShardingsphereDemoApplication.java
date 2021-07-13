package cn.leithda;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.leithda.dao")
public class ShardingsphereDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingsphereDemoApplication.class, args);
    }

}
