<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<body>
	<h1>${_systemProp.name} 서버입니다.</h1>
	<p>동작중인 Profile : ${_profile}</p>
	
	<hr>
	<h3>접속 사용자 정보</h3>
	<p>접속한 디바이스 : ${_deviceType}</p>
	<p>이름 : </p>
	<p>이메일 : </p>
	
</body>

</html>