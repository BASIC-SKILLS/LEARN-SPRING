package com.spring.second;

public class Speaker {
	private String brand;
	private int maxVolume;
	private int currentVolume;
	
	public Speaker(String brand, int maxVolume) {
		this.brand = brand;
		this.maxVolume = maxVolume;
		System.out.printf("---- %s 스피커 생성 ---\n", this.brand);
	}

	public int getCurrentVolume() {
		return currentVolume;
	}

	public void setCurrentVolume(int currentVolume) {
		this.currentVolume = currentVolume;
	}

	public String getBrand() {
		return brand;
	}

	public int getMaxVolume() {
		return maxVolume;
	}
	
	public void volumeUp() {
		if (this.currentVolume < this.maxVolume) {
			this.currentVolume++;
		} 
		System.out.printf("Speaker 현재 볼륨 : %d\n", this.currentVolume);
	}
	
	public void volumeDown() {
		if (this.currentVolume > 1) {
			this.currentVolume--;
		}
		System.out.printf("Speaker 현재 볼륨 : %d\n", this.currentVolume);
	}
}
