package com.cnbdty.device.runnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnbdty.device.queue.model.Location;
import com.cnbdty.device.service.DeviceAlarmService;

public class LocationLogicRunnable implements Runnable {
	
	public  static Logger logger = LoggerFactory.getLogger(LocationLogicRunnable.class);

	private DeviceAlarmService deviceAlarmService;

	private Location location;

	public LocationLogicRunnable(DeviceAlarmService deviceAlarmService, Location location) {
		super();
		this.location = location;
	}

	@Override
	public void run() {
		
		
		

	}

	public DeviceAlarmService getDeviceAlarmService() {
		return deviceAlarmService;
	}

	public void setDeviceAlarmService(DeviceAlarmService deviceAlarmService) {
		this.deviceAlarmService = deviceAlarmService;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	
	
}
