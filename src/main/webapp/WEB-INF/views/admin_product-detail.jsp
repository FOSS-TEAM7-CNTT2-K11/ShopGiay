<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div style="text-align: right;">
	<a href="${pageContext.request.contextPath}/admin/product"
		class="btn btn-primary mx-3 float-left">Quay lại</a> <a
		href="${pageContext.request.contextPath}/admin/product/detail/add?productId=${productId}"
		class="btn btn-success mx-3">Thêm chi tiết sản phẩm</a>
</div>
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
						<th>Số lượng còn</th>
						<th>
							<form method="post"
								action="${pageContext.request.contextPath }/admin/product/detail/clear">
								<input type="hidden" name="productId" value="${productId }" />
								<button
									onclick="return confirm('Chắc chắn xóa tất cả chi tiết sản phẩm?')"
									class="btn btn-danger">Xóa tất cả</button>
							</form>
						</th>
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
						<th>Số lượng còn</th>
						<th>
							<form method="post"
								action="${pageContext.request.contextPath }/admin/product/detail/clear">
								<input type="hidden" name="productId" value="${productId }" />
								<button
									onclick="return confirm('Chắc chắn xóa tất cả chi tiết sản phẩm?')"
									class="btn btn-danger">Xóa tất cả</button>
							</form>
						</th>

					</tr>
				</tfoot>
				<tbody>
					<c:if test="${productDetailModels.size() == 0 }">
						<tr>
							<td colspan="8" style="text-align: center"
								class="alert alert-danger">Bạn chưa thêm chi tiết sản phẩm.</td>
						</tr>
					</c:if>
					<c:forEach items="${productDetailModels }" var="pdm" varStatus="i">
						<tr>
							<td>${i.index+1 }</td>
							<td>${pdm.product.name }</td>
							<td>${pdm.product.price }</td>
							<td><a><img
									src="${pageContext.request.contextPath }/images/${pdm.product.thumbnail}" /></a></td>
							<td><div
									style="background-color: ${pdm.color.color }; width:30%; height: 20px; margin:auto"></div></td>
							<td>${pdm.size.size }</td>
							<td>${pdm.amount}</td>
							<td class="d-flex"><a class="btn btn-warning"
								href="${pageContext.request.contextPath }/admin/product/detail/update?id=${pdm.id}">Sửa</a>
								<form method="post" class="form-inline"
									action="${pageContext.request.contextPath }/admin/product/detail/delete">
									<input type="hidden" name="id" value="${pdm.id }"> <input
										type="hidden" name="productId" value="${pdm.product.id }" />
									<button
										onclick="return confirm('Chắc chắn xóa ${pdm.product.name} - ${pdm.color.color } - ${pdm.size.size }?')"
										class="btn btn-danger mx-2">Xóa</button>
								</form></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>