<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/commonInclude.jsp" %>

<script type="text/javascript" src="/resources/adm/smarteditor/js/HuskyEZCreator.js"></script>
<script type="text/javaScript">
<!--
	$(document).ready(function() {
		var oEditors = [];
		nhn.husky.EZCreator.createInIFrame({
			oAppRef: oEditors,
			elPlaceHolder: "popupDc",
			sSkinURI: "/resources/adm/smarteditor/SmartEditor2Skin.html",
			fCreator: "createSEditor2",
			htParams: { 
				fOnBeforeUnload : function() {
				}
			}
		});
		$('#noticeDateStart').on('change', function() {
			$('#noticeStartDay').val($(this).val().replaceAll('-', ''));
		});
		$('#noticeDateEnd').on('change', function() {
			$('#noticeEndDay').val($(this).val().replaceAll('-', ''));
		});
		$('input[name=popupKind]').on('change', function() {
			if($('#form-2-2').prop('checked')) {
				$('#popupDc').next().height('350px');
				$('#poupKind1').hide();
				$('#poupKind2').show();
			} else {
				$('#poupKind1').show();
				$('#poupKind2').hide();
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
	function boardPopupRegistration() {
		if($('#popupNm').val() == '') {
			swal('제목을 입력해주세요.');
			$('#popupNm').focus();
			return false;
		}
		if($('#noticeDateStart').val() == '') {
			swal('시작일을 입력해주세요.');
			$('#noticeDateStart').focus();
			return false;
		}
		if($('#noticeDateEnd').val() == '') {
			swal('종료일을 입력해주세요.');
			$('#noticeDateEnd').focus();
			return false;
		}
		if($('#popupLocationLeft').val() == '') {
			swal('왼쪽 위치를 입력해주세요.');
			$('#popupLocationLeft').focus();
			return false;
		}
		if($('#popupLocationRight').val() == '') {
			swal('오른쪽 위치를 입력해주세요.');
			$('#popupLocationRight').focus();
			return false;
		}
		if($('#popupSizeWidth').val() == '') {
			swal('넓이를 입력해주세요.');
			$('#popupSizeWidth').focus();
			return false;
		}
		if($('#popupSizeHeight').val() == '') {
			swal('높이를 입력해주세요.');
			$('#popupSizeHeight').focus();
			return false;
		}
		if($('#form-2-2').prop('checked')) {
			oEditors.getById["popupDc"].exec("UPDATE_CONTENTS_FIELD", []);
			var tempPopupDc = $('#popupDc').val();
			if(tempPopupDc == '' || tempPopupDc == null || tempPopupDc == '&nbsp;' || tempPopupDc == '<p>&nbsp;</p>' || tempPopupDc == '<br>') {
				swal('HTML을 입력해주세요.');
				oEditors.getById["popupDc"].exec("FOCUS");
				return false;
			}
		} else {
			if($('#fileNameText').text() == '') {
				swal('이미지를 업로드해주세요.');
				$('#fileNameText').focus();
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

<form name="listForm" action="<c:url value='/adm/sst/boardPopupList.do'/>" method="post" onSubmit="fnSelectLinkPage(1); return false;">
	<input type="hidden" name="currentPageNo" value="<c:out value='${searchVO.currentPageNo}'/>">
</form>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="card">
				<div class="card-heading border bottom">
					<h2 class="card-title">팝업 관리</h2>
				</div>
				<div class="card-block">
					<form id="regForm" name="regForm" action="<c:url value='/adm/sst/boardPopupRegistration.do'/>" method="post" onSubmit="boardPopupRegistration(); return false;" enctype="multipart/form-data">
						<input type="hidden" id="boardSeq" name="boardSeq" value="2">
						<input type="hidden" id="noticeStartDay" name="noticeStartDay" value="">
						<input type="hidden" id="noticeEndDay" name="noticeEndDay" value="">
						<div class="row">
							<div class="col-md-12">
								<!--table-->
								<table class="modify-table">
									<caption>팝업 관리</caption>
									<colgroup>
										<col style="width:200px">
										<col>
									</colgroup>
									<tbody>
										<tr>
											<th scope="row">제목</th>
											<td>
												<input type="text" class="form-control" id="popupNm" name="popupNm">
											</td>
										</tr>
										<tr>
											<th scope="row">게시기간</th>
											<td>
												<div class="term">
													<div>
														<label for="noticeDateStart">시작일</label>
														<input type="date" class="form-control datepicker-1 date_st" id="noticeDateStart" name="noticeDateStart" placeholder="시작일" data-provide="datepicker" required>
													</div>
													<div>
														<label for="noticeDateEnd">종료일</label>
														<input type="date" class="form-control datepicker-2 date_end" id="noticeDateEnd" name="noticeDateEnd" placeholder="종료일" data-provide="datepicker" required>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<th scope="row">위치</th>
											<td>
												<div class="location">
													<div>
														<span>왼쪽<input type="text" class="form-control numberOnly text-right" id="popupLocationLeft" name="popupLocationLeft" placeholder="왼쪽" required>px</span>
													</div>
													<div>
														<span>오른쪽<input type="text" class="form-control numberOnly mrg-left-10 text-right" id="popupLocationRight" name="popupLocationRight" placeholder="오른쪽" required>px</span>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<th scope="row">크기</th>
											<td>
												<div class="size">
													<div>
														<span>넓이<input type="text" class="form-control numberOnly text-right" id="popupSizeWidth" name="popupSizeWidth" placeholder="넓이" required>px</span>
													</div>
													<div>
														<span>높이<input type="text" class="form-control numberOnly mrg-left-10 text-right" id="popupSizeHeight" name="popupSizeHeight" placeholder="높이" required>px</span>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<th scope="row">팝업종류</th>
											<td>
												<div class="Kinds">
													<div class="radio">
														<input type="radio" name="popupKind" id="form-2-1" value="1" checked="checked">
														<label for="form-2-1">이미지</label>
													</div>
													<div class="radio">
														<input type="radio" name="popupKind" id="form-2-2" value="2">
														<label for="form-2-2">HTML</label>
													</div>
												</div>
											</td>
										</tr>
										<tr id="poupKind1">
											<th scope="row">이미지 업로드</th>
											<td>
												<div class="filebox">
													<span class="input_file" id="fileNameText" name="fileNameText"></span>
													<input type="file" id="files" class="files" name="imgFile" file-type="attch" onchange="javascript:document.getElementById('fileNameText').innerHTML = this.value">
													<label for="files" class="btn_files">파일찾기</label>
												</div>
											</td>
										</tr>
										<tr id="poupKind2" style="display:none;">
											<th scope="row">HTML</th>
											<td>
												<textarea class="form-control" rows="3" id="popupDc" name="popupDc" style="width:100%;height:300px;"></textarea>
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