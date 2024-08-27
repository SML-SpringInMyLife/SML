package com.sml.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import com.sml.model.DonationVO;
import com.sml.model.MemberVO;
import com.sml.service.DonationService;

@Controller
@RequestMapping("/donation")
public class DonationController {

    private static final Logger logger = LoggerFactory.getLogger(DonationController.class);

    @Autowired
    private DonationService donationService;

    @GetMapping("/main")
    public String donationMain(HttpSession session, Model model) {
        MemberVO member = (MemberVO) session.getAttribute("loginMember"); // 세션에서 로그인한 사용자 정보 가져오기
        model.addAttribute("member", member); // 모델에 추가하여 JSP로 전달
        return "donation/donationMain";
    }



    @GetMapping("/all")
    public String getAllDonations(Model model) {
        logger.info("getAllDonations 메서드 진입");

        List<DonationVO> donations = donationService.getAllDonations();
        if (donations == null || donations.isEmpty()) {
            logger.warn("Donation 데이터가 비어있습니다.");
        } else {
            logger.info("Donation 데이터 수: " + donations.size());
        }

        model.addAttribute("donations", donations);
        logger.info("donations 모델에 추가 완료");

        return "donation/donationlist"; // JSP 파일에서 데이터 출력
    }
    
    
    @PostMapping("/verify/{impUid}")
    @ResponseBody
    public ResponseEntity<String> verifyPayment(@PathVariable("impUid") String impUid) {
        try {
            IamportResponse<Payment> response = donationService.verifyPayment(impUid);
            if (response.getResponse() != null && response.getResponse().getStatus().equals("paid")) {
                return ResponseEntity.ok("결제 검증 성공");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("결제 검증 실패");
            }
        } catch (Exception e) {
            logger.error("결제 검증 중 오류 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("결제 검증 중 오류 발생");
        }
    }


}
