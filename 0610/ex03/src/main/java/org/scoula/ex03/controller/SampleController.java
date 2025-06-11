package org.scoula.ex03.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.dto.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController {
    @RequestMapping("")
    public void basic() {
        log.info("basic...");
    }

    @RequestMapping(value="/basic", method = {RequestMethod.GET, RequestMethod.POST})
    public void basicGet() {log.info("basic Get...");}

    @GetMapping("/basicOnlyGet")
    public void basicOnlyGet() {
        log.info("Only Get...");
    }

    @GetMapping("/ex01")
    public String ex01(SampleDTO dto) {
        log.info("" + dto);
        return "ex01";
    }

    @GetMapping("/ex02")
    public String ex02(
            @RequestParam("name") String name,
            @RequestParam("age") int age
    ) {
        log.info("name : " + name + ", age : " + age);
        return "ex02";
    }

    @GetMapping("/ex04")
    public String ex04(SampleDTO dto, int page) {
        log.info("" + dto);
        log.info("page : " + page);
        return "sample/ex04";
    }

    @GetMapping("/ex06")
    public String ex06(RedirectAttributes ra) {
        log.info("/ex06........");
        ra.addAttribute("name", "AAA");
        ra.addAttribute("age", 10);
        return "redirect:/sample/ex06-2";
    }

    @GetMapping("/ex07")
    public @ResponseBody SampleDTO ex07() {
        log.info("/ex07........");
        SampleDTO dto = new SampleDTO();
        dto.setAge(10);
        dto.setName("홍길동");
        return dto;

    }

    @GetMapping("/exUpload")
    public void exUpload() {

        log.info("/exUpload..........");

    }
}
