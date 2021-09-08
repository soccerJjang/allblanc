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
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no, shrink-to-fit=no">
	    <title>Allblanc</title>
	
	    <!-- Favicon -->
	    <link rel="shortcut icon" href="/resources/adm/images/logo/favicon.png">
	
	    <!-- plugins css -->
	    <link rel="stylesheet" href="/resources/adm/vendors/bootstrap/dist/css/bootstrap.css" />
	    <link rel="stylesheet" href="/resources/adm/vendors/PACE/themes/blue/pace-theme-minimal.css" />
	    <link rel="stylesheet" href="/resources/adm/vendors/perfect-scrollbar/css/perfect-scrollbar.min.css" />
	
	    <!-- core css -->
	    <link href="/resources/adm/css/ei-icon.css" rel="stylesheet">
	    <link href="/resources/adm/css/themify-icons.css" rel="stylesheet">
	    <link href="/resources/adm/css/font-awesome.min.css" rel="stylesheet">
	    <link href="/resources/adm/css/animate.min.css" rel="stylesheet">
	    <link href="/resources/adm/css/app.css" rel="stylesheet">
		<link href="/resources/adm/css/style.css" rel="stylesheet">
		
		<script src="/resources/adm/vendors/jquery/dist/jquery.min.js"></script>
		<script type="text/javaScript">
		<!--
			$(document).ready(function() {
				$('#loginProcess').click(function() {
					loginCheck();
				});
				
				$('#frm').on('keyup', '#adminId, #adminPswd', function(e) {
					if(e.keyCode == 13) {
						loginCheck();
					}
				});
			});
			
			function loginCheck() {
				if($('#adminId').val() == '') {
					swal('아이디를 입력해주세요.');
					$('#adminId').focus();
					return;	
				}
				if($('#adminPswd').val() == '') {
					swal('비밀번호를 입력해주세요.');
					$('#adminPswd').focus();
					return;	
				}
				document.frm.submit();
			}
			<c:if test="${!empty param.status and param.status eq 'fail'}">swal('관리자 정보가 존재하지 않습니다.');
			</c:if>
		-->
		</script>
	</head>
	<body>
	    <div class="app">
	        <div class="authentication">
	            <div class="sign-in">
	                <div class="row no-mrg-horizon">
	                    <div class="col-md-8 no-pdd-horizon d-none d-md-block">
	                        <div class="full-height bg" style="background-image: url('/resources/adm/images/others/img-29.jpg')">
	                        </div>
	                    </div>
	                    <div class="col-md-4 no-pdd-horizon">
	                        <div class="full-height bg-white height-100">
	                            <div class="vertical-align full-height pdd-horizon-70">
	                                <div class="table-cell">
	                                    <div class="pdd-horizon-15">
	                                        <h2>로그인</h2>
	                                        <p class="mrg-btm-15 font-size-13">아이디 및 패스워드를 입력해주세요</p>
	                                        <form id="frm" name="frm" action="/adm/lgn/loginProcess.do" method="POST">
	                                            <div class="form-group">
	                                                <input type="text" id="adminId" name="adminId" class="form-control" style="ime-mode:inactive;" placeholder="User name">
	                                            </div>
	                                            <div class="form-group">
	                                                <input type="password" id="adminPswd" name="adminPswd" class="form-control" placeholder="Password">
	                                            </div>
	                                            <button id="loginProcess" class="btn btn-info">로그인</button>
	                                        </form>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	
	    <!-- build:js /resources/adm/js/vendor.js -->
	    <!-- plugins js -->
	    <script src="/resources/adm/vendors/jquery/dist/jquery.min.js"></script>
	    <script src="/resources/adm/vendors/popper.js/dist/umd/popper.min.js"></script>
	    <script src="/resources/adm/vendors/bootstrap/dist/js/bootstrap.js"></script>
	    <script src="/resources/adm/vendors/PACE/pace.min.js"></script>
	    <script src="/resources/adm/vendors/perfect-scrollbar/js/perfect-scrollbar.jquery.js"></script>
	    <!-- endbuild -->
	
	    <!-- build:js /resources/adm/js/app.min.js -->
	    <!-- core js -->
	    <script src="/resources/adm/js/app.js"></script>
	    <!-- endbuild -->
	
	    <!-- page js -->
	</body>
</html>