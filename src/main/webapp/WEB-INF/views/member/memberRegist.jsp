<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<title>회원 등록</title>

<!-- Font Awesome Icons -->
<link rel="stylesheet" href="${cp }/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${cp }/resources/bootstrap/dist/css/adminlte.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	$(function() {
		
		// 주소 검색 버튼이 클릭되었을 때 다음주소 api 팝업을 연다
		$("#addrBtn").on("click", function() {
			new daum.Postcode({
				oncomplete : function(data) {

					$("#addr1").val(data.roadAddress); //도로주소
					$("#zipcode").val(data.zonecode); //우편번호
					
					// 사용자 편의성을 위해 상세주소 입력 input태그로 focus 설정 -  도로주소를 입력하면 커서가 상세주소로
					$("#addr2").focus();
				}
			}).open();

		})
		$("#registBtn").on("click", function() {
			var userid = $("#userid").val();
			var pass = $("#pass").val();
			var usernm = $("#usernm").val();
			if(userid==""){
				alert("아이디는 필수 입력사항입니다.")
				return false
			}
			if(pass==""){
				alert("비밀번호는 필수 입력사항입니다.")
				return false
			}
			if(usernm==""){
				alert("이름은 필수 입력사항입니다.")
				return false
			}
			$("#frm").submit();
		})
		$("#cancelBtn").on("click", function() {
			$("#userid").val("");
			$("#pass").val("");
			$("#usernm").val("");
			$("#alias").val("");
			$("#addr1").val("");
			$("#addr2").val("");
			$("#zipcode").val("");
			
		})
		
		
	})
	
	function idCheck() {
	$.ajax({
		type : "post",
		data : userid,
		url : "/member/idCheck",
		dataType : "json",
		data : { "userid" : $("#userid").val()},
		success : function(data) {
			if(data == 1) {
				alert("존재하는 아이디 입니다. 다른 아이디를 입력하세요.");
				$("#submit").attr("disabled", "disabled");
			} else if(data == 0) {
				alert("사용 가능한 아이디 입니다.");
				$("#submit").removeAttr("disabled");
			}
		}
	});
}

</script>

</head>
<body class="hold-transition sidebar-mini">
	<div class="wrapper">

		<!-- Navbar -->
		<nav class="main-header navbar navbar-expand navbar-white navbar-light">
			<!-- Left navbar links -->
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" data-widget="pushmenu" href="#"><i class="fas fa-bars"></i></a></li>
				<li class="nav-item d-none d-sm-inline-block"><a href="#" class="nav-link">
