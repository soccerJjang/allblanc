<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/commonInclude.jsp" %>

<script type="text/javaScript">
<!--
	$(document).ready(function() {
		$('.btn-default.btn-sm').on('click', function() {
		  	document.listForm.action = "<c:url value='/adm/sst/boardDesignDeletion.do'/>";
		  	document.listForm.submit();
		});
		
		$('.btn-primary.btn-sm').on('click', function() {
			document.listForm.boardDesignSeq.value = 0;
		});
	});
	
	/*********************************************************
	 * 페이징 처리 함수
	 ******************************************************** */
	function fnSelectLinkPage(pageNo) {
		document.listForm.currentPageNo.value = pageNo;
	   	document.listForm.submit();
	}
	
	/*********************************************************
	 * 검색 처리 함수
	 ******************************************************** */
	function fnSeach() {
		document.listForm.currentPageNo.value = 1;
		document.listForm.searchKey.value = $('#searchSelectKey').val();
		document.listForm.searchValue.value = $('#searchSelectValue').val();
	   	document.listForm.submit();
	}
	
	/* ********************************************************
	 * 등록 처리 함수
	 ******************************************************** */
	function boardDesignRegistrationView() {
		document.listForm.action = "<c:url value='/adm/sst/boardDesignRegistrationView.do' />";
		document.listForm.submit();
	}
	
	/* ********************************************************
	 * 수정 처리 함수
	 ******************************************************** */
	function boardDesignModificationView(boardDesignSeq) {
		document.listForm.boardDesignSeq.value = boardDesignSeq;
	  	document.listForm.action = "<c:url value='/adm/sst/boardDesignModificationView.do'/>";
	  	document.listForm.submit();
	}
	
	/*********************************************************
	 * 삭제 처리 함수
	 ******************************************************** */
	function boardDesignDeletion(boardDesignSeq) {
		swal({
			title: "삭제하시겠습니까?",
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
				document.listForm.boardDesignSeq.value = boardDesignSeq;
			  	document.listForm.action = "<c:url value='/adm/sst/boardDesignDeletion.do'/>";
			  	document.listForm.submit();
			} else {
				swal("취소되었습니다.", "", "error");
			}
		});
		
	}
	
	/*********************************************************
	 *  정렬 처리 함수
	 ******************************************************** */
	function boardDesignOrdrUpdate(boardDesignSeq, sortOrdr, sortOrdrUpdateFlag) {
		document.listForm.boardDesignSeq.value = boardDesignSeq;
		document.listForm.sortOrdr.value = sortOrdr;
		document.listForm.sortOrdrUpdateFlag.value = sortOrdrUpdateFlag;
	  	document.listForm.action = "<c:url value='/adm/sst/boardDesignOrdrUpdate.do'/>";
	  	document.listForm.submit();
	}

	<c:if test="${!empty resultMsg}">swal("${resultMsg}");</c:if>
-->
</script>

<form name="listForm" action="<c:url value='/adm/sst/boardDesignList.do'/>" method="post" onSubmit="fnSelectLinkPage(1); return false;">
	<input type="hidden" name="currentPageNo" value="<c:out value='${searchVO.currentPageNo}'/>">
	<input type="hidden" name="searchKey" value="<c:out value='${searchVO.searchKey}'/>">
	<input type="hidden" name="searchValue" value="<c:out value='${searchVO.searchValue}'/>">
	<input type="hidden" name="boardDesignSeq" value="0">
	<input type="hidden" name="sortOrdr" value="0">
	<input type="hidden" name="sortOrdrUpdateFlag" value="">
