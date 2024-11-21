package com.ohgiraffers.crud.config;

// Application 의 위치를 config 내부로 옮기면서 BeanScan 범위가 config 내로 줄어들게 되었다
// 따라서 Bean Scan 범위를 재 지정해줄 필요가 있기 때문에 ContextConfig(환경설정) 생성

// BeanFactory = IoC Container = Application Context

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.ohgiraffers.crud")
@MapperScan(basePackages = "com.ohgiraffers.crud",annotationClass = Mapper.class)
public class ContextConfig {

}
