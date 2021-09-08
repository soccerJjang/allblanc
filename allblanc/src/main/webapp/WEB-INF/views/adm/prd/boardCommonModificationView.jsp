<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/commonInclude.jsp" %>

<script type="text/javaScript">
<!--
	$(document).ready(function() {
		$('#planDiscountAt').on('change', function() { // 기획상품 중 할인제품 체크시
			if($('#planDiscountAt').prop('checked')) {
				$('#discountRate').show();
				$('#discountRateSpan').show();
			} else {
				$('#discountRate').hide();
				$('#discountRateSpan').hide();
			}
		});
		$('#category1Seq').on('change', function() { // 대분류 선택시
			// 중분류는 대분류에 속해있는 카테고리만 노출
			$('#category2Seq option').hide();
			$('#category2Seq option.category1Seq_0').show();
			$('#category2Seq option.category1Seq_0').prop('selected', 'selected');
			$('#category2Seq option.category1Seq_' + $('#category1Seq').val()).show();
			$('#category3Seq option').each(function() { // 소분류는 값 초기화
				if($(this).val() == '0') {
					$(this).show();
					$(this).prop('selected', 'selected');
				} else {
					$(this).hide();
				}
			});
		});
		$('#category2Seq').on('change', function() { // 중분류 선택시
			// 소분류는 중분류에 속해있는 카테고리만 노출
			$('#category3Seq option').hide();
			$('#category3Seq option.category2Seq_0').show();
			$('#category3Seq option.category2Seq_0').prop('selected', 'selected');
			$('#category3Seq option.category2Seq_' + $('#category2Seq').val()).show();
		});
		$('#category1Seq').val('<c:out value="${boardCommonDetail.category1Seq}"/>');
		$('#category2Seq').val('<c:out value="${boardCommonDetail.category2Seq}"/>');
		$('#category3Seq').val('<c:out value="${boardCommonDetail.category3Seq}"/>');
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
	function boardCommonModification() {
		if($('#boardSj').val() == '') {
			swal('상품명을 입력해주세요.');
			$('#boardSj').focus();
			return false;
		}
		if($('#price').val() == '') {
			swal('가격를 입력해주세요.');
			$('#price').focus();
			return false;
		}
		if($('#productCnt').val() == '') {
			swal('총수량를 입력해주세요.');
			$('#productCnt').focus();
			return false;
		}
		if($('#fileNameText').text() == '') {
			swal('제품 이미지를 추가해주세요.');
			$('#fileNameText').focus();
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

<form name="listForm" action="<c:url value='/adm/prd/boardCommonList.do'/>" method="post" onSubmit="fnSelectLinkPage(1); return false;">
	<input type="hidden" name="currentPageNo" value="<c:out value='${searchVO.currentPageNo}'/>">
</form>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="card">
				<div class="card-heading border bottom">
					<h2 class="card-title">상품 관리</h2>
				</div>
				<div class="card-block">
					<form id="regForm" name="regForm" action="<c:url value='/adm/prd/boardCommonModification.do'/>" method="post" onSubmit="boardCommonModification(); return false;" enctype="multipart/form-data">
						<input type="hidden" id="boardSeq" name="boardSeq" value="1">
						<input type="hidden" id="boardCommonSeq" name="boardCommonSeq" value="${boardCommonDetail.boardCommonSeq }">
						<input type="hidden" id="productCode" name="productCode" value="${boardCommonDetail.productCode }">
						<div class="row">
							<div class="col-md-12">
								<!--table-->
								<table class="modify-table">
									<caption>상품 관리</caption>
									<colgroup>
										<col style="width:200px">
										<col>
									</colgroup>
									<tbody>
										<tr>
											<th scope="row">상태</th>
											<td>
												<div class="state">
													<div>
														<input type="radio" name="useAt" id="form-3-1" value="1"<c:if test="${boardCommonDetail.useAt ne 2 and boardCommonDetail.useAt ne 3 }"> checked="checked"</c:if>>
														<label for="form-3-1">판매중</label>
													</div>
													<div>
														<input type="radio" name="useAt" id="form-3-2" value="2"<c:if test="${boardCommonDetail.useAt eq 2 }"> checked="checked"</c:if>>
														<label for="form-3-2">품절</label>
													</div>
													<div>
														<input type="radio" name="useAt" id="form-3-3" value="3"<c:if test="${boardCommonDetail.useAt eq 3 }"> checked="checked"</c:if>>
														<label for="form-3-3">일시중단</label>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<th scope="row">상품코드</th>
											<td>
												<input type="text" class="form-control col-md-8" id="productCodeDisableed" name="productCodeDisableed" value="${boardCommonDetail.productCode }" disabled="disabled">
											</td>
										</tr>
										<tr>
											<th scope="row">상품명</th>
											<td>
												<input type="text" class="form-control col-md-8" id="boardSj" name="boardSj" value="${boardCommonDetail.boardSj }">
											</td>
										</tr>
										<tr>
											<th scope="row">분류</th>
											<td>
												<div class="size">
													<select id="category1Seq" name="category1Seq" class="file_type file_type1">
														<option value="0">대분류</option>
														<c:forEach items="${productCategoryAllDepthList }" var="categoryInfo">
															<c:if test="${categoryInfo.depth eq 1 }">
																<option value="${categoryInfo.productCategorySeq }"><c:out value="${categoryInfo.productCategoryNm }"/></option>
															</c:if>
														</c:forEach>
													</select>
													<select id="category2Seq" name="category2Seq" class="file_type file_type2">
														<option class="category1Seq_0" value="0">중분류</option>
														<c:forEach items="${productCategoryAllDepthList }" var="categoryInfo">
															<c:if test="${categoryInfo.depth eq 2 }">
																<option class="category1Seq_${categoryInfo.parentsProductCategorySeq }" style="display:none;" value="${categoryInfo.productCategorySeq }"><c:out value="${categoryInfo.productCategoryNm }"/></option>
															</c:if>
														</c:forEach>
													</select>
													<select id="category3Seq" name="category3Seq" class="file_type file_type3">
														<option class="category2Seq_0" value="0">소분류</option>
														<c:forEach items="${productCategoryAllDepthList }" var="categoryInfo">
															<c:if test="${categoryInfo.depth eq 3 }">
																<option class="category2Seq_${categoryInfo.parentsProductCategorySeq }" style="display:none;" value="${categoryInfo.productCategorySeq }"><c:out value="${categoryInfo.productCategoryNm }"/></option>
															</c:if>
														</c:forEach>
													</select>
												</div>
											</td>
										</tr>
										<tr>
											<th scope="row">가격</th>
											<td>
												<input type="text" class="form-control col-md-8 text-right" id="price" name="price" value="${boardCommonDetail.price }" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
											</td>
										</tr>
										<tr>
											<th scope="row">총수량</th>
											<td>
												<input type="text" class="form-control col-md-8 text-right" id="productCnt" name="productCnt" value="${boardCommonDetail.productCnt }" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
											</td>
										</tr>
										<tr>
											<th scope="row">기획상품</th>
											<td>
												<div class="vertical-m">
													<input type="checkbox" id="planNewAt" name="planNewAt" value="1"<c:if test="${boardCommonDetail.planNewAt eq 1 }"> checked="checked"</c:if>><label for="planNewAt">신제품</label>
													<input type="checkbox" id="planBestAt" name="planBestAt" value="1"<c:if test="${boardCommonDetail.planBestAt eq 1 }"> checked="checked"</c:if>><label for="planBestAt">BEST</label>
													<input type="checkbox" id="planRecommendAt" name="planRecommendAt" value="1"<c:if test="${boardCommonDetail.planRecommendAt eq 1 }"> checked="checked"</c:if>><label for="planRecommendAt">추천상품</label>
													<input type="checkbox" id="planDiscountAt" name="planDiscountAt" value="1"<c:if test="${boardCommonDetail.planDiscountAt eq 1 }"> checked="checked"</c:if>><label for="planDiscountAt">할인제품</label>
													<input type="text" id="discountRate" name="discountRate"<c:if test="${boardCommonDetail.planDiscountAt ne 1 }"> style="display:none;"</c:if> class="form-control col-md-2 text-right" value="${boardCommonDetail.discountRate }">
													<label for="discountRate" id="discountRateSpan"<c:if test="${boardCommonDetail.planDiscountAt ne 1 }"> style="display:none;"</c:if>>&#37;</label>
												</div>
											</td>
										</tr>
										<tr>
											<th scope="row">제품 이미지</th>
											<td>
												<div class="filebox">
													<span class="input_file" id="fileNameText" name="fileNameText">${boardCommonDetail.originalFileNm }</span>
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