<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<style>
.onoffswitch {
	position: relative;
	width: 100px;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
}

.onoffswitch-checkbox {
	display: none;
}

.onoffswitch-label {
	display: block;
	overflow: hidden;
	cursor: pointer;
	border: 2px solid #999999;
	border-radius: 20px;
}

.onoffswitch-inner {
	display: block;
	width: 200%;
	margin-left: -100%;
	transition: margin 0.3s ease-in 0s;
}

.onoffswitch-inner:before, .onoffswitch-inner:after {
	display: block;
	float: left;
	width: 50%;
	height: 30px;
	padding: 0;
	line-height: 30px;
	font-size: 14px;
	color: white;
	font-family: Trebuchet, Arial, sans-serif;
	font-weight: bold;
	box-sizing: border-box;
}

.onoffswitch-inner:before {
	content: "單一爐次";
	padding-left: 10px;
	background-color: #34A7C1;
	color: #FFFFFF;
}

.onoffswitch-inner:after {
	content: "混爐";
	padding-right: 10px;
	background-color: #EEEEEE;
	color: #999999;
	text-align: right;
}

.onoffswitch-switch {
	display: block;
	width: 18px;
	margin: 6px;
	background: #FFFFFF;
	position: absolute;
	top: 0;
	bottom: 0;
	right: 56px;
	border: 2px solid #999999;
	border-radius: 20px;
	transition: all 0.3s ease-in 0s;
}

.onoffswitch-checkbox:checked+.onoffswitch-label .onoffswitch-inner {
	margin-left: 0;
}

.onoffswitch-checkbox:checked+.onoffswitch-label .onoffswitch-switch {
	right: 0px;
}
</style>
<%@include file="header.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {

		// isSingle 為true /false 的型態
		// 單一爐次為true 
		var isSingle
		$('#singleLot').click(function() {
			isSingle = $("input[name='onoffswitch']").is(":checked");
 			if(isSingle==false)
			{
				var lot_id = $('#reactor_Lot1').val()
				$('#reactor_Lot2').val("")
				$('#reactor_Lot3').val("")
			}

		}

		);

		//check lot
		function checklot(lot) {
			var respData = $.ajax({
				async : true,
				type : "POST",
				url : "/MFGWEB/validation",
				data : {
					lots : lot
				},
			})
			respData.done(function(respData) {
				if (respData == "true")
					alert('資料庫中已有此袋，請洽領班');
				console.log(respData)
			});

		}

		$('#reactor_Lot1').keypress(function(e) {
			if (e.which == 13) {
				isSingle = $("input[name='onoffswitch']").is(":checked");
				if(isSingle == true)
				{
					lot_id = $('#reactor_Lot1').val()
					$('#reactor_Lot2').val(lot_id)
					$('#reactor_Lot3').val(lot_id)
				}else{
					$('#reactor_Lot2').val("")
					$('#reactor_Lot3').val("")
				}
 				$('#lot1').focus()
				e.preventDefault();
			}
			
			
		});

		$('#lot1').keypress(function(e) {
			if (e.which == 13) {
				//ajax
				if ($('#lot1').val().substr(0, 1) != 'B') {
					alert('袋號異常，請確認');
					$('#lot1').val("")
					$('#lot1').focus();
					e.preventDefault();

				} else {
					checklot($('#lot1').val());
					e.preventDefault();
					$('#product1').focus()
				}

			}
		})

		$("#product1").keypress(function(e) {
			if (e.which == 13) {
				e.preventDefault();
				$("#lot2").focus();
			}
		}) //end of product1

		$('#lot2').keypress(function(e) {
			if (e.which == 13) {

				if ($('#lot2').val().substr(0, 1) != 'B') {
					alert('袋號異常，請確認');
					$('#lot2').val("")
					$('#lot2').focus();
					e.preventDefault();

				} else {
					checklot($('#lot2').val());
					e.preventDefault();
					lot1 = $('#lot1').val()
					lot2 = $('#lot2').val()
					if (lot1 == lot2) {
						alert('批號重複，請確認')
					} else {

						$('#product2').focus()
					}
				}
			}
		})

		$("#product2").keypress(function(e) {
			if (e.which == 13) {
				e.preventDefault();

				prod1 = $('#product1').val()
				prod2 = $('#product2').val()

				if (prod1 == prod2) {
					$("#lot3").focus();
				} else {
					alert('注意，產品名稱不同。請再確認')
					$('#product2').val("")
					$('#lot2').val("")
					$("#lot2").focus();
				}
			}

		}) //end of product2

		$('#lot3').keypress(function(e) {
			if (e.which == 13) {
				e.preventDefault();

				if ($('#lot3').val().substr(0, 1) != 'B') {
					alert('袋號異常，請確認');
					$('#lot3').val("")
					$('#lot3').focus();
					e.preventDefault();

				} else {
					checklot($('#lot3').val());

					lot1 = $('#lot1').val()
					lot2 = $('#lot2').val()
					lot3 = $('#lot3').val()
					if (lot1 == lot3 || lot2 == lot3) {
						alert('批號重複，請確認')
					} else {

						$('#product3').focus()
					}
				}
			}
		})

		$("#product3").keypress(function(e) {
			if (e.which == 13) {
				e.preventDefault();
				prod1 = $('#product1').val()
				prod2 = $('#product3').val()
				if (prod1 == prod2) {
					$("#box").focus();
				} else {
					alert('注意，產品名稱不同。請再確認')
					$('#product3').val("")
					$('#lot3').val("")
					$("#lot3").focus();
				}
			}
		})
		//end of product3

		$('#box').keypress(function(e) {
			box = $('#box').val()
			if (e.which == 13) {
				if (box.substr(0, 1) != 'C' || box.length < 9) {
					alert('箱號異常，請確認');
					$('#box').val("");
					$('#box').focus();
					e.preventDefault();
				}
			}
		})

		$('#clear').click(function() {
			$('#lot1').val("")
			$('#lot2').val("")
			$('#lot3').val("")
			$('#product1').val("")
			$('#product2').val("")
			$('#product3').val("")
			$('#box').val("")
		})

		function validateForm() {
			var x = $('#box').val()
			if (x == "") {
				alert("Name must be filled out");
				return false;
			}
		}
	}) //end of document ready
