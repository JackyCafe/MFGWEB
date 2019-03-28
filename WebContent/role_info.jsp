<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@page import="model.*"%>
<%@page import="misc.*"%>
<%@page import="model.dao.*"%>
<%@page import="model.service.*"%>

<%@include file="header.jsp"%>
<style type="text/css">
#page {
	color: purple;
	background-color: white;
	position: absolute;
	right: 5em;
}

a {
	font-family: Helvetica, Geneva, Arial, SunSans-Regular, sans-serif;
	font-size: 16px;
}
</style>
<script type="text/javascript">
	$(function() {
		$('#importDialog').hide();
		$('#importdata').click(function() {
			$('#importDialog').dialog();

		})
		$('#addHuman').click(function() {
			$(location).attr('href', 'addHuman.jsp')
		})
	})
</script>
<%
	HumanInfoService service = new HumanInfoService(new HumanInfoDAO(HibernateUtil.getSessionFactory()));
	//List<HumanInfoBean> select = service.select();
	int pages = 1;
	int count = 20;
	try {
		pages = Integer.parseInt(request.getParameter("pages"));
	} catch (Exception e) {
		pages = 1;
	}
	List<HumanInfoBean> select = service.selectByPage(pages, count);
	request.setAttribute("select", select);
	request.setAttribute("pages", pages);
%>
<c:choose>
	<c:when test="${account==null}">
		<c:out value="請先登入" />
	</c:when>

	<c:when test="${(account!=null)&&(account.permission == 'A')}">
		<nav>
			<ul class="pagination">
				<li class='page-item'><button id='addHuman' class='button btn btn-info  '>新增</button></li>
				<li class='page-item'><button id='importdata' class='button btn btn-info '>匯入</button></li>
			 
				
				<%
					int size = service.select().size();
							int tatalpage = (size % count == 0 ? (size / count) : (size / count) + 1);
							for (int i = 1; i <= tatalpage; i++) {
								out.print("<li class='page-item' ><a class='page-link' href=role_info.jsp?pages=" + i + ">" + i
										+ "</a></li> ");
							}
				%>

			</ul>
		</nav>

		<c:if test="${not empty select}">
			<table
				class="table table-hover table-striped table-bordered table-rwd">
				<thead>
					<tr class="tr-only-hide">
						<th>工號</th>
						<th>姓名</th>
						<th>帳號</th>
						<th>性別</th>
						<th>到職日</th>
						<th>職等</th>
						<th>電話</th>
						<th>生日</th>
						<th>班別</th>
						<th>區域</th>
						<th>緊急聯絡人</th>
						<th>緊急聯絡人電話</th>
						<th>居住地</th>
						<th>宿舍</th>
						<th>功能</th>

					</tr>
				</thead>
				<tbody>
					<c:if test='${account.permission=="A"}'>
						<c:forEach var="row" items="${select}">
							<tr>
								<td data-th="工號"><c:out value="${row.workNum}" /></td>
								<td data-th="姓名"><c:out value="${row.name}" /></td>
								<td data-th="帳號"><c:out value="${row.account}" /></td>
								<td data-th="性別"><c:out value="${row.sex}" /></td>
								<td data-th="到職日"><c:out value="${row.dutyDate}" /></td>
								<td data-th="職等"><c:out value="${row.grade}" /></td>
								<td data-th="電話"><c:out value="${row.tel}" /></td>
								<td data-th="生日"><c:out value="${row.birthday}" /></td>
								<td data-th="班別"><c:out value="${row.class_}" /></td>
								<td data-th="區域"><c:out value="${row.area}" /></td>
								<td data-th="緊急聯絡人"><c:out value="${row.contactPersion}" /></td>
								<td data-th="緊急聯絡人電話"><c:out
										value="${row.contactPersionTel}" /></td>
								<td data-th="居住地"><c:out value="${row.address}" /></td>
								<td data-th="宿舍"><c:out value="${row.dormitory}" /></td>
								<td data-th="功能"><a
									href="<c:url value='editHuman.jsp'>
						<c:param name="id" value="${row.id}"/>
						<c:param name="workNum" value="${row.workNum}"/>
						<c:param name="account" value="${row.account}"/>
						<c:param name="name" value="${row.name}"/>
						<c:param name="password" value="${row.password}"/>
						<c:param name="sex" value="${row.sex}"/>
						<c:param name="duty_date" value="${row.dutyDate}"/>
						<c:param name="resignation_date" value="${row.resignationDate}"/>
						<c:param name="grade" value="${row.grade}"/>
						<c:param name="class_" value="${row.class_}"/>
						<c:param name="area" value="${row.area}"/>
						<c:param name="email" value="${row.email}"/>
						<c:param name="permission" value="${row.permission}"/>
						<c:param name="birthday" value="${row.birthday}"/>
						<c:param name="tel" value="${row.tel}"/>
						<c:param name="contact_persion" value="${row.contactPersion}"/>
						<c:param name="contact_persion_tel" value="${row.contactPersionTel}"/>
						<c:param name="address" value="${row.address}"/>
						<c:param name="dormitory" value="${row.dormitory}"/>
						<c:param name="pages" value="${pages}"/>
						
					</c:url>">編輯</a>
								</td>

							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table>
		</c:if>
	</c:when>
</c:choose>



<div id="importDialog" title="請輸入登入資訊" m>
	<form method="post" action='/MFGWEB/Human.process'
		enctype="multipart/form-data">
		<label for="uploadfile">選擇檔案:</label> <input type="file"
			name="uploadfile" id="uploadfile" />
		<p>
			<input type='submit' name='action' value='upload' />
	</form>
</div>
<%@include file="footer.jsp"%>
