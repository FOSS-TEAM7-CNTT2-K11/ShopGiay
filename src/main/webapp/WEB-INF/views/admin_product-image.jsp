<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<div>
	<a href="${pageContext.request.contextPath }/admin/product"
		class="btn btn-primary mb-2">Quay lại</a> <a
		href="${pageContext.request.contextPath }/admin/product/images/add?productId=${productId}"
		class="btn btn-success mb-2 float-right">Thêm mới hình ảnh</a>
</div>

<c:choose>
	<c:when test="${productImages.size() == 0 }">
		<div class="alert alert-danger text-center">Sản phẩm chưa có ảnh
			chi tiết. Vui lòng nhấn thêm mới!</div>
	</c:when>
	<c:otherwise>
		<!--div slide  -->
		<div id="slideShow" class="carousel slide">

			<!-- Indicators -->
			<ul class="carousel-indicators">
				<c:forEach var="p" items="${productImages }" varStatus="i">
					<c:if test="${i.index==0 }">
						<li data-target="#slideShow" data-slide-to="${i.index }"
							class="myli active"></li>
					</c:if>
					<c:if test="${i.index!=0 }">
						<li data-target="#slideShow" class="myli"
							data-slide-to="${i.index }"></li>
					</c:if>
				</c:forEach>
			</ul>


			<!-- The slideshow -->
			<div class="carousel-inner">
				<c:forEach items="${productImages }" var="p" varStatus="i">
					<c:if test="${i.index == 0 }">
						<div class="carousel-item mydiv active">
					</c:if>
					<c:if test="${i.index != 0 }">
						<div class="carousel-item mydiv">
					</c:if>
					<img src="${pageContext.request.contextPath }/images/${p.image}"
						alt="${p.image}">
			</div>
			</c:forEach>
		</div>

		<!-- Left and right controls -->
		<a class="carousel-control-prev" href="#slideShow" data-slide="prev">
			<span class="carousel-control-prev-icon"></span>
		</a>
		<a class="carousel-control-next" href="#slideShow" data-slide="next">
			<span class="carousel-control-next-icon"></span>
		</a>
		</div>


		<!-- div image list -->
		<div class="image-list mt-2">
			<c:forEach items="${productImages }" var="p" varStatus="i">

				<span id="myImg"> <img class="image-product mb-2"
					onclick="abc(${i.index})" alt=""
					src="${pageContext.request.contextPath }/images/${p.image}">
					<a id="delImg"
					href="${pageContext.request.contextPath }/admin/product/images/delete?id=${p.id}&productId=${p.product.id}"
					onclick="return confirm('Chắc chắn xóa hình ảnh?')">x</a>
				</span>
			</c:forEach>

		</div>
	</c:otherwise>
</c:choose>



