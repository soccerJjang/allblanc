<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/commonInclude.jsp" %>

<script type="text/javaScript">
<!--
	$(document).ready(function() {
		$('#categorySeq').on('change', function() {
			if($('#categorySeq').val() == '8') {
				$('#trVideoUrl').show();
			} else {
				$('#trVideoUrl').hide();
			}
		});
	});
	
	/*********************************************************
	 * 목록 처리 함수
	 ******************************************************** */
	function fnSelectLinkPage(pageNo) {
	   	document.listForm.submit();
	}
	
	/* ********************************************************
	 * 등록 처리 함수
	 ******************************************************** */
	function boardDesignRegistration() {
		if($('#boardSj').val() == '') {
			swal('제목을 입력해주세요.');
			$('#boardSj').focus();
			return false;
		}
		if($('#boardCn').val() == '') {
			swal('텍스트를 입력해주세요.');
			$('#boardCn').focus();
			return false;
		}
		if($('#fileNameText').text() == '') {
			swal('첨부파일을 추가해주세요.');
			$('#fileNameText').focus();
			return false;
		}
		if($('#categorySeq').val() == '8') {
			if($('#videoUrl').val() == '') {
				swal('영상 URL을 입력해주세요.');
				$('#videoUrl').focus();
				return false;
			}
		}
		
		swal({
			title: "등록하시겠습니까?",
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

<form name="listForm" action="<c:url value='/adm/sst/boardDesignList.do'/>" method="post" onSubmit="fnSelectLinkPage(1); return false;">
	<input type="hidden" name="currentPageNo" value="<c:out value='${searchVO.currentPageNo}'/>">
</form>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="card">
				<div class="card-heading border bottom">
					<h2 class="card-title">디자인 설정 관리</h2>
				</div>
				<div class="card-block">
					<form id="regForm" name="regForm" action="<c:url value='/adm/sst/boardDesignRegistration.do'/>" method="post" onSubmit="boardDesignRegistration(); return false;" enctype="multipart/form-data">
						<input type="hidden" id="boardSeq" name="boardSeq" value="1">
						<div class="row">
							<div class="col-md-12">
								<!--table-->
								<table class="modify-table">
									<caption>디자인 설정 관리</caption>
									<colgroup>
										<col style="width:200px">
										<col>
									</colgroup>
									<tbody>
										<tr>
											<th scope="row">구분</th>
											<td>
												<select id="categorySeq" name="categorySeq" class="file_type">
													<option value="7">이미지</option>
													<option value="8">영상</option>
												</select>
											</td>
										</tr>
										<tr>
											<th scope="row">제목</th>
											<td>
												<input type="text" title="제목" class="form-control" id="boardSj" name="boardSj">
											</td>
										</tr>
										<tr id="trVideoUrl" style="display:none;">
											<th scope="row">영상 URL</th>
											<td>
												<input type="text" title="영상 URL" class="form-control" id="videoUrl" name="videoUrl">
											</td>
										</tr>
										<tr>
											<th scope="row">텍스트</th>
											<td>
												<textarea class="form-control" rows="3" id="boardCn" name="boardCn"></textarea>
											</td>
										</tr>
										<tr>
											<th scope="row">첨부파일</th>
											<td>
												<div class="filebox">
													<span class="input_file" id="fileNameText" name="fileNameText"></span>
													<input type="file" id="files" class="files" name="imgFile" file-type="attch" onchange="javascript:document.getElementById('fileNameText').innerHTML = this.value">
													<label for="files" class="btn_files">파일찾기</label>
												</div>
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