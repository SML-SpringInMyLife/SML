package com.sml.service;

import com.sml.mapper.DonationBoardMapper;
import com.sml.model.DonationBoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationBoardServiceImpl implements DonationBoardService {

    @Autowired
    private DonationBoardMapper donationBoardMapper;

    @Override
    public List<DonationBoardVO> getAllBoards() {
        return donationBoardMapper.getAllBoards();
    }

    @Override
    public DonationBoardVO getBoardById(Long id) {
        return donationBoardMapper.getBoardById(id);
    }

    @Override
    public void saveBoard(DonationBoardVO board) {
        if (board.getId() != null) {
            donationBoardMapper.saveBoard(board);
        } else {
            donationBoardMapper.saveBoard(board);
        }
    }

    @Override
    public void deleteBoard(Long id) {
        donationBoardMapper.deleteBoard(id);
    }
}
