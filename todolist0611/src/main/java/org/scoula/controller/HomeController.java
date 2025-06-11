package org.scoula.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.todo.domain.TodoDTO;
import org.scoula.todo.service.TodoService;
import org.scoula.todo.service.TodoServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 🏠 홈(메인) 페이지 컨트롤러
 * - 애플리케이션의 메인 페이지와 기본 요청을 처리하는 컨트롤러
 *
 * 📋 주요 기능:
 * - 루트 경로("/") 요청 처리
 * - 홈페이지 렌더링
 * - 기본 진입점 역할
 *
 * @Controller
 * - @ComponentScan 어노테이션으로 스캔된 경우 자동으로 Bean 등록되는
 *   @Component 어노테이션의 하위 어노테이션
 * - Spring MVC 컨트롤러임을 명시
 *
 * @Log4j2
 * - Lombok을 이용해 log 관련 필드를 생성하는 어노테이션
 * - private static final org.apache.logging.log4j.Logger log
 *   = org.apache.logging.log4j.LogManager.getLogger(HomeController.class)
 */
@Controller // Spring MVC 컨트롤러로 등록
@Log4j2
@RequiredArgsConstructor
public class HomeController {
    // TodoService 를 구현한 TodoServiceImpl Bean 의존성 주입
    private final TodoService todoService;

    @GetMapping("/")
    public String home(Model model) {
        List<TodoDTO> todos = todoService.selectAll();
        model.addAttribute("todos", todos);

        log.info("================> HomeController /");
        return "index"; // View의 이름 (ServletConfig의 ViewResolver에 의해 /WEB-INF/views/index.jsp로 변환)
    }
}
