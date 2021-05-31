package com.koreait.java;

public class LgTv extends Tv{
	
	//has B (멤버필드 객체화) LgTv는 Tv라서 상속
	//is B (멤버필드 객체화) LgTv는 Speaker가 아니고, Speaker를 가지고 있다.
	
	private Speaker speaker;
	
	public LgTv(Speaker speaker) {
		super(100, 40, 50, "LG TV", speaker);
	}

}
