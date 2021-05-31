package com.koreait.second.spring;

import com.koreait.java.Speaker;

public class MiTv {
	private Speaker speaker;
	
	public MiTv() {
		System.out.println("MiTv 기본 생성자");
	}
	
	public MiTv(Speaker speaker) {
		this.speaker = speaker;
	}
}
