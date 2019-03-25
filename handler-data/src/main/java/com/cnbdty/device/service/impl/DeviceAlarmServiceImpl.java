package com.cnbdty.device.service.impl;

import org.springframework.stereotype.Service;

import com.cnbdty.device.service.DeviceAlarmService;

@Service
public class DeviceAlarmServiceImpl implements DeviceAlarmService {
	
	/*
	@Autowired
	private DeviceAlarmMapper deviceAlarmMapper;

	@Override
	public int insertSelective(DeviceAlarm record) {
		return deviceAlarmMapper.insert(record);
	}

	@Override
	public Map<Integer, List<Position>> queryZoneLocations(String terminalId) {
		List<Position> positions=deviceAlarmMapper.queryZoneLocations(terminalId);
		if(positions!=null&&positions.size()>0){
			Map<Integer, List<Position>> map=new HashMap<Integer, List<Position>>();
			for(Position p:positions){
				List<Position> lp= map.get(p.getAreaId());
				if(lp==null){
					lp=new ArrayList<Position>();
					lp.add(p);
					map.put(p.getAreaId(), lp);
				}else{
					lp.add(p);
				}
			}
			return map;
		}
		return null;
	}

	 */
}
