package com.koreait.first.spring;

import com.koreait.java.LgTv;
import com.koreait.java.SamsungTv;
import com.koreait.java.Speaker;
import com.koreait.java.Tv;

public class SpringTvTest {
	public static void main(String[] args) {
		Speaker s1 = SpeakerFactory.choiceSpeaker(args[0]);
		Speaker s2 = SpeakerFactory.choiceSpeaker(args[1]);
		
		Tv lgTv = new LgTv(s1);
		Tv samTv = new SamsungTv(s2);
		
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
