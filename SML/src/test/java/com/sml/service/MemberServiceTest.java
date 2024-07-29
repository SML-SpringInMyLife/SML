package com.sml.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private AdminService service;
	
	@Test
	public void bookEnrollTest() throws Exception{
		
		BookVO book = new BookVO();
		book.setBookName("serviceTest");
		book.setAuthorId(1);
		book.setPublishYear("2024-07-12");
		book.setPublisher("출판사");
		book.setCateCode("1234");
		book.setBookPrice(20000);
		book.setBookStock(32);
		book.setBookDiscount(0.20);
		book.setBookIntro("책 소개");
		book.setBookContents("목차");
		
		service.bookEnroll(book);
	}

}
