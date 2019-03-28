<%@page import="misc.HibernateUtil"%>
<%@page import="model.dao.PackageInfoDAO"%>
<%@page import="model.service.PackageInfoService"%>
<%@page import="model.PackageInfoBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <%
  String lots=request.getParameter("lots");
  PackageInfoService service = new PackageInfoService(new PackageInfoDAO(HibernateUtil.getSessionFactory()));	
  boolean isExist = service.select(lots);
  out.print(isExist);
 
 %>
</body>
</html>