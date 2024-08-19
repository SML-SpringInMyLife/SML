package com.sml.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@Controller
@RequestMapping("/donation")
public class DonationController {
    private static final Logger logger = LoggerFactory.getLogger(DonationController.class);

    // API Key와 Secret Key를 하드코딩으로 유지
    private final IamportClient iamportClient = new IamportClient("your_api_key", "your_api_secret");

    @GetMapping("/main")
    public String donation() {
        logger.info("donationMain page accessed");
        return "donation/donationMain"; // 뷰 이름
    }

    @GetMapping("/payment")
    public String payment() {
        logger.info("donationPayment page accessed");
        return "donation/donationPayment";
    }

    @ResponseBody
    @RequestMapping("/verify/{imp_uid}")
    public IamportResponse<Payment> paymentByImpUid(@PathVariable("imp_uid") String imp_uid)
            throws IamportResponseException, IOException {
        logger.info("Verifying payment with imp_uid: " + imp_uid);
        return iamportClient.paymentByImpUid(imp_uid);
    }
}
