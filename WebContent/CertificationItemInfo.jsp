<%@page import="misc.HibernateUtil"%>
<%@page import="model.dao.CertificationItemDAO"%>
<%@page import="model.service.CertificationItemService"%>
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<%@ include file="header.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 各區科目設定 -->
<%
	CertificationItemService service = new CertificationItemService(new CertificationItemDAO(HibernateUtil.getSessionFactory()));
	List<CertificationItemBean> certificationSelect = service.select();
	request.setAttribute("certificationSelect", certificationSelect);	
%> 

 <c:choose>
	 	<c:when test="${account==null}">
	 		 <c:out value="請先登入"/>
	 	</c:when>
	 	<c:when test = "${account!=null}">
			<c:if test='${account.permission=="A"}'>
			<a href ='addCertification.html' class='button'>新增</a>
				<c:if test="${not empty certificationSelect}">		 
				<table>
					<tr>
					<th>item</th>
					<th>區域</th>
					<th>學科</th>
					<th>術科</th>
					<th>訓練 </th>
					<th>只訓練不認證 </th>
					<th colspan = 2>功能</th>
					</tr>
					<c:forEach var="rows" items="${certificationSelect}" varStatus="loop">
					<tr>
					<td><c:out value ="${loop.count }"/></td>
					<td><c:out value ="${rows.area }"/></td>					
					<td><c:out value ="${rows.knowledge }"/></td>
					<td><c:out value ="${rows.technical }"/></td>
					<td><c:out value ="${rows.training }"/></td>
					
					<td><c:choose>
						<c:when test="${rows.trainingOnly == 1 }">							
							<input type='checkbox' checked/>
							
						</c:when>
						<c:otherwise> 	<input type='checkbox'/>
						</c:otherwise>
					</c:choose> </td>
					<td> <!-- 編輯參數 -->
						<a href = '<c:url value="editCertification.jsp">
	 					<c:param name="id" value ="${rows.id}"/>
	 					<c:param name="area" value ="${rows.area}"/>
	 					<c:param name="knowledge" value ="${rows.knowledge}"/>
	 					<c:param name="technical" value ="${rows.technical}"/>
	 					<c:param name ="training" value ="${rows.training }" />
	 					<c:param name ="training_only" value ="${rows.trainingOnly }" />
	 				</c:url>'>編輯</a>
					<td>
						 
	 					<a href='<c:url value="Certification.process">
	 					<c:param name="id" value="${rows.id}"/>
	 					<c:param name="action" value="delete"/>
	 				</c:url>'>刪除</a>
					</td>
					</tr>
					</c:forEach>
					
					
				</table>
				</c:if>			
			</c:if> 
	 	</c:when  > 
</c:choose>	 	

 <%@include file ="footer.jsp" %>    
 