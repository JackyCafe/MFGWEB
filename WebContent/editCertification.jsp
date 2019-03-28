<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<html>
<head>
<meta charset="UTF-8">
   
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
 
<title>修改資料</title>
</head>
<body>
	<form action="Certification.process" method='post'>
		id: <input type='text' name = 'id' value ='${param.id}'> <p>	
		區域: <input type='text' name = 'area' value ='${param.area}'> <p>		
		學科: <input type='text' name = 'knowledge' value ='${param.knowledge}' /><p>
		術科: <input type='text' name = 'technical' value ='${param.technical}'/></p>
		訓練: <input type='text' name = 'training' value ='${param.training }' /> <p> 		 
 		只訓練不認證 :<input type='checkbox' id='training_only'  name='training_only'
 			${param.training_only eq 1? "checked":" " }
 		 >
 		
		<input type ='submit' name ='action' value = 'update' /> 
	</form>


</body>
</html>