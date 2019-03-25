package com.cnbdty.device;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerServer {
	
	public static void main(String[] args) {
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring.xml");
	}
}
