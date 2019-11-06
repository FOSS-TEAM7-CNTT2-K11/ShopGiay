<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:if test="${prePage != null }">
	<div style="text-align: right;">
	<a href="${prePage}"
		class="btn btn-primary mx-3 my-2 float-left">Quay lại</a>
</div>
</c:if>
<div>

	<div class="card-body">
		<div class="table-responsive">
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
								class="alert alert-danger">Lỗi lấy chi tiết đơn hàng. Vui
								lòng thử lại!</td>
						</tr>
					</c:if>
					<c:forEach items="${checkoutDetail }" var="ch" varStatus="i">
						<tr>
							<td>${i.index+1 }</td>
							<td><b><a href="${pageContext.request.contextPath}/admin/view-user?accId=${ch.account.id }">${ch.account.username }</a></b></td>
							<td><a href="${pageContext.request.contextPath }/admin/product/detail/${ch.productDetail.id}">${ch.productDetail.product.name }</a></td>
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