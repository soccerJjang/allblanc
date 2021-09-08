<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/commonInclude.jsp" %>

<script type="text/javaScript">
<!--
	$(document).ready(function() {
		$('.btn-default.btn-sm').on('click', function() {
		  	document.listForm.action = "<c:url value='/adm/sst/adminManagerDeletion.do'/>";
		  	document.listForm.submit();
		});
		
		$('.btn-primary.btn-sm').on('click', function() {
			document.listForm.adminSeq.value = 0;
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
	function adminManagerRegistrationView() {
		document.listForm.action = "<c:url value='/adm/sst/adminManagerRegistrationView.do' />";
		document.listForm.submit();
	}
	
	/* ********************************************************
	 * 수정 처리 함수
	 ******************************************************** */
	function adminManagerModificationView(adminSeq) {
		document.listForm.adminSeq.value = adminSeq;
	  	document.listForm.action = "<c:url value='/adm/sst/adminManagerModificationView.do'/>";
	  	document.listForm.submit();
	}
	
	/*********************************************************
	 * 삭제 처리 함수
	 ******************************************************** */
	function adminManagerDeletion(adminSeq) {
		document.listForm.adminSeq.value = adminSeq;
	}

	<c:if test="${!empty resultMsg}">swal("<spring:message code="${resultMsg}" />");</c:if>
-->
</script>

<form name="listForm" action="<c:url value='/adm/sst/adminManagerList.do'/>" method="post" onSubmit="fnSelectLinkPage(1); return false;">
	<input type="hidden" name="currentPageNo" value="<c:out value='${searchVO.currentPageNo}'/>">
	<input type="hidden" name="searchKey" value="<c:out value='${searchVO.searchKey}'/>">
	<input type="hidden" name="searchValue" value="<c:out value='${searchVO.searchValue}'/>">
	<input type="hidden" name="adminSeq" value="0">
	<input type="hidden" name="sortOrdr" value="0">
	<input type="hidden" name="sortOrdrUpdateFlag" value="">
</form>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="card">
				<div class="card-heading border bottom">
					<h2 class="card-title">관리자 관리</h2>
				</div>
				<div class="card-block">
					<div class="table-overflow">
						<div class="search_box">
							<select id="searchSelectKey" name="searchSelectKey">
								<option value="">전체</option>
								<option value="adminId"<c:if test="${searchVO.searchKey eq 'adminId' }"> selected="selected"</c:if>>ID</option>
								<option value="adminNm"<c:if test="${searchVO.searchKey eq 'adminNm' }"> selected="selected"</c:if>>이름</option>
							</select>
							<input type="text" id="searchSelectValue" name="searchSelectValue" placeholder="검색어를 입력해주세요." value="${searchVO.searchValue }" onKeypress="javascript:if(event.keyCode==13) {fnSeach();}">
							<a href="#n" onclick="javascript:fnSeach();">검색</a>
						</div>
						<table class="table table-lg table-hover col-md-12">
							<caption>관리자 관리</caption>
							<colgroup>
								<col style="width: 5%;">
								<col>
								<col>
								<col>
								<col style="width: 10%;">
								<col>
							</colgroup>
							<thead>
								<tr>
									<th>번호</th>
									<th>관리자 구분</th>
									<th>ID</th>
									<th>이름</th>
									<th>편집</th>
									<th>로그인일시</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
									<tr>
										<td>
											<span><c:out value="${totalCnt - ((searchVO.currentPageNo-1) * resultInfo.pageSize + status.index)}"/></span>
										</td>
										<td>
											<c:choose>
												<c:when test="${resultInfo.adminDivision eq 1 }"><span class="text-info"><b>슈퍼관리자</b></span></c:when>
												<c:otherwise><span class="text-info">일반관리자</span></c:otherwise>
											</c:choose>
										</td>
										<td>
											<span><c:out value="${resultInfo.adminId}"/></span>
										</td>
										<td>
											<span><c:out value="${resultInfo.adminNm}"/></span>
										</td>
										<td>
											<div class="mrg-top-15">
												<a href="#n" class="btn btn-default edit_bt" onclick="javascript:adminManagerModificationView('${resultInfo.adminSeq }')">수정</a>
												<a href="#n" class="btn btn-default edit_bt" data-toggle="modal" data-target="#default-modal" onclick="javascript:adminManagerDeletion('${resultInfo.adminSeq }')">삭제</a>
											</div>
										</td>
										<td>
											<span><fmt:formatDate pattern="yyyy.MM.dd HH:mm:ss" value="${resultInfo.loginDt }"/></span>
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
					<a href="#n" class="btn btn-primary submit" onclick="javascript:adminManagerRegistrationView()">
						<span>등록하기</span>
					</a>
				</div>
				<div class="modal fade show" id="default-modal">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-body">
								<p>해당 관리자을 삭제 하시겠습니까?</p>
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
