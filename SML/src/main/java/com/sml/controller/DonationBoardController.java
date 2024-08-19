package com.sml.controller;

import com.sml.model.DonationBoardVO;
import com.sml.service.DonationBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/donation")
public class DonationBoardController {

    @Autowired
    private DonationBoardService boardService;


    
    @GetMapping("/list")
    public String listBoards(Model model) {
        List<DonationBoardVO> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "donation/donationPayment"; 
    }


    @GetMapping("/write")
    public String showCreateForm(Model model) {
        model.addAttribute("board", new DonationBoardVO());
        return "donation/write";
    }

    @PostMapping("/save")
    public String saveBoard(@ModelAttribute("board") DonationBoardVO board, RedirectAttributes redirectAttributes) {
        boolean isUpdate = board.getId() != null; // id가 있으면 수정, 없으면 새 글 작성

        boardService.saveBoard(board);

        // 메시지 설정
        if (isUpdate) {
            redirectAttributes.addFlashAttribute("message", "글이 수정되었습니다");
        } else {
            redirectAttributes.addFlashAttribute("message", "글이 등록되었습니다");
        }

        return "redirect:/donation/list"; 
    }


    @GetMapping("/delete/{id}")
    public String deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
        return "redirect:/donation/list";
    }

 

   
    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable("id") Long id, Model model) {
        DonationBoardVO board = boardService.getBoardById(id);
        model.addAttribute("board", board);
        return "donation/detail"; 
    }
    
    // 게시글 수정 폼을 보여주는 메서드
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        DonationBoardVO board = boardService.getBoardById(id);
        model.addAttribute("board", board);
        return "donation/write"; // 수정 폼 페이지
    }
    
    
}
