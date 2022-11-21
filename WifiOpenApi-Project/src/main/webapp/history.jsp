<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.dto.HistoryDto"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.HistoryDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>와이파이 정보 구하기</title>
<link type="text/css" rel="stylesheet" href="/resources/table.css"></link>

</head>

<body>
	
	<h1>위치 히스토리 목록</h1>
	<div>
		<a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="load_wifi.jsp">Open API 와이파이 정보 가져오기</a>
	</div>
	<br>
	<form action="test">
		<table id="customers">
			<thead>
				<tr>
					<th>ID</th>
					<th>X좌표</th>
					<th>Y좌표</th>
					<th>조회일자</th>
					<th>비고</th>
				</tr>
			</thead>
			<tbody>
				<% 
				HistoryDao historyDao = new HistoryDao();
				List<HistoryDto> historyList = historyDao.selectAll();
				
				if (historyList.size() == 0) { 
				%>
					<td colspan="17" style="padding:20px">위치 정보를 입력한 후에 조회해 주세요.</td>
				<% } else {
					for(int j = historyList.size()-1; j >= 0; j--){
						HistoryDto history = historyList.get(j);
				%>
					<tr>
							<td name="id" required><%=history.getId()%></td>
							<td><%=history.getLnt()%></td>
							<td><%=history.getLat()%></td>
							<td><%=history.getRefer_dt() %></td>
							<td><div style="text-align:center"><button id="event" type="button">삭제</button></div></td>			
					</tr>
				<%} %> <!-- for -->
				<%} %> <!-- if -->
			</tbody>
		</table>
	</form>
	

</body>
</html>