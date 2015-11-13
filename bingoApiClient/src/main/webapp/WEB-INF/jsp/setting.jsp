<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<body>
	<p>FrontEnd 서비스 프로젝트</p>
	<p>동작중인 Profile : ${profile}</p>
	<p>
		DB 설정정보 <br>
		driverClassName : ${driverClassName} <br>
		url : ${url} <br>
		username : ${username} <br>
		password : ${password} <br>		
	</p>
	<p>
		SYSTEM 설정정보 <br>
		name : ${name} <br>
		domain : ${domain} <br>
		machineName : ${machineName} <br>
		ip : ${ip} <br>		
		listenQueueName : ${listenQueueName} <br>		
	</p>
</body>

</html>