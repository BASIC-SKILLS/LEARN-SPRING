package com.spring.second;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring.xml" ); //모든 bean을 객체화해서 가져온다 -> 싱글톤 
		//MiTv tv1 = (MiTv) ctx.getBean("mitv");
		MiTv tv1 = ctx.getBean("mitv", MiTv.class); // ("아이디", "리턴타입")그 중에서 mitv라는 아이디를 가진 것을 가져온다. 
		MiTv tv2 = ctx.getBean("mitv", MiTv.class); // 싱글톤이라서 한 번만 객체화 된다. 
		System.out.println("tv1 : "+tv1);
		System.out.println("tv2 : "+tv2);
		
		//tv1.volumeUp(); MiTv 객체 생성 시 speaker객체를 넣어주지 않아서
		//xml 넣은 후, 
		tv1.volumeUp(); // xml에서 appleSpeaker객체부터 만든 후 mitv객체 생성자에 넣어준다. 그래서 console에 appleSpeaker객체부터 만들어진 결과부터 찍힌다.
		
	}

}
