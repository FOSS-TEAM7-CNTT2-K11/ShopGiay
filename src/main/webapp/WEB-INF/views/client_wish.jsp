<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="site-section">
	<div class="container">
		<div class="row mb-5">
			<form class="col-md-12" method="post">
				<div class="site-blocks-table">
					<div ng-if="carts.length > 0">
						<table class="table table-bordered" id="cartTable">
							<thead>
								<tr>
									<th class="product-thumbnail">Hình ảnh</th>
									<th class="product-name">Tên sản phẩm</th>
									<th class="product-price">Giá</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${wishes }" var="w">
									<tr>
										<td class="product-thumbnail">
											<img
												src="${pageContext.request.contextPath }/images/${w.product.thumbnail}"
												alt="Image" class="img-fluid">
										</td>
										<td class="product-name">
											<h2 class="h5 text-black">
												<a
													href="${pageContext.request.contextPath }/single-shop?productId=${w.product.id}">${w.product.name}</a>
											</h2>
										</td>

										<td>${w.product.realPrice} VNĐ</td>
										<td>
											<a
												href="${pageContext.request.contextPath }/single-shop?productId=${w.product.id}"
												class="btn btn-primary btn-sm">Đến xem</a>
												<a href="${pageContext.request.contextPath }/wish/delete?productId=${w.product.id}" class="btn btn-danger" onclick="return confirm('Chắc chắn xóa?')">Xóa bỏ</a>
										</td>
									</tr>
								</c:forEach>



							</tbody>
						</table>
					</div>
					<c:if test="${w.size() == 0 }">
						<div style="text-align: center;">Danh sách trống</div>
					</c:if>

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
		</div>
	</div>
</div>
