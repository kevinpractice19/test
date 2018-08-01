package com.example.test;

import com.example.test.entity.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication(scanBasePackages = "com.example.test")
@Slf4j
@MapperScan(basePackages = "com.example.test.mapper")
public class TestApplication {

    public static void main(String[] args) {

        UserVo userVo = new UserVo();
        if (userVo.getAccount() == null) {
            System.out.println(userVo.getAccount());
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        SpringApplication.run(TestApplication.class, args);
    }
}
