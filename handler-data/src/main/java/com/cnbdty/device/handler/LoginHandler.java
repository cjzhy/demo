package com.cnbdty.device.handler;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cnbdty.device.constant.Constant;
import com.cnbdty.device.model.ObjectInfo;
import com.cnbdty.device.queue.model.Login;
import com.cnbdty.device.service.ObjectInfoService;
import com.cnbdty.device.util.JsonUtils;

@Component
public class LoginHandler implements Handler{
	
	public  static Logger logger = LoggerFactory.getLogger(LoginHandler.class); 
	
	
	@Autowired
	private ObjectInfoService objectInfoService;
	
	@Override
	public void handle(String message) {
		try {
			Login login=JsonUtils.jsonToObject(message, Login.class);
			String terminalId=login.getTerminalId().trim();
			ObjectInfo objInfo = objectInfoService.selectByTerminalId(terminalId);

			//设备不存在，登录失败
			if(objInfo==null){
				logger.info("devcie imei {} not exist.",terminalId);
				return ;
			}
			
			//设备过期
			Date expireDate=objInfo.getExpiredDate();
			if(expireDate!=null){
				long minu=System.currentTimeMillis()-expireDate.getTime();
				//过期
				if(minu>0){
					logger.info("devcie imei {}  expired.",terminalId);
					//1在线 0下线
					objInfo.setIsOnline(Constant.OFF_LINE);
					objInfo.setUpdateTime(new Date());
					objectInfoService.updateByPrimaryKeySelective(objInfo);
					return ;
				}
				
			}
			
			//不在线就更新在线状态
			if(objInfo.getIsOnline()!=1){
				logger.info("devcie imei {}  login success.",terminalId);
				//1在线 0下线
				objInfo.setIsOnline(Constant.ON_LINE);
				objInfo.setUpdateTime(new Date());
				objectInfoService.updateByPrimaryKeySelective(objInfo);
			}
		} catch (Exception e) {
			logger.error("handle login logic exception",e);
		}
	}
}
