package com.openapi;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class OpenApiConnect {
//	private static HttpURLConnection conn = null;

	public static HttpURLConnection getConnection(String start, String end) throws IOException {
		HttpURLConnection conn = null;
		/* URL */
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");

		/* 인증키(sample사용시에는 호출시 제한됩니다.) */
		urlBuilder.append("/" + URLEncoder.encode("61714b45427365793132376f55755151", "UTF-8"));

		/* 요청파일타입 (xml,xmlf,xls,json) */
		urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8"));

		/* 서비스명 (대소문자 구분 필수입니다.) */
		urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8"));

		/* 요청시작위치 (sample인증키 사용시 5이내 숫자) */
		urlBuilder.append("/" + URLEncoder.encode(start, "UTF-8"));

		/* 요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨) */
		// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
		// 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에
		// 자세히 나와 있습니다.
		urlBuilder.append("/" + URLEncoder.encode(end, "UTF-8"));

		URL url = new URL(urlBuilder.toString());

		if (conn == null) {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
		}

		return conn;
	}

	public static void closeConnection(HttpURLConnection conn) {
		if (conn != null) {
			try {
				conn.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
