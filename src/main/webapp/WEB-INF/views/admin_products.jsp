<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div style="text-align: right;">
	<a href="${pageContext.request.contextPath}/admin/product/add" class="btn btn-success mx-3">Thêm sản phẩm</a>
</div>
<div class="product-div">
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-bordered" id="dataTable">
				<thead>
					<tr>
						<th>STT</th>
						<th>Tên sản phẩm</th>
						<th>Ảnh đại diện</th>
						<th>Images</th>
						<th>Price</th>
						<th>Sale</th>
						<th>Ngày tạo</th>
						<th>Mô tả</th>
						<th>More</th>
						
						
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th>STT</th>
						<th>Tên sản phẩm</th>
						<th>Ảnh đại diện</th>
						<th>Images</th>
						<th>Price</th>
						<th>Sale</th>
						<th>Ngày tạo</th>
						<th>Mô tả</th>
						<th>More</th>
						
					</tr>
				</tfoot>
				<tbody>
					<c:if test="${products.size() == 0 }">
						<tr>
							<td colspan="8" style="text-align: center" class="alert alert-danger">Chưa có sản phẩm nào.</td>
						</tr>
					</c:if>
					<c:forEach items="${products }" var="p" varStatus="i">
						<tr>
							<td>${i.index+1 }</td>
							<td>${p.name }</td>
							<td><img src="${pageContext.request.contextPath }/images/${p.thumbnail}"></td>
							<td><a href="${pageContext.request.contextPath }/admin/product/images?productId=${p.id}" class="btn btn-primary">Quản lý</a></td>
							<td>${p.price }</td>
							<td>${p.salePrice }%</td>
							
							<td><fmt:formatDate value="${p.created }"
									pattern="dd/MM/yyyy : HH:mm:ss" /></td>
										<td>${p.description }</td>
							<td class="d-flex">
								<a href="${pageContext.request.contextPath }/admin/product/detail?productId=${p.id}" class="btn btn-primary">Chi tiết</a>
								
								<a href="${pageContext.request.contextPath }/admin/product/update?productId=${p.id}" class="btn btn-warning mx-2">Sửa</a>
								
								
								<form method="post" class="form-inline"
									action="${pageContext.request.contextPath }/admin/product/delete">
									<input type="hidden" name="productId" value="${p.id }">
									<button class="btn btn-danger" onclick="return confirm('Xóa sản phẩm gốc sẽ xóa tất cả sản phẩm con và hình ảnh. Chắc chăn xóa ${p.name}?')">Xóa</button>
								</form>
							</td>
						
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>