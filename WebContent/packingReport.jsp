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

	<table class="table table-hover table-striped table-bordered table-rwd" id='report'>
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
					<td data-th="筆數">${i.count}</td>
					<td data-th="日期">${r.date1}</td>
					<td data-th="箱號">${r.box}</td>
					<td data-th="產品名稱">${r.product}</td>
					<td data-th="CVD 批號">${r.reactorLot}</td>
					<td data-th="袋號">${r.lot1}</td>
					<td data-th="CVD 批號">${r.reactorLot2}</td>
					<td data-th="袋號">${r.lot2}</td>
					<td data-th="CVD 批號">${r.reactorLot3}</td>
					<td data-th="袋號">${r.lot3}</td>
					<td data-th="總重">${r.totalWeight}</td>
					<td data-th="紀錄者">${r.recorder}</td>
				
					<c:choose>
						<c:when test="${r.pullerUse==false}">
							<td data-th="puller領用"> </td>
							<td data-th="備註"> </td>
						</c:when>
						<c:otherwise>
							<td data-th="puller領用"> V </td>
							<td data-th="備註">${r.memo} </td>
						</c:otherwise>
					</c:choose>
				</tr>

			</c:forEach>
		</tbody>
	</table>
</body>
</html>