package com.sml.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/location")
public class LocationController {
    
    private static final Logger logger = LoggerFactory.getLogger(LocationController.class);
    
    @GetMapping("/map")
    public String location() {
        logger.info("Location page accessed");
        return "location/locationMain"; // 수정된 뷰 이름 반환
    }
}
