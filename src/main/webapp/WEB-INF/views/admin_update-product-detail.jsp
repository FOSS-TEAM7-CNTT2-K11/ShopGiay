<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div>
	<form action="${pageContext.request.contextPath }/admin/product/detail/update" method="post">
		<input type="hidden" name="id" value="${productDetail.id}">
		<input type="hidden" name="productId" value="${productDetail.product.id}">
		<div class="form-group">
			<b>Tên sản phẩm:</b>
			<input type="text" value="${productDetail.product.name}" readonly="readonly" class="form-control"/>
		</div>
		<div class="form-group">
			<b>Ảnh đại diện:</b>
			<img src="${pageContext.request.contextPath }/images/${productDetail.product.thumbnail }" alt="Ảnh đại diện" class=" col-md-3 col-xs-6">
		</div>
		<div class="form-group">
			<label for="color"><b>Màu:</b></label>
			<input value="${productDetail.color.color }" type="color" readonly="readonly" disabled="disabled" class="form-control col-xs-3 col-md-1 "/>
		</div>
		<div class="form-group">
			<label for="size"><b>Size:</b></label>
			<input value="${productDetail.size.size }" required="required" type="text" readonly="readonly" class="form-control col-xs-6 col-md-3"/>
		</div>
		
		<div class="form-group">
			<label for="amount"><b>Số lượng:</b></label>
			<c:if test="${statusMessage.error==true }"><br><span style="color: red; font-size: 12px;">Vui lòng nhập số!</span></c:if>
			<input value="${productDetail.amount }" required="required" type="number" name="amount" id="amount" min="0" placeholder="Enter số lượng..." class="form-control col-xs-6 col-md-3"/>
			<span style="font-size: 12px; color: blue"><i>Tip: Bạn muốn sửa màu và size có thể ấn quay lại và ấn thêm mới!</i></span>
		</div>
		<div class="form-group">
			<button class="btn btn-success mx-2">Update</button>
			<a class="btn btn-primary" href="${pageContext.request.contextPath }/admin/product/detail?productId=${productDetail.product.id}" onclick="return confirm('Quay lại sẽ mất dữ liệu bạn vừa nhập. Tiếp tục?')">Quay lại</a>
		</div>
	</form>
</div>