<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:if test="${prePage != null }">
	<div style="text-align: right;">
	<a href="${prePage}"
		class="btn btn-primary mx-3 mb-3 float-left">Quay lại</a>
</div>
</c:if>
<div class="product-div">
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-bordered" id="dataTable">
				<thead>
					<tr>
						<th>STT</th>
						<th>Tên sản phẩm</th>
						<th>Price</th>
						<th>Thumbnail</th>
						<th>Màu</th>
						<th>Size</th>
						<th>Số lượng</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th>STT</th>
						<th>Tên sản phẩm</th>
						<th>Price</th>
						<th>Thumbnail</th>
						<th>Màu</th>
						<th>Size</th>
						<th>Số lượng</th>
					</tr>
				</tfoot>
				<tbody>
					<c:if test="${productDetail == null }">
						<tr>
							<td colspan="8" style="text-align: center"
								class="alert alert-danger">Gặp lỗi khi xem chi tiết sản phẩm. Vui lòng thử lại!</td>
						</tr>
					</c:if>
					
						<tr>
							<td>${i.index+1 }</td>
							<td>${productDetail.product.name }</td>
							<td>${productDetail.product.price }</td>
							<td><a><img
									src="${pageContext.request.contextPath }/images/${productDetail.product.thumbnail}" /></a></td>
							<td><div
									style="background-color: ${productDetail.color.color }; width:30%; height: 20px; margin:auto"></div></td>
							<td>${productDetail.size.size }</td>
							<td>${productDetail.amount}</td>
						</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>