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
<title>新增</title>
</head>
<body>
<div class="container-fluid">

	<form action="Human.process" method='post'>
	 <div class='form-group row'>       
	   <div class="form-group col-md-2">
		工號<input  class = 'form-control' type='text' id ='workNum' name ='workNum' /> 
		</div></div>
	  <div class='form-group row'>	
		<div class="form-group col-md-2">
		姓名<input  class = 'form-control' type='text' id ='name' name = 'name' />
		</div>
	
		<div class="form-group col-md-2">	
		帳號<input  class = 'form-control' type='text' id ='account' name = 'account' />
		</div>
		  
		<div class="form-group col-md-2">	
		密碼<input   class = 'form-control' type="password" id ='password' name = 'password' ></div>
	    <div class="form-group col-md-2">	
		性別<select  class = 'form-control' id = 'sex' name ='sex'>
				<option>男</option>
				<option>女</option>
			</select> </div>
	</div>		
	<div class='form-group row'>		
		<div class="form-group col-md-2">
			到職日<input  class = 'form-control' type="date" id ='duty_date' name = 'duty_date' >
		</div>
		<div class="form-group col-md-2">
			離職日<input  class = 'form-control' type="date" id ='resignation_date' name = 'resignation_date' >
		</div>
	</div>	
	<div class='form-group row'>	
	<div class="form-group col-md-2">職等<input  class = 'form-control' type='text' id ='grade' name = 'grade' /></div>
	<div class="form-group col-md-2">班別:<select  class = 'form-control' id='class' name='class'>
			<option>NA</option>
			<option>NB</option>
			<option>DA</option>
			<option>DB</option>
			<option>常日</option>
		 </select></div>
	<div class="form-group col-md-2">區域 <select  class = 'form-control' id='area' name='area'>
			<option>ALL</option>
			<option>PULLER</option>
			<option>FILAMENT</option>
			<option>ETCH</option>
			<option>CVD</option>
			<option>PH</option>
			<option> </option>
		 </select></div>
    </div>		 
	<div class='form-group row'>		
		<div class="form-group col-md-5">
		信箱<input  class = 'form-control' type='text' id ='email' name = 'email' /></div>
		<div class="form-group col-md-1">權限<input  class = 'form-control' type='text' id ='permission' name = 'permission' /></div>
	</div>	
	<div class='form-group row'>		
		<div class="form-group col-md-2">
		生日:<input  class = 'form-control' type='date' id ='birthday' name = 'birthday' /></div>
		<div class="form-group col-md-1">
		電話:<input  class = 'form-control' type='text' id ='tel' name = 'tel' /></div>
		<div class="form-group col-md-1">
		緊急聯絡人:<input   class = 'form-control' type='text' id ='contact_persion' name = 'contact_persion' /></div>
		<div class="form-group col-md-1">
		緊急聯絡人電話:<input  class = 'form-control'  type='text' id ='contact_persion_tel' name = 'contact_persion_tel' /></div>
	</div>		
	<div class='form-group row'>		
		<div class="form-group col-md-2">	
		地址<input  class = 'form-control'  type='text' id ='address' name = 'address' /></div>
		<div class="form-group col-md-2">
		
		宿舍<select  class = 'form-control' id ='dormitory' name = 'dormitory' >
			<option>無</option>
			<option>台中</option>
			<option>彰化</option>
			<option>靜宜</option>
			</select>
		</div>
	 </div>
	 <div class='form-group row'>		
	 	<div class="form-group col-md-2">
	 	
		<input  class="btn btn-info"  type ='submit' name ='action' value = 'add' />
		</div> 
	  </div>
		
	</form>
</div>

 </body></html>