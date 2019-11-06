<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<script
	src="${pageContext.request.contextPath }/static/js/my-js-custom.js"></script>


<div>
	<form action="${pageContext.request.contextPath }/admin/product/add"
		method="post" enctype="multipart/form-data">
		<div class="form-group col-md-12">
			<label for="name">
				<b>Tên sản phẩm:</b>
			</label>
			<input type="text" name="name" id="name" class="form-control" value="${product.name }"
				required="required" placeholder="Tên sản phẩm" autofocus="autofocus" />
		</div>
		<div class="form-group col-md-12">
			<label for="category">
				<b>Thuộc danh mục:</b>
			</label>
			<div class="checkbox-list pl-1">
				<c:forEach items="${categories }" var="cat" varStatus="i">
					<div class="each-checkbox-div">
						<div class="my-checkbox mr-1">
						
						${LongStream.of(product.categoryId).anyMatch(cat.id)}
							<c:choose>
								<c:when test="${LongStream.of(product.categoryId).anyMatch(cat.id) }">
									<input class="my-input"
										style="opacity: 0; width: 100%; height: 100%;"
										id="myCheckbox${i.index }" type="checkbox" checked="checked"
										name="categoryId" onclick="changeColorLable()"
										value="${cat.id }">
										da checked
								</c:when>
								<c:otherwise>
									<input class="my-input"
										style="opacity: 0; width: 100%; height: 100%;"
										id="myCheckbox${i.index }" type="checkbox" name="categoryId"
										onclick="changeColorLable()" onload="changeColorLable()"
										value="${cat.id }">
								</c:otherwise>
							</c:choose>
							<label class="my-lable" for="myCheckbox${i.index }"></label>
						</div>

						<span>${cat.name }</span>
					</div>
					
				</c:forEach>
			</div>
		</div>
		<div class="form-group col-md-3">
			<label for="price">
				<b>Giá:</b>
			</label>
			<div class="form-inline d-flex align-item-center">
				<input class="form-control mr-2" type="number" name="price"
					id="price" value="${product.price }" required="required" placeholder="Giá"
					step="1000" min="0" />
				<span> VNĐ</span>
			</div>
		</div>
		<div class="form-group col-md-6">
			<label for="salePriceNumber">
				<b>% giảm giá:</b>
			</label>
			<br>
			<div
				class="form-inline d-flex align-item-center justify-content-start">
				<span>
					0
					<input type="range" name="salePrice" id="salePriceRange" max="100"
						min="0" step="5" value="${product.salePrice }" required="required"
						onchange="setSalePriceNumber()" />
					100
				</span>
				<span class="mx-2" style="color: orange"> <-> </span>
				<input class="form-control" type="number" name="salePrice" min="0"
					max="100" step="5" value="${product.salePrice }" required="required"
					placeholder="% giảm giá" onchange="setSalePriceRange()"
					id="salePriceNumber" class="mx-3" />

			</div>
		</div>
		<div class="form-group col-md-12">
			<label for="des">
				<b>Mô tả:</b>
			</label>
			<textarea rows="2" class="form-control" name="description" id="des"
				placeholder="Thêm mô tả...">${product.description }</textarea>
		</div>
		<div class="form-group col-md-4">
			<label for="thumbnail">
				<b>Ảnh đại diện:</b>
			</label>
			<br>
			<input type="file" name="thumbnail" id="thumbnail"
				required="required" placeholder="Ảnh đại diện" alt="Ảnh đại diện cho sản phẩm"/>
		</div>
		<div class="form-group col-md-12">
			<hr>
			<button class="btn btn-success my-3">Thêm</button>
			<a
				onclick="return confirm('Quay lại sẽ mất dữ liệu bạn vừa nhập. Tiếp tục?')"
				class="btn btn-primary"
				href="${pageContext.request.contextPath }/admin/product">Quay
				lại</a>
		</div>

	</form>
	<script type="text/javascript">changeColorLable()</script>
</div>