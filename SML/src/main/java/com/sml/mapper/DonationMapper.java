package com.sml.mapper;

import com.sml.model.DonationVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface DonationMapper {
    DonationVO getDonationById(int id); // 특정 ID로 후원 정보를 가져옴
    void insertDonation(DonationVO donation); // 후원 정보를 저장
    List<DonationVO> getAllDonations(); // 모든 후원 정보를 가져옴
}
