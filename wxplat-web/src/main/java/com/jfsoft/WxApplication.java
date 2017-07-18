package com.jfsoft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

/**
 * @author ChenXc
 * @version V1.0
 * @Date 2017/7/13  9:22
 * @Description TODO(一句话描述类作用)
 */
@SpringBootApplication
@MapperScan("com.jfsoft.mapper")
public class WxApplication {
    public static void main(String[] args) {

        SpringApplication.run(WxApplication.class, args);

    }
}
