package com.sml.mapper;

import java.sql.Time;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sml.model.CourseVO;

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
	@Test
	public void cateListTest() throws Exception {
		System.out.println("카테고리 리스트 구현" +mapper.cateList());
	}
	
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
	

}
