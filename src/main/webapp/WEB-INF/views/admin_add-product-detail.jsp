<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
<div>
	<form action="${pageContext.request.contextPath }/admin/product/detail/add" method="post">
		<input type="hidden" name="productId" value="${product.id}">
		<div class="form-group">
			<b>Tên sản phẩm:</b>
			<input type="text" value="${product.name}" readonly="readonly" class="form-control"/>
		</div>
		<div class="form-group">
			<b>Ảnh đại diện:</b>
			<img src="${pageContext.request.contextPath }/images/${product.thumbnail }" alt="Ảnh đại diện" class=" col-md-3 col-xs-6">
		</div>
		<div class="form-group">
			<label for="color"><b>Màu:</b></label>
			<input type="color" name="color" id="color" value="${productDetailModel.color }" placeholder="Enter color..." class="form-control col-xs-3 col-md-1 "/>
		</div>
		<div class="form-group">
			<label for="size"><b>Size:</b></label>
			<input required="required" type="text" name="size" id="size" value="${productDetailModel.size }" placeholder="Enter size..." class="form-control col-xs-6 col-md-3"/>
		</div>
		
		<div class="form-group">
			<label for="amount"><b>Số lượng:</b></label>
			<c:if test="${statusMessage.error==true }"><br><span style="color: red; font-size: 12px;">Vui lòng nhập số!</span></c:if>
			<input required="required" type="number" name="amount" id="amount"  value="${productDetailModel.amount}+0" min="0" placeholder="Enter số lượng..." class="form-control col-xs-6 col-md-3"/>
		</div>
		<div class="form-group">
			<button class="btn btn-success mx-2">Thêm</button>
			<a class="btn btn-primary" href="${pageContext.request.contextPath }/admin/product/detail?id=${product.id}" onclick="return confirm('Quay lại sẽ mất dữ liệu bạn vừa nhập. Tiếp tục?')">Quay lại</a>
		</div>
	</form>
</div>