<%@page import="model.service.PersonCertificationInfoService"%>
<%@page import="model.dao.PersonCertificationInfoDAO"%>
<%@page import="model.dao.HumanInfoDAO"%>
<%@page import="model.service.HumanInfoService"%>
<%@page import="misc.HibernateUtil"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<%@page import="model.dao.CertificationItemDAO"%>
<%@page import="model.service.CertificationItemService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--2019/01/04  
	傳入區域找出該區學術科與訓練項目 
	-->

 	<%
		String area = request.getParameter("area");
 		CertificationItemService cis = new CertificationItemService(new CertificationItemDAO(HibernateUtil.getSessionFactory())); 
 	    List<CertificationItemBean> certifications= cis.queryNonTraining(area); 	    
 	    List<CertificationItemBean> trainings = cis.queryTraining(area);
 	 %>	
 	   
 	    認證科目:<select id ='certificationlist' name ='certificationlist' class = 'form-control'>
		<% 
		for (CertificationItemBean certification:trainings )
		{ 	   %><option><%=certification.getTraining()  %></option>
		<%}  
 	    for (CertificationItemBean certification:certifications )
 	    {%>
 	    	<option><%=certification.getKnowledge()  %></option>
 	     <%}
	  %>
 		</select>

</body>
</html>