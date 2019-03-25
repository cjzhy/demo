package com.cnbdty.device.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnbdty.device.dao.ObjectInfoMapper;
import com.cnbdty.device.model.ObjectInfo;
import com.cnbdty.device.service.ObjectInfoService;

@Service
public class ObjectInfoServiceImpl implements ObjectInfoService {
	
	@Autowired
	private ObjectInfoMapper objectInfoMapper;


	@Override
	public void updateByPrimaryKeySelective(ObjectInfo record) {
		objectInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public ObjectInfo selectByTerminalId(String terminalId) {
		return objectInfoMapper.selectByTerminalId(terminalId);
	}

	

}
