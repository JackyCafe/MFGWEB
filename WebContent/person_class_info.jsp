<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
  
<%@page import="java.util.*" %>
<%@page import="model.*" %>
<%@page import="misc.*" %>
<%@page import="model.dao.*" %>
<%@page import="model.service.*" %>
<%@include file ="header.jsp" %>
	

<% 
	 ClassInfoService classInfoService = new ClassInfoService(
				new ClassInfoDAO(HibernateUtil.getSessionFactory()));
	 List<ClassInfoBean> classInfoSelect = classInfoService.select();
	 request.setAttribute("classInfoSelect", classInfoSelect);
 		
	 HumanInfoService humanInfoService = new HumanInfoService(
			 new HumanInfoDAO(HibernateUtil.getSessionFactory()));
	 //List<HumanInfoBean> humanInfoSelect =  humanInfoService.select();
	 int count = 25 ;
	 int pages = 1 ;
	 try{
		 pages = Integer.parseInt(request.getParameter("pages"));
	 }catch(Exception e){
		 pages = 1;
	 }
	 
	 List<HumanInfoBean> humanInfoSelect =  humanInfoService.selectByPage(pages,count);
	 
	 request.setAttribute("humanInfoSelect", humanInfoSelect);
	%>
	 <c:choose>
	 	<c:when test="${account==null}">
	 		 <c:out value="請先登入"/>
	 	</c:when>
	 	
	 	<c:when test = "${account!=null}">
			<c:if test='${account.permission=="A"}'>
		 	<c:if test="${not empty classInfoSelect}">
		 		<a href="reload" class="button">整理</a>
				<table>			
				<!--表頭 -->
 				<th>工號</th>
				<th>職等</th>
				<th>姓名</th>				 
				<c:forEach var="row" items="${classInfoSelect}">									
					<th><c:out value="${row.className}" /></th>	
				</c:forEach> 	
 				<!--表頭 -->
				<c:forEach var = "humanInfoSelect" items ="${humanInfoSelect}" >
				<tr>		
					<c:if test="${humanInfoSelect.workNum!=0}">
					<td>${humanInfoSelect.workNum}</td>
					<td>${humanInfoSelect.grade }</td>
					<td>${humanInfoSelect.name }</td>		
					<c:forEach var="classInfoSelect" items="${classInfoSelect}">									
						 <!-- 課程等級 -->	
						 <fmt:parseNumber var="classGrade" value ="${classInfoSelect.grade}"   />
						 <!-- 人員職等 -->
						 <fmt:parseNumber var="humanGrade" value ="${humanInfoSelect.grade}"  />
						 <c:set var ="name" value = "${humanInfoSelect.name}"/>
						 <c:set var ="workNum" value = "${humanInfoSelect.workNum}"/>
						 <c:set var ="className" value = "${classInfoSelect.className}"/>
							<%
						 	 	String name =(String)pageContext.getAttribute("name");
						 		String className =(String)pageContext.getAttribute("className");
						 		String workNum = (String)pageContext.getAttribute("workNum");
						 		PersonClassInfoService personClassService = new PersonClassInfoService(
						 				new PersonClassInfoDAO(HibernateUtil.getSessionFactory()));
						 		boolean isExist = personClassService.isExist(name, className);
						 		int id = personClassService.findId(name, className);						 		
 						 		request.setAttribute("isExist", isExist);
 						 		request.setAttribute("id", id);
						 	%>
							<c:choose>
								<c:when test="${isExist == false }">
									<c:choose>	
										<c:when test="${classGrade<=humanGrade }">
											<td bgcolor="#fca7d6">X</td>
										</c:when>
										<c:otherwise><td bgcolor="#fff"></td>
										</c:otherwise>
									</c:choose>
								</c:when>
								
								<c:otherwise>								 
								<td bgcolor="#fff">
								
								<a href = <c:url value = "addRecord.jsp">
									<c:param name="id" value ="${id}" />
									<c:param name="name" value ="${name}" />
									<c:param name="className" value ="${className}" />
								</c:url>  ><c:out value="${id}"></c:out> </a> </td> 								
								</c:otherwise>
							</c:choose> 
				</c:forEach> 	
				</c:if>			
 				</tr>
				</c:forEach> 
				</table>			
			</c:if>
			</c:if> 	
	 	</c:when>	 
	 </c:choose>
 <div id = 'page'>
	<%
		int size = humanInfoService.select().size();
		int tatalpage = (size % count == 0 ? (size /count):(size /count)+1);
		for (int i = 1 ; i<= tatalpage; i++)
		{
			out.print("<a href=person_class_info.jsp?pages="+i+">"+i+"</a> ");
		}
	%>
	</div>
<%@include file ="footer.jsp" %>