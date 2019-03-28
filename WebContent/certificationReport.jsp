<%@page import="model.dao.PersonCertificationRecordDAO"%>
<%@page import="model.service.PersonCertificationRecordService"%>
<%@page import="model.dao.CertificationItemDAO"%>
<%@page import="model.service.CertificationItemService"%>
<%@page import="misc.HibernateUtil"%>
<%@page import="model.dao.HumanInfoDAO"%>
<%@page import="model.service.HumanInfoService"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/index.css">

</head>
<body>
	<%
		String area = request.getParameter("area");
		String shift = request.getParameter("shift");
		HumanInfoService humanService = new HumanInfoService(new HumanInfoDAO(HibernateUtil.getSessionFactory()));
		CertificationItemService certificationService = new CertificationItemService(
				new CertificationItemDAO(HibernateUtil.getSessionFactory()));
		PersonCertificationRecordService pcrs = new PersonCertificationRecordService(
				new PersonCertificationRecordDAO(HibernateUtil.getSessionFactory()));

		/*找出班別區域內的人數*/
		List<HumanInfoBean> humans = humanService.queryByAreaShift(area, shift);
		request.setAttribute("humans", humans);

		/*找出認證科目*/
		List<CertificationItemBean> certifications = certificationService.selectByArea(area);
		request.setAttribute("certifications", certifications);
	%>
	<c:if test="${humans!=null }">
		<table class="table  table-sm table-hover table table-hover table-striped table-bordered table-rwd"  >
		 <thead class="thead-light"><!-- 表頭 -->
			<tr class="tr-only-hide">  
				<th>班別</th>
				<th>工號</th>
				<th>姓名</th>
				
				<% 
					for(CertificationItemBean bean:certifications)
					{
						if (bean.getKnowledge()!=null)
							out.print("<th>"+bean.getKnowledge()+" </th> ");
						else if (bean.getTraining()!=null)
							out.print("<th>"+bean.getTraining()+" </th> ");
					}	
						%>	
			</tr> <!-- 表頭 -->
			</thead>
			<tbody><!-- 表頭 -->
		 
			<% 
			
			int i = humans.size(),count = certifications.size()  ;
			int j = 0;
			int[] pass = new int[count];
			
			for(HumanInfoBean human:humans)
			{ 
				out.print("<tr>");
				out.print("<td data-th='班別' >"+human.getClass_()+"</td>");
				out.print("<td data-th='工號'>"+human.getWorkNum()+"</td>");
				out.print("<td data-th='姓名'>"+human.getName()+"</td>");
				j = 0;
 				for(CertificationItemBean bean:certifications)
				{
					if (bean.getKnowledge()!=null)
					{
						if(pcrs.isPass(human.getName(), bean.getKnowledge())){
							out.print("<td bgcolor='#AA999999' data-th='"+bean.getKnowledge()+"' >V</td>");
							pass[j]++;
						}
						else	
						out.print("<td bgcolor='#fca7af' data-th='"+bean.getKnowledge()+"' ></td>");		
						 
					}
					else if (bean.getTraining()!=null)
					{
						if(pcrs.isFinish(human.getName(), bean.getTraining()))
						{
							out.print("<td bgcolor='#AA999999' data-th='"+bean.getTraining()+"' >V</td>");
							pass[j]++;
						}
							else	
							out.print("<td bgcolor='#fca7af' data-th='"+bean.getTraining()+"'></td>");	
					}
 					j++;
				}
				out.print("</tr>");
 			 
			} 		 
			 
			%>
			<tr class="tr-only-hide"><td></td><td></td><td></td>
				<% for (int k=0;k<count;k++)
					out.print("<td>"+pass[k]+"/"+i+"</td>");
					%>
			</tr>	
			
			</tbody>
		</table>
 	</c:if>
</body>
</html>