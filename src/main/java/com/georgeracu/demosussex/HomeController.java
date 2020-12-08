package com.georgeracu.demosussex;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author georgicaracu
 */
@Controller
public class HomeController {
    @GetMapping("/")
    @ResponseBody
    public String getHome() {
        return "Hello World v1";
    }
}
