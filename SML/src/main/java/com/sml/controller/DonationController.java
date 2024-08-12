package com.sml.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/donation")
public class DonationController {
	private static final Logger logger = LoggerFactory.getLogger(DonationController.class);
	
    @GetMapping("/main")
    public String donation() {
        logger.info("donationMani page accessed");
        return "donation/donationMain"; // 뷰 이름
    }
	
    @GetMapping("/payment")
    public String payment() {
        logger.info("donationPayment page accessed");
        return "donation/donationPayment"; 
    }
}
