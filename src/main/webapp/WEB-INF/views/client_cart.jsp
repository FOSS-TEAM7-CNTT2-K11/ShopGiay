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
				<strong class="text-black">Cart</strong>
			</div>
		</div>
	</div>
</div>

<c:if test="${message != null}">
	<script>
		alert("${message.message}");
	</script>
</c:if>

<div class="site-section" ng-app="myapp">
	<div class="container" ng-controller="cartController"
		ng-init="getCart()">
		<div class="row mb-5">
			<form class="col-md-12" method="post">
				<div class="site-blocks-table">
					<div ng-if="carts.length > 0">
						<table class="table table-bordered" id="cartTable">
							<thead>
								<tr>
									<th class="product-thumbnail">Hình ảnh</th>
									<th class="product-name">Tên sản phẩm</th>
									<th class="product-thumbnail">Màu sắc</th>
									<th class="product-thumbnail">Kích thước</th>
									<th class="product-price">Giá</th>
									<th class="product-quantity">Số lượng</th>
									<th class="product-total">Tổng</th>
									<th class="product-remove">Xóa</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="cart in carts">
									<td class="product-thumbnail">
										<img
											src="${pageContext.request.contextPath }/images/{{cart.productImage}}"
											alt="Image" class="img-fluid">
									</td>
									<td class="product-name">
										<h2 class="h5 text-black">{{cart.productName}}</h2>
									</td>

									<td class="product-name" style="text-align: center; padding: 0px;">
										<div style="margin: auto ;width: 80px; height: 50px; background: {{cart.color}}; "></div>
									</td>

									<td>
										{{cart.size}}
									</td>
									<td>{{cart.realPrice}} VNĐ</td>
									<td>
										<div class="input-group mb-3" style="min-width: 120px; max-width: 120px;">
											<div class="input-group-prepend">
												<a  ng-click="changeAmount(cart.amountSale - 1, $index)"  class="btn btn-outline-primary js-btn-minus" href="">&minus;</a>
											</div>
											<input type="text" class="form-control text-center"
												value="{{cart.amountSale}}" placeholder=""
												aria-label="Example text with button addon"
												aria-describedby="button-addon1">
											<div class="input-group-append">
												<a ng-click="changeAmount(cart.amountSale + 1, $index)" class="btn btn-outline-primary js-btn-plus" href="">&plus;</a>
											</div>
										</div>

									</td>
									<td>{{cart.totalPrice}}</td>
									<td>
										<a ng-click="delCart($index)" class="btn btn-primary btn-sm">X</a>
									</td>
								</tr>


							</tbody>
						</table>
					</div>
					<div ng-if="carts.length == 0" style="text-align: center;">Giỏ hàng trống</div>

				</div>
			</form>
		</div>

		<div class="row">
			<div class="col-md-6">
				<div class="row mb-5">

					<div class="col-md-6">
						<a href="${pageContext.request.contextPath }/shop"
							class="btn btn-outline-primary btn-sm btn-block">Tiếp tục mua
							sắm</a>
					</div>
				</div>
			</div>
			<div class="col-md-6 pl-5">
				<div class="row justify-content-end">
					<div class="col-md-7">
						<div class="row">
							<div class="col-md-12 text-right border-bottom mb-5">
								<h3 class="text-black h4 text-uppercase">Cart Totals</h3>
							</div>
						</div>

						<div class="row mb-5">
							<div class="col-md-6">
								<span class="text-black">Tổng tiền</span>
							</div>
							<div class="col-md-6 text-right">
								<strong class="text-black">{{cartTotal}} VNĐ</strong>
							</div>
						</div>

						<div class="row">
							<div class="col-md-12">
								<div ng-if="carts.length > 0">
									<a href="${pageContext.request.contextPath  }/cart/checkout"
										class="btn btn-primary btn-lg py-3 btn-block"
										onclick="return confirm('Xác nhận thanh toán?')">Thanh
										toán</a>
								</div>
								<div ng-if="carts.length == 0">
									<a href="" class="btn btn-primary btn-lg py-3 btn-block"
										style="background-color: grey;">Thanh toán</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
