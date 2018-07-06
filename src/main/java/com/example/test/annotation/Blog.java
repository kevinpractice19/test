package com.example.test.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Blog {

    public String name();
    public String address() default "www.baidu.com";
}
