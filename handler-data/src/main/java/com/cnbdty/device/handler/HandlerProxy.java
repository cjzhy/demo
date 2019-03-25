package com.cnbdty.device.handler;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnbdty.device.constant.ProtocolConstants;
import com.cnbdty.device.util.JsonUtils;
import com.cnbdty.device.util.SpringContextUtil;

public class HandlerProxy {
	public  static Logger logger = LoggerFactory.getLogger(HandlerProxy.class);


	private static HandlerProxy instance = new HandlerProxy();

	private HandlerProxy() {

	}

	public static HandlerProxy getInstance() {
		if (null == instance)
			instance = new HandlerProxy();
		return instance;
	}
	
	public Handler forward(String message) {
		Handler handler = (Handler) SpringContextUtil.getBean(convert(message));
		return handler;
	}
	

	private  Class<? extends Handler> convert(String message) {
		if (StringUtils.isBlank(message)) {
			throw new NullPointerException("datas is null!");
		}
		Map<String,String> map= JsonUtils.jsonToObject(message, Map.class);
		String cmd=map.get("cmd");
		if(StringUtils.isBlank(cmd)){
			logger.error("read mq message not right {}",message);
			return DefaultHandler.class;
		}
		if (ProtocolConstants.LOGIN.equals(cmd)) {
			return LoginHandler.class;
		} else if (ProtocolConstants.HEARTBEAT.equals(cmd)) {
			return HeartbeatHandler.class;
		} else if (ProtocolConstants.LOCATION.equals(cmd)) {
			return LocationHandler.class;
		} else if (ProtocolConstants.SHUTDOWN.equals(cmd)) {
			return ShutdownHandler.class;
		} else if (ProtocolConstants.SOS_ALARM.equals(cmd)) {
			return SOSAlarmHandler.class;
		} else {
			return DefaultHandler.class;
		}
		

	}

}
