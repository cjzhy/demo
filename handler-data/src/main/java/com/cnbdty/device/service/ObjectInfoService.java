package com.cnbdty.device.service;

import com.cnbdty.device.model.ObjectInfo;

public interface ObjectInfoService {
	
	ObjectInfo selectByTerminalId(String terminalId);
	
	void updateByPrimaryKeySelective(ObjectInfo record);
}
