package com.cnbdty.device.handler;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cnbdty.device.constant.Constant;
import com.cnbdty.device.model.ObjectInfo;
import com.cnbdty.device.queue.model.HeartBeat;
import com.cnbdty.device.service.ObjectInfoService;
import com.cnbdty.device.util.JsonUtils;

@Component
public class HeartbeatHandler implements Handler {
	
	public  static Logger logger = LoggerFactory.getLogger(HeartbeatHandler.class);
	
	@Autowired
	private ObjectInfoService objectInfoService;

	@Override
	public void handle(String message) {
		try {
			HeartBeat heartBeat=JsonUtils.jsonToObject(message, HeartBeat.class);
			String terminalId=heartBeat.getTerminalId().trim();
			ObjectInfo tm=objectInfoService.selectByTerminalId(terminalId);
			//没有过期，并且没有在线的情况下更新在线状态
			if(tm!=null&&tm.getIsOnline()==0){
				ObjectInfo obj=new ObjectInfo();
				obj.setObjId(tm.getObjId());
				obj.setIsOnline(Constant.ON_LINE);
				obj.setUpdateTime(new Date());
				objectInfoService.updateByPrimaryKeySelective(obj);
			}
		} catch (Exception e) {
			logger.error("heart beat logic exception",e);
		}
		
		
	} 

	

}
