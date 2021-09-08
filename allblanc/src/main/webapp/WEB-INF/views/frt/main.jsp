<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/commonInclude.jsp" %>

<script type="text/javaScript">
<!--
	$(document).ready(function() {
		$('.drink_con').on('click touchstart', function() {
			console.log($(this));
		  	var thisPrice = $(this).find('.price').text().replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');
		  	var currentPrice = $('#currentPrice').val()*1 + thisPrice*1;
		  	if(!$(this).prev().is(':checked')) {
		  		currentPrice = $('#currentPrice').val()*1 - thisPrice*1;
		  	}
	  		$('#currentPrice').val(currentPrice);
		  	if(currentPrice > 999) {
		  		$('.order_txt > span').html(currentPrice.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + '원');
		  	} else {
		  		$('.order_txt > span').html(currentPrice + '원');
		  	}
		});
		
		$('.btn-primary.btn-sm').on('click', function() {
			
		});
	});
	
	function frtBoardCommonOrder() {
		
	}
-->
</script>

<form name="listForm" action="" method="post" onSubmit="return false;">
	<input type="hidden" name="boardCommonSeq" value="0">
	<input type="hidden" id="currentPrice" name="currentPrice" value="0">
</form>

<!-- <div class="box">
	<div class="wave -one"></div>
	<div class="wave -two"></div>
	<div class="wave -three"></div>
</div> -->
<div id="wrap">
	<div class="message_area">
		<p class="thumbnail">ALL BLANC</p>
		<div class="message_inner">
			<p class="c_white">
				ALLBLANC 에 오신것을 환영합니다.<br />
				<span>음료가 필요하세요?</span>
			</p>
			<ul class="chat_btn">
				<li><a href="/">NO</a></li>
				<li class="btn_yes"><a href="#n">YES</a></li>
			</ul>
		</div>
	</div>
	<div class="new_message_area">
		<p>주문하실 음료를 선택해주세요.</p>
		<ul class="drink_list">
			<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
				<c:choose>
					<c:when test="${resultInfo.planNewAt eq '1'}"><c:set var="planClass" value="new"/></c:when>
					<c:when test="${resultInfo.planBestAt eq '1'}"><c:set var="planClass" value="best"/></c:when>
					<c:otherwise><c:set var="planClass"/></c:otherwise>
				</c:choose>
				<c:forEach items="${productCategoryAllDepthList }" var="productCategoryInfo">
					<c:if test="${productCategoryInfo.depth eq 1 }">
						<c:if test="${productCategoryInfo.productCategorySeq eq resultInfo.category1Seq }">
							<c:set var="productCategoryNm" value="${productCategoryInfo.productCategoryNm }"/>
						</c:if>
					</c:if>
				</c:forEach>
				<li>
					<input type="checkbox" name="drink" />
					<div class="drink_con">
						<div class="${planClass }">
							<img src="/upload/${resultInfo.filePath }${resultInfo.newFileNm }" alt="${resultInfo.originalFileNm } 이미지" />
						</div>
						<p class="title"><em><c:out value="${productCategoryNm }"/></em><c:out value="${resultInfo.boardSj }"/></p>
						<p class="price"><fmt:formatNumber value="${resultInfo.price }" pattern="#,###" />원</p>
					</div>
				</li>
			</c:forEach>
		</ul>
		<p class="order_txt">
			총 주문금액 <span>0원</span><br /><em>선택한 음료를 구매하시겠습니까?</em>
		</p>
		<ul class="order_btn">
			<li><a href="#n">NO</a></li>
			<li class="btn_yes"><a href="#n" onclick="frtBoardCommonOrder();">YES</a></li>
		</ul>
	</div>
	<div class="message_area">
		<p class="thumbnail">ALL BLANC</p>
		<div class="message_inner">
			<p class="message_con">결재방법은 어떤걸로 하시겠습니까?</p>
		</div>
	</div>
	<div class="new_message_area payment">
		<ul>
			<li>
				<a href="#n"><img src="/resources/frt/img/pay_Card.png" alt="카드 결제" /></a>
			</li>
			<li>
				<a href="#n"><img src="/resources/frt/img/pay_Naver.png" alt="네이버페이 결제" /></a>
			</li>
			<li class="kakaoPay">
				<a href="#n"><img src="/resources/frt/img/pay_Kakao.png" alt="카카오페이 결제" /></a>
			</li>
			<li>
				<a href="#n"><img src="/resources/frt/img/pay_SamSung.png" alt="삼성페이 결제" /></a>
			</li>
		</ul>
	</div>
	<div class="message_area">
		<p class="thumbnail">ALL BLANC</p>
		<div class="message_inner">
			<p class="message_con">
				우측에 있는 바코드를 스캔 해주시기 바랍니다.
			</p>
			<div class="barcode">
				<img src="/resources/frt/img/barcode.png" alt="바코드" />
			</div>
		</div>
	</div>
	<div class="message_area banner">
		<p class="thumbnail">ALL BLANC</p>
		<div class="message_inner">
			<div class="payment_drink">
				<img src="/resources/frt/img/payment_drink.png" alt="결제 음료 이미지" />
			</div>
		</div>
	</div>
	<div class="message_area last">
		<p class="thumbnail">ALL BLANC</p>
		<div class="message_inner">
			<p class="order_checked">
				결제가 완료 되었습니다.
				<!-- <a class="end" href="#"
					><span></span><span></span><span></span><span></span
				></a> -->
				<a class="end" href="#"><span></span><span></span><span></span></a>
			</p>
			<p class="message_con t_center">
				음료 트레이에서 음료수를<br />받아가세요.
			</p>
		</div>
	</div>
</div>