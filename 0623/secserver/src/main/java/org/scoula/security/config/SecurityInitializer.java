package org.scoula.security.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    // 별도의 구현은 필요 없다.
    // AbstractSecurityWebApplicationInitializer 에는 추상이 없고 이미 다 구현되어있다.
    // 근데 abstract 라서 이렇게 객체로 만들기 위해 이렇게 사용한다.
    // 웹 애플리케이션 시작 시에 자동으로 Sequrity 필터 등록
}
