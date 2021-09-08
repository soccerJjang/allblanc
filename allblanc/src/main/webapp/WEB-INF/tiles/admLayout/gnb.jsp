<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/commonInclude.jsp" %>
<tiles:importAttribute name="gnbList"/>

<% 
	String adminNm = (String)session.getAttribute("adminNm");
	String adminId = (String)session.getAttribute("adminId");
%>
<c:set var="adminNm" value="${adminNm }"/>
<div class="header-container">
	<ul class="nav-left">
		<li>
			<a class="side-nav-toggle" href="javascript:void(0);">
				<i class="ti-view-grid"></i>
			</a>
		</li>
	</ul>
	<ul class="nav-right">
		<li class="user-profile dropdown">
			<a href="#n" class="dropdown-toggle" data-toggle="dropdown">
				<div class="user-info">
					<span class="name pdd-right-5"><c:out value="${adminNm}"/><em> 님이 로그인 하셨습니다</em></span>
				</div>
			</a>
		</li>
		<li class="log_out">
			<a href="/adm/lgn/logout.do">로그아웃</a>
		</li>
	</ul>
</div>
	