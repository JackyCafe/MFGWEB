<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>
<%@ page import="misc.*" %>
<%@ page import="model.dao.*" %>
<%@ page import="model.service.*" %>   
<%@ include file="header.jsp" %>
  
  <script type="text/javascript">
  	
  	function selectClass(){
  		var mclass = document.getElementById('classSel').value;
  		/*alert(mclass)
   		$.ajax({
  			url:"/MFGWEB/queryPersonRecord.jsp?mclass_="+mclass,
  			datatype:"html",
  			type:"get",
  			success:doSuccess,
  		//	error:doError
  		});	
  		*/
  		$.post({
  			url:"/MFGWEB/queryPersonRecord.jsp",
  			data:{mclass_:mclass},
  			datatype:"html",
  			 
  		}).done(function doSuccess(json) {
  			$('#personresult').html(json);
  		});
  	}
  	
  
  </script>
  
 
	 <%
		ClassInfoService service = new ClassInfoService(
				new ClassInfoDAO(HibernateUtil.getSessionFactory()));;
		List<String>  classNameList = service.getClassNameList();
		request.setAttribute("classNameList", classNameList);
	%>
	<c:choose>
		<c:when test="${account==null }">
			 		 <c:out value="請先登入"/>	
		</c:when>
		<c:when test="${account!=null }">
		 <div class='form-group row'>   
		 <div class="form-group col-md-2">    
			<select id='classSel'  class = 'form-control'
				 onChange='selectClass()' data-toggle="personresult"
				 aria-expanded="true" aria-controls="personresult"
				 >	
 				<c:forEach var ='className' items='${classNameList}' varStatus="loop" >
 				<c:choose>
 				<c:when test="${loop.index==2}">
 					<option selected="selected"><c:out value ='${className }'/></option>
 				</c:when>
				<c:otherwise> 	
				 	<option><c:out value ='${className }'/></option>
				</c:otherwise> 	
				</c:choose> 	
			</c:forEach>	
			</select>
		</div>	
		</div>
		</c:when>

</c:choose>
	
	<div id = 'personresult'>
	
	</div> 
	 
<%@ include file="footer.jsp" %>