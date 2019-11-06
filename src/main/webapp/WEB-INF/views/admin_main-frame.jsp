<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>${titlePage }</title>


<!-- Custom fonts for this template-->
<link
	href="${pageContext.request.contextPath }/static/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
	
	

<!-- Page level plugin CSS-->
<link
	href="${pageContext.request.contextPath }/static/vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="${pageContext.request.contextPath }/static/css/sb-admin.css"
	rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/my-custom-css.css" type="text/css">
</head>

<body id="page-top">

	<jsp:include page="/WEB-INF/layouts/admin_navbar.jsp"></jsp:include>

	<div id="wrapper">
		<!-- Sidebar -->
		<jsp:include page="/WEB-INF/layouts/admin_sidebar.jsp"></jsp:include>

		<div id="content-wrapper">

			<div class="container-fluid">

				<!-- Breadcrumbs-->
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><h3>${titleContent}</h3></li>
				</ol>
				<c:if test="${statusMessage !=null }">
					<c:if test="${statusMessage.error }">
						<div class="alert alert-danger">${statusMessage.message }</div>
					</c:if>
					<c:if test="${!statusMessage.error }">
						<div class="alert alert-success">${statusMessage.message }</div>
					</c:if>
				</c:if>
				<!-- DataTables Example -->
				<div>
					<jsp:include page="/WEB-INF/views/${page}.jsp"></jsp:include>
				</div>
			</div>
			<!-- /.container-fluid -->

			<!-- Sticky Footer -->
			<footer class="sticky-footer">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright © Your Website 2019</span>
					</div>
				</div>
			</footer>

		</div>
		<!-- /.content-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.html">Logout</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script
		src="${pageContext.request.contextPath }/static/vendor/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/js/my-js-custom.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="${pageContext.request.contextPath }/static/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Page level plugin JavaScript-->
	<script
		src="${pageContext.request.contextPath }/static/vendor/chart.js/Chart.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/vendor/datatables/jquery.dataTables.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/vendor/datatables/dataTables.bootstrap4.js"></script>

	<!-- Custom scripts for all pages-->
	<script
		src="${pageContext.request.contextPath }/static/js/sb-admin.min.js"></script>

	<!-- Demo scripts for this page-->
	<script
		src="${pageContext.request.contextPath }/static/js/demo/datatables-demo.js"></script>
	<script
		src="${pageContext.request.contextPath }/static/js/demo/chart-area-demo.js"></script>

</body>

</html>
