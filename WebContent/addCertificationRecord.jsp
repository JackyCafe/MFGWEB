<!DOCTYPE html>
<%@page import="misc.HibernateUtil"%>
<%@page import="model.dao.PersonCertificationInfoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<%@ page import ="model.service.*" %>
<%@ page import ="model.*" %>
<%@ page import ="java.util.*" %>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
 <script src="jquery.ui.core.js"></script>
 <script src="jquery.ui.datepicker-zh-TW.js"></script>
  <script>

        $(function () {
            $('.date').datepicker({
                dateFormat: "yy/mm/dd", //選用日期格式
                showButtonPanel:true
            });
        });

    </script>

<title>新增認證資料</title>
<%
	String sid = request.getParameter("id");
 	PersonCertificationInfoService pcis = new PersonCertificationInfoService(new PersonCertificationInfoDAO(HibernateUtil.getSessionFactory()));
 	int id = 0 ;
 	id = Integer.valueOf(sid);
 	model.PersonCertificationInfoBean select = pcis.select(id);
 	request.setAttribute("select", select);
%>
</head>
<body>
  	<form action="CertificationRecord.process" method='post'>
		id: <input type='text' id ='person_class_id' name ='person_class_id' value =<c:out value ="${param.id}"/> > <p>
		姓名: <input type='text' id ='name' name = 'name' value =<c:out value ="${select.name}"/>><p>
		工號: <input type='text' id ='worknum' name = 'worknum' value =<c:out value ="${select.workNum}"/>><p>
		區域 <input type ="text" id = 'area' name = 'area'  value =<c:out value ="${select.area}"/> ><p>
		班別<input type ="text" id = 'shift' name = 'shift'  value =<c:out value ="${select.shift}"/> ><p>
		學科 <input type ="text" id = 'knowledge' name = 'knowledge'  value =<c:out value ="${select.knowledge}"/> ><p>
		學科測驗日期 <input type ="text" class="date" id = 'knowledgedate' name = 'knowledgedate'><p>
		學科測驗分數 <input type ="text" id = 'knowledgescore' name = 'knowledgescore'><p>
		術科 <input type ="text" id = 'technical' name = 'technical'  value =<c:out value ="${select.technical}"/> ><p>
		術科測驗日期 <input type ="text" class="date" id = 'technicaldate' name = 'technicaldate'><p>
		術科測驗分數 <input type ="text" id = 'technicalscore' name = 'technicalscore'><p>
		訓練日期 <input type ="text" class="date" id = 'trainingdate' name = 'trainingdate'><p>
		 
		<input type ='submit' name ='action' value = 'add' /> </p> 	
	</form>


</body>
</html>