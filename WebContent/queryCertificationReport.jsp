<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="header.jsp"%>
<Script>
	$(document).ready(function() {
		
		$('#shift').click(function(){
			var area = $('#area').val()
			var shift = $('#shift').val()
			 
			$.ajax({
				type:"POST",
				url:"/MFGWEB/certificationReport.jsp",
	  			data:{area:area,shift:shift},
	  			datatype:"html",
	  			 
	  		}).done(function doSuccess(json) {
	  			$('#report').html(json);
	  		});
			
			
		}); //end of ('#shift').click		
		 
		 
	}
	
	
	);
	 
</Script>


<!-- 驗證身分 -->
<c:if test="${account !=null }">
	<c:if test="${account.permission =='A' }">
		<form>
 		 <div class="form-group col-md-2">
			選擇區域: <Select id='area' name='area' class = 'form-control'  >
				<option>ALL</option>
				<option>PULLER</option>
				<option>FILAMENT</option>
				<option>ETCH</option>
				<option>CVD</option>
				<option>PH</option>
			</Select>
			</div> 
			 <div class="form-group col-md-2">
			選擇班別 <Select id='shift' name='shift' class = 'form-control'>
					<option>ALL</option>
					<option>DA</option>
					<option>NA</option>
					<option>DB</option>
					<option>NB</option>
				</Select>
				</div>
 		</form>
		<div id='report'></div>
	</c:if>
</c:if>


<%@ include file="footer.jsp"%>

