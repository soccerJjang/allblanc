<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/commonInclude.jsp" %>

<script type="text/javaScript">
<!--
	$(document).ready(function() {
		$('.btn-default.btn-sm').on('click', function() {
		  	document.listForm.action = "<c:url value='/adm/sst/boardPopupDeletion.do'/>";
		  	document.listForm.submit();
		});
		
		$('.btn-primary.btn-sm').on('click', function() {
			document.listForm.boardPopupSeq.value = 0;
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
	function boardPopupRegistrationView() {
		document.listForm.action = "<c:url value='/adm/sst/boardPopupRegistrationView.do' />";
		document.listForm.submit();
	}
	
	/* ********************************************************
	 * 수정 처리 함수
	 ******************************************************** */
	function boardPopupModificationView(boardPopupSeq) {
		document.listForm.boardPopupSeq.value = boardPopupSeq;
	  	document.listForm.action = "<c:url value='/adm/sst/boardPopupModificationView.do'/>";
	  	document.listForm.submit();
	}
	
	/*********************************************************
	 * 삭제 처리 함수
	 ******************************************************** */
	function boardPopupDeletion(boardPopupSeq) {
		document.listForm.boardPopupSeq.value = boardPopupSeq;
	}

	<c:if test="${!empty resultMsg}">swal("<spring:message code="${resultMsg}" />");</c:if>
-->
</script>

<form name="listForm" action="<c:url value='/adm/sst/boardPopupList.do'/>" method="post" onSubmit="fnSelectLinkPage(1); return false;">
	<input type="hidden" name="currentPageNo" value="<c:out value='${searchVO.currentPageNo}'/>">
	<input type="hidden" name="searchKey" value="<c:out value='${searchVO.searchKey}'/>">
	<input type="hidden" name="searchValue" value="<c:out value='${searchVO.searchValue}'/>">
	<input type="hidden" name="boardPopupSeq" value="0">
	<input type="hidden" name="sortOrdr" value="0">
	<input type="hidden" name="sortOrdrUpdateFlag" value="">
</form>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="card">
				<div class="card-heading border bottom">
					<h2 class="card-title">팝업 관리</h2>
				</div>
				<div class="card-block">
					<div class="table-overflow">
						<div class="search_box">
							<select id="searchSelectKey" name="searchSelectKey">
								<option value="">전체</option>
								<option value="popupNm"<c:if test="${searchVO.searchKey eq 'popupNm' }"> selected="selected"</c:if>>제목</option>
								<option value="popupDc"<c:if test="${searchVO.searchKey eq 'popupDc' }"> selected="selected"</c:if>>내용</option>
							</select>
							<input type="text" id="searchSelectValue" name="searchSelectValue" placeholder="검색어를 입력해주세요." value="${searchVO.searchValue }" onKeypress="javascript:if(event.keyCode==13) {fnSeach();}">
							<a href="#n" onclick="javascript:fnSeach();">검색</a>
						</div>
						<table class="table table-lg table-hover col-md-12">
							<caption>팝업 관리</caption>
							<colgroup>
								<col style="width: 5%;">
								<col>
								<col>
								<col>
								<col>
							</colgroup>
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>게시기간</th>
									<th>진행상태</th>
									<th>등록일</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
									<tr>
										<td>
											<span><c:out value="${totalCnt - ((searchVO.currentPageNo-1) * resultInfo.pageSize + status.index)}"/></span>
										</td>
										<td>
											<span class="text-info pointer" onclick="javascript:boardPopupModificationView('<c:out value="${resultInfo.boardPopupSeq}"/>')"><c:out value="${resultInfo.popupNm}"/></span>
										</td>
										<td>
											<c:choose>
												<c:when test="${fn:length(resultInfo.noticeStartDay) le 8}">
													<c:set var="startDay">${fn:substring(resultInfo.noticeStartDay, 0, 4)}.${fn:substring(resultInfo.noticeStartDay, 4, 6)}.${fn:substring(resultInfo.noticeStartDay, 6, 8)}</c:set>
													<c:set var="endDay">${fn:substring(resultInfo.noticeEndDay, 0, 4)}.${fn:substring(resultInfo.noticeEndDay, 4, 6)}.${fn:substring(resultInfo.noticeEndDay, 6, 8)}</c:set>
												</c:when>
												<c:when test="${fn:length(resultInfo.noticeStartDay) le 6}">
													<c:set var="startDay">${fn:substring(resultInfo.noticeStartDay, 0, 4)}.${fn:substring(resultInfo.noticeStartDay, 4, 6)}</c:set>
													<c:set var="endDay">${fn:substring(resultInfo.noticeEndDay, 0, 4)}.${fn:substring(resultInfo.noticeEndDay, 4, 6)}</c:set>
												</c:when>
												<c:otherwise>
													<c:set var="startDay">${fn:substring(resultInfo.noticeStartDay, 0, 4)}.${fn:substring(resultInfo.noticeStartDay, 4, fn:length(resultInfo.noticeStartDay))}</c:set>
													<c:set var="endDay">${fn:substring(resultInfo.noticeEndDay, 0, 4)}.${fn:substring(resultInfo.noticeEndDay, 4, fn:length(resultInfo.noticeEndDay))}</c:set>
												</c:otherwise>
											</c:choose>
											<span><c:out value="${startDay}"/> ~ <c:out value="${endDay}"/></span>
										</td>
										<td>
											<c:choose>
												<c:when test="${resultInfo.noticeAt eq '1' }"><span class="text-info Proceeding"><b>진행중</b></span></c:when>
												<c:otherwise><span class="text-info End"><b>종료</b></span></c:otherwise>
											</c:choose>
										</td>
										<td>
											<span><fmt:formatDate value="${resultInfo.createDt}" pattern="yyyy.MM.dd"/></span>
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
					<a href="#n" class="btn btn-primary submit" onclick="javascript:boardPopupRegistrationView()">
						<span>등록하기</span>
					</a>
				</div>
			</div>
		</div>
	</div>
</div>

<%-- <div class="paginate">
	<ui:pagination paginationInfo="${paginationInfo}" type="admin" jsFunction="fnSelectLinkPage"/>
</div> --%>

<!-- page plugins js -->
<script src="/resources/adm/vendors/datatables/media/js/jquery.dataTables.js"></script>

<!-- page js -->
<script src="/resources/adm/js/table/data-table.js"></script>
