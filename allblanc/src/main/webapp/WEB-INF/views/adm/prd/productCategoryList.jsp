<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/commonInclude.jsp" %>

<script type="text/javaScript">
<!--
	$(document).ready(function() {
		$('#oneDepth').on('click', function() { // 대분류 추가 클릭
			formInit(0);
			$('.group_list td:eq(0) li').removeClass('on');
		});
		$('#twoDepth').on('click', function() { // 중분류 추가 클릭
			if($('.group_list td:eq(0) li.on').length < 1) { // 대분류 선택된 값이 없는 경우 실패처리
				swal('대분류를 선택해 주세요.');
				return false;
			} else {
				var productCategorySeq = $('.group_list td:eq(0) li.on a').attr('id').replace('productCategorySeq_', '');
				formInit(productCategorySeq);
				$('.group_list td:eq(1) li').removeClass('on');
			}
		});
		$('#threeDepth').on('click', function() { // 소분류 추가 클릭
			if($('.group_list td:eq(1) li.on').length < 1) { // 중분류 선택된 값이 없는 경우 실패처리
				swal('중분류를 선택해 주세요.');
				return false;
			} else {
				var productCategorySeq = $('.group_list td:eq(1) li.on a').attr('id').replace('productCategorySeq_', '');
				formInit(productCategorySeq);
				$('.group_list td:eq(2) li').removeClass('on');
			}
		});
		$('.group_list').on('click', 'td a', function() { // 카테고리 선택시
			var curIdx = 0;
			var curTdElement = $(this).closest('td');
			$('.group_list td').each(function(idx) {
				if($(curTdElement)[0] == $(this)[0]) {
					curIdx = idx;
				}
			});
			$('.board_ty.ds02 table thead th:eq('+(curIdx+1)+') a').removeClass('btn-disabled');
			$('.group_list td:eq('+curIdx+') li').removeClass('on');
			$(this).closest('li').addClass('on');
			var productCategorySeq = $(this).attr('id').replace('productCategorySeq_', '');
			if(productCategorySeq > 0) {
				productCategoryDetail(productCategorySeq);
				productCategoryChildList(productCategorySeq, curIdx+1);
			}
		});
		$('.btn_ty03').on('click', function(e) { // 저장 클릭
			e.preventDefault();
			productCategoryRegistrationValidation();
		});
		$('.btn_ty02').on('click', function(e) { // 삭제 클릭
			e.preventDefault();
		});
		$('.btn_ty01').on('click', function(e) { // 취소 클릭
			e.preventDefault();
		});
		$('#default-modal .btn-default.btn-sm').on('click', function() { // 삭제 모달 예 클릭
		  	document.regForm.action = "<c:url value='/adm/prd/productCategoryDeletion.do'/>";
		  	document.regForm.submit();
		});
		
		$('#default-modal .btn-primary.btn-sm').on('click', function() { // 삭제 모달 아니오 클릭
			document.regForm.productCategorySeq.value = 0;
		});
		$('#primary-modal .btn-default.btn-sm').on('click', function() { // 저장 모달 예 클릭
			document.regForm.action = "<c:url value='/adm/prd/productCategoryRegistration.do' />";
			document.regForm.submit();
		});
		$('.upOrdr').on('click', function() { // 순서 위로
			var element = $(this)[0];
			var productCategoryDepth = -1;
			$('.upOrdr').each(function(idx) {
				if($(element)[0] == $(this)[0]) {
					productCategoryDepth = idx;
				}
			});
			if(productCategoryDepth >= 0) {
				var liSeq = -1;
				$('.group_list > td:eq('+productCategoryDepth+') li').each(function(idx) {
					if($(this).hasClass('on')) {
						liSeq = idx;
					}
				});
				if(liSeq < 0) {
					swal('선택된 코드가 없습니다.');
				} else if(liSeq == 0) {
					swal('더이상 위로 이동할 수 없습니다.');
				} else {
					var curOrdr = $('.group_list > td:eq('+productCategoryDepth+') li:eq('+liSeq+') > a').attr('class').replace('sortOrdr_', '');
					var changeOrdr = $('.group_list > td:eq('+productCategoryDepth+') li:eq('+(liSeq-1)+') > a').attr('class').replace('sortOrdr_', '');
					$('.group_list > td:eq('+productCategoryDepth+') li:eq('+liSeq+') > a').attr('class', 'sortOrdr_' + changeOrdr);
					$('.group_list > td:eq('+productCategoryDepth+') li:eq('+(liSeq-1)+') > ').attr('class', 'sortOrdr_' + curOrdr);
					$('.group_list > td:eq('+productCategoryDepth+') li:eq('+liSeq+')').insertBefore($('.group_list > td:eq('+productCategoryDepth+') li:eq('+(liSeq-1)+')'));
					$('.order_wrap > a.save:eq('+productCategoryDepth+')').addClass('on');
				}
			}
		});
		$('.downOrdr').on('click', function(idx) { // 순서 아래로
			var element = $(this)[0];
			var productCategoryDepth = -1;
			$('.downOrdr').each(function(idx) {
				if($(element)[0] == $(this)[0]) {
					productCategoryDepth = idx;
				}
			});
			if(productCategoryDepth >= 0) {
				var liSeq = -1;
				$('.group_list > td:eq('+productCategoryDepth+') li').each(function(idx) {
					if($(this).hasClass('on')) {
						liSeq = idx;
					}
				});
				if(liSeq < 0) {
					swal('선택된 코드가 없습니다.');
				} else if(liSeq+1 >= $('.group_list > td:eq('+productCategoryDepth+') li').length) {
					swal('더이상 아래로 이동할 수 없습니다.');
				} else {
					var curOrdr = $('.group_list > td:eq('+productCategoryDepth+') li:eq('+liSeq+') > a').attr('class').replace('sortOrdr_', '');
					var changeOrdr = $('.group_list > td:eq('+productCategoryDepth+') li:eq('+(liSeq+1)+') > a').attr('class').replace('sortOrdr_', '');
					$('.group_list > td:eq('+productCategoryDepth+') li:eq('+liSeq+') > a').attr('class', 'sortOrdr_' + changeOrdr);
					$('.group_list > td:eq('+productCategoryDepth+') li:eq('+(liSeq+1)+') > a').attr('class', 'sortOrdr_' + curOrdr);
					$('.group_list > td:eq('+productCategoryDepth+') li:eq('+liSeq+')').insertAfter($('.group_list > td:eq('+productCategoryDepth+') li:eq('+(liSeq+1)+')'));
					$('.order_wrap > a.save:eq('+productCategoryDepth+')').addClass('on');
				}
			}
		});
		$('.save').on('click', function() { // 순서 저장
			var element = $(this)[0];
			var productCategoryDepth = -1;
			$('.save').each(function(idx) {
				if($(element)[0] == $(this)[0]) {
					productCategoryDepth = idx;
				}
			});
			if(productCategoryDepth >= 0) {
				var productCategorySeqArray = '';
				var sortOrdrArray = '';
				$('.group_list > td:eq('+productCategoryDepth+') li').each(function(idx) {
					if($(this).find('> a').length > 0) {
						console.log($(this));
						var productCategorySeq = $(this).find('> a').attr('id').replace('productCategorySeq_', '');
						productCategorySeqArray += ',' + productCategorySeq;
						var sortOrdr = $(this).find('> a').attr('class').replace('sortOrdr_', '');
						sortOrdrArray += ',' + sortOrdr;
					}
				});
				if(productCategorySeqArray.length > 0) {
					productCategorySeqArray = productCategorySeqArray.substr(1);
				}
				if(sortOrdrArray.length > 0) {
					sortOrdrArray = sortOrdrArray.substr(1);
				}
				document.listForm.productCategorySeqArray.value = productCategorySeqArray;
				document.listForm.sortOrdrArray.value = sortOrdrArray;
			  	document.listForm.action = "<c:url value='/adm/prd/productCategoryOrdrUpdate.do'/>";
			  	document.listForm.submit();
			}
		});
		$('.group_list td:eq(0) ul li:eq(0) a').click();
	});
	
	/* ********************************************************
	 * 추가시 폼 초기화
	 ******************************************************** */
	function formInit(productCategorySeq) {
		document.regForm.productCategorySeq.value = 0;
		document.regForm.productCategoryNm.value = '';
		document.regForm.sortOrdr.value = document.regForm.sortOrdr.children[0].value;
		$('#radio05').click();
		$('#radio07').click();
		$('#parentsProductCategorySeq').val(productCategorySeq);
		$('#sortOrdr').html('<option value="0" selected="selected">신규등록</option>');
		$('#categeryForm').show();
		$('.btn_ty03').attr('data-toggle', '');
	}
	
	/* ********************************************************
	 * 카테고리 정보 가져오기
	 ******************************************************** */
	function productCategoryDetail(productCategorySeq) {
		$.ajax({
			url:'./productCategoryDetail.do',
			type: 'GET',
			dataType: 'html',
			data: {
				productCategorySeq: productCategorySeq
			},
			success: function(data) {
				$('#regForm table tbody').html(data);
				$('#productCategorySeq').val(productCategorySeq);
				$('#categeryForm').show();
			},
			error: function(e) {
				console.log(e);
			}
  		});
	}
	
	/* ********************************************************
	 * 하위 카테고리 정보 가져오기
	 ******************************************************** */
	function productCategoryChildList(productCategorySeq, idx) {
		$.ajax({
			url:'./productCategoryChildList.do',
			type: 'GET',
			dataType: 'html',
			data: {
				productCategorySeq: productCategorySeq
			},
			success: function(data) {
				$('.group_list td:eq('+idx+') ul').html(data);
				if(idx == 1) { // 중분류 최대 sortOrdr 셋팅
					$('#maxTwoDepthSortOrdr')
				} else if(idx == 2) { // 소분류 최대 sortOrdr 셋팅
					
				}
			},
			error: function(e) {
				console.log(e);
			}
  		});
	}
	
	/* ********************************************************
	 * 등록 처리 함수
	 ******************************************************** */
	function productCategoryRegistrationValidation() {
		$('.btn_ty03').attr('data-toggle', '');
		if(document.regForm.productCategoryNm.value == '') {
			document.regForm.productCategoryNm.focus();
			swal('카테고리명을 입력하세요.');
			return false;
		}
		$('.btn_ty03').attr('data-toggle', 'modal');
	}
	
	/*********************************************************
	 * 삭제 처리 함수
	 ******************************************************** */
	function productCategoryDeletion(productCategorySeq) {
		document.regForm.productCategorySeq.value = productCategorySeq;
	}
	
	<c:if test="${!empty resultMsg}">swal("<spring:message code="${resultMsg}" />");</c:if>
