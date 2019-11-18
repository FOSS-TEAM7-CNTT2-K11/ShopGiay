<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="site-section block-8">
	<div class="container">
		<div class="row justify-content-center  mb-5">
			<div class="col-md-7 site-section-heading text-center pt-4">
				<h2>Big Sale!</h2>
			</div>
		</div>
		<div class="row align-items-center">
			<div class="col-md-12 col-lg-7 mb-5">
				<a href="${pageContext.request.contextPath }/big-sale"><img src="${pageContext.request.contextPath }/static/_client/images/big-sale.jpeg" alt="Image placeholder"
					class="img-fluid rounded"></a>
			</div>
			<div class="col-md-12 col-lg-5 text-center pl-md-5">
				<h2>
					<a href="#">Giảm giá cực mạnh</a>
				</h2>
				<!-- <p class="post-meta mb-4">
					By <a href="#">Carl Smith</a> <span class="block-8-sep">&bullet;</span>
					September 3, 2018
				</p> -->
				<p>Thử lướt ngay các sản phẩm đang giảm giá sốc bên cửa hảng của chúng tôi!</p>
				<p>
					<a href="${pageContext.request.contextPath }/big-sale" class="btn btn-primary btn-sm">Vào khu giảm giá sốc</a>
				</p>
			</div>
		</div>
	</div>
</div>