package com.cnbdty.device.dao;

import com.cnbdty.device.model.ObjectPosition;

public interface ObjectPositionMapper {
	
		int insertSelective(ObjectPosition record);

		ObjectPosition selectByPrimaryKey(Long id);

	    int updateByPrimaryKeySelective(ObjectPosition record);
}