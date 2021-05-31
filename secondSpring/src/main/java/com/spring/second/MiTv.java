package com.spring.second;

public class MiTv {
	private Speaker speaker;
	
	public MiTv() {
		System.out.println("MiTv 기본 생성자");
	}
	
	public MiTv(Speaker speaker) {
		System.out.println("MiTv speaker 생성자");
		this.speaker = speaker;
	}
	
	// xml 설정없이 main에서 객체.volumeUp() 하면 error
	// 객체 만들 때, speaker를 안넣어줘서 this.speaker가 null이기 때문이다.
	public void volumeUp() {
		speaker.volumeUp();
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	
	
}
