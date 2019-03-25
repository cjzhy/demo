package com.cnbdty.device.handler;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cnbdty.device.model.ObjectInfo;
import com.cnbdty.device.model.Position;
import com.cnbdty.device.queue.model.SOSAlarm;
import com.cnbdty.device.service.ObjectInfoService;
import com.cnbdty.device.util.BaiduAPIConverter;
import com.cnbdty.device.util.JsonUtils;

@Component
public class SOSAlarmHandler implements Handler {

	public static Logger logger = LoggerFactory.getLogger(SOSAlarmHandler.class);

	
	@Autowired
	private ObjectInfoService objectInfoService;

	@Override
	public void handle(String message) {
		try {
			SOSAlarm sosAlarm = JsonUtils.jsonToObject(message, SOSAlarm.class);
			
			// 0 是基站 1是gps
			if (sosAlarm.getIsGps() == 1) {
				ObjectInfo objInfo = objectInfoService.selectByTerminalId(sosAlarm.getTerminalId().trim());
				// 0不过期  1过期
				if(objInfo!=null&&objInfo.getExpiredDate()!=null){
					Date expireDate=objInfo.getExpiredDate();
					long minu=System.currentTimeMillis()-expireDate.getTime();
					//过期
					if(minu>0){
						logger.info("sos alarm teminal {} is not exist or expired.", sosAlarm.getTerminalId().trim());
						return ;
					}
				}
				/*
				Position position = BaiduAPIConverter.wgs84ToBaidu(sosAlarm.getLg(), sosAlarm.getLt());
				if (position.getLatitude() <= 0 || position.getLongitude() <= 0) {
					logger.info("sos alarm convert baidu location exception:{}", position.toString());
					return;
				}
				
				DeviceAlarm alarm = new DeviceAlarm();
				alarm.setTerminalid(sosAlarm.getTerminalId());
				alarm.setTime(new Date(sosAlarm.getTimeMillis()));
				alarm.setBaidulg(position.getLongitude());
				alarm.setBaidult(position.getLatitude());
				alarm.setLongitude(sosAlarm.getLg());
				alarm.setLatitude(sosAlarm.getLt());
				// 报警类型：1:电池低电；2:sos报警； 3:摘表报警；4:围栏报警
				alarm.setType(2);
				// 插入告警
				deviceAlarmService.insertSelective(alarm);
				*/

			}else{
				logger.info("sos alarm location is not gps ,{}",message);
			}

		} catch (Exception e) {
			logger.error("handle login logic exception", e);
		}

	}

}
