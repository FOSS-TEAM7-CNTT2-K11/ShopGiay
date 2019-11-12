<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul class="sidebar navbar-nav">
	
	<li class="nav-item active">
		<a class="nav-link"
			href="${pageContext.request.contextPath }/admin/categories">
			<i class="fas fa-fw fa-tachometer-alt"></i>
			<span>Quản lý danh mục</span>
		</a>
	</li>
	<li class="nav-item active">
		<a class="nav-link" href="${pageContext.request.contextPath }/admin/product">
			<i class="fas fa-fw fa-tachometer-alt"></i>
			<span>Quản lý sản phẩm</span>
		</a>
	</li>
	<li class="nav-item active dropdown">
		<a class="nav-link dropdown-toggle" href="" id="pagesDropdown"
			role="button" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="false">
			<i class="fas fa-fw fa-folder"></i>
			<span>Quản lý giao dịch</span>
		</a>
		<div class="dropdown-menu" aria-labelledby="pagesDropdown">
			<a class="dropdown-item" href="${pageContext.request.contextPath }/admin/checkout">Tất cả giao dịch</a>
			<a class="dropdown-item" href="${pageContext.request.contextPath }/admin/checkout/waiting-checkout">Đơn hàng đang chờ</a>
			<a class="dropdown-item" href="${pageContext.request.contextPath }/admin/checkout/completing-checkout">Đơn hàng đang giao</a>
			<a class="dropdown-item" href="${pageContext.request.contextPath }/admin/checkout/completed-checkout">Đơn hàng đã giao</a>
			<a class="dropdown-item" href="${pageContext.request.contextPath }/admin/checkout/denyed-checkout">Đơn hàng bị hủy</a>
		</div>
	</li>
	
</ul>