package com.ohgiraffers.crud.config;

// Application 의 위치를 config 내부로 옮기면서 BeanScan 범위가 config 내로 줄어들게 되었다
// 따라서 Bean Scan 범위를 재 지정해줄 필요가 있기 때문에 ContextConfig(환경설정) 생성

// BeanFactory = IoC Container = Application Context

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@Configuration
@ComponentScan(basePackages = "com.ohgiraffers.crud")
@MapperScan(basePackages = "com.ohgiraffers.crud",annotationClass = Mapper.class)
public class ContextConfig {

    @Bean
    // message.properties 파일을 자바 객체 형태로 읽어올 수 있게해준다.
    public ReloadableResourceBundleMessageSource messageSource(){

        // classpath : src/main/resource, scr/main/java 를 의미한다.
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:/messages/message");
        source.setDefaultEncoding("UTF-8");

        // 제공하지 않는 언어로 요청이 드어왔을때 사용할 메세지 설정
        Locale.setDefault(Locale.KOREA);

        return source;
    }

}
