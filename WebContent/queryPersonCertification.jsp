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
<!-- 由PersonCertificationInfo.jsp 呼叫ajax 的檔案。主要用來看
	 用來維護person_certification_info 檔案
 -->
 	 
 	<%
		String area = request.getParameter("area");
		String shift = request.getParameter("shift");
		
		/*載入人員資料*/
 		HumanInfoService his = new HumanInfoService(new HumanInfoDAO(HibernateUtil.getSessionFactory()));
		List<HumanInfoBean> hib = his.queryByAreaShift(area, shift); //0 
		request.setAttribute("hib", hib);
  		// 載入Service
		CertificationItemService cis = new CertificationItemService(
				new CertificationItemDAO(HibernateUtil.getSessionFactory()));
		
		/*載入學術科認證資料*/
		List<CertificationItemBean> certifications = cis.queryNonTraining(area);
		request.setAttribute("certifications", certifications);
		
 		int nonTrainingSize = certifications.size();
		
		/*載入只訓練不認證科目*/
		List<CertificationItemBean> trainingCertifications = cis.queryTraining(area);
		request.setAttribute("trainingCertifications", trainingCertifications); 
  		int trainingSize = trainingCertifications.size();
  		
 		
		/* 人員認證	*/
		PersonCertificationInfoService pcis = new PersonCertificationInfoService(
				new PersonCertificationInfoDAO(HibernateUtil.getSessionFactory()));
		
		 
 		
	%>
 	<a href='reloadCertification?area=<%=area %>&shift=<%=shift %>'  class="button">整理</a>
	
	<c:choose>
		<c:when test="${certifications!=null }">
			<table>
				<!-- 表頭 -->
				<tr>
					<th rowspan="2">工號</th>
					<th rowspan="2">姓名</th>
					<%
						/* 標題 學術科先來 */
						for (int i = 0; i < nonTrainingSize; i++) {
									out.print("<th>學科/術科/訓練</th>");
									//out.print("<th>術科</th>");
						}
						
						//換只訓練不認證
						for (int i = 0; i < trainingSize ; i++ )
						{
							out.print("<th>訓練</th>");
 						}
					
						/*次標題*/
						out.print("<tr>");
						
						/*學術科*/
						for (CertificationItemBean bean : certifications) {
							out.print("<th>" + bean.getKnowledge() + "</th>");
 						}
					
						/*只訓練不認證標題*/
						for (CertificationItemBean bean : trainingCertifications) {
							out.print("<th>" + bean.getTraining() + "</th>");
 						}
								
						out.print("</tr>");
					%>
				</tr>
				<!-- 載入人名及工號產生學科、術科認證資料 -->
				<c:forEach var="human" items="${hib }">
					<tr>
						<td><c:out value="${human.workNum } " /></td>
						<td><c:out value="${human.name } " /></td>
						<!-- 學科、術科、對應人名及id -->
						<c:set var="name" value="${human.name}" />			
					
						<%
							for ( CertificationItemBean certification:certifications  )
							{
								String name = (String)pageContext.getAttribute("name");
								String knowledge = certification.getKnowledge(); //學科
								String technical = certification.getTechnical(); //術科
								
								
								boolean isKnowledgeExist = pcis.isKnowledgeExist(name, knowledge);
  								int idKnowledge = pcis.findKnowledgeId(name, knowledge);
  								if (isKnowledgeExist){
  									out.print("<td bgcolor=''#fff'><a href='addCertificationRecord.jsp?id="+idKnowledge+"'>"+idKnowledge+"</a></td>");
 								}
 								else
 									out.print(" <td bgcolor='#fca7d6'>X</td>"); 
 								
 							}
							for ( CertificationItemBean certification:trainingCertifications  )
							{
								String name = (String)pageContext.getAttribute("name");
								String training = certification.getTraining();
								boolean isTrainingExist = pcis.isTrainingExist(name, training);
								int idTraining = pcis.findTrainingId(name, training);
								if (isTrainingExist){
  									out.print("<td bgcolor=''#fff'><a href='addCertificationRecord.jsp?id="+idTraining+"'>"+idTraining+"</a></td>");
 								}
 								else
 									out.print(" <td bgcolor='#fca7d6'>X</td>"); 
								
							}
						
							 
						%> 
					</tr>
				</c:forEach>


			</table>
		</c:when>




	</c:choose>



</body>
</html>