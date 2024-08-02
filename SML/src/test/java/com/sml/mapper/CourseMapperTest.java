package com.sml.mapper;

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
	
	@Test
	public void courseEnrollTest() throws Exception{
		CourseVO course = new CourseVO();
		course.setCcatCode(101);
		course.setCourseName("mapperTest");
		course.setCourseContent("mapperTest");
		course.setCourseLimit(30);
		course.setStartDate("2024-08-02");
		course.setEndDate("2024-08-31");
		course.setTeacherCode(2);
		course.setStartTime("3:15");
		course.setEndTime("5:50");
		course.setCourseDay("¿ù, ¼ö, ±Ý");
		mapper.courseEnroll(course);
	}

}
