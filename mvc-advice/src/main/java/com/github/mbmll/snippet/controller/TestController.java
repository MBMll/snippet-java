package com.github.mbmll.snippet.controller;

import com.github.mbmll.snippet.advice.Res;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xlc
 * @Description
 * @Date 2023/1/30 上午 11:00
 */
@RestController
@Validated
public class TestController {
    @GetMapping("/t1")
    public String t1() {
        return "t1";
    }

    @GetMapping("/t2")
    public List<String> t2(@RequestParam @NotBlank String name, @RequestParam @Email String email) {
        return Arrays.asList(name, email);
    }

    @GetMapping("/return_res")
    public Res<Object> t3ReturnRes(@RequestParam @NotBlank String name, @RequestParam @Email String email) {
        return Res.builder().data(Arrays.asList(name, email)).build();
    }
}
