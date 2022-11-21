<%@page import="com.dao.HistoryDao"%>
<%@page import="com.dto.WifiDto"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.WifiDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>와이파이 정보 구하기</title>
<link type="text/css" rel="stylesheet" href="/resources/table.css"></link>
<script type="text/javascript">
	function hidestatus() {
		window.status = ""
		return true
	}

	if (document.layers)
		document.captureEvents(Event.MOUSEOVER | Event.MOUSEOUT)

	document.onmouseover = hidestatus
	document.onmouseout = hidestatus
</script>
</head>

<body>
	<script type="text/JavaScript"
		src="https://code.jquery.com/jquery-1.12.4.js"></script>

	<script type="text/JavaScript">
		console.log(location.href);
		let params = new URLSearchParams(location.search);
		let lat = params.get("lat");
		let lng = params.get("lng");

	</script>

	<h1>와이파이 정보 구하기</h1>
	<div>
		<a href="">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a
			href="load_wifi.jsp">Open API 와이파이 정보 가져오기</a>
	</div>
	</br>
	<div>
		<label for="LAT">LAT : </label> <input type="text" id="latitude"
			name="latitude" required minlength="4" maxlength="20" size="20"
			placeholder="0.0"> <label for="LNT">, LNT : </label> <input
			type="text" id="longitude" name="longitude" required minlength="4"
			maxlength="20" size="20" placeholder="0.0">

		<button id="myPositionBtn" type="button">내 위치 가져오기</button>
		<script type="text/javascript">
			if (lat != null && lng != null) {
				document.getElementById("latitude").value = lat;
				document.getElementById("longitude").value = lng;
			}

			document.getElementById("myPositionBtn").onclick = function() {
				navigator.geolocation.getCurrentPosition(onGeoOk, onGeoError);
			};

			function onGeoOk(position) {
				lat = position.coords.latitude;
				lng = position.coords.longitude;

				document.getElementById("latitude").value = lat;
				document.getElementById("longitude").value = lng;

				console.log("You live in", lat, lng);
			}
			function onGeoError() {
				alert("Can't find you. No weather for you.");
			}
		</script>

		<button id="getWifiInfo" type="button">근처 WIFI 정보 보기</button>
		<script type="text/javascript">
			document.getElementById("getWifiInfo").onclick = function() {
				lat = document.getElementById("latitude").value;
				lng = document.getElementById("longitude").value;
				
				window.location.href = "?lat=" + lat + "&lng=" + lng;
			};
		</script>

	</div>
	<br>
	<table  id="customers">
		<thead>
			<tr>
				<th>거리(KM)</th>
				<th>관리번호</th>
				<th>자치구</th>
				<th>와이파이명</th>
				<th>도로명주소</th>
				<th>상세주소</th>
				<th>설치위치(층)</th>
				<th>설치유형</th>
				<th>설치기관</th>
				<th>서비스구분</th>
				<th>망종류</th>
				<th>설치년도</th>
				<th>실내외구분</th>
				<th>WIFI접속환경</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>작업일자</th>
			</tr>
		</thead>
		<tbody>
			<%
			WifiDao wifiDao = new WifiDao();
			HistoryDao historyDao = new HistoryDao();

			List<WifiDto> wifiList = null;

			String lat = request.getParameter("lat"); // 위도
			String lng = request.getParameter("lng"); // 경도

			if (lng == null && lat == null) {
			%>
				<td colspan="17" style="padding: 10px">위치 정보를 입력한 후에 조회해 주세요.</td>
			<%
			} else {
				wifiList = wifiDao.nearByWifiSelect(lat, lng);
				historyDao.insert(lat, lng);
			
				for (WifiDto wifi : wifiList) {
			%>
					<tr>
						<td><%=String.format("%.4f", wifi.getDistance())%></td>
						<td><%=wifi.getMgr_no()%></td>
						<!-- 관리번호 -->
						<td><%=wifi.getWrdofc()%></td>
						<!-- 자치구 -->
						<td><%=wifi.getWifi_name()%></td>
						<!-- 와이파이명 -->
						<td><%=wifi.getRoad_adres()%></td>
						<!-- 도로명주소 -->
						<td><%=wifi.getDetail_adres()%></td>
						<!-- 상세주소 -->
						<td><%=wifi.getInstall_floor()%></td>
						<!-- 설치위치(층) -->
						<td><%=wifi.getInstall_org()%></td>
						<!-- 설치유형 -->
						<td><%=wifi.getInstall_type()%></td>
						<!-- 설치기관 -->
						<td><%=wifi.getService_type()%></td>
						<!-- 서비스구분 -->
						<td><%=wifi.getNet_type()%></td>
						<!-- 망종류 -->
						<td><%=wifi.getInstall_year()%></td>
						<!-- 설치년도 -->
						<td><%=wifi.getInout_door()%></td>
						<!-- 설치기관 -->
						<td><%=wifi.getAccess_env()%></td>
						<!-- WIFI접속 환경 -->
						<td><%=wifi.getLat()%></td>
						<!-- x좌표 -->
						<td><%=wifi.getLnt()%></td>
						<!-- y좌표 -->
						<td><%=wifi.getWork_dt()%></td>
						<!-- 작업일자 -->
		
					</tr>
				<%
				}
				%>
			<%
			}
			%>
		</tbody>
	</table>


</body>
</html>