package com.sml.service;

import com.sml.model.DonationBoardVO;
import java.util.List;

public interface DonationBoardService {
    List<DonationBoardVO> getAllBoards();
    DonationBoardVO getBoardById(Long id);
    void saveBoard(DonationBoardVO board);
    void deleteBoard(Long id);
}
