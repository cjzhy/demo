package com.cnbdty.device.service;

import com.cnbdty.device.queue.model.Location;

public interface DevicePositionService {
	
	void insertPosition(Location location)throws Exception;

}
