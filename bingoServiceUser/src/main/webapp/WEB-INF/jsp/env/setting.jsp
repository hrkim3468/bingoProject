<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<body>
	<h1>${_systemProp.name} 서버 설정정보입니다.</h1>
	<p>동작중인 Profile : ${_profile}</p>
	
	<hr>
	<h3>DB 설정정보 </h3>
	<p>driverClassName : ${_databaseProp.driverClassName}</p>
	<p>url : ${_databaseProp.url}</p>
	<p>username : ${_databaseProp.username}</p>
	<p>password : ${_databaseProp.password}</p>	

	<hr>
	<h3>SYSTEM 설정정보</h3>
	<p>name : ${_systemProp.name}</p>
	<p>domain : ${_systemProp.domain}</p>
	<p>machineName : ${_systemProp.machineName}</p>
	<p>ip : ${_systemProp.ip}</p>		
	<p>listenQueueName : ${_systemProp.listenQueueName}</p>		

</body>

</html>