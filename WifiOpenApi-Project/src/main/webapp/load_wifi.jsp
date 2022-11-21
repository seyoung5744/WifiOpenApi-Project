<%@page import="com.dao.WifiDao"%>
<%@page import="com.dto.WifiDto"%>
<%@page import="java.util.List"%>
<%@page import="com.wifi.controller.WifiController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>와이파이 정보 구하기</title>
<style type="text/css">
body {
	background-color: var(- -color-black);
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

label {
	font-size: 20pt;
	margin: 40px;
}
</style>

</head>
<body>

	<%
	WifiController wifiController = new WifiController();
	List<WifiDto> dtos = wifiController.getWifiInfoFromApi();
	System.out.println("총 개수 : " + dtos.size());
	
	WifiDao dao = new WifiDao();
	int res = dao.insert(dtos);
	System.out.println(res);
	if(res == dtos.size()) {
		System.out.println("db save success");
	}else {
		System.out.println("db save fail");
	}
	
	%>

	<div>
		<label><%= dtos.size() %>개의 WIFI 정보를 정상적으로 저장하였습니다.</label>
	</div>
	<div>
		<br> <a href="index.jsp">홈으로 가기</a>
	</div>
</body>
</html>