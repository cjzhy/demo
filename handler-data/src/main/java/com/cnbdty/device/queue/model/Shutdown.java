package com.cnbdty.device.queue.model;

public class Shutdown extends Common{
	
	//1代表正常关机，2代表电量不足 3其他异常关闭
	private String result;
	
	public Shutdown(){
		
	}
	
	public Shutdown(String cmd,String terminalId) {
		this.cmd=cmd;
		this.terminalId=terminalId;
	}
	

	public Shutdown(String cmd,String terminalId,String result) {
		this.cmd=cmd;
		this.terminalId=terminalId;
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
	
	

}
