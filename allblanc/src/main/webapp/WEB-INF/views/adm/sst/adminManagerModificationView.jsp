<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/commonInclude.jsp" %>

<script type="text/javaScript">
<!--
	$(document).ready(function() {
	});
	
	/*********************************************************
	 * 목록 처리 함수
	 ******************************************************** */
	function fnSelectLinkPage(pageNo) {
	   	document.listForm.submit();
	}
	
	/* ********************************************************
	 * 수정 처리 함수
	 ******************************************************** */
	function adminManagerModification() {
		if($('#adminDivision').val() == '') {
			swal('구분을 선택하세요.');
			$('#adminDivision').focus();
			return false;
		}
		if($('#adminId').val() == '') {
			swal('관리자 ID를 입력하세요.');
			$('#adminId').focus();
			return false;
		}
		if($('#adminNm').val() == '') {
			swal('관리자 이름을 입력하세요.');
			$('#adminNm').focus();
			return false;
		}
		
		swal({
			title: "수정하시겠습니까?",
			text: "",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "예",
			cancelButtonText: "아니오",
			loseOnConfirm: false,
			closeOnCancel: false
		}, function(isConfirm) {
			if (isConfirm) {
				document.regForm.submit();
			} else {
				swal("취소되었습니다.", "", "error");
			}
		});
	}

	<c:if test="${!empty resultMsg}">swal("<spring:message code="${resultMsg}" />");</c:if>
-->
</script>

<form name="listForm" action="<c:url value='/adm/sst/adminManagerList.do'/>" method="post" onSubmit="fnSelectLinkPage(1); return false;">
	<input type="hidden" name="currentPageNo" value="<c:out value='${searchVO.currentPageNo}'/>">
</form>

<div class="container-fluid">
	<div class="page-title">
		<h4></h4>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="card">
				<div class="card-heading border bottom">
					<h2 class="card-title">관리자 관리</h2>
				</div>
				<div class="card-block">
					<form id="regForm" name="regForm" action="<c:url value='/adm/sst/adminManagerModification.do'/>" method="post" onSubmit="adminManagerModification(); return false;">
						<input type="hidden" id="boardSeq" name="boardSeq" value="1">
						<input type="hidden" id="adminSeq" name="adminSeq" value="${adminManagerDetail.adminSeq }">
						<div class="row">
							<div class="col-md-12">
								<!--table-->
								<table class="modify-table">
									<caption>관리자 관리</caption>
									<colgroup>
										<col style="width:200px;">
										<col>
									</colgroup>
									<tbody>
										<tr>
											<th scope="row">구분</th>
											<td>
												<select id="adminDivision" name="adminDivision" class="w200">
													<option value="1"<c:if test="${adminManagerDetail.adminDivision eq 1 }"> selected="selected"</c:if>>슈퍼관리자</option>
													<option value="2"<c:if test="${adminManagerDetail.adminDivision eq 2 }"> selected="selected"</c:if>>일반관리자</option>
												</select>
											</td>
										</tr>
										<tr>
											<th scope="row">관리자 ID</th>
											<td>
												<input type="text" class="form-control" id="adminId" name="adminId" value="${adminManagerDetail.adminId }">
											</td>
										</tr>
										<tr>
											<th scope="row">관리자 이름</th>
											<td>
												<input type="text" class="form-control" id="adminNm" name="adminNm" value="${adminManagerDetail.adminNm }">
											</td>
										</tr>
										<tr>
											<th scope="row">관리자 패스워드</th>
											<td>
												<input type="password" class="form-control" id="adminPswd" name="adminPswd" value="${adminManagerDetail.adminPswd }">
											</td>
										</tr>
									</tbody>
								</table>
								<div class="text-right">
									<button class="btn btn-default" onclick="javascript:fnSelectLinkPage(1); return false;">목록</button>
									<button type="submit" class="btn btn-primary">등록하기</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>