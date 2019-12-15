<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<header class="site-navbar" role="banner">
	<div class="site-navbar-top">
		<div class="container">
			<div class="row align-items-center">

				<div
					class="col-6 col-md-4 order-2 order-md-1 site-search-icon text-left">
					<form action="" class="site-block-top-search">
						<span class="icon icon-search2"></span>
						<input type="text" class="form-control border-0 my-search-input"
							placeholder="Search">
					</form>
				</div>

				<div
					class="col-12 mb-3 mb-md-0 col-md-4 order-1 order-md-2 text-center">
					<div class="site-logo">
						<a href="${pageContext.request.contextPath }/home"
							class="js-logo-clone">ZuZuShoe</a>
					</div>
				</div>

				<div class="col-6 col-md-4 order-3 order-md-3 text-right">
					<div class="site-top-icons">
						<ul>
							<li>

								<sec:authorize access="hasRole('USER')">
									<a
										href="${pageContext.request.contextPath }/user"
										title="Tài khoản: ${account.username }">
										<span class="icon icon-person"></span>
									</a>
									<a href="${pageContext.request.contextPath }/logout" title="Đăng xuất khỏi hệ thống">Logout</a>
								</sec:authorize>
								<sec:authorize access="!hasRole('USER')">
									<a href="${pageContext.request.contextPath }/login" title="Bạn đã có tài khoản? Đăng nhập nào!">Login</a>/
									<a href="${pageContext.request.contextPath }/register" title="Bạn chưa có tài khoản? Đăng ký ngay nào!">Register</a> 
								</sec:authorize>
							</li>
							<li>
								<a href="${pageContext.request.contextPath }/wish"
									title="Danh sách yêu thích của bạn">
									<span class="icon icon-heart-o"></span>
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath }/cart?accountId=${account.id}"
									class="site-cart" title="Giỏ hàng">
									<span class="icon icon-shopping_cart"></span>
									<sec:authorize access="hasRole('USER')">
										<span class="count">${cartItems.size() }</span>
									</sec:authorize>
									<sec:authorize access="!hasRole('USER')">
									<span class="count">0</span>
								</sec:authorize>
								</a>
							</li>
							<li class="d-inline-block d-md-none ml-md-0">
								<a href="#" class="site-menu-toggle js-menu-toggle">
									<span class="icon-menu"></span>
								</a>
							</li>
						</ul>
					</div>
				</div>

			</div>
		</div>
	</div>
	<nav class="site-navigation text-right text-md-center"
		role="navigation">
		<div class="container">
			<ul class="site-menu js-clone-nav d-none d-md-block">
				<li>
					<a class="a-home" style=""
						href="${pageContext.request.contextPath }/home">
						<b>Home</b>
					</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath }/about">Giới thiệu</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath }/shop">Vào cửa hàng</a>
				</li>

				<li>
					<a href="${pageContext.request.contextPath }/contact">Liên hệ</a>
				</li>
			</ul>
		</div>
	</nav>
</header>