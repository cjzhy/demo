package com.cnbdty.device.util;

import java.text.SimpleDateFormat;

public class DateUtils {
		
	public static String parseDate(long timestamp){
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		  return sdf.format(timestamp);
	}

}
