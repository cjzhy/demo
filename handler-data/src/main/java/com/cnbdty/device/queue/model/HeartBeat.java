package com.cnbdty.device.queue.model;

public class HeartBeat extends Common{
	
	public HeartBeat(){
		
	}
	
	private String elec;
	
	
	public HeartBeat(String cmd,String terminalId) {
		this.cmd=cmd;
		this.terminalId=terminalId;
	}
	

	public HeartBeat(String cmd,String terminalId,String elec) {
		this.cmd=cmd;
		this.terminalId=terminalId;
		this.elec=elec;
	}

	public String getElec() {
		return elec;
	}


	public void setElec(String elec) {
		this.elec = elec;
	}
	
	
	
	

}
