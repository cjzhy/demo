package com.cnbdty.device.handler;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cnbdty.device.constant.Constant;
import com.cnbdty.device.model.ObjectInfo;
import com.cnbdty.device.queue.model.Shutdown;
import com.cnbdty.device.service.ObjectInfoService;
import com.cnbdty.device.util.JsonUtils;

@Component
public class ShutdownHandler implements Handler {
	public  static Logger logger = LoggerFactory.getLogger(ShutdownHandler.class);
	
	@Autowired
	private ObjectInfoService objectInfoService;
	
	@Override
	public void handle(String message) {
		try {
			Shutdown shutdown=JsonUtils.jsonToObject(message, Shutdown.class);
			
			String terminalId=shutdown.getTerminalId().trim();
			
			ObjectInfo objInfo=objectInfoService.selectByTerminalId(terminalId);
			if(objInfo==null){
				return ;
			}
			//0：离线 1：在线
			objInfo.setIsOnline(Constant.OFF_LINE);
			objInfo.setUpdateTime(new Date());
			objectInfoService.updateByPrimaryKeySelective(objInfo);
			
		} catch (Exception e) {
			logger.error("handle shutdown logic exception",e);
		}
	
		
	} 
	
	
}
