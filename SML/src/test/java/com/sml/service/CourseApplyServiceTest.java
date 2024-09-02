package com.sml.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sml.model.CourseApplyDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CourseApplyServiceTest {
	
	@Autowired
	private CourseApplyService service;
	
	@Test
	public void enrollApplyTest() {
		int memCode = 2;
		int courseCode = 2;
		
		CourseApplyDTO apply = new CourseApplyDTO();
		apply.setMemCode(memCode);
		apply.setCourseCode(courseCode);
		
		int result = service.enrollApply(apply);
		System.out.println("°á°ú´Â........" +result);
	}

}
