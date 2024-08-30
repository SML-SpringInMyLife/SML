package com.sml.mapper;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sml.model.Criteria;
import com.sml.model.FileupVO;
import com.sml.model.LikeVO;
import com.sml.model.NoticeVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class NoticeMapperTest {

	/*
	 * @Autowired private AttachMapper attachMapper;
	 * 
	 * 이미지 정보 반환
	 * 
	 * @Test public void getAttachListTests() {
	 * 
	 * int noticeCode = 70;
	 * 
	 * System.out.println("이미지 정보 : " + attachMapper.getAttachList(noticeCode));
	 * 
	 * 
	 * }
	 */

	/*
	 * @Autowired private NoticeMapper mapper;
	 * 
	 * @Test public void testGetLike() { // 테스트 데이터 생성 LikeVO likeVO = new LikeVO();
	 * likeVO.setMemCode(1); likeVO.setNoticeCode(5); likeVO.setLikeType("NOTICE");
	 * mapper.insertLike(likeVO);
	 * 
	 * // getLike 메서드 테스트 List<LikeVO> likes = mapper.getLike(5, 1);
	 * 
	 * // 검증 assertNotNull(likes); assertFalse(likes.isEmpty()); assertEquals(1,
	 * likes.size());
	 * 
	 * LikeVO retrievedLike = likes.get(0); assertEquals(1,
	 * retrievedLike.getMemCode()); assertEquals(5, retrievedLike.getNoticeCode());
	 * assertEquals("NOTICE", retrievedLike.getLikeType()); }
	 * 
	 * @Test public void testGetLikeNoResult() { // 존재하지 않는 데이터로 테스트 List<LikeVO>
	 * likes = mapper.getLike(999, 999);
	 * 
	 * // 검증 assertNotNull(likes); assertTrue(likes.isEmpty()); }
	 */
}
	
   

	/*
	 * 지정 이미지 삭제
	 * 
	 * @Test public void deleteImageAllTest() {
	 * 
	 * int noticeCode = 70;
	 * 
	 * mapper.deleteImageAll(noticeCode);
	 * 
	 * }
	 */

	/*
	 * @Test public void communityDetail() {
	 * 
	 * int noticeCode = 4; NoticeVO notice = mapper.noticeGetDetail(noticeCode);
	 * System.out.println("notice........." +notice);
	 * 
	 * }
	 */

	/*
	 * @Test public void noticeModifyTest() { NoticeVO notice = new NoticeVO();
	 * 
	 * notice.setNoticeCode(4); System.out.println("수정전..............." +
	 * mapper.noticeGetDetail(notice.getNoticeCode()));
	 * 
	 * notice.setNoticeTitle("글제목 수정하였습니다."); notice.setNoticeBody("글수정 하였습니다 ");
	 * 
	 * mapper.noticeModify(notice); System.out.println("수정후..........." +
	 * mapper.noticeGetDetail(notice.getNoticeCode())); }
	 */
	/*
	 * @Test public void communityDetail() {
	 * 
	 * int noticeCode = 1;
	 * 
	 * int result = mapper.noticeDelete(noticeCode);
	 * 
	 * if (result == 1) { System.out.println("삭제 성공"); }
	 * 
	 * }
	 */
	/*
	 * @Test public void noticeListGETTest() throws Exception {
	 * 
	 * Criteria cri = new Criteria(3, 10); cri.setKeyword("mapperTest");
	 * 
	 * List<NoticeVO> list = mapper.noticeGetList(cri);
	 * 
	 * for(int i=0; i<list.size(); i++) { System.out.println("list" +i +"......"
	 * +list.get(i));
	 * 
	 * } }
	 */
	/*
	 * @Test public void noticeRegisterTest() throws Exception { NoticeVO notice =
	 * new NoticeVO();
	 * 
	 * notice.setMemCode(1); notice.setCategoryCode(1);
	 * notice.setNoticeTitle("mapper테스트"); notice.setNoticeBody("mapper텍스트"); //
	 * noticeCode는 자동 생성되므로 설정하지 않습니다.
	 * 
	 * System.out.println("Before noticeVO: " + notice);
	 * 
	 * mapper.noticeRegister(notice);
	 * 
	 * System.out.println("After noticeVO: " + notice);
	 * 
	 * assertNotNull(notice.getNoticeCode()); // noticeCode가 생성되었는지 확인
	 * assertTrue(notice.getNoticeCode() > 0); // 생성된 noticeCode가 양수인지 확인 }
	 */

	/*
	 * 이미지 등록
	 * 
	 * @Test public void imageEnrollTest() {
	 * 
	 * FileupVO vo = new FileupVO();
	 * 
	 * vo.setNoticeCode(64); vo.setFileName("test"); vo.setFilePath("test");
	 * vo.setFileUuid("test2"); vo.setFileType("NOTICE");
	 * 
	 * mapper.imageEnroll(vo);
	 * 
	 * }
	 */


