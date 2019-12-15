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
				<a href="${pageContext.request.contextPath }/user/view-history-bill">Lịch sử mua hàng</a>
				<span class="mx-2 mb-0">/</span>
				<strong class="text-black">Chi tiết đơn hàng</strong>
			</div>
		</div>
	</div>
</div>


<div>
	<div class="site-section">
		<div class="container">
		<div><a href="${pageContext.request.contextPath }/user/view-history-bill" class="btn btn-primary mb-2">Quay lại</a></div>
			<table class="table table-bordered" id="dataTable">
				<thead>
					<tr>
						<th>STT</th>
						<th>Tên khách hàng</th>
						<th>Tên sản phẩm</th>
						<th>Màu</th>
						<th>Kích thước</th>
						<th>Số lượng</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th>STT</th>
						<th>Tên khách hàng</th>
						<th>Tên sản phẩm</th>
						<th>Màu</th>
						<th>Kích thước</th>
						<th>Số lượng</th>
					</tr>
				</tfoot>
				<tbody>
					<c:if test="${checkoutDetail.size() == 0 }">
						<tr>
							<td colspan="8" style="text-align: center"
								class="alert alert-danger">Chi tiết đơn hàng trống!</td>
						</tr>
					</c:if>
					<c:forEach items="${checkoutDetail }" var="ch" varStatus="i">
						<tr>
							<td>${i.index+1 }</td>
							<td><b>${ch.account.username }</b></td>
							<td><a href="${pageContext.request.contextPath }/single-shop?productId=${ch.productDetail.product.id}">${ch.productDetail.product.name }</a></td>
							<td><div
									style="background-color: ${ch.productDetail.color.color }; width:30%; height: 20px; margin:auto"></div></td>
							<td>${ch.productDetail.size.size}</td>
							<td>${ch.amount}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>