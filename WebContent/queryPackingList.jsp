<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file='header.jsp'%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
 

<script type="text/javascript">



	$(document).ready(function() {
		$('.date').datepicker({
			dateFormat : "yy/mm/dd", //選用日期格式
			showButtonPanel : true
		}); //end of datepicker

		$('#confirm').click(function() {
			var start_date = $('#start_date').val() 
			var end_date = $('#end_date').val() 
			var lot_id = $('#lot_id').val()
			$.post({
				url : "/MFGWEB/packingReport.jsp",
				data : {
					start_date : start_date,
					end_date:end_date,
					lot_id:lot_id,
				},
				datatype : "html",
			}).done(function(json) {
				$('#report').html(json)
			})
		}); //end of confirm
		
	 
	 
		
	})
	var tableToExcel = (function() {
  var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()
</script>
<form>
	<div class='panel'>      <div class ='input-group'>
	<div class='form-group row'>
	<div class="form-group col-md-4">
		輸入起始日期:<input  class = 'date form-control' type='text' id='start_date'>
	</div><div class="form-group col-md-4">
		輸入結束日期:<input  class = 'date form-control' type='text' id='end_date'>
		</div> 
	 
	 <div class="form-group col-md-4">
		輸入批號<input  class = 'form-control' type='text'   id='lot_id'  placeholder='查詢批號(非必要)'>
		</div> 
	</div></div>		
		<div class="btn-group">		
		<input class="btn btn-primary"	type='button' id='confirm' value='confirm' >
		<input class="btn btn-primary"	type='button' id='export' value='Excel' onclick="tableToExcel('report', ' ')">
		</div>
		</div>
		
</form>
<div id='report'></div>

<%@ include file='footer.jsp'%>