-->
</script>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="card">
				<div class="card-heading border bottom">
					<h2 class="card-title">카테고리 관리</h2>
				</div>
				<div class="card-block">
					<div class="table-overflow">
						<form id="listForm" name="listForm" action="<c:url value='/adm/prd/productCategoryOrdrUpdate.do'/>" method="post";>
							<input type="hidden" id="ordrDepth" name="ordrDepth" value="1">
							<input type="hidden" id="productCategorySeqArray" name="productCategorySeqArray" value="">
							<input type="hidden" id="sortOrdrArray" name="sortOrdrArray" value="">
						</form>
						<table class="table-lg col-md-12 modify-table">
							<colgroup>
								<col span="1" style="width:33.3%">
								<col span="1" style="width:33.3%">
								<col span="1" style="width:33.3%">
							</colgroup>
							<thead>
								<tr>
									<th><a href="#none" id="oneDepth" class="btn sm">대분류 추가</a></th>
									<th><a href="#none" id="twoDepth" class="btn sm btn-disabled">중분류 추가</a></th>
									<th><a href="#none" id="threeDepth" class="btn sm btn-disabled">소분류 추가</a></th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${fn:length(resultList) == 0}">
										<tr class="group_list">
											<td class="va_top">
												<ul class="blit_list">
													<li><em class="txt_999">대분류 카테고리가 없습니다.</em></li>
												</ul>
											</td>
											<td class="va_top">
												<ul class="blit_list">
													<li><em class="txt_999">해당 분류는 하위 카테고리가 없습니다.</em></li>
												</ul>
											</td>
											<td class="va_top">
												<ul class="blit_list">
													<li><em class="txt_999">해당 분류는 하위 카테고리가 없습니다.</em></li>
												</ul>
											</td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr class="group_list">
											<td class="va_top">
												<ul class="blit_list">
													<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
														<li><a href="#none" id="productCategorySeq_${resultInfo.productCategorySeq}" class="sortOrdr_${resultInfo.sortOrdr}"><c:out value="${resultInfo.productCategoryNm}"/></a></li>
													</c:forEach>
												</ul>
											</td>
											<td class="va_top">
												<ul class="blit_list">
													<li><em class="txt_999">해당 분류는 하위 카테고리가 없습니다.</em></li>
												</ul>
											</td>
											<td class="va_top">
												<ul class="blit_list">
													<li><em class="txt_999">해당 분류는 하위 카테고리가 없습니다.</em></li>
												</ul>
											</td>
										</tr>
										<tr>
											<td>
												<div class="order_wrap">
													<div class="order_btn">
														순서 <a href="#n" class="btn arrow upOrdr">&#9650;<span class="blind">위로</span></a>
														<a href="#n" class="btn arrow downOrdr">&#9660;<span class="blind">아래로</span></a>
													</div>
													<a href="#n" class="btn btn-primary save" data-target="#save-modal">저장</a>
												</div>
											</td>
											<td>
												<div class="order_wrap">
													<div class="order_btn">
														순서 <a href="#n" class="btn arrow upOrdr">&#9650;<span class="blind">위로</span></a>
														<a href="#n" class="btn arrow downOrdr">&#9660;<span class="blind">아래로</span></a>
													</div>
													<a href="#n" class="btn btn-primary save" data-target="#save-modal">저장</a>
												</div>
											</td>
											<td>
												<div class="order_wrap">
													<div class="order_btn">
														순서 <a href="#n" class="btn arrow upOrdr">&#9650;<span class="blind">위로</span></a>
														<a href="#n" class="btn arrow downOrdr">&#9660;<span class="blind">아래로</span></a>
													</div>
													<a href="#n" class="btn btn-primary save" data-target="#save-modal">저장</a>
												</div>
											</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="row" id="categeryForm" style="display:none;">
		<div class="col-md-12">
			<div class="card">
				<div class="card-heading border bottom">
					<h2 class="card-title">카테고리 정보</h2>
				</div>
				<div class="card-block">
					<form id="regForm" name="regForm" action="<c:url value='/adm/prd/productCategoryRegistration.do'/>" method="post" onSubmit="productCategoryRegistrationValidation(); return false;">
						<input type="hidden" id="parentsProductCategorySeq" name="parentsProductCategorySeq" value="0">
						<input type="hidden" id="productCategorySeq" name="productCategorySeq" value="0">
						<div class="row">
							<div class="col-md-12">
								<table class="modify-table">
									<caption>카테고리 정보</caption>
									<colgroup>
										<col style="width:200px">
										<col>
									</colgroup>
									<tbody>
										<tr>
											<th scope="row">카테고리명</th>
											<td>
												<input type="text" id="productCategoryNm" name="productCategoryNm" class="form-control col-md-11" maxlength="60" placeholder="카테고리명을 입력하세요.">
												<label for="productCategoryNm" class="no-mrg-btm mrg-top-10">※ 최대 60자까지 입력하세요.</label>
											</td>
										</tr>
										<tr>
											<th scope="row">표시순서</th>
											<td>
												<div class="classify">
													<select class="file_type" id="sortOrdr" name="sortOrdr">
														<option value="${maxSortOrdr}" selected="selected">신규등록</option>
														<c:forEach var="sortSeq" begin="1" end="${maxSortOrdr-1}">
															<option value="${sortSeq}"><c:out value="${sortSeq }"/></option>
														</c:forEach>
													</select>
												</div>
											</td>
										</tr>
										<tr>
											<th scope="row">표시여부</th>
											<td>
												<div class="input_wrap vertical-m">
													<span class="ds_raido">
														<span>
															<input type="radio" name="useAt" id="radio05" checked="checked" value="Y">
															<label for="radio05">표시</label>
														</span>
														<span>
															<input type="radio" name="useAt" id="radio06" value="N">
															<label for="radio06">비표시</label>
														</span>
														<span class="dc">※ 비표시 선택시 사용자화면에서 <em class="t_point01">비표시되며 접근불가 처리</em>됩니다.</span>
													</span>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
								<div class="text-right mrg-top-15">
									<button class="btn btn-danger btn_ty02" data-toggle="modal" data-target="#default-modal" onclick="javascript:productCategoryDeletion(document.regForm.productCategorySeq.value)">삭제</button>
									<button type="submit" data-target="#primary-modal" class="btn btn-primary btn_ty03">저장</button>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal fade show" id="default-modal">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-body">
								<p>해당 카테고리을 삭제 하시겠습니까?</p>
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
				<div class="modal fade show" id="primary-modal">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-body">
								<p>해당 카테고리를 저장 하시겠습니까?</p>
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
				<div class="modal fade show" id="save-modal">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-body">
								<p>해당 순서를 저장 하시겠습니까?</p>
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
