package com.cnbdty.device.handler;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cnbdty.device.constant.Constant;
import com.cnbdty.device.model.ObjectInfo;
import com.cnbdty.device.queue.model.Location;
import com.cnbdty.device.service.DevicePositionService;
import com.cnbdty.device.service.ObjectInfoService;
import com.cnbdty.device.util.JsonUtils;

/**
 * 上传定位信息
 * 
 * @author Administrator
 * 
 */
@Component
public class LocationHandler implements Handler {
	
	public  static Logger logger = LoggerFactory.getLogger(LocationHandler.class);
	
	@Autowired
	private ObjectInfoService objectInfoService;
	
	@Autowired
	private DevicePositionService devicePositionService;


	@Override
	public void handle(String message) {
		
		try {
			Location location=JsonUtils.jsonToObject(message, Location.class);
			String terminalId=location.getTerminalId().trim();
			ObjectInfo objInfo=objectInfoService.selectByTerminalId(terminalId);
			//没有过期，并且没有在线的情况下更新在线状态 0不过期  1过期
			
			if(objInfo!=null){
				//下线的情况下要更新状态设置为上线 0：下线，1在线
				if(objInfo.getIsOnline()==Constant.OFF_LINE){
					objInfo.setIsOnline(Constant.ON_LINE);
					objInfo.setUpdateTime(new Date());
					objectInfoService.updateByPrimaryKeySelective(objInfo);
				}
				
				//除去0坐标的点
				if(location.getLg()>0&&location.getLt()>0){
					//处理坐标信息
					devicePositionService.insertPosition(location);
				}else{
					logger.info("this locaiton: {}",location.toString());
				}
			}else{
				logger.info("this {} device is not exist or expired",terminalId);
			}
		} catch (Exception e) {
			logger.error("handle location logic exception",e);
		}
	} 

}
