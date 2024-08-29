package com.sml.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import com.sml.mapper.DonationMapper;
import com.sml.model.DonationVO;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Service
public class DonationServiceImpl implements DonationService {

    private static final Logger logger = LoggerFactory.getLogger(DonationServiceImpl.class);

    // IamportClient 직접 초기화
    private final IamportClient iamportClient;
    private final DonationMapper donationMapper;

    public DonationServiceImpl(@Value("${IAMPORT_API_KEY}") String iamportApiKey,
                               @Value("${IAMPORT_API_SECRET}") String iamportApiSecret,
                               DonationMapper donationMapper) {
        // 여기서 IamportClient를 직접 초기화
        this.iamportClient = new IamportClient(iamportApiKey, iamportApiSecret);
        this.donationMapper = donationMapper;
    }

    @Override
    public IamportResponse<Payment> verifyPayment(String impUid) throws IamportResponseException, IOException {
        logger.info("impUid: {}로 결제를 검증 중입니다.", impUid);

        IamportResponse<Payment> response = iamportClient.paymentByImpUid(impUid);

        if (response != null && response.getResponse() != null) {
            Payment payment = response.getResponse();

            DonationVO donation = new DonationVO();
            donation.setMerchantUid(payment.getMerchantUid());
            donation.setImpUid(payment.getImpUid());
            donation.setAmount(payment.getAmount().intValue());
            donation.setBuyerName(payment.getBuyerName());
            donation.setBuyerEmail(payment.getBuyerEmail());
            donation.setBuyerTel(payment.getBuyerTel());
            donation.setRegDate(new Timestamp(System.currentTimeMillis()));

            donationMapper.insertDonation(donation);
            logger.info("결제 정보가 데이터베이스에 성공적으로 저장되었습니다.");
        } else {
            logger.error("결제 검증 실패 또는 결제 데이터가 없습니다.");
        }

        return response;
    }

    @Override
    public List<DonationVO> getAllDonations() {
        return donationMapper.getAllDonations();
    }
}
