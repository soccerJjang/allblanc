<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE HTML>
<html lang="ko">
	<head>
		<tiles:insertAttribute name="header" />
	</head>
	<body>
		<div class="app">
			<!-- Side Nav START -->
            <div class="side-nav">
				<tiles:insertAttribute name="lnb" />
			</div>
            <!-- Side Nav END -->

            <!-- Page Container START -->
            <div class="page-container">
				<!-- Header START -->
                <div class="header navbar">
					<tiles:insertAttribute name="gnb" />
				</div>
                <!-- Header END -->

                <!-- Side Panel START -->
                <div class="side-panel">
                </div>
                <!-- Side Panel END -->

                <!-- Content Wrapper START -->
                <div class="main-content">
					<tiles:insertAttribute name="contents" />
				</div>
				<!-- Content Wrapper END -->
					
                <!-- Footer START -->
				<!-- footer -->
				<footer class="content-footer">
					<tiles:insertAttribute name="footer" />
				</footer>
                <!-- Footer END -->
                
	        </div>
	        <!-- Page Container END -->
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
