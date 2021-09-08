<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/commonInclude.jsp" %>

<c:choose>
	<c:when test="${fn:length(resultList) == 0}">
		<li><em class="txt_999">해당 분류는 하위 카테고리가 없습니다.</em></li>
	</c:when>
	<c:otherwise>
		<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			<li><a href="#none" id="productCategorySeq_${resultInfo.productCategorySeq }" class="sortOrdr_${resultInfo.sortOrdr}"><c:out value="${resultInfo.productCategoryNm}"/></a></li>
		</c:forEach>
	</c:otherwise>
</c:choose>