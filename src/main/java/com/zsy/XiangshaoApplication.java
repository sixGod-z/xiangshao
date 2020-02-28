package com.zsy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com/zsy/mapper")
public class XiangshaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiangshaoApplication.class, args);
    }

}
