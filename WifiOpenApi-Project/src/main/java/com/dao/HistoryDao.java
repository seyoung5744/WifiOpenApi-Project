package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.dto.HistoryDto;
import com.dto.WifiDto;
import com.jdbc.JDBCTemplate;

public class HistoryDao extends JDBCTemplate {
	public int insert(String lat, String lnt) {
		Connection conn = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		String sql = "insert into history(LNT, LAT, REFER_DATE) " + "values(?, ?, ?);";
		LocalDateTime now = LocalDateTime.now();
		System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS").format(now));
		
		try {
			pstm = conn.prepareStatement(sql);

			pstm.setDouble(1, Double.valueOf(lat));
			pstm.setDouble(2, Double.valueOf(lnt));
			pstm.setString(3, String.valueOf(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS").format(now)));

			// 4. Query 실행
			int affected = pstm.executeUpdate();

			// 5. 결과 수행

			if (affected > 0) {
				System.out.println("저장 성공");
			} else {
				System.out.println("저장 실패");
			}

			commit(conn);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		close(conn);
		close(pstm);

		return res;
	}
	
	public List<HistoryDto> selectAll() {
		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		List<HistoryDto> historyList = new ArrayList();
		
		String sql = "select * " + "from history;";

		try {
			pstm = conn.prepareStatement(sql);

			// Query 실행
			rs = pstm.executeQuery();

			// 5. 결과 수행
			 // 포맷터
	       	        
			while (rs.next()) {
				HistoryDto historyDto = new HistoryDto( rs.getInt("ID"),rs.getDouble("LNT"), rs.getDouble("LAT"),
						rs.getString("REFER_DATE"));

				historyList.add(historyDto);
			}

			commit(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		close(conn);
		close(pstm);
		close(rs);
		
		return historyList;
	}
	
}
