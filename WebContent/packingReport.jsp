<%@page import="misc.HibernateUtil"%>
<%@page import="model.*"%>
<%@page import="model.dao.PackageInfoDAO"%>
<%@page import="model.service.PackageInfoService"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		String lot_id = request.getParameter("lot_id");
		PackageInfoService service = new PackageInfoService(new PackageInfoDAO(HibernateUtil.getSessionFactory()));
		List<PackageInfoBean> lists = null;
		if (lot_id.length() !=0) {
			lists = service.select(start_date, end_date,lot_id);
 		}else{
			lists = service.select(start_date, end_date);

			 
		}
 		request.setAttribute("lists", lists);
	%>

	<table class="table table-hover" id='report'>
		<thead class="thead-dark">
			<tr>
				<th scope="col">筆數</th>
				<th scope="col">日期</th>
				<th scope="col">箱號</th>
				<th scope="col">產品</th>
				<th scope="col">批號</th>
				<th scope="col">袋號1</th>
				<th scope="col">批號</th>
				<th scope="col">袋號2</th>
				<th scope="col">批號</th>
				<th scope="col">袋號3</th>
				<th scope="col">總重</th>
				<th scope="col">記錄員</th>
				<th scope="col">Puller領用</th>
				<th scope="col">備註</th>
				
			</tr>
		</thead>
		<tbody>

			<c:forEach var='r' items="${lists }" varStatus="i">
				<tr>
					<td scope="row">${i.count}</td>
					<td scope="row">${r.date1}</td>
					<td>${r.box}</td>
					<td>${r.product}</td>
					<td>${r.reactorLot}</td>
					<td>${r.lot1}</td>
					<td>${r.reactorLot2}</td>
					<td>${r.lot2}</td>
					<td>${r.reactorLot3}</td>
					<td>${r.lot3}</td>
					<td>${r.totalWeight}</td>
					<td>${r.recorder}</td>
				
					<c:choose>
						<c:when test="${r.pullerUse==false}">
							<td> </td>
							<td> </td>
						</c:when>
						<c:otherwise>
							<td> V </td>
							<td>${r.memo} </td>
						</c:otherwise>
					</c:choose>
				</tr>

			</c:forEach>
		</tbody>
	</table>
</body>
</html>