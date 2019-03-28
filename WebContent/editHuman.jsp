<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src ="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.min.js"></script>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <!-- jQuery library -->
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <!-- Latest compiled JavaScript -->
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>新增資料</title>
</head>
<body>
	 
	 <%
	 	String[] sex = new String[]{"男","女"};
	 	request.setAttribute("sex", sex);
	 	String[] shift = new String[]{"NA","NB","DA","DB","常日"};
	 	request.setAttribute("shift", shift);
	 	String[] areas = new String[]{"ALL","PULLER","FILAMENT","ETCH","CVD","PH"};
	 	request.setAttribute("areas", areas);
	 %>
	<div class="container-fluid">
	<form action="Human.process" method='post'>
	 	<div class='form-group row'>       	   
		<input  class = 'form-control' type='hidden' id ='id' name ='id' value=<c:out value ="${param.id}"/>> 
		<input  class = 'form-control' type='hidden' id ='pages' name ='pages' value=<c:out value ="${param.pages}"/>> 
		
		<div class="form-group col-md-2">
		工號: <input  class = 'form-control' type='text' id ='workNum' name ='workNum' value=<c:out value ="${param.workNum}"/>>
		</div>
		</div>
		<div class='form-group row'>
		<div class="form-group col-md-2">
		姓名: <input  class = 'form-control' type='text' id ='name' name = 'name' value=<c:out value ="${param.name}"/>>
		</div>
		<div class="form-group col-md-2">
		帳號: <input  class = 'form-control' type='text' id ='account' name = 'account' value=<c:out value ="${param.account}"/>></div>
		<div class="form-group col-md-2">
		密碼: <input  class = 'form-control' type="password" id ='password' name = 'password' value=<c:out value ="${param.password}"/>></div>
		<div class="form-group col-md-2">
		
		性別: <select  class = 'form-control' id = 'sex' name ='sex'>
	 			<c:forEach var ='s' items='${sex}'>
	 				<option value= "${s }" ${s == param.sex ? 'selected' : ''}  /><c:out value='${s }'/> </option>
	 			</c:forEach>
			</select> </div>
		</div>	
		<div class='form-group row'>
		<div class="form-group col-md-2">
		到職日: <input  class = 'form-control' type="date" id ='duty_date' name = 'duty_date' value=<c:out value ="${param.duty_date}"/>></div>
		<div class="form-group col-md-2">
		離職日: <input  class = 'form-control' type="date" id ='resignation_date' name = 'resignation_date' value=<c:out value ="${param.resignation_date}"/> ></div>
		</div>
		<div class='form-group row'>
		<div class="form-group col-md-2">
		職等:<input  class = 'form-control' type='text' id ='grade' name = 'grade' value=<c:out value ="${param.grade}"/>></div>
		<div class="form-group col-md-2">
		班別 <select  class = 'form-control' id='class' name='class'>
			<c:forEach var ='s' items='${shift}'>
			<option value= "${s }" ${s == param.class_ ? 'selected' : ''}  /><c:out value='${s }'/> </option>
			</c:forEach>
		</select></div>	
		<div class="form-group col-md-2">
		區域:
		<select class = 'form-control' id='area' name='area'>
			<c:forEach var ='area' items='${areas}'>
			<option value= "${area }" ${area == param.area ? 'selected' : ''}  /><c:out value='${area }'/> </option>
			</c:forEach>
		</select></div>	
		</div>
		<div class='form-group row'>
		<div class="form-group col-md-5">
 		信箱:<input type='text'  class = 'form-control' id ='email' name = 'email' value=<c:out value ="${param.email}"/>></div>
		<div class="form-group col-md-1">		
		權限:<input type='text'  class = 'form-control' id ='permission' name = 'permission' value=<c:out value ="${param.permission}"/>></div>
		</div>
		<div class='form-group row'>
		<div class="form-group col-md-2">
		生日:<input type='date'  class = 'form-control' id ='birthday' name = 'birthday' value=<c:out value ="${param.birthday}"/>></div>
		<div class="form-group col-md-1">
		電話:<input type='text'  class = 'form-control' id ='tel' name = 'tel' value=<c:out value ="${param.tel}"/>></div>
		<div class="form-group col-md-1">
		緊急聯絡人:<input type='text' class = 'form-control'  id ='contact_persion' name = 'contact_persion' value=<c:out value ="${param.contact_persion}"/>></div>
		<div class="form-group col-md-1">
		緊急連絡人電話:<input type='text' class = 'form-control' id ='contact_persion_tel' name = 'contact_persion_tel' 
		value=<c:out value ="${param.contact_persion_tel}"/>></div>
		</div>
		<div class='form-group row'><div class="form-group col-md-2">				
		居住區域:<input type='text' class = 'form-control' id ='address' name = 'address'  value=<c:out value ="${param.address}"/>>
		</div>
		<div class="form-group col-md-2">
		宿舍:<select class = 'form-control'  id ='dormitory' name = 'dormitory' value=<c:out value ="${param.dormitory}"/> >
			<option>無</option>
			<option>台中</option>
			<option>彰化</option>
			<option>靜宜</option>
			</select>
		</div>
		</div>
		<div class='form-group row'>
		<div class="form-group col-md-2">
		<input type ='submit' class="btn btn-info" name ='action' value = 'update' ></div>
		 
	 
	</form>
	</div>	


</body>
</html>