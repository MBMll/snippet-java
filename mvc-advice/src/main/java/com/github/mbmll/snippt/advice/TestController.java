package com.github.mbmll.snippt.advice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xlc
 * @Description
 * @Date 2023/1/30 上午 11:00
 */
@RestController
public class TestController {
    @GetMapping("/t1")
    public String t1() {
        return "t1";
    }
}
