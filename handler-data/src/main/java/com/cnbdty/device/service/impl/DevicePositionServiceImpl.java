package com.cnbdty.device.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnbdty.device.dao.ObjectPositionMapper;
import com.cnbdty.device.model.ObjectInfo;
import com.cnbdty.device.model.ObjectPosition;
import com.cnbdty.device.model.Position;
import com.cnbdty.device.queue.model.Location;
import com.cnbdty.device.service.DeviceAlarmService;
import com.cnbdty.device.service.DevicePositionService;
import com.cnbdty.device.service.ObjectInfoService;
import com.cnbdty.device.util.BaiduAPIConverter;

@Service
public class DevicePositionServiceImpl implements DevicePositionService {
	public static Logger logger = LoggerFactory.getLogger(DevicePositionServiceImpl.class);

	@Autowired
	private DeviceAlarmService deviceAlarmService;

	@Autowired
	private ObjectPositionMapper objectPositionMapper;
	
	//@Autowired
	//private ElectronicRecordMapper electronicRecordMapper;
	
	@Autowired
	private ObjectInfoService objectInfoService;
	

	@Override
	public void insertPosition(Location location) throws Exception {

		// GPS定位才保存轨迹
		if (location.getIsGps() != 1) {
			logger.info("location is not a gps location,{}", location.toString());
			return;
		}

		final Position position = BaiduAPIConverter.wgs84ToBaidu(location.getLg(), location.getLt());
		if(position.getLatitude()<=0||position.getLongitude()<=0){
			logger.info("baidu convert location not right,{}", position.toString());
			return;
		}
		
		ObjectInfo objInfo=objectInfoService.selectByTerminalId(location.getTerminalId());
		
		if(objInfo==null){
			logger.error("not found this object info ,teminalid {}", location.getTerminalId());
			return ;
		}

		ObjectPosition objPosition=new ObjectPosition();
		objPosition.setLatitude(location.getLt());
		objPosition.setLongitude(location.getLg());
		objPosition.setBaiduLt(position.getLatitude());
		objPosition.setBaiduLg(position.getLongitude());
		objPosition.setGpsType(location.getIsGps()& 0xff );
		objPosition.setGpsTime(new Date(location.getTimeMillis()));
		
		objPosition.setObjId(objInfo.getObjId());
		
		String address=BaiduAPIConverter.LgLtTOAddress(position.getLongitude(),position.getLatitude());
		objPosition.setAddress(address);
		// 插入定位数据
		objectPositionMapper.insertSelective(objPosition);
		
		
		
		//围栏告警处理
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				/*
				Map<Integer, List<Position>> map = deviceAlarmService.queryZoneLocations(devicePosition.getTerminalid());
				if(map==null||map.size()==0){
					return;
				}
				for(Integer key:map.keySet()){
				 boolean isInPolygon=BaiduAPIConverter.isInPolygon(position,map.get(key));
				 
				 //不在围栏内，告警
				 if(!isInPolygon){
					 ElectronicRecord record=new ElectronicRecord();
					 record.setAreaid(key);
					 record.setTerminalid(devicePosition.getTerminalid());
					 record.setTime(devicePosition.getGpstme());
					 record.setBaidulg(devicePosition.getBaidulg());
					 record.setBaidult(devicePosition.getBaidult());
					 record.setLongitude(devicePosition.getLg());
					 record.setLatitude(devicePosition.getLt());
					 //type=0 出围栏
					 record.setType(0);
					 electronicRecordMapper.insertSelective(record);
					 
					 DeviceAlarm alarm=new DeviceAlarm();
					 
					 alarm.setTerminalid(devicePosition.getTerminalid());
					 alarm.setTime(devicePosition.getGpstme());
					 alarm.setBaidulg(devicePosition.getBaidulg());
					 alarm.setBaidult(devicePosition.getBaidult());
					 alarm.setLongitude(devicePosition.getLg());
					 alarm.setLatitude(devicePosition.getLt());
					 //报警类型：1:电池低电；2:sos报警； 3:摘表报警；4:围栏报警
					 alarm.setType(4);
					 //插入告警
					 deviceAlarmService.insertSelective(alarm);
					 
				 }
					
					
				}
				*/
				

			}
		}).start();

	}
	
	/**
	 * 这个方法在以后不会用到，现在因为web没源代码修改，现在暂时这么处理
	 * @param position
	 */
	/*
	private void handleLatestLoaction(DevicePosition position){
		Terminal terminal=new Terminal();
		terminal.setTerminalid(position.getTerminalid());
		terminal.setBaidulg(position.getBaidulg());
		terminal.setBaidult(position.getBaidult());
		
		terminal.setLongitude(position.getLg());
		terminal.setLatitude(position.getLt());
		terminal.setGpstime(position.getGpstme());
		objectInfoService.updateByPrimaryKeySelective(terminal);
		
	}
	*/

}
