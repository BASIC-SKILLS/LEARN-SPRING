package com.koreait.java;

public class TvTest {
	
	public static void main(String[] args) {
		Tv lgTv = new LgTv(new AppleSpeaker(40));
		Tv samTv = new SamsungTv(new SonySpeaker(30));
		
		System.out.println(lgTv);
		System.out.println(samTv);
		
		lgTv.ChannelDown();
		lgTv.ChannelUp();
		lgTv.ChannelUp();
		lgTv.ChannelDown();
		
		lgTv.volumeUp();
		lgTv.volumeDown();
		lgTv.volumeDown();
		lgTv.volumeUp();
		lgTv.volumeUp();
		
		samTv.ChannelDown();
		samTv.ChannelUp();
		samTv.ChannelUp();
		samTv.ChannelUp();
		samTv.ChannelDown();
		samTv.ChannelDown();
		
		samTv.volumeUp();
		samTv.volumeUp();
		samTv.volumeUp();
		samTv.volumeDown();
		samTv.volumeDown();
		
	}

}
