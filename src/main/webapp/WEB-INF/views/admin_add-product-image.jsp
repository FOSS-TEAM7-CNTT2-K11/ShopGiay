<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	
<div>
	<form action="${pageContext.request.contextPath }/admin/product/images/add" method="post" enctype="multipart/form-data">
	<input type="hidden" name="productId" value="${product.id }">
	<div class="form-group">
		<label><b>Tên sản phẩm:</b></label>
		<input class="form-control" type="text" value="${product.name }" disabled="disabled" readonly="readonly">
	</div>
	<div class="form-group col-xs-6 col-md-3">
		<label><b>Ảnh đại diện:</b></label>
		<img style="width: 100%;" alt="${product.description }" src="${pageContext.request.contextPath }/images/${product.thumbnail}">
	</div>
	<div class="form-group">
		<label for="image"><b>Hình ảnh chi tiết:</b></label>
		<input type="file" name="image" required="required" id="image" class="form-control-file" multiple="multiple">
	</div>
	<div class="form-group">
		<button class="btn btn-success mx-2">Thêm</button>
		<a class="btn btn-primary" onclick="return confirm('Quay lại sẽ mất dữ liệu bạn vừa nhập. Tiếp tục?')">Quay lại</a>
	</div>
	</form>
</div>