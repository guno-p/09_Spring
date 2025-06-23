package org.scoula.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {
        "org.scoula.advice",
        "org.scoula.sample.service"
})
@EnableAspectJAutoProxy
public class RootConfig {
    /*
    * 일반 설정 : 어디에서든지 써먹는 설정
    * ex) DB 설정 : 어디서든지 DB 설정 일어날 수 있음
    * */
}
