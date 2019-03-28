<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<%@ page import="java.util.*" %>
<%@page import="model.*" %>
<%@page import="misc.*" %>
<%@page import="model.dao.*" %>
<%@page import="model.service.*" %>   
<%@include file ="header.jsp" %>
	
	 <%
		ClassInfoService service = new ClassInfoService(
				new ClassInfoDAO(HibernateUtil.getSessionFactory()));;
		List<ClassInfoBean> select = service.select();
		request.setAttribute("select", select);
	%>
	<c:choose>
	 	<c:when test="${account==null}">
	 		 <c:out value="請先登入"/>
	 	</c:when>
	 	<c:when test = "${account!=null}">
	 		<c:if test='${account.permission =="A"}'>
	 		<a href ='addClass.html' class='button'>新增</a>
	 		
	 		<c:if test="${not empty select}">
	 			<table class="table table-hover">
	 			<thead>
	 			<tr>
	 				<th>item</th> 
	 				<th>level</th>
	 				<th>職等</th>
	 				<th>課程名稱</th>
	 				<th colspan="2" >功能</th>
	 				
	 			</tr>
	 			</thead>
	 			<body>
	 			<c:forEach var="row" items="${select}" varStatus="loop">
	 			<tr>	 			
	 			<td><c:out value="${loop.index+1 }"/></td>
	 			<td><c:out value="${row.level }"></c:out></td>
	 			<td><c:out value="${row.grade }"></c:out></td>
	 			<td><c:out value="${row.className }"></c:out></td>
	 			
	 			<td><a href = '<c:url value="editClass.jsp">
	 				<c:param name="id" value ="${row.id}"/>
	 				<c:param name="level" value ="${row.level}"/>
	 				<c:param name="grade" value ="${row.grade}"/>
	 				<c:param name="className" value ="${row.className}"/>
	 			</c:url>'>編輯</a></td>
	 			
	 			<td><a href='<c:url value="Class.process">
	 				<c:param name="id" value="${row.id}"/>
	 				<c:param name="action" value="delete"/>
	 			</c:url>'>刪除</a></td>
	 			</tr>
	 		</c:forEach>
	 		</body>
	 		
	 	</table>
	 			
	 		</c:if>
	 		</c:if>
	 		

	 	</c:when>
	</c:choose>
<%@include file ="footer.jsp" %>