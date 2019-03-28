<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>教育訓練系統</title>
<link type='text/css' rel="stylesheet" href='css/index.css'>
<meta charset="utf-8">
  
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
  
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  

  <script>
  $( function() {
	    $( "#loginDialog" ).hide();
	    $("#callLogin").click(function(){
	    		$( "#loginDialog" ).dialog();
	    	})
	  } );  
  </script>
  </head>
  
<body>		
	  <nav class="navbar navbar-default" >
        <div class="container-fluid" >
            <div class="navbar-header"  >
                <button type="button" class="navbar-toggle navbar-light bg-primary" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span> 
                </button>
                <a class="navbar-brand" href="#" >製造管理系統</a>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">           
            <ul class="nav navbar-nav">     
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
            	    通識教育<span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="role_info.jsp">人事資料</a></li>
                <li><a href="class_info.jsp">通識課程維護</a></li>
                <li><a href="person_class_info.jsp">人員通識課程維護</a></li>
                <li><a href="query_record.jsp">通識課程訓練紀錄</a></li>
            </ul>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
                	專業訓練<span class="caret"></span></a>
                	<ul class="dropdown-menu">               
                <li><a href="CertificationItemInfo.jsp">專業訓練課程維護</a></li>
                <li><a href="PersonCertificationInfo.jsp">認證紀錄新增</a></li>
                <li><a href="PersonCertificationRecord.jsp">認證紀錄查詢</a></li>
                <li><a href="queryCertificationReport.jsp">認證報表</a></li>
            </ul></li>
            <li class="dropdown"><a class="dropdown-toggle"  data-toggle="dropdown" href="#">
                	出貨管理<span class="caret"></span></a>
                	<ul class="dropdown-menu">               
            <li><a href="packing.jsp">裝箱作業</a></li>
            <li><a href="queryPackingList.jsp">裝箱報表</a></li>
                
            </ul>
                
       		 </li>
       		  
        </ul>
                <ul class="nav navbar-nav navbar-right  ">
                     <c:choose>				       
				      		<c:when test="${account==null}">
				      		<c:out value="${account}"></c:out>
				      			<li><a href='register.jsp'><span class='glyphicon glyphicon-user'/>
				      			註冊</a></li>
				      			<li> <a id = 'callLogin' href='#' >
				      				<span class='glyphicon glyphicon-log-in'></span>登入</a></li>
				      		</c:when>
				      		<c:when test="${account!=null}">	
				      			<li>歡迎 <c:out value="${account.name}" /></li>				      						      		
				      			<li> <a href='register.jsp'><span class='glyphicon glyphicon-user'/>
				      					註冊</a></li>
				      			<li> <a href='logout'>
				      				<span class='glyphicon glyphicon-log-out'/>登出</a></li>
				      		</c:when>
				      </c:choose>
                </ul>
            </div>
        </div>
    </nav>
		
	<div id="loginDialog" title="請輸入登入資訊">
  			<form method="post" action='/MFGWEB/vertify'>
	  			<label for="account">帳號:</label>
					<input type="account" name="account" id="account" /><p>
  				<label for="password">密碼:</label>
					<input type="password" name="password" id="password" />
   				<input type = 'submit' name = 'login' value='登入'/> 
  			</form>
	</div>
	   
 