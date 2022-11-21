package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dto.WifiDto;
import com.jdbc.JDBCTemplate;


public class WifiDao extends JDBCTemplate {

	public int insert(List<WifiDto> dtos) {
		Connection conn = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		String sql = "insert or ignore into wifi("
				+ "MGR_NO, WRDOFC, WIFI_NAME, ROAD_ADRES, DETAIL_ADRES,INSTALL_FLOOR,INSTALL_ORG,INSTALL_TYPE, SERVICE_TYPE,"
				+ "NET_TYPE," + "INSTALL_YEAR," + "INOUT_DOOR," + "ACCESS_ENV," + "LNT," + "LAT," + "WORK_DATE)"
				+ "values(?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?);";

		try {
			pstm = conn.prepareStatement(sql);

			for (int i = 0; i < dtos.size(); i++) {
				WifiDto dto = dtos.get(i);

				pstm.setString(1, dto.getMgr_no());
				pstm.setString(2, dto.getWrdofc());
				pstm.setString(3, dto.getWifi_name());
				pstm.setString(4, dto.getRoad_adres());
				pstm.setString(5, dto.getDetail_adres());
				pstm.setString(6, dto.getInstall_floor());
				pstm.setString(7, dto.getInstall_org());
				pstm.setString(8, dto.getInstall_type());
				pstm.setString(9, dto.getService_type());
				pstm.setString(10, dto.getNet_type());
				pstm.setString(11, dto.getInstall_year());
				pstm.setString(12, dto.getInout_door());
				pstm.setString(13, dto.getAccess_env());
				pstm.setDouble(14, dto.getLnt());
				pstm.setDouble(15, dto.getLat());
				pstm.setString(16, dto.getWork_dt());

				pstm.addBatch();
			}

			int[] result = pstm.executeBatch();
			System.out.println(result.length);
			System.out.println(Arrays.toString(result));

			for (int i = 0; i < result.length; i++) {
				if (result[i] == 1) {
					res++;
				} else {
					res--;
				}
			}

			if (res == dtos.size()) {
				commit(conn);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		close(conn);
		close(pstm);

		return res;
	}

	public List<WifiDto> selectAll() {
		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		List<WifiDto> wifiList = new ArrayList();
		
		String sql = "select * " + "from wifi;";

		try {
			pstm = conn.prepareStatement(sql);

			// Query 실행
			rs = pstm.executeQuery();

			// 5. 결과 수행

			while (rs.next()) {
				WifiDto wifiDto = new WifiDto(rs.getDouble("distance"), rs.getString("MGR_NO"), rs.getString("WRDOFC"), rs.getString("WIFI_NAME"),
						rs.getString("ROAD_ADRES"), rs.getString("DETAIL_ADRES"), rs.getString("INSTALL_FLOOR"),
						rs.getString("INSTALL_ORG"), rs.getString("INSTALL_TYPE"), rs.getString("SERVICE_TYPE"),
						rs.getString("NET_TYPE"), rs.getString("INSTALL_YEAR"), rs.getString("INOUT_DOOR"),
						rs.getString("ACCESS_ENV"), rs.getDouble("LNT"), rs.getDouble("LAT"),
						rs.getString("WORK_DATE"));

				wifiList.add(wifiDto);
			}

			commit(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		close(conn);
		close(pstm);
		close(rs);
		
		return wifiList;
	}
	
	public List<WifiDto> nearByWifiSelect(String lat, String lnt) {
		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		List<WifiDto> wifiList = new ArrayList();

		String sql = "SELECT ( 6371 * acos( cos( radians( ? ) ) * cos( radians( lat) ) * cos( radians( lnt )"
				+ "- radians(?) ) + sin( radians(?) ) * sin( radians(lat) ) ) ) AS distance, *  "
				+ "FROM wifi "
				+ "ORDER BY distance " 
				+ "LIMIT 0,20";

		
		try {
			pstm = conn.prepareStatement(sql);

			pstm.setDouble(1, Double.valueOf(lat));
			pstm.setDouble(2, Double.valueOf(lnt));
			pstm.setDouble(3, Double.valueOf(lat));
			
			// Query 실행
			rs = pstm.executeQuery();
			System.out.println(rs.getRow());
			
			// 5. 결과 수행
			while (rs.next()) {
				System.out.println(rs.getDouble("distance"));
				WifiDto wifiDto = new WifiDto(rs.getDouble("distance"), rs.getString("MGR_NO"), rs.getString("WRDOFC"), rs.getString("WIFI_NAME"),
						rs.getString("ROAD_ADRES"), rs.getString("DETAIL_ADRES"), rs.getString("INSTALL_FLOOR"),
						rs.getString("INSTALL_ORG"), rs.getString("INSTALL_TYPE"), rs.getString("SERVICE_TYPE"),
						rs.getString("NET_TYPE"), rs.getString("INSTALL_YEAR"), rs.getString("INOUT_DOOR"),
						rs.getString("ACCESS_ENV"), rs.getDouble("LNT"), rs.getDouble("LAT"),
						rs.getString("WORK_DATE"));
				wifiDto.setDistance(rs.getDouble("distance"));
				wifiList.add(wifiDto);
			}

			commit(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		close(conn);
		close(pstm);
		close(rs);
		
		return wifiList;
	}

	public void delete() {
		Connection conn = getConnection();
		System.out.println(conn.toString());
		PreparedStatement pstm = null;

		String sql = "delete from wifi " + "where NAME = ?;";

//		String sql ="select * from test;";
		try {
			pstm = conn.prepareStatement(sql);
//			pstm.setInt(1, 123);
			pstm.setString(1, "test");

			// Query 실행
			int affected = pstm.executeUpdate();

			// 5. 결과 수행

			if (affected > 0) {
				System.out.println("Delete Success");
			} else {
				System.out.println("Delete Fail");
			}

			commit(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		close(conn);
		close(pstm);
	}

}