</a></li>
				<li class="nav-item d-none d-sm-inline-block"><a href="#;" class="nav-link">회원관리</a></li>
			</ul>

			<!-- SEARCH FORM -->
			<form class="form-inline ml-3">
				<div class="input-group input-group-sm">
					<input class="form-control form-control-navbar" type="search" placeholder="Search" aria-label="Search">
					<div class="input-group-append">
						<button class="btn btn-navbar" type="submit">
							<i class="fas fa-search"></i>
						</button>
					</div>
				</div>
			</form>

			<!-- Right navbar links -->
			<ul class="navbar-nav ml-auto">
				<!-- Messages Dropdown Menu -->
				<li class="nav-item dropdown"><a class="nav-link" data-toggle="dropdown" href="#"> <i class="far fa-comments"></i> <span class="badge badge-danger navbar-badge">3</span>
				</a>
					<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
						<a href="#" class="dropdown-item"> <!-- Message Start -->
							<div class="media">
								<img src="./resources/bootstrap/dist/img/user1-128x128.jpg" alt="User Avatar" class="img-size-50 mr-3 img-circle">
								<div class="media-body">
									<h3 class="dropdown-item-title">
										Brad Diesel <span class="float-right text-sm text-danger"><i class="fas fa-star"></i></span>
									</h3>
									<p class="text-sm">Call me whenever you can...</p>
									<p class="text-sm text-muted">
										<i class="far fa-clock mr-1"></i> 4 Hours Ago
									</p>
								</div>
							</div> <!-- Message End -->
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item"> <!-- Message Start -->
							<div class="media">
								<img src="${cp }/resources/bootstrap/dist/img/user8-128x128.jpg" alt="User Avatar" class="img-size-50 img-circle mr-3">
								<div class="media-body">
									<h3 class="dropdown-item-title">
										John Pierce <span class="float-right text-sm text-muted"><i class="fas fa-star"></i></span>
									</h3>
									<p class="text-sm">I got your message bro</p>
									<p class="text-sm text-muted">
										<i class="far fa-clock mr-1"></i> 4 Hours Ago
									</p>
								</div>
							</div> <!-- Message End -->
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item"> <!-- Message Start -->
							<div class="media">
								<img src="${cp }/resources/bootstrap/dist/img/user3-128x128.jpg" alt="User Avatar" class="img-size-50 img-circle mr-3">
								<div class="media-body">
									<h3 class="dropdown-item-title">
										Nora Silvester <span class="float-right text-sm text-warning"><i class="fas fa-star"></i></span>
									</h3>
									<p class="text-sm">The subject goes here</p>
									<p class="text-sm text-muted">
										<i class="far fa-clock mr-1"></i> 4 Hours Ago
									</p>
								</div>
							</div> <!-- Message End -->
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item dropdown-footer">See All Messages</a>
					</div></li>
				<!-- Notifications Dropdown Menu -->
				<li class="nav-item dropdown">
					<a class="nav-link" data-toggle="dropdown" href="#">
						<i class="far fa-bell"></i> <span class="badge badge-warning navbar-badge">15</span>
					</a>
					<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
						<span class="dropdown-header">15 Notifications</span>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item"> <i class="fas fa-envelope mr-2"></i> 4 new messages <span class="float-right text-muted text-sm">3 mins</span>
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item"> <i class="fas fa-users mr-2"></i> 8 friend requests <span class="float-right text-muted text-sm">12 hours</span>
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item"> <i class="fas fa-file mr-2"></i> 3 new reports <span class="float-right text-muted text-sm">2 days</span>
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item dropdown-footer">See All Notifications</a>
					</div></li>
				<li class="nav-item"><a class="nav-link" data-widget="control-sidebar" data-slide="true" href="#"><i class="fas fa-th-large"></i></a></li>
			</ul>
		</nav>
		<!-- /.navbar -->


		<!-- Main Sidebar Container -->
		<aside class="main-sidebar sidebar-dark-primary elevation-4">
			<!-- Brand Logo -->
			<a href="#" class="brand-link">
				<img src="${cp }/resources/images/line.png" class="brand-image img-circle elevation-3" style="opacity: .8">
				<span class="brand-text font-weight-light">회원 관리</span>
			</a>

			<!-- Sidebar -->
			<div class="sidebar">
				<!-- Sidebar user panel (optional) -->
				<div class="user-panel mt-3 pb-3 mb-3 d-flex">
						<div class="image">
							<img src="${cp}/member/profile?userid=${S_USER.userid}" class="img-circle elevation-2" alt="User Image">
						</div>
						<div class="info">
							<div class="row">
								<a class="col-md-8" href="#" class="d-block">${S_USER.usernm }</a>
							</div>
						</div>
					</div>

				<!-- Sidebar Menu -->
				<nav class="mt-2">
					<ul class="nav nav-pills nav-sidebar flex-column subMenuList" data-widget="treeview" data-accordion="false">

					</ul>
				</nav>
				<!-- /.sidebar-menu -->
			</div>

			<!-- /.sidebar -->
		</aside>


		<div id="if_list_div" style="position: relative; padding: 0; overflow: hidden;">
			<!-- Content Wrapper. Contains page content -->
			<div class="content-wrapper">

				<!-- Main content -->
				<section class="content register-page" style="height:100%;">
					<div class="container-fluid">
						<div class="login-logo">
							<b>회원 등록</b>
						</div>
						<!-- form start -->
						<div class="card">
							<div class="register-card-body">
								<form id="frm" role="form" class="form-horizontal" method="post" action="${cp }/member/registUser" enctype="multipart/form-data">
									<div class="input-group mb-3">
										<div class="mailbox-attachments clearfix" style="text-align: center; width:100%;">
											<div class="mailbox-attachment-icon has-img" id="pictureView" style="border: 1px solid green; height: 200px; width: 140px; margin: 0 auto;">
												<img id="pictureViewImg" style="width:100%; height:100%;"/>
											</div>
											<div class="mailbox-attachment-info">
												<div class="input-group input-group-sm">
													<input id="picture" class="form-control"
													
														   type="file" name="profile" accept=".gif, .jpg, .png" style="height:37px;"/>
												</div>
											</div>
										</div>
										<br />
									</div>
									
									<div class="form-group row">
										<label for="id" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;">*</span>아이디
										</label>
										<div class="col-sm-9 input-group-sm">
											<input name="userid" type="text" class="form-control" id="userid" placeholder="회원 id">
											<span style="color: red;"><form:errors path="memVo.userid" /></span> 
											<button type="button" id="idCkeck" onclick="idCkeck();">중복확인</button>
										</div>
									</div>
								
									
									<div class="form-group row">
										<label for="pwd" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;">*</span>패스워드</label>
										<div class="col-sm-9 input-group-sm">
											<input class="form-control" name="pass" type="password" class="form-control" id="pass" placeholder="비밀번호" />
										</div>
									</div>
									
									<div class="form-group row">
										<label for="name" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;">*</span>이 름
										</label>
										<div class="col-sm-9 input-group-sm">
											<input class="form-control" name="usernm" type="text" id="usernm" placeholder="이름" />
										</div>

									</div>
									<div class="form-group row">
										<label for="alias" class="col-sm-3" style="font-size: 0.9em;">별명</label>
										<div class="col-sm-9 input-group-sm">
											<input class="form-control" name="alias" type="text" id="alias" placeholder="별명">
										</div>
									</div>
									<div class="form-group row">
										<label for="addr1" class="col-sm-3 control-label">주소</label>
										<div class="col-sm-3 input-group-sm">
											<input name="addr1" type="text" class="form-control" id="addr1" placeholder="주소" readonly>
										</div>
										<div class="col-sm-3 input-group-sm">
											<input name="addr2" type="text" class="form-control" id="addr2" placeholder="상세주소">	
										</div>
										
										<div class="col-sm-2 input-group-sm">
											<input name="zipcode" type="text" class="form-control" id="zipcode" placeholder="우편번호" readonly>
										</div>
										<div class="col-sm-1 input-group-sm">
											<span class="input-group-append-sm">
												<button type="button"  id ="addrBtn" class="btn btn-info btn-sm btn-append">주소검색</button>
											</span>
										</div>
									</div>
									
									<div class="card-footer">
										<div class="row">
											<div class="col-sm-6">
												<button type="button" id="registBtn" class="btn btn-info">등록</button>
											</div>

											<div class="col-sm-6">
												<button type="button" id="cancelBtn"  class="btn btn-default float-right">&nbsp;&nbsp;&nbsp;취 &nbsp;&nbsp;소&nbsp;&nbsp;&nbsp;</button>
											</div>

										</div>
									</div>
								</form>
							</div>
							<!-- register-card-body -->
						</div>
					</div>
				</section>
				<!-- /.content -->
			</div>
			<!-- /.content-wrapper -->
		</div>
	</div>

	<!-- Main Footer -->
	<footer class="main-footer">
		<!-- To the right -->
		<div class="float-right d-none d-sm-inline">Anything you want</div>
		<!-- Default to the left -->
		<strong>Copyright &copy; 2014-2019 <a href="https://adminlte.io">AdminLTE.io</a>.
		</strong> All rights reserved.
	</footer>
	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED SCRIPTS -->

	<!-- jQuery -->
	<script src="${cp }/resources/bootstrap/plugins/jquery/jquery.min.js"></script>

	<!-- Bootstrap 4 -->
	<script src="${cp }/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="${cp }/resources/bootstrap/dist/js/adminlte.min.js"></script>
	<script>
		$(document).ready(function(){
			// picture input의 파일 변경시 이벤트 
			$("#picture").change(function(){
			   readURL(this);
			});
		});
		
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
			  
				reader.onload = function (e) {
					$('#pictureViewImg').attr('src', e.target.result);  
				}
				reader.readAsDataURL(input.files[0]);
			}
		}
			 
		
	</script>
</body>
</html>






