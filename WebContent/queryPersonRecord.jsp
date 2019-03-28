<%@page import="model.service.PersonClassRecordService"%>
<%@page import="model.service.PersonClassInfoService"%>
<%@page import="misc.HibernateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import = "model.dao.*"  %>   
<%@ page import = "model.*" %>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String mclass =request.getParameter("mclass_");  	
	PersonClassInfoDAO dao = new PersonClassInfoDAO(HibernateUtil.getSessionFactory()) ; 
	PersonClassInfoService service = new PersonClassInfoService(new PersonClassInfoDAO(HibernateUtil.getSessionFactory()));	
	List<PersonClassInfoBean> info = service.select(mclass); 
	request.setAttribute("info",info);
	 
%>
<c:choose>
	<c:when test="${info!=null }">
		<table class='table table-hover table-striped table-bordered table-rwd'>
			<tr>
				<th rowSpan = 2>工號</th>
				<th rowSpan = 2>姓名</th>
				<th colspan="3">上課記錄</th>
			</tr>			
			<tr>
				<th>上課日期</th>
				<th>測驗日期</th>
				<th>測驗分數</th>
				
				
			</tr>
			<c:forEach var="row" items="${info}" >
				<tr>
					<td data-th='工號'> ${row.workNum}</td>
					<td> ${row.name}</td>
					<c:choose>
						<c:when test="${row.personClassRecords.size()==0}"><td></td><td></td><td></td> </c:when>
						<c:otherwise>
							<c:forEach var ="r" items="${row.personClassRecords}" varStatus="loop">
				 				<c:if test="${loop.last }" >
				 					<td> ${r.classDate } </td>
				 					<td> ${r.testDate } </td>
				 					<td> ${r.testScore }</td>
				 				</c:if>
				 			</c:forEach>
						</c:otherwise>
					</c:choose>
				</tr>	
			</c:forEach>
		</table>
	</c:when>
</c:choose>
 
</body>
</html>