</form>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="card">
				<div class="card-heading border bottom">
					<h2 class="card-title">디자인 설정 관리</h2>
				</div>
				<div class="card-block">
					<div class="table-overflow">
						<div class="search_box">
							<select id="searchSelectKey" name="searchSelectKey">
								<option value="">전체</option>
								<option value="boardSj"<c:if test="${searchVO.searchKey eq 'boardSj' }"> selected="selected"</c:if>>제목</option>
								<option value="boardCn"<c:if test="${searchVO.searchKey eq 'boardCn' }"> selected="selected"</c:if>>내용</option>
							</select>
							<input type="text" id="searchSelectValue" name="searchSelectValue" placeholder="검색어를 입력해주세요." value="${searchVO.searchValue }" onKeypress="javascript:if(event.keyCode==13) {fnSeach();}">
							<a href="#n" onclick="javascript:fnSeach();">검색</a>
						</div>
						<table class="table table-lg table-hover col-md-12">
							<caption>디자인 설정 관리</caption>
							<colgroup>
								<col style="width: 5%;">
								<col style="width: 20%;">
								<col style="width: 10%;">
								<col style="width: 45%;">
								<col style="width: 10%;">
								<col style="width: 10%;">
							</colgroup>
							<thead>
								<tr>
									<th>번호</th>
									<th>썸네일 이미지</th>
									<th>타입</th>
									<th>문구</th>
									<th>편집</th>
									<th>정렬</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
									<tr>
										<td>
											<span><c:out value="${totalCnt - ((searchVO.currentPageNo-1) * resultInfo.pageSize + status.index)}"/></span>
										</td>
										<td>
											<div class="list-info">
												<img onerror="/resources/adm/images/avatars/thumb-1.jpg" src="/upload/${resultInfo.filePath }${resultInfo.newFileNm }" alt="썸네일 이미지">
											</div>
										</td>
										<td>
											<c:choose>
												<c:when test="${resultInfo.categorySeq eq 8 }"><span>영상</span></c:when>
												<c:otherwise><span>이미지</span></c:otherwise>
											</c:choose>
										</td>
										<td>
											<c:choose>
												<c:when test="${resultInfo.categorySeq eq 8 }"><a href="#n" onclick="javascript:boardDesignModificationView('${resultInfo.boardDesignSeq }')">영상 등록시 텍스트 문구 비활성화</a></c:when>
												<c:otherwise><a href="#n" onclick="javascript:boardDesignModificationView('${resultInfo.boardDesignSeq }')"><c:out value="${resultInfo.boardCn}"/></a></c:otherwise>
											</c:choose>
										</td>
										<td>
											<div class="mrg-top-15">
												<a href="#n" class="btn btn-default edit_bt" onclick="javascript:boardDesignModificationView('${resultInfo.boardDesignSeq }')">수정</a>
												<a href="#n" class="btn btn-default edit_bt" onclick="javascript:boardDesignDeletion('${resultInfo.boardDesignSeq }')">삭제</a>
											</div>
										</td>
										<td>
											<div class="mrg-top-15">
												<a href="#n" class="btn btn-default edit_bt" onclick="javascript:boardDesignOrdrUpdate('${resultInfo.boardDesignSeq}', '${resultInfo.sortOrdr}', 'Y')">위로</a>
												<a href="#n" class="btn btn-default edit_bt" onclick="javascript:boardDesignOrdrUpdate('${resultInfo.boardDesignSeq}', '${resultInfo.sortOrdr}', 'N')">아래로</a>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- paging -->
					<div class="paging">
						<ui:pagination paginationInfo="${paginationInfo}" type="admin" jsFunction="fnSelectLinkPage"/>
					</div>
					<!-- //paging -->
					<a href="#n" class="btn btn-primary submit" onclick="javascript:boardDesignRegistrationView()">
						<span>등록하기</span>
					</a>
				</div>
				<div class="modal fade show" id="default-modal">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-body">
								<p>해당 게시물을 삭제 하시겠습니까?</p>
							</div>
							<div class="modal-footer no-border">
								<div class="text-right">
									<button class="btn btn-default btn-sm" data-dismiss="modal">예</button>
									<button class="btn btn-primary btn-sm" data-dismiss="modal">아니오</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
