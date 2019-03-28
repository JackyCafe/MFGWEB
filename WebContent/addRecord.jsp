<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
   <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
 <script src ="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.min.js"></script>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> 
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 
 
 
<script>

        $(function () {
            $('.date').datepicker({
                dateFormat: "yy/mm/dd", //選用日期格式
                showButtonPanel:true
            });
        });

    </script>
<meta charset="UTF-8">
<title>Add Record</title>
</head>
<body>
	<form action="ClassRecord.process" method='post'>
		 <div class='panel'>      <div class ='input-group'>
	
		id: <input type='text'  id ='person_class_id' name ='person_class_id' value =<c:out value ="${param.id}"/> > <p>
		姓名: <input class = 'form-control' type='text' id ='name' name = 'name' value =<c:out value ="${param.name}"/>><p>
		課程名稱: <input class = 'form-control' type='text' id ='className' name = 'className' value =<c:out value ="${param.className}"/>><p>
		上課日期 <input class ="date form-control" id = 'class_date' name = 'class_date' ></p>
		測驗日期 <input class ="date form-control" id = 'test_date' name = 'test_date' ></p>
		測驗分數  <input class = 'form-control' type ="text" id = 'scope' name = 'scope' ></p>
		<input type ='submit' class = 'form-control' name ='action' value = 'add' /> </p> 	
		</div></div>
	</form>


</body>
</html>