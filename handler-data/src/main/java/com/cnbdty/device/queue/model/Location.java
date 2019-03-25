package com.cnbdty.device.queue.model;

public class Location extends Common {

	// 数据类型 1代表实时数据，2代表定时数据
	private int type;

	// 定位类型:0:基站;1:GPS
	private byte isGps;

	// 经度
	private double lg;

	// 维度
	private double lt;

	private long timeMillis;
	
	public Location(){
		
	}

	public Location(String cmd, String terminalId) {
		this.cmd = cmd;
		this.terminalId = terminalId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Location [type=");
		builder.append(type);
		builder.append(", isGps=");
		builder.append(isGps);
		builder.append(", lg=");
		builder.append(lg);
		builder.append(", lt=");
		builder.append(lt);
		builder.append(", timeMillis=");
		builder.append(timeMillis);
		builder.append("]");
		return builder.toString();
	}
	
	

}
