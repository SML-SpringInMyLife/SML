package com.sml.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sml.model.CourseApplyDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CourseApplyMapperTest {
	
	@Autowired
	private CourseApplyMapper mapper;
	
	@Test
	public void enrollApply() throws Exception {
		int memCode = 1;
		int courseCode = 2;
		
		CourseApplyDTO apply = new CourseApplyDTO();
		apply.setMemCode(memCode);
		apply.setCourseCode(courseCode);
		
		int result = 0;
		result = mapper.enrollApply(apply);
		
		System.out.println("°á°ú´Â.............." +result);
	}
	
	@Test
	public void modifyApply() {
		int courseCode = 2;
		
		CourseApplyDTO apply = new CourseApplyDTO();
		apply.setCourseCode(courseCode);
		apply.setApplyStatus("Y");
	}
	
	@Test
	public void checkApply() {
		int memCode = 2;
		int courseCode = 2;
		
		CourseApplyDTO apply = new CourseApplyDTO();
		apply.setMemCode(memCode);
		apply.setCourseCode(courseCode);
		
		CourseApplyDTO resultApply = mapper.checkApply(apply);
		
	}
}
