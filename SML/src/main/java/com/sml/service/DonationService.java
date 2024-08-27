package com.sml.service;

import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import com.sml.model.DonationVO;
import java.util.List;

public interface DonationService {
    IamportResponse<Payment> verifyPayment(String impUid) throws Exception;
    List<DonationVO> getAllDonations(); 
}
