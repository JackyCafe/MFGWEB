<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="model.*"%>
<%@ page import="misc.*"%>
<%@ page import="model.dao.*"%>
<%@ page import="model.service.*"%>
<%@include file="header.jsp"%>

<!-- 認證紀錄查詢，by 區、班別、科目 查詢各班各科成績 主要是透過ajax 將參數
  	   傳遞給queryPersonCertificationRecord.jsp 查詢	
  -->
<!--以ajax 拋資料給  -->
<script>
	$(document).ready(function() {
		/*班別下拉式選單變動，以area 為參數向queryCertification 撈取各區應認證科目*/
		$("#shift").click(function() {
			var area = $("#area").val()
			$.post({
				url : "/MFGWEB/queryCertification.jsp",
				data : {
					area : area
				},
			}).done(function(json) {
				$('#certificationList').html(json);
			});
		}); //end of shift click

 
		$('#send').click(selectClass); //when send click

	}); //end of $()   

	 
	
	
	
	/*20181227 button 按下 send 後，會以area，shift 為參數
	  發送post 方法給queryPersonCertificationRecord.jsp 查詢 各區各班認證資料
	 */
	function selectClass() {
		var area = $('#area').val()
		var shift = $('#shift').val()
		var certification = $('#certificationList').val()
		$.ajax({
			type:"POST",
			url : "/MFGWEB/queryPersonCertificationRecord.jsp",			
			data : {
				area : area,
				shift : shift,
				certification : certification,
			},
		}).done(function(json) {	
 			$('#certificationRecordResult').html(json);
		}) 
	} //end of selectClass
</script>


 	<c:choose>
		<c:when test="${account==null }">
			<c:out value="請先登入" />
		</c:when>

		<c:when test="${account!=null }">
			<!-- 功能表單 -->
  			
			<div class="form-group col-md-2">
			<label for='area'>
			   選擇區域:</label>
			    <select id='area' name='area' class = 'form-control' >
				<option>PULLER</option>
				<option>FILAMENT</option>
				<option>ETCH</option>
				<option>CVD</option>
				<option>PH</option>
			</select>
			 </div>
			 <div class="form-group col-md-2">
			 <label for='shift'>
				選擇班別:</label><select id='shift' name='shift' class = 'form-control' >
					<option>ALL</option>
					<option>NA</option>
					<option>NB</option>
					<option>DA</option>
					<option>DB</option>
				</select>
			</div>
			<div class="form-group col-md-2"  >
			
 				<label for='certificationList'>認證課程 </label>
					<select id='certificationList' class = 'form-control' >
					<option></option>
				</select>
 			</div>
 			 <div class="form-group row">
    			<div class="col-sm-10">
   			<input type='button' id='send' value='確定' class = 'btn btn-primary' />   			 
 			<input type='button' id='export' value='excel'  class = 'btn btn-primary' />
 			</div></div>
  		</c:when>
	</c:choose>

<div id='certificationRecordResult'></div>

</body>


