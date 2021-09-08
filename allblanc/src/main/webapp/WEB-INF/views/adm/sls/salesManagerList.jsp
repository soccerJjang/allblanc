<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/commonInclude.jsp" %>

<script type="text/javaScript">
<!--
	$(document).ready(function() {
		var date = new Date();
	    var d = date.getDate();
	    var m = date.getMonth();
	    var y = date.getFullYear();
	    var events = [{
			    title: '매출 : 3,000,000원',
	        	start: new Date(y, m, 1),
	        	desc:'Meetings',
	        	url: 'javascript:openPopup()',
	        	bullet: 'success' 
        	},
			{
			    title: '매출 : 5,000,000원',
	        	start: new Date(y, m, 2),
	        	desc:'Meetings',
	        	url: 'javascript:openPopup()',
	        	bullet: 'success' 
        	},
			{
			    title: '매출 : 6,000,000원',
	        	start: new Date(y, m, 5),
	        	desc:'Hangouts',
	        	url: 'javascript:openPopup()',
	        	bullet: 'success' 
        	},
	    ];
	    
	    $('#full-calendar').fullCalendar({
	    	height: 800,
	    	editable: true,
	    	header:{
	    		left: 'month,agendaWeek,agendaDay',
	    		center: 'title',
	    		right: 'today prev,next'
	    	},
	    	events: events
	    });
	    
	    $('#calendar-edit').on('click', '.btn-success', function() {
	    	$('body').removeClass('modal-open');
			$('body').css('padding-right', '');
			$('#calendar-edit').removeClass('show');
			$('#calendar-edit').css('display', 'none');
			$('#calendar-edit').css('padding-right', '');
			$('#calendar-edit').attr('aria-hidden', 'true');
			$('body .modal-backdrop').remove();
	    });
	});
	
	/*********************************************************
	 * 팝업 처리 함수
	 ******************************************************** */
	function openPopup() {
		$('body').addClass('modal-open');
		$('body').css('padding-right', '16px');
		$('#calendar-edit').addClass('show');
		$('#calendar-edit').css('display', 'block');
		$('#calendar-edit').css('padding-right', '16px');
		$('body').append('<div class="modal-backdrop fade show"></div>');
	}
	
	/*********************************************************
	 * 페이징 처리 함수
	 ******************************************************** */
	function fnSelectLinkPage(pageNo) {
		document.listForm.currentPageNo.value = pageNo;
	   	document.listForm.submit();
	}

	<c:if test="${!empty resultMsg}">swal("<spring:message code="${resultMsg}" />");</c:if>
-->
</script>

<form name="listForm" action="<c:url value='/adm/prd/boardCommonList.do'/>" method="post" onSubmit="fnSelectLinkPage(1); return false;">
	<input type="hidden" name="currentPageNo" value="<c:out value='${searchVO.currentPageNo}'/>">
	<input type="hidden" name="boardCommonSeq" value="0">
	<input type="hidden" name="sortOrdr" value="0">
	<input type="hidden" name="sortOrdrUpdateFlag" value="">
</form>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="card">
				<div class="card-heading border bottom">
					<h2 class="card-title">매출 관리</h2>
				</div>
				<div class="card-block">
						<div class="row">
							<div class="col-md-12">
								<div class="calendar_option">
									<div class="file_download">
										<a href="#n">엑셀 다운로드 (년)</a>
										<a href="#n">엑셀 다운로드 (월)</a>
									</div>
								</div>
								<div id='full-calendar'></div>
							</div>
						</div>
					</div>
					
				</div>
			</div>
		</div>
		<div class="modal fade" id="calendar-edit">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="border btm padding-15 ">
						<h4 class="no-mrg">2021년 07월 02일(금요일)</h4>
						<div class="text-right">
							<button class="btn btn-success" data-dismiss="modal">닫기</button>
						</div>
					</div>
					<div class="modal-body">
						<form>
							<table>
								<caption>하루 상세 매출</caption>
								<tbody>
									<tr>
										<th>제품 A</th>
										<td>10개</td>
										<td class="text-right">30,000원</td>
									</tr>
									<tr>
										<th>제품 B</th>
										<td>100개</td>
										<td class="text-right">300,000원</td>
									</tr>
									<tr>
										<th>제품 C</th>
										<td>100개</td>
										<td class="text-right">2,000,000원</td>
									</tr>
									<tr>
										<th>총합</th>
										<td>1,110개</td>
										<td class="text-right">2,330,000원</td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- page plugins js -->
<script src="/resources/adm/vendors/moment/min/moment.min.js"></script>
<script src="/resources/adm/vendors/fullcalendar/dist/fullcalendar.min.js"></script>
<script src="/resources/adm/vendors/fullcalendar/dist/gcal.js"></script>
<script src="/resources/adm/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
<script src="/resources/adm/vendors/bootstrap-datepicker/dist/js/bootstrap-datepicker.js"></script>
<script src="/resources/adm/vendors/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>

