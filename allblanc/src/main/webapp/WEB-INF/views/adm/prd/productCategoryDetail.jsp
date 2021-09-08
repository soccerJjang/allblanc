<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/commonInclude.jsp" %>

<tr>
	<th scope="row">카테고리명</th>
	<td>
		<input type="text" id="productCategoryNm" name="productCategoryNm" class="form-control col-md-11" maxlength="60" placeholder="카테고리명을 입력하세요." value="${resultInfo.productCategoryNm}">
		<label for="productCategoryNm"  class="no-mrg-btm mrg-top-10">※ 최대 60자까지 입력하세요.</label>
	</td>
</tr>
<tr>
	<th scope="row">표시순서</th>
	<td>
		<div class="classify">
			<select class="file_type" id="sortOrdr" name="sortOrdr">
				<c:forEach var="sortSeq" begin="1" end="${maxSortOrdr-1}">
					<option value="${sortSeq}"<c:if test="${resultInfo.sortOrdr eq sortSeq }"> selected="selected"</c:if>><c:out value="${sortSeq }"/></option>
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
				<c:choose>
					<c:when test="${resultInfo.useAt eq 'Y'}">
						<c:set var="radio05Input">value="Y" checked="checked"</c:set>
						<c:set var="radio06Input">value="N"</c:set>
					</c:when>
					<c:otherwise>
						<c:set var="radio05Input">value="Y"</c:set>
						<c:set var="radio06Input">value="N" checked="checked"</c:set>
					</c:otherwise>
				</c:choose>
				<span>
					<input type="radio" name="useAt" id="radio05" ${radio05Input }>
					<label for="radio05">표시</label>
				</span>
				<span>
					<input type="radio" name="useAt" id="radio06" ${radio06Input }>
					<label for="radio06">비표시</label>
				</span>
				<span class="dc">※ 비표시 선택시 사용자화면에서 <em class="t_point01">비표시되며 접근불가 처리</em>됩니다.</span>
			</span>
		</div>
	</td>
</tr>