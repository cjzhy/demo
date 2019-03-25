package com.cnbdty.device.constant;

public class ProtocolConstants
{
	
	public static final String HEAD = "{";
	public static final String ENDING = "}";
	public static final String TYPE = "1";
	public static final String VERSION = "1";
	public static final String ENCRYPT = "0";
	public static final String SEPARATOR = ":";
	public static final String LOCATION_PARAM_SEPARATOR = ",";
	public static final String SET_SEPARATOR = "\\|";
	public static final String SOS_SET_SEPARATOR = ";";
	public static final String BASE_STATION_SEPARATOR = "\\&";
	
	
	/**
	 * 终端登录
	 */
	public static final String LOGIN = "T61";
	/**
	 * 终端登录回复
	 */
	public static final String LOGIN_RESP = "S61";
	/**
	 * 终端上传心跳数据
	 */
	public static final String HEARTBEAT = "T62";
	/**
	 * 服务器回复上传心跳数据
	 */
	public static final String HEARTBEAT_RESP = "S62";
	/**
	 * 终端上传LBS数据
	 */
	public static final String LOCATION = "T66";
	/**
	 * 服务器回复上传LBS数据
	 */
	public static final String LOCATION_RESP = "S66";
	/**
	 * 服务器设置SOS号码
	 */
	public static final String SOS_SET = "S69";
	/**
	 * 终端回复设置SOS号码
	 */
	public static final String SOS_SET_RESP = "T69";
	/**
	 * 终端上传关机指令
	 */
	public static final String SHUTDOWN = "T72";
	/**
	 * 终端上传关机指令回复
	 */
	public static final String SHUTDOWN_RESP = "S72";
	/**
	 * 服务器转发激活终端
	 */
	public static final String ACTIVATE = "S75";
	/**
	 * 终端激活回复
	 */
	public static final String ACTIVATE_RESP = "T75";
	/**
	 * 终端SOS报警
	 */
	public static final String SOS_ALARM = "T80";
	/**
	 * 服务器回复终端SOS报警
	 */
	public static final String SOS_ALARM_RESP = "S80";
	/**
	 * 终端摘表报警
	 */
	public static final String DROP_DEVICE_ALARM = "T81";
	/**
	 * 服务器回复终端摘表报警
	 */
	public static final String DROP_DEVICE_ALARM_RESP = "S81";
	/**
	 * 服务器下发设置定位时间间隔
	 */
	public static final String LOCATE_INTERVAL_SET = "S82";
	/**
	 * 终端回复设置定位时间间隔
	 */
	public static final String LOCATE_INTERVAL_SET_RESP = "T82";
	/**
	 * 上班
	 */
	public static final String WORK = "T83";
	/**
	 * 上班回复
	 */
	public static final String WORK_RESP = "S83";
	
	/**
	 * 下班
	 */
	public static final String GET_OFF_WORK = "T84";
	/**
	 * 下班回复
	 */
	public static final String GET_OFF_WORK_RESP = "S84";
	
	
	public static final String LOGIN_FAILURE = "0";
	public static final String LOGIN_SUCCESS = "1";
	public static final String LOGIN_EXPIRED = "2";
	
	public static final String WORK_FAILURE = "0";
	public static final String WORK_SUCCESS = "1";
}
