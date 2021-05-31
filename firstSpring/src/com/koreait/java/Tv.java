package com.koreait.java;

public class Tv {
	private Speaker speaker;
	private int inch; 
	private int maxChannel;
	private int maxVolume;
	private int currentChannel;
	private String brand;
	
	private int currentVolume;
	
	protected Tv(int inch, int maxChannel, int maxVolume, String brand, Speaker speaker) {
		this.inch = inch;
		this.maxChannel = maxChannel;
		this.maxVolume = maxVolume;
		this.currentChannel = 1;
		
		this.brand = brand;
		this.speaker = speaker;
	}

	public int getMaxChannel() {
		return maxChannel;
	}

	public void setMaxChannel(int maxChannel) {
		this.maxChannel = maxChannel;
	}

	public int getMaxVolume() {
		return maxVolume;
	}

	public void setMaxVolume(int maxVolume) {
		this.maxVolume = maxVolume;
	}

	public int getInch() {
		return inch;
	}

	public int getCurrentChannel() {
		return currentChannel;
	}

	public int getCurrentVolume() {
		return currentVolume;
	}

	public String getBrand() {
		return brand;
	}
	
	@Override
	public String toString() {
		return String.format("%s |  %d인치 %d채널 %d볼륨", this.getBrand(), this.getInch(), this.getMaxChannel(), getMaxVolume());
	}
	
	public void ChannelUp() {
		if (this.currentChannel >= this.maxChannel) {
			this.currentChannel = this.maxChannel;
		} else {
			this.currentChannel++;
		}
		System.out.printf("%s 현재 채널 : %d\n",this.brand, this.currentChannel);
	}
	
	public void ChannelDown() {
		if (this.currentChannel <= 1) {
			this.currentChannel = 1;
		} else {
			this.currentChannel--;
		}
		System.out.printf("%s 현재 채널 : %d\n",this.brand, this.currentChannel);
	}
	
	public void volumeUp() {
		if (this.currentVolume < this.maxVolume) {
			this.currentVolume++;
			speaker.volumeUp();
		} 
		System.out.printf("%s 현재 볼륨 : %d\n",this.brand, this.currentVolume);
	}
	
	public void volumeDown() {
		if (this.currentVolume > 1) {
			this.currentVolume--;
			speaker.volumeDown();
		}
		System.out.printf("%s 현재 볼륨 : %d\n",this.brand, this.currentVolume);
	}
}
