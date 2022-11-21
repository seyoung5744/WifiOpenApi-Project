package com.wifi.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.dto.WifiDto;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.openapi.OpenApiConnect;

public class WifiController {
	public List<WifiDto> getWifiInfoFromApi() throws IOException {
		HttpURLConnection conn = null;
		int listTotalCount = 0;
		BufferedReader rd = null;

		List<WifiDto> dtos = new ArrayList<>();
		int start = 1;
		int end = 1000;

		for (int i = 0; i < 18; i++) {
			conn = OpenApiConnect.getConnection(String.valueOf(start), String.valueOf(end));
			System.out.println(conn.getURL());
			/* 연결 자체에 대한 확인이 필요하므로 추가합니다. */
			System.out.println("Open Api Response code: " + conn.getResponseCode());

			// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
			// HttpUrlConnection 한글 깨짐 (UTF-8)
			// https://sarc.io/index.php/development/1444-httpurlconnection-utf-8

			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
//				System.err.println("요청 실패!!");
			}
			StringBuilder sb = new StringBuilder();
			String line;

			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}

			JsonObject jsonObject = JsonParser.parseString(sb.toString()).getAsJsonObject();
			JsonObject parse_response = (JsonObject) jsonObject.get("TbPublicWifiInfo");
			JsonArray parse_items = (JsonArray) parse_response.get("row");
			JsonObject obj;

			for (int j = 0; j < parse_items.size(); j++) {
				obj = (JsonObject) parse_items.get(j);

				WifiDto wifiDto = new WifiDto(
						null,
						obj.get("X_SWIFI_MGR_NO").getAsString(),
						obj.get("X_SWIFI_WRDOFC").getAsString(), 
						obj.get("X_SWIFI_MAIN_NM").getAsString(),
						obj.get("X_SWIFI_ADRES1").getAsString(), 
						obj.get("X_SWIFI_ADRES2").getAsString(),
						obj.get("X_SWIFI_INSTL_FLOOR").getAsString(),
						obj.get("X_SWIFI_INSTL_MBY").getAsString(), 
						obj.get("X_SWIFI_INSTL_TY").getAsString(),
						obj.get("X_SWIFI_SVC_SE").getAsString(),
						obj.get("X_SWIFI_CMCWR").getAsString(), 
						obj.get("X_SWIFI_CNSTC_YEAR").getAsString(),
						obj.get("X_SWIFI_INOUT_DOOR").getAsString(), 
						obj.get("X_SWIFI_REMARS3").getAsString(),
						Double.parseDouble(obj.get("LNT").getAsString()),
						Double.parseDouble(obj.get("LAT").getAsString()), 
						obj.get("WORK_DTTM").getAsString());

				dtos.add(wifiDto);
			}
			System.out.println(dtos.size());

			start = end + 1;
			end = end + 1000;

			rd.close();
			OpenApiConnect.closeConnection(conn);
		}

		return dtos;
	}
}
