<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/commonInclude.jsp" %>
<tiles:importAttribute name="lnbList"/>

<div class="side-nav-inner">
	<div class="side-nav-logo">
		<a href="/adm">
			<div class="logo logo-dark" style="background-image: url('/resources/adm/images/logo/logo.png')"></div>
			<div class="logo logo-white" style="background-image: url('/resources/adm/images/logo/logo-white.png')"></div>
		</a>
		<div class="mobile-toggle side-nav-toggle">
			<a href="#n">
				<i class="ti-arrow-circle-left"></i>
			</a>
		</div>
	</div>
	<ul class="side-nav-menu scrollable">
		<!-- <li class="nav-item">
			<a class="mrg-top-30" href="/adm/stats/dashboard.do">
				<span class="icon-holder">
					<i class="ti-home"></i>
				</span>
				<span class="title">대시보드</span>
			</a>
		</li> -->
		<li class="nav-item dropdown">
			<a class="dropdown-toggle" href="javascript:void(0);">
				<span class="icon-holder">
					<i class="ti-package"></i>
				</span>
				<span class="title">디자인 관리</span>
				<span class="arrow">
					<i class="ti-angle-right"></i>
				</span>
			</a>
			<ul class="dropdown-menu">
				<li>
					<a href="/adm/sst/boardDesignList.do">디자인 설정 관리</a>
				</li>
				<li>
					<a href="/adm/sst/boardPopupList.do">팝업 관리</a>
				</li>
			</ul>
		</li>
		<li class="nav-item dropdown">
			<a class="dropdown-toggle" href="javascript:void(0);">
				<span class="icon-holder">
					<i class="ti-palette"></i>
				</span>
				<span class="title">상품관리</span>
				<span class="arrow">
					<i class="ti-angle-right"></i>
				</span>
			</a>
			<ul class="dropdown-menu">
				<li>
					<a href="/adm/prd/productCategoryList.do">카테고리 관리</a>
				</li>
				<li>
					<a href="/adm/prd/boardCommonList.do">상품관리</a>
				</li>
			</ul>
		</li>
		<li class="nav-item dropdown">
			<a class="dropdown-toggle" href="javascript:void(0);">
				<span class="icon-holder">
					<i class="ti-file"></i>
				</span>
				<span class="title">매출관리</span>
				<span class="arrow">
					<i class="ti-angle-right"></i>
				</span>
			</a>
			<ul class="dropdown-menu">
				<li>
					<a href="/adm/sls/salesManagerList.do">매출관리</a>
				</li>
			</ul>
		</li>
		<!-- <li class="nav-item dropdown">
			<a class="dropdown-toggle" href="javascript:void(0);">
				<span class="icon-holder">
					<i class="ti-layout-media-overlay"></i>
				</span>
				<span class="title">통계관리</span>
				<span class="arrow">
					<i class="ti-angle-right"></i>
				</span>
			</a>
			<ul class="dropdown-menu">
				<li class="active">
					<a href="#n">상품별 매출통계</a>
				</li>
			</ul>
		</li> -->
		<li class="nav-item dropdown">
			<a class="dropdown-toggle" href="javascript:void(0);">
				<span class="icon-holder">
					<i class="ti-pie-chart"></i>
				</span>
				<span class="title">시스템 관리</span>
				<span class="arrow">
					<i class="ti-angle-right"></i>
				</span>
			</a>
			<ul class="dropdown-menu">
				<li>
					<a href="/adm/sst/adminManagerList.do">관리자 관리</a>
				</li>
				<li>
					<a href="/adm/sst/codeManagerList.do">공통 코드관리</a>
				</li>
			</ul>
		</li>
	</ul>
</div>
