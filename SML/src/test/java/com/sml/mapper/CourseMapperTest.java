package com.sml.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sml.model.CourseApplyDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CourseMapperTest {
	
	@Autowired
	private CourseMapper mapper;
	
	@Autowired
	private CourseTeacherMapper teaMapper;
	
	// 강사 테스트 
	
//	@Test
//	public void teacherEnrollTest() throws Exception {
//		CourseTeacherVO teacher = new CourseTeacherVO();
//		teacher.setTeaName("mapperTest");
//		teacher.setTeaIntro("mapperTest");
//		teaMapper.teacherEnroll(teacher);
//	}
	
//	@Test
//	public void teacherListTest() throws Exception {
//		Criteria cri = new Criteria(3,10);
//		cri.setKeyword("피아노");
//		List<CourseTeacherVO> list = teaMapper.teacherList(cri);
//		for(int i = 0; i < list.size(); i++) {
//			System.out.println("list" +i + ".........." +list.get(i));
//		}
//	}
	
//	@Test
//	public void teacherListTotalTest() throws Exception {
//		Criteria cri = new Criteria();
//		cri.setKeyword("피아노");
//		int total = teaMapper.teacherListTotal(cri);
//		System.out.println("total......." +total);
//	}
	
//	@Test
//	public void teacherDetailTest() {
//		int teaCode = 100;
//		CourseTeacherVO teacher = teaMapper.teacherDetail(teaCode);
//		System.out.println("teacher......" +teacher);
//	}
	
//	@Test
//	public void teacherModifyTest() {
//		CourseTeacherVO teacher = new CourseTeacherVO();
//		teacher.setTeaCode(100);
//		System.out.println("수정 전........" +teaMapper.teacherDetail(teacher.getTeaCode()));
//		
//		teacher.setTeaName("modifyMapperTest");
//		teacher.setTeaIntro("modifyMapperTest");
//		
//		teaMapper.teacherModify(teacher);
//		System.out.println("수정 후........" +teaMapper.teacherDetail(teacher.getTeaCode()));
//	}
	
//	@Test
//	public void teacherDeleteTest() {
//		int teaCode = 253;
//		int result = teaMapper.teacherDelete(teaCode);
//		
//		if(result == 1) {
//			System.out.println("삭제 정상 처리");
//		}
//	}
	
	// 카테고리 테스트
//	@Test
//	public void cateListTest() throws Exception {
//		System.out.println("카테고리 리스트 구현" +mapper.cateList());
//	}
	
	// 게시판 테스트
//	@Test
//	public void courseEnroll() throws Exception{
//		CourseVO course = new CourseVO();
//		
//		course.setCourseName("mapperTest");
//		course.setCcatCode(109);
//		course.setCourseContent("mapperTest");
//		course.setCourseLimit(30);
//		course.setStartDate("2024-01-01");
//		course.setEndDate("2024-12-31");
//		course.setTeaCode(258);
////		course.setStartTime("09:00:00");
//		course.setCourseDay("월, 수, 금");
//		
//		mapper.courseEnroll(course);
//	}
	
//	@Test
//	public void courseListTest() {
//		Criteria cri = new Criteria();
//		
//		cri.setKeyword("test");
//		
//		List list = mapper.courseList(cri);
//		for(int i = 0; i < list.size(); i++) {
//			System.out.println("result......." + i + " : " + list.get(i));
//		}
//		
//		int result = mapper.courseTotal(cri);
//		System.out.println("result.........." + result);
//	}
	
//	@Test
//	public void courseDetailTest() {
//		int courseCode = 100;
//		CourseVO result = mapper.courseDetail(courseCode);
//		System.out.println("수업 조회 데이터 : " +result);
//	}
	
//	@Test
//	public void courseModifyTest() {
//		CourseVO course = new CourseVO();
//		course.setCourseCode(2581);
//		System.out.println("수정 전........" +mapper.courseDetail(course.getCourseCode()));
//		
//		course.setCourseName("modifyTest");
//		course.setTeaCode(1);
//		course.setCcatCode(107);
//		course.setCourseLimit(11);
//		course.setCourseDay("월~금");
//		
//		mapper.courseModify(course);
//		System.out.println("수정 후........" +mapper.courseDetail(course.getCourseCode()));
//	}
	
//	@Test
//	public void courseDeleteTest() {
//		int courseCode = 100;
//		int result = mapper.courseDelete(courseCode);
//		if(result == 1) {
//			System.out.println("삭제 성공");
//		}
//	}
	
//	@Test
//	public void courseApplyDetailTest() {
//		int courseCode = 200;
//		CourseVO result = mapper.courseApplyDetail(courseCode);
//		System.out.println("수업 조회 데이터 : " +result);
//		
//	}
	
	@Autowired
	private CourseApplyMapper applyMapper;
	
//	@Test 
//	public void applyApply() throws Exception {
//		int memCode = 3;
//		int courseCode = 2581;
//		
//		CourseApplyDTO apply = new CourseApplyDTO();
//		apply.setMemCode(memCode);
//		apply.setCourseCode(courseCode);
//		
//		int result = 0;
//		result = applyMapper.courseApply(apply);
//		
//		System.out.println("결과 : " +result);
//	}

}
