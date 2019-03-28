<%@page import="misc.HibernateUtil"%>
<%@page import="model.*"%>
<%@page import="model.dao.*"%>
<%@page import="model.service.*"%>
<%@page import="java.util.*"%>
<%@page import="java.util.Comparator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- 主要用來查詢各區、各班、各科成績  -->
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="./css/index.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
	 
</script>
<meta charset="UTF-8">

</head>
<body>
	<%
		String area = request.getParameter("area");
		String shift = request.getParameter("shift");
		String certification = request.getParameter("certification");

		PersonCertificationInfoService pcis = new PersonCertificationInfoService(
				new PersonCertificationInfoDAO(HibernateUtil.getSessionFactory()));
		/*判斷丟進來的科目是屬於學科術科還是訓練*/
		String category = pcis.findCategory(certification);
		/* 依類別班別與科目 在PersonCertificationInfoService 找出對應的人名*/
		List<PersonCertificationInfoBean> certifications = null;
		//Personcertification的Select 有三個參數
		//科目名稱、班別、只訓練不認證(true)。
		if (category.equals("training"))
			certifications = pcis.select(certification, shift, true);
		else
			certifications = pcis.select(certification, shift, false);
		request.setAttribute("certifications", certifications);
		request.setAttribute("certification", certification);
	%>


	<if test="${certifications!=null }">
	<table id='certifications' class="table table-hover table-striped table-bordered table-rwd">
		<thead class="thead-dark">

			<tr class="tr-only-hide">
				<th rowspan=2>科目</th>
				<th rowspan=2>班別</th>
				<th rowspan=2>工號</th>
				<th rowspan=2>姓名</th>

				<th colspan=2>學科</th>
				<th colspan=2>術科</th>
				<th colspan=2>訓練</th>
			</tr>
			<tr class="tr-only-hide">

				<th>日期</th>
				<th>分數</th>
				<th>日期</th>
				<th>分數</th>
				<th>日期</th>
				<th>訓練員</th>
			</tr>
		</thead>
		<body>
			<c:forEach var='rows' items='${certifications}'>
				<tr>
					<td width='15%' data-th="科目"><%=request.getParameter("certification")%></td>
					<td data-th="班別">${rows.shift }</td>
					<td data-th="工號">${rows.workNum }</td>
					<td data-th="姓名">${rows.name }</td>
					<td data-th="日期"><c:forEach var='r'
							items='${rows.personCertificationRecords  }' varStatus='loop'>
							<c:if test='${loop.last}'>
									 ${r.knowledgeDate } 
								</c:if>
						</c:forEach></td>
					<td data-th="學科分數"><c:forEach var='r'
							items='${rows.personCertificationRecords  }' varStatus='loop'>
							<c:if test='${loop.last}'>
									 ${r.knowledgeScore } 
								</c:if>
						</c:forEach></td>
					<td data-th="術科日期"><c:forEach var='r'
							items='${rows.personCertificationRecords  }' varStatus='loop'>
							<c:if test='${loop.last}'>
									 ${r.technicalDate } 
								</c:if>
						</c:forEach></td>
					<td data-th="術科分數"><c:forEach var='r'
							items='${rows.personCertificationRecords  }' varStatus='loop'>
							<c:if test='${loop.last}'>
									 ${r.technicalScore } 
								</c:if>
						</c:forEach></td>
					<td data-th="訓練日期"><c:forEach var='r'
							items='${rows.personCertificationRecords  }' varStatus='loop'>
							<c:if test='${loop.last}'>
									 ${r.trainingDate } 
								</c:if>
						</c:forEach></td>
					<td data-th="訓練員"><c:forEach var='r'
							items='${rows.personCertificationRecords  }' varStatus='loop'>
							<c:if test='${loop.last}'>
									 ${r.trainer } 
								</c:if>
						</c:forEach></td>

				</tr>
			</c:forEach>
		</body>
	</table>
	</if>




</body>
</html>