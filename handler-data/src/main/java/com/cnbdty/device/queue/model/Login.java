package com.cnbdty.device.queue.model;

public class Login extends Common {

	public Login() {

	}

	public Login(String cmd, String terminalId) {
		this.cmd = cmd;
		this.terminalId = terminalId;
	}

	public Login(String cmd, String terminalId, String result) {
		this.cmd = cmd;
		this.terminalId = terminalId;
		this.result = result;
	}

	// 1代表登录成功，2代表服务到期，0代表登录失败
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
