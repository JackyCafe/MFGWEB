<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增資料</title>
</head>
<body>
	<form action="Class.process" method='post'>
		<input type='hidden' id ='id' name ='id' 
		value=<c:out value ="${param.id}"/> > <p>
		Level: <input type='text' id ='level' name ='level'
			value=<c:out value ="${param.level}"/>
		 /> <p>
		等級: <input type='text' id ='grade' name = 'grade' 
			value=<c:out value ="${param.grade}"/>
		/><p>
		課程名稱: <input type='text' id ='className' name = 'className' 
			value=<c:out value ="${param.className}"/>
		/><p>		 
		<input type ='submit' name ='action' value = 'update' /> 
	</form>


</body>
</html>