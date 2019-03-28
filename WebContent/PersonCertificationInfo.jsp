<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>
<%@ page import="misc.*" %>
<%@ page import="model.dao.*" %>
<%@ page import="model.service.*" %>   
<%@ include file="header.jsp"%>
 
  

  <script type="text/javascript">
  $(document).ready(function(){		  
		  $('#send').click(selectClass)}
  );
   
  
  function selectClass(){
	   var area = $('#area').val()
	   var shift =$('#shift').val()
	   $.post({
  			url:"/MFGWEB/queryPersonCertification.jsp",
  			data:{area:area,shift:shift},
  			
   		}).done(function (json)
  		  {
  			$('#certificationResult').html(json);  
  		  });	   
  }
  
  
  </script>	
  
	
	 
	<c:choose>
		<c:when test="${account==null }">
			 		 <c:out value="請先登入"/>	
		</c:when>
		<c:when test="${account!=null }">
			   選擇區域: <select id='area' name='area'>
 	   	   	<option>PULLER</option>
	   	   	<option>FILAMENT</option>
	   	   	<option>ETCH</option>
	   	   	<option>CVD</option>
	   	   	<option>PH</option>   	
	   </select><p>
	   選擇班別:<select id = 'shift' name='shift'>
	   		<option>NA</option>
	   		<option>NB</option>
	   		<option>DA</option>
	   		<option>DB</option>
	   </select><p>
	 <input type='button' id='send' value='確定' />	   
		</c:when>

</c:choose>
 
 <div id='certificationResult'>
</div>

<%@ include file="footer.jsp"%>


 