</script>
<c:choose>
	<c:when test="${account.permission == null }">
		<c:out value="請先登入" />
	</c:when>

	<c:when
		test="${(account.permission == 'A')||(account.permission == 'B')  }">
	<div class="container-fluid">
		<div class='panel'>      <div class ='input-group'>
		<form action='Package.process' method='post'
			onsubmit="return validateForm() ">

			<input type='hidden' name="name" value="${account.name }">
		<div class='form-group row'>       	
			<div class="onoffswitch">
				<input type="checkbox" name="onoffswitch"
					class="onoffswitch-checkbox" id="singleLot" checked> <label
					class="onoffswitch-label" for="singleLot"> <span
					class="onoffswitch-inner"></span> <span class="onoffswitch-switch"></span>
				</label>
			</div>
		</div>	 

			<p>
			<div class='form-group row'>       	
			<div class="form-group col-md-3">
				輸入CVD 批號 <input class = 'form-control' type='text' id='reactor_Lot1' name='reactor_Lot1'
					value="${reactor_Lot}" required /></div> 
			<div class="form-group col-md-3">	袋號 1 <input class = 'form-control' type='text' id='lot1' name='lot1' autofocus /></div> 
			<div class="form-group col-md-3">	產品 1 <input class = 'form-control' type='text'	id='product1' name='product1' /> </div>
			<div class="form-group col-md-3">	重量 1 <input class = 'form-control' type='text'	id='weight1' name='weight1' value='10' /></div>
			</div>		
			<p>
			<div class='form-group row'>       	
			<div class="form-group col-md-3">
				輸入CVD 批號 <input type='text' class = 'form-control'  id='reactor_Lot2' name='reactor_Lot2'	value="${reactor_Lot}" required /></div> 
			<div class="form-group col-md-3">袋號 2 <input class = 'form-control'  type='text' id='lot2' name='lot2' autofocus /> </div>
			<div class="form-group col-md-3">產品 2 <input class = 'form-control'  type='text' id='product2' name='product2' /></div>
			 <div class="form-group col-md-3">重量 2 <input class = 'form-control'  type='text' id='weight2' name='weight2' value='10' /></div>
			</div>		
			<p>
			<div class='form-group row'>      
			<div class="form-group col-md-3">
			輸入CVD 批號 <input type='text' class = 'form-control'  id='reactor_Lot3' name='reactor_Lot3'	value="${reactor_Lot}" required /> </div>
				<div class="form-group col-md-3"> 袋號 3 <input type='text' class = 'form-control' id='lot3' name='lot3' autofocus /> </div> 
				<div class="form-group col-md-3"> 產品 3 <input type='text' class = 'form-control' id='product3' name='product3' /> </div>
				<div class="form-group col-md-3"> 重量 3 <input type='text' class = 'form-control' id='weight3' name='weight3' value='10' /></div>
			<p>
			</div>
			<div class='form-group row'>      
			<div class="form-group col-md-2">
				箱號 <input class = 'form-control'  type='text' id='box' name='box'required ">
			</div></div>	
			<p>
			<p>
				<input class="btn btn-primary" type='submit' id='action' name='action' value='add'>
				<input class="btn btn-primary" type='button' id='clear' name='clear' value='clear'>
		</form>

		</div></div>
	</div>

	</c:when>

</c:choose>


<%@include file="footer.jsp"%>
