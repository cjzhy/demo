package com.cnbdty.device.queue.model;

public class SOSAlarm extends Common {


	// 定位类型:0:基站;1:GPS
	private byte isGps;

	// 经度
	private double lg;

	// 维度
	private double lt;

	private long timeMillis;
	
	public SOSAlarm(){
		
	}

	public SOSAlarm(String cmd, String terminalId) {
		this.cmd = cmd;
		this.terminalId = terminalId;
	}

	

	public byte getIsGps() {
		return isGps;
	}

	public void setIsGps(byte isGps) {
		this.isGps = isGps;
	}

	public double getLg() {
		return lg;
	}

	public void setLg(double lg) {
		this.lg = lg;
	}

	public double getLt() {
		return lt;
	}

	public void setLt(double lt) {
		this.lt = lt;
	}

	public long getTimeMillis() {
		return timeMillis;
	}

	public void setTimeMillis(long timeMillis) {
		this.timeMillis = timeMillis;
	}

}
