<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<% pageContext.setAttribute("crlf", "\r\n"); %>
<c:set var="nowDate" value="<%=new java.util.Date() %>" />

<!DOCTYPE HTML>
<html lang="ko">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no, shrink-to-fit=no">
		<title>Allblanc</title>
		
		<!-- Favicon -->
		<link rel="shortcut icon" href="/resources/adm/images/logo/favicon.png">
		
		<!-- plugins css -->
		<link rel="stylesheet" href="/resources/adm/vendors/bootstrap/dist/css/bootstrap.css" />
		<link rel="stylesheet" href="/resources/adm/vendors/PACE/themes/blue/pace-theme-minimal.css" />
		<link rel="stylesheet" href="/resources/adm/vendors/perfect-scrollbar/css/perfect-scrollbar.min.css" />
		
		<!-- page plugins css -->
		<link rel="stylesheet" href="/resources/adm/vendors/datatables/media/css/jquery.dataTables.css" />
		<link rel="stylesheet" href="/resources/adm/vendors/fullcalendar/dist/fullcalendar.min.css" />
		<link rel="stylesheet" href="/resources/adm/vendors/bootstrap-daterangepicker/daterangepicker.css" />
		<link rel="stylesheet" href="/resources/adm/vendors/bootstrap-datepicker/dist/css/bootstrap-datepicker3.css" />
		
		<!-- core css -->
		<link href="/resources/adm/css/ei-icon.css" rel="stylesheet">
		<link href="/resources/adm/css/themify-icons.css" rel="stylesheet">
		<link href="/resources/adm/css/font-awesome.min.css" rel="stylesheet">
		<link href="/resources/adm/css/animate.min.css" rel="stylesheet">
		<link href="/resources/adm/css/app.css" rel="stylesheet">
		    
		<link href="/resources/adm/css/style.css" rel="stylesheet">
		
		<script src="/resources/adm/vendors/jquery/dist/jquery.min.js"></script>
	</head>
	<body>
		<div class="app">
			<div class="authentication bg" style="background-image: url('/resources/adm/images/others/img-32.jpg')">
				<div class="page-500 container">
					<div class="full-height">
						<div class="vertical-align full-height pdd-horizon-70">
							<div class="table-cell">
								<div class="text-center">
									<h1 class="font-size-100 text-white ls-3 lh-1"><b></b></h1>
									<p class="font-size-28 text-light text-white text-opacity lh-1-4 mrg-btm-30">심각한 오류가 발생하였습니다.<br class="hidden-sm hidden-xs"> 관리자에게 문의 바랍니다.</p>
									<a href="javascript:history.back();" class="btn btn-info">뒤로 가기</a>
								</div>
							</div>
						</div>
					</div>		
				</div>
			</div>
		</div>
		<!-- build:js /resources/adm/js/vendor.js -->
		<!-- plugins js -->
		<script src="/resources/adm/vendors/popper.js/dist/umd/popper.min.js"></script>
		<script src="/resources/adm/vendors/bootstrap/dist/js/bootstrap.js"></script>
		<script src="/resources/adm/vendors/PACE/pace.min.js"></script>
		<script src="/resources/adm/vendors/perfect-scrollbar/js/perfect-scrollbar.jquery.js"></script>
		<!-- endbuild -->
		
		<!-- build:js /resources/adm/js/app.min.js -->
		<!-- core js -->
		<script src="/resources/adm/js/app.js"></script>
		<!-- endbuild -->

		<!-- page plugins js -->
		<script src="/resources/adm/vendors/jquery-validation/dist/jquery.validate.min.js"></script>
		<script src="/resources/adm/vendors/datatables/media/js/jquery.dataTables.js"></script>
		
		<!-- page js -->
		<script src="/resources/adm/js/forms/form-validation.js"></script>
		<script src="/resources/adm/js/table/data-table.js"></script>
	</body>
</html>
