package com.imooc;

import com.imooc.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

	@Resource
	private MailService mailService;

	@Test
	public void contextLoads() {
		mailService.sendSimpleMail("harrypater02@163.com","two mail","hello zeep!");
	}



}
