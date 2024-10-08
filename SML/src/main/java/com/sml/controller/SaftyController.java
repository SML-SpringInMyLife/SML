package com.sml.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sml.mapper.AttachMapper;
import com.sml.model.CategoryVO;
import com.sml.model.Criteria;
import com.sml.model.FileupVO;
import com.sml.model.MemberVO;
import com.sml.model.NoticeVO;
import com.sml.model.PageDTO;
import com.sml.model.SaftyVO;
import com.sml.service.CategoryService;
import com.sml.service.SaftyService;

@Controller
@RequestMapping(value = "/safty")
public class SaftyController {
	private static final Logger logger = LoggerFactory.getLogger(SaftyController.class);
    
	@Autowired
	private SaftyService saftyservice;
	
	@Autowired
    private CategoryService categoryService; 
	
	/* 생활안전 조회페이지 이동 */
	 @GetMapping("/list")
	    public void saftyListGET(Criteria cri, Model model, HttpSession session) throws Exception {
	        logger.info("생활안전 조회페이지 이동" + cri);

	        // 카테고리 목록 가져오기
	        List<CategoryVO> categories = categoryService.getCategoriesByRange(11, 20);
	        model.addAttribute("categories", categories);

	        // 카테고리 필터링 적용
	        if (cri.getCategoryCode() != null && cri.getCategoryCode() != 0) {
	            logger.info("카테고리 필터링 적용: " + cri.getCategoryCode());
	        }

	        List<SaftyVO> list = saftyservice.saftyGetList(cri);
	        if(!list.isEmpty()) {
	            model.addAttribute("list", list);
	        } else {
	            model.addAttribute("listCheck", "empty");
	        }

	        /* 페이징처리 */
	        int total = saftyservice.saftyGetTotal(cri);
	        PageDTO pageMaker = new PageDTO(cri, total);
	        model.addAttribute("pageMaker", pageMaker);

	        // 로그인 상태 확인
	        MemberVO member = (MemberVO) session.getAttribute("member");
	        boolean isLoggedIn = (member != null);
	        boolean isAdmin = false;
	        if(isLoggedIn) {
	            isAdmin = member.getMemAdminCheck() == 1;
	        }
	        model.addAttribute("isLoggedIn", isLoggedIn);
	        model.addAttribute("isAdmin", isAdmin);
	    }
	
	 /* 생활안전 등록페이지 이동 */
    @GetMapping("/enroll")
    public void saftyEnrollGET(Model model) throws Exception {
        logger.info("공지사항 등록페이지 이동");
        List<CategoryVO> categories = categoryService.getCategoriesByRange(11, 20);
        model.addAttribute("categories", categories);
    }
	
	/* 생활안전 등록 */
    @PostMapping("/enroll.do")
    public String saftyEnrollPOST(SaftyVO safty, RedirectAttributes rttr) throws Exception {
        logger.info("공지사항 글 등록: " + safty);
        logger.info("Received category code: " + safty.getCategoryCode());
        saftyservice.saftyRegister(safty);
        rttr.addFlashAttribute("enroll_result", safty.getSaftyTitle());
        return "redirect:/safty/list";
    }

