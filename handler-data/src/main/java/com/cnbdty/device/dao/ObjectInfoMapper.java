package com.cnbdty.device.dao;

import com.cnbdty.device.model.ObjectInfo;

public interface ObjectInfoMapper {
	
    ObjectInfo selectByTerminalId(String terminalId);

    int updateByPrimaryKeySelective(ObjectInfo record);
    

}