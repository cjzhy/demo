package com.cnbdty.device.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnbdty.device.model.Position;

public class BaiduAPIConverter {

	public static Logger logger = LoggerFactory.getLogger(BaiduAPIConverter.class);

	private static String BAIDU_GEOCONV_KEY = "78OhopFWz7wPFKlaFXA1DYPU";

	public static Position wgs84ToBaidu(double lg, double lt) throws IOException {
		OutputStreamWriter out = null;
		InputStream l_urlStream = null;
		try {
			URL url = new URL("http://api.map.baidu.com/geoconv/v1/?coords=" + lg + "," + lt + "&from=1&to=5&output=json&ak=" + BAIDU_GEOCONV_KEY);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			out = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
			// remember to clean up

			// 一旦发送成功，用以下方法就可以得到服务器的回应：
			String sCurrentLine = null;
			StringBuilder sTotalString = new StringBuilder();
			l_urlStream = connection.getInputStream();

			BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream));
			while ((sCurrentLine = l_reader.readLine()) != null) {
				if (!StringUtils.isBlank(sCurrentLine)) {
					sTotalString.append(sCurrentLine);
				}

			}
			Position position = new Position();
			Map map = JsonUtils.jsonToObject(sTotalString.toString(), Map.class);
			if (map != null) {
				Integer status = (Integer) map.get("status");
				if (status == 0) {
					List<Map<String, Object>> results = (List) map.get("result");
					if (results.size() > 0) {
						position.setLongitude((Double) results.get(0).get("x"));
						position.setLatitude((Double) results.get(0).get("y"));
					}
				}
			}
			out.flush();
			return position;
		} catch (Exception e) {
			logger.error("covert baidu position exception", e);
		} finally {
			if (out != null) {
				out.close();
			}
			if (l_urlStream != null) {
				l_urlStream.close();
			}

		}
		return null;
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public static String LgLtTOAddress(Double lg, Double lat) throws IOException {
		String address = null;

		OutputStreamWriter out = null;
		InputStream l_urlStream = null;
		try {
			URL url = new URL("http://api.map.baidu.com/geocoder/v2/?ak=" + BAIDU_GEOCONV_KEY + "&location=" + lat + "," + lg + "&output=json&pois=0");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			out = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
			// remember to clean up
			// 一旦发送成功，用以下方法就可以得到服务器的回应：
			String sCurrentLine = null;
			StringBuilder sTotalString = new StringBuilder();
			l_urlStream = connection.getInputStream();

			BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream));
			while ((sCurrentLine = l_reader.readLine()) != null) {
				if (!StringUtils.isBlank(sCurrentLine)) {
					sTotalString.append(sCurrentLine);
				}

			}

			Map map = JsonUtils.jsonToObject(sTotalString.toString(), Map.class);
			if (map != null) {
				Integer status = (Integer) map.get("status");
				if (status == 0) {
					Map<String, Object> result = (Map) map.get("result");
					address = (String) result.get("formatted_address");

				}
			}
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("covert baidu position address exception", e);
		} finally {
			if (out != null) {
				out.close();
			}
			if (l_urlStream != null) {
				l_urlStream.close();
			}

		}
		return address;
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public static String GPS84LgLtTOAddress(Double lg, Double lat) throws IOException {
		String address = null;
		OutputStreamWriter out = null;
		InputStream l_urlStream = null;
		try {
			URL url = new URL("http://api.map.baidu.com/geocoder/v2/?ak=" + BAIDU_GEOCONV_KEY + "&location=" + lat + "," + lg + "&output=json&pois=0&coordtype=wgs84ll");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			out = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
			// remember to clean up
			// out.flush();
			// out.close();
			// 一旦发送成功，用以下方法就可以得到服务器的回应：
			// 一旦发送成功，用以下方法就可以得到服务器的回应：
			String sCurrentLine = null;
			StringBuilder sTotalString = new StringBuilder();
			l_urlStream = connection.getInputStream();

			BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream));
			while ((sCurrentLine = l_reader.readLine()) != null) {
				if (!StringUtils.isBlank(sCurrentLine)) {
					sTotalString.append(sCurrentLine);
				}

			}

			Map map = JsonUtils.jsonToObject(sTotalString.toString(), Map.class);
			if (map != null) {
				Integer status = (Integer) map.get("status");
				if (status == 0) {
					Map<String, Object> result = (Map) map.get("result");
					address = (String) result.get("formatted_address");

				}
			}
			out.flush();
		} catch (Exception e) {
			logger.error("covert baidu GPS84LgLtTOAddress address exception", e);
		} finally {
			if (out != null) {
				out.close();
			}
			if (l_urlStream != null) {
				l_urlStream.close();
			}

		}
		return address;
	}

	/**
	 * 功能：判断点是否在多边形内 方法：求解通过该点的水平线与多边形各边的交点 结论：单边交点为奇数，成立!
	 * 
	 * @param p
	 *            指定的某个点
	 * @param list
	 *            多边形的各个顶点坐标（首末点可以不一致）
	 * @return
	 */
	public static boolean isInPolygon(Position p, List<Position> list) {
		int nCross = 0;
		Position p1, p2;
		for (int i = 0; i < list.size(); i++) {
			p1 = list.get(i);
			p2 = list.get((i + 1) % list.size());
			// 求解 y=p.y 与 p1p2 的交点
			if (p1.getLongitude() == p2.getLongitude()) // p1p2 与 y=p0.y平行
				continue;
			if (p.getLongitude() < Math.min(p1.getLongitude(), p2.getLongitude())) // 交点在p1p2延长线上
				continue;
			if (p.getLongitude() >= Math.max(p1.getLongitude(), p2.getLongitude())) // 交点在p1p2延长线上
				continue;
			// 求交点的 X 坐标
			// --------------------------------------------------------------
			double x = (double) (p.getLongitude() - p1.getLongitude()) * (double) (p2.getLatitude() - p1.getLatitude()) / (double) (p2.getLongitude() - p1.getLongitude()) + p1.getLatitude();
			if (x > p.getLatitude())
				nCross++; // 只统计单边交点
		}
		// 单边交点为偶数，点在多边形之外 ---
		return (nCross % 2 == 1);
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		 String s= LgLtTOAddress(113.425146,23.668952);
		//for (int i = 0; i < 1000; i++) {
		//	String lists2 = GPS84LgLtTOAddress(113.4411390000, 22.1728180000);
			//System.out.println(lists2);
		//}

		// System.out.println(lists2);
		// String lists3 = GPS84LgLtTOAddress(113.4411390000,23.1728180000);
		// System.out.println(lists3);
		// for (JSONBean bean:lists) {
		// System.out.println(bean.getLongitude()+"  ,  "+bean.getLatitude());
		// System.out.println(bean.getLatitude()+","+bean.getLongitude());
		//
		// }
		System.out.println(s);

	}
}
