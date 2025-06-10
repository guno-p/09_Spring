package org.scoula.ex03.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.ex03.dto.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Controller // MVC 의 Controller 임을 명시, 자동 Bean 등록된다.
@Log4j2 // log 라는 필드를 만들어주는 롬복 -> 로그 찍기 위한 필드임
@RequestMapping("/sample") // "/sample" 로 시작하는 요청은 현재 컨트롤러로 매핑
public class SampleController {

    // 클래스 레벨 "/sample" + 메서드 레벨 "" -> "/sample" URL 요청 매핑
    // 단, GET, POST 요청만 매핑
    @RequestMapping(value="", method={RequestMethod.GET, RequestMethod.POST})
    public String basic() {
        log.info("[GET][POST] /sample");

        // prefix
        // /WEB-INF/views/
        // .jsp
        return "sample/input_page";
    }

    @RequestMapping(value="/input2", method={RequestMethod.GET, RequestMethod.POST})
    public String input2() {
        log.info("/sample/input2");
        return "sample/input_page2";
    }

    @RequestMapping(value="/basic2", method={RequestMethod.GET})
    public void basic2() {
        log.info("[GET] /sample/basic2");
    }

    // methos 관계 없음
    // {id}는 해당 위치에 존재하는 값은 id 라고 인식
    // @PathVariable("id") long id -> 요청 주소에서 {id} 값을 얻어와서 매개 변수 long id 에 주입
    @RequestMapping(value="/board/{id:[0-9]+}")
    public void selectBoard(@PathVariable("id") long id) {
        log.info("입력 id : " + id);
    }

    // GET 요청만 처리 - 조회 작업에 사용 (Safe, Idempotent)
    @GetMapping("/basicOnlyGet")
    public void basicGet2() {
        log.info("basic get only get............");
        // 데이터 조회, 페이지 표시 등 안전한 작업
    }

    @PostMapping("/ex01")
    public String ex01(SampleDTO dto) {  // HandlerAdapter가 자동으로 객체 생성 및 프로퍼티 바인딩
        // 자동으로 담기 위해서는 DTO 에는 **기본 생성자** 가 있어야 함.
        // 그리고 JAVA Reflection 이 어떤 필드가 있는지 유추하는데 그를 위해 **Setter** 가 있어야 함.
        // @ModelAttribute 기능을 통해 마무리로 넣어준다.
        // SampleDTO 는 파라미터가 필드에 자동으로 바인딩된 **커맨드 객체**
        log.info("" + dto);   // 바인딩된 데이터 로그 출력으로 확인
        return "sample/ex01"; // ViewResolver에 의해 /WEB-INF/views/sample/ex01.jsp로 forward
    }

    @GetMapping("/ex02")
    public String ex02(
            @RequestParam("name") String name,
            @RequestParam("age") int age
    ) {
        log.info("name : " + name + ", age : " + age);
        return "sample/ex02";
    }

    // @RequestParam 옵션 활용 - 파라미터 누락 및 기본값 처리
    @GetMapping("/ex02-advanced")
    public String ex02Advanced(
            @RequestParam(value="name", required=false, defaultValue="익명") String name,
            @RequestParam(value="age", required=true) int age) {
        // required=false: 파라미터가 없어도 에러 발생하지 않음
        // defaultValue: 파라미터가 없을 때 사용할 기본값 (문자열로 지정, 자동 형변환)
        log.info("advanced name : " + name + ", age : " + age);
        return "sample/ex02";
    }

    @GetMapping("/ex02List")
    public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
        log.info("ids: " + ids);
        return "ex02List";
        // 동일한 파라미터명으로 전송된 여러 값을 List로 자동 수집
    }

    @GetMapping("/ex02Array")
    public String ex02Array(@RequestParam("ids") String[] ids) {
        log.info("array ids: " + Arrays.toString(ids));
        return "ex02Array";
        // 동일한 파라미터명으로 전송된 여러 값을 배열로 자동 수집
    }


    // @ModelAttribute("page") -> Model.addAttribute("page", page)
    @GetMapping("/ex04")
    public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
        // /sample/ex04?name=aa&age=12&page=2
        log.info("dto: " + dto);
        log.info("page: " + page);
        return "sample/ex04";
        // @ModelAttribute로 기본 자료형도 Model에 추가하여 뷰에서 접근 가능
    }

    @GetMapping("/exUpload")
    public String exUpload() {
        log.info("/exUpload..........");
        return "sample/exUpload"; // forward
    }

    @PostMapping("/exUploadPost")
    public void exUploadPost(ArrayList<MultipartFile> files) {
        // MultipartFile: Spring이 제공하는 업로드 파일 래퍼 클래스
        for(MultipartFile file : files) {
            log.info("----------------------------------");
            log.info("name:" + file.getOriginalFilename());  // 원본 파일명
            log.info("size:" + file.getSize());              // 파일 크기 (바이트)
            log.info("contentType:" + file.getContentType()); // MIME 타입

            // 파일이 실제로 선택되었는지 확인
            if (!file.isEmpty()) {
                try {
                    // 파일을 지정된 위치에 저장
                    File saveFile = new File("c:/upload/" + file.getOriginalFilename());
                    file.transferTo(saveFile);  // 임시 파일을 최종 위치로 이동
                } catch (IOException e) {
                    log.error("파일 저장 실패", e);
                }
            }
        }
    }
}
