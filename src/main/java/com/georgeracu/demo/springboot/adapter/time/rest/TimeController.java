package com.georgeracu.demo.springboot.adapter.time.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.ZonedDateTime;

/**
 * Author georgeracu
 */
@Controller
@RequestMapping("/api/v1/time")
public class TimeController {

    @GetMapping
    @ResponseBody
    public GetTime getTime() {
        return new GetTime(ZonedDateTime.now());
    }
}
