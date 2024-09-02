package com.sml.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sml.model.CourseApplyDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CourseServiceTest {
	
	@Autowired
	private CourseService service;
	
	@Autowired
	private CourseTeacherService teaService;
	
	@Autowired
	private CourseApplyService applyService;
	
//	@Test
//	public void teacherEnrollTest() throws Exception {
//		CourseTeacherVO teacher = new CourseTeacherVO();
//		teacher.setTeaName("serviceTest");
//		teacher.setTeaIntro("serviceTest");
//		teaService.teacherEnroll(teacher);
//	}

//	@Test
//	public void applyApplyTest() {
//		int memCode = 21;
//		int courseCode = 1;
//		
//		CourseApplyDTO dto = new CourseApplyDTO();
//		dto.setMemCode(memCode);
//		dto.setCourseCode(courseCode);
//		
//		int result = applyService.applyApply(dto);
//		
//		System.out.println("** result : " +result);
//	}
//	

}
