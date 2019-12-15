<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="bg-light py-3">
	<div class="container">
		<div class="row">
			<div class="col-md-12 mb-0">
				<a href="${pageContext.request.contextPath }/">Home</a>
				<span class="mx-2 mb-0">/</span>
				<strong class="text-black">Đơn hàng đã được gửi</strong>
			</div>
		</div>
	</div>
</div>

<div class="site-section">
	<div class="container">
		<div class="row">
			<div class="col-md-12 text-center">
				<span class="icon-check_circle display-3 text-success"></span>
				<h2 class="display-3 text-black">Thank you!</h2>
				<p class="lead mb-5">Đơn hàng của bạn đã được gửi đi. Vui lòng
					chờ xác nhận từ chúng tôi!</p>
				<p>
					<a href="${pageContext.request.contextPath }/user"
						class="btn btn-sm btn-primary">Xem thông tin đơn hàng</a>
				</p>
				<p>
					<a href="${pageContext.request.contextPath }/shop"
						class="btn btn-sm btn-primary">Tiếp tục mua sắm</a>
				</p>
			</div>
		</div>
	</div>
</div>
