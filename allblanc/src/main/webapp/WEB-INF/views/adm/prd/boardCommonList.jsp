<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/commonInclude.jsp" %>

<script type="text/javaScript">
<!--
	$(document).ready(function() {
		$('.btn-default.btn-sm').on('click', function() {
		  	document.listForm.action = "<c:url value='/adm/prd/boardCommonDeletion.do'/>";
		  	document.listForm.submit();
		});
		
		$('.btn-primary.btn-sm').on('click', function() {
			document.listForm.boardCommonSeq.value = 0;
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
	function boardCommonRegistrationView() {
		document.listForm.action = "<c:url value='/adm/prd/boardCommonRegistrationView.do' />";
		document.listForm.submit();
	}
	
	/* ********************************************************
	 * 수정 처리 함수
	 ******************************************************** */
	function boardCommonModificationView(boardCommonSeq) {
		document.listForm.boardCommonSeq.value = boardCommonSeq;
	  	document.listForm.action = "<c:url value='/adm/prd/boardCommonModificationView.do'/>";
	  	document.listForm.submit();
	}
	
	/*********************************************************
	 * 삭제 처리 함수
	 ******************************************************** */
	function boardCommonDeletion(boardCommonSeq) {
		document.listForm.boardCommonSeq.value = boardCommonSeq;
	}
	
	/*********************************************************
	 *  정렬 처리 함수
	 ******************************************************** */
	function boardCommonOrdrUpdate(boardCommonSeq, sortOrdr, sortOrdrUpdateFlag) {
		document.listForm.boardCommonSeq.value = boardCommonSeq;
		document.listForm.sortOrdr.value = sortOrdr;
		document.listForm.sortOrdrUpdateFlag.value = sortOrdrUpdateFlag;
	  	document.listForm.action = "<c:url value='/adm/prd/boardCommonOrdrUpdate.do'/>";
	  	document.listForm.submit();
	}

	<c:if test="${!empty resultMsg}">swal("<spring:message code="${resultMsg}" />");</c:if>
-->
</script>

<form name="listForm" action="<c:url value='/adm/prd/boardCommonList.do'/>" method="post" onSubmit="fnSelectLinkPage(1); return false;">
	<input type="hidden" name="currentPageNo" value="<c:out value='${searchVO.currentPageNo}'/>">
	<input type="hidden" name="searchKey" value="<c:out value='${searchVO.searchKey}'/>">
	<input type="hidden" name="searchValue" value="<c:out value='${searchVO.searchValue}'/>">
	<input type="hidden" name="boardCommonSeq" value="0">
	<input type="hidden" name="sortOrdr" value="0">
	<input type="hidden" name="sortOrdrUpdateFlag" value="">
</form>

<div class="container-fluid">
	<div class="page-title">
		<h4></h4>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="card">
				<div class="card-heading border bottom">
					<h2 class="card-title">상품 관리</h2>
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
							<caption>상품 관리</caption>
							<colgroup>
								<col style="width: 5%;">
								<col>
								<col>
								<col>
								<col>
								<col style="width: 10%;">
								<col style="width: 10%;">
								<col>
								<col>
							</colgroup>
							<thead>
								<tr>
									<th>번호</th>
									<th>썸네일 이미지</th>
									<th>상품명</th>
									<th>판매가</th>
									<th>상태</th>
									<th>편집</th>
									<th>정렬</th>
									<th>총수량</th>
									<th>판매수</th>
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
											<span><c:out value="${resultInfo.boardSj }"/></span>
										</td>
										<td>
											<span><b><fmt:formatNumber value="${resultInfo.price }" pattern="#,###" />원</b></span>
										</td>
										<td>
											<c:choose>
												<c:when test="${resultInfo.useAt eq '2' }"><span class="text-info"><b>품절</b></span></c:when>
												<c:when test="${resultInfo.useAt eq '3' }"><span class="text-info"><b>일시중단</b></span></c:when>
												<c:otherwise><span class="text-info"><b>판매중</b></span></c:otherwise>
											</c:choose>
										</td>
										<td>
											<div class="mrg-top-15">
												<a href="#n" class="btn btn-default edit_bt" onclick="javascript:boardCommonModificationView('${resultInfo.boardCommonSeq }')">수정</a>
												<a href="#n" class="btn btn-default edit_bt" data-toggle="modal" data-target="#default-modal" onclick="javascript:boardCommonDeletion('${resultInfo.boardCommonSeq }')">삭제</a>
											</div>
										</td>
										<td>
											<div class="mrg-top-15">
												<a href="#n" class="btn btn-default edit_bt" onclick="javascript:boardCommonOrdrUpdate('${resultInfo.boardCommonSeq}', '${resultInfo.sortOrdr}', 'Y')">위로</a>
												<a href="#n" class="btn btn-default edit_bt" onclick="javascript:boardCommonOrdrUpdate('${resultInfo.boardCommonSeq}', '${resultInfo.sortOrdr}', 'N')">아래로</a>
											</div>
										</td>
										<td>
											<fmt:formatNumber value="${resultInfo.productCnt }" pattern="#,###" />
										</td>
										<td>
											<fmt:formatNumber value="${resultInfo.saleCnt }" pattern="#,###" />
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
					<a href="#n" class="btn btn-primary submit" onclick="javascript:boardCommonRegistrationView()">
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