    /* 새 카테고리 등록 */
    @PostMapping("/category/add")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addCategory(@RequestParam("categoryName") String categoryName) {
        logger.info("새 카테고리 등록: " + categoryName);
        Map<String, Object> response = new HashMap<>();
        
        try {
            CategoryVO category = new CategoryVO();
            category.setCategoryName(categoryName);
            category.setStatus(1); // 활성 상태로 설정
            categoryService.addCategory(category);
            
            response.put("success", true);
            response.put("message", "카테고리가 성공적으로 추가되었습니다.");
            response.put("categoryCode", category.getCategoryCode());
            response.put("categoryName", category.getCategoryName());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("카테고리 추가 중 오류 발생", e);
            response.put("success", false);
            response.put("message", "카테고리 추가 중 오류가 발생했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
	
    @Autowired
	private AttachMapper attachMapper;
   
	/* 이미지 정보 반환 */
	@GetMapping(value="/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<FileupVO>> getAttachList(int saftyCode){
		
		logger.info("getAttachList.........." + saftyCode);
		
		return new ResponseEntity<List<FileupVO>>(attachMapper.getAttachListss(saftyCode), HttpStatus.OK);
		
	}
	/* 이미지 미리보기 */
   @GetMapping("/display")
	public ResponseEntity<byte[]> getImage(String fileName){
		
		File file = new File("c:\\upload\\" + fileName);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-type", Files.probeContentType(file.toPath()));
			
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
   
   /* 첨부 파일 업로드 */
   @PostMapping(value="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<FileupVO>> uploadAjaxActionPOST(MultipartFile[] uploadFile) {
		
		logger.info("uploadAjaxActionPOST..........");
		
		/* 이미지 파일 체크 */
		for(MultipartFile multipartFile: uploadFile) {
			
			File checkfile = new File(multipartFile.getOriginalFilename());
			String type = null;
			
			try {
				type = Files.probeContentType(checkfile.toPath());
				logger.info("MIME TYPE : " + type);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(!type.startsWith("image")) {
				
				List<FileupVO> list = null;
				return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
				
			}
			
		}// for
		
		String uploadFolder = "C:\\upload";
		
		/* 날짜 폴더 경로 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);
		
		String datePath = str.replace("-", File.separator);
		
		/* 폴더 생성 */
		File uploadPath = new File(uploadFolder, datePath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		/* 이미저 정보 담는 객체 */
		List<FileupVO> list = new ArrayList();
		
		// 향상된 for
		for(MultipartFile multipartFile : uploadFile) {
			 
			FileupVO vo = new FileupVO();
	
			/* 파일 이름 */
	   		String uploadFileName = multipartFile.getOriginalFilename();
	   		vo.setFileName(uploadFileName);
	   		vo.setFilePath(datePath);
			
			/* uuid 적용 파일 이름(동일한이름을 가진 파일일시, 고유한 이름을 가지도록 해결함)*/ 
			String uuid = UUID.randomUUID().toString();
			vo.setFileUuid(uuid);
			
			uploadFileName = uuid + "_" + uploadFileName;
			
			/* 파일 위치, 파일 이름을 합친 File 객체 */
			File saveFile = new File(uploadPath, uploadFileName);
			
			/* 파일 저장 */
			try {
				multipartFile.transferTo(saveFile);
				
				/* 썸네일 생성 */
				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);
				
				BufferedImage bo_image = ImageIO.read(saveFile);
				
				/* 비율 */
				double ratio = 3;
				/*넓이 높이*/
				int width = (int) (bo_image.getWidth() / ratio);
				int height = (int) (bo_image.getHeight() / ratio);
				
				BufferedImage bt_image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
								
				Graphics2D graphic = bt_image.createGraphics();
				
				graphic.drawImage(bo_image, 0, 0,width,height, null);
					
				ImageIO.write(bt_image, "jpg", thumbnailFile);

				
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			list.add(vo);
		} // for문
		ResponseEntity<List<FileupVO>> result = new ResponseEntity<List<FileupVO>>(list, HttpStatus.OK);
	
		return result;
	}
   
   /* 이미지 파일 삭제 */
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(@RequestParam String fileName){
		
		logger.info("deleteFile........" + fileName);
		File file = null;
		
		try {
			/* 썸네일 파일 삭제 */
			file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));
			
			file.delete();
			
			/* 원본 파일 삭제 */
			String originFileName = file.getAbsolutePath().replace("s_", "");
			
			logger.info("originFileName : " + originFileName);
			
			file = new File(originFileName);
			
			file.delete();
			
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);
			
		} //catch
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	/* 생활안전 상세조회페이지 이동 */
	@GetMapping({"/detail" , "/modify"})
	public void saftyDetailGET(int saftyCode,Criteria cri, Model model, HttpSession session) throws Exception {

		logger.info("생활안전 상세조회 이동 ");
			
		// 조회수 증가
		saftyservice.saftyCount(saftyCode);
		/* 생활안전 조회 페이지 정보 */
		model.addAttribute("cri",cri);
			/* 선택 글 정보 */
		model.addAttribute("saftyDetail", saftyservice.saftyGetDetail(saftyCode));
		 // 로그인 상태 확인
		// MemberVO 객체를 가져옵니다.
	    MemberVO member = (MemberVO) session.getAttribute("member");
	    
	    // member 객체가 null이 아니라면 로그인 상태입니다.
	    boolean isLoggedIn = (member != null);
	    boolean isAdmin = false;
	    
	    if(isLoggedIn) {
	        isAdmin = member.getMemAdminCheck() == 1;
	    }
	    model.addAttribute("isLoggedIn", isLoggedIn);
	    model.addAttribute("isAdmin", isAdmin);
	}
	
	/* 생활안전 조회수기능 */
    @PostMapping("/Count")
    @ResponseBody
    public ResponseEntity<String> saftyCount(@RequestParam("saftyCode") int saftyCode, HttpSession session) {
        try {
            // 세션에서 현재 생활안전의 조회 여부를 확인
            Boolean isViewed = (Boolean) session.getAttribute("safty_viewed_" + saftyCode);
            
            if (isViewed == null || !isViewed) {
                // 조회하지 않은 경우에만 조회수 증가
                int affectedRows = saftyservice.saftyCount(saftyCode);
                logger.info("조회수 증가: 영향을 받은 행의 수 = " + affectedRows);
                
                // 세션에 조회 여부 저장
                session.setAttribute("safty_viewed_" + saftyCode, true);
                
                return ResponseEntity.ok("조회수 증가 성공");
            } else {
                logger.info("이미 조회한 공지사항: " + saftyCode);
                return ResponseEntity.ok("이미 조회한 공지사항");
            }
        } catch (Exception e) {
            logger.error("조회수 증가 중 오류 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("조회수 증가 실패");
        }
    }
    
	/* 공지사항 좋아요 */
    @PostMapping("/like/{saftyCode}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> toggleLike(@PathVariable int saftyCode, HttpSession session) {
        MemberVO member = (MemberVO) session.getAttribute("member");
        Map<String, Object> response = new HashMap<>();

        if (member == null) {
            response.put("status", "error");
            response.put("message", "로그인이 필요합니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        int memCode = member.getMemCode();
        boolean isLiked = saftyservice.toggleLike(saftyCode, memCode);
        int likeCount = saftyservice.getSaftyLikeCount(saftyCode);

        response.put("status", isLiked ? "Liked" : "Unliked");
        response.put("likeCount", likeCount);
         
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
    
    /* 생활안전 수정 */
	   @PostMapping("/modify")
	   public String saftyModifyPOST(SaftyVO safty ,RedirectAttributes rttr) throws Exception{
		   logger.info("생활안전 수정: " + safty); 
		   
		   int result= saftyservice.saftyModify(safty);
		   
		   rttr.addFlashAttribute("modify_result", result);
		   
		   return "redirect:/safty/list";
	   }
	
		/* 생활안전 삭제 */
	   @PostMapping("/delete")
	   public String saftyDeletePOST(int saftyCode,RedirectAttributes rttr) throws Exception{
		  
			logger.info("생활안전 삭제.........." + saftyCode);
			
			int result = 0;
			
			try {
				
				result = saftyservice.saftyDelete(saftyCode);
				
			} catch (Exception e) {
				
				e.printStackTrace();
				result = 2;
				rttr.addFlashAttribute("delete_result", result);
				
				return "redirect:/safty/list";
			}

			logger.info("삭제 결과.........." + result);
			rttr.addFlashAttribute("delete_result", result);
			
			return "redirect:/safty/list";
		}
    
    
    
}
