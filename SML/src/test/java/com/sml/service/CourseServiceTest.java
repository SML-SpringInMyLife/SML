package com.sml.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sml.model.CourseTeacherVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CourseServiceTest {
	
	@Autowired
	private CourseService service;
	
	@Autowired
	private CourseTeacherService teaService;
	
//	@Test
//	public void teacherEnrollTest() throws Exception {
//		CourseTeacherVO teacher = new CourseTeacherVO();
//		teacher.setTeaName("serviceTest");
//		teacher.setTeaIntro("serviceTest");
//		teaService.teacherEnroll(teacher);
//	}
	

}
