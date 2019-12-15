<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="site-section block-3 site-blocks-2 bg-light">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-7 site-section-heading text-center pt-4">
				<h2>Newest Products</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="nonloop-block-3 owl-carousel">
					<c:if test="${newestProducts.size() == 0 }">
						<div class="text-danger font-weight-italic align-center">Lỗi
							lấy sản phẩm mới nhất. Mong khách hàng thông cảm!</div>
					</c:if>
					<c:forEach var="p" items="${newestProducts }">
						<div class="item">
							<div class="block-4 text-center">
								<figure class="block-4-image">
									<a
										href="${pageContext.request.contextPath }/single-shop?productId=${p.id}">
										<img
											src="${pageContext.request.contextPath }/images/${p.thumbnail}"
											alt="${p.name }" width="100%" height="100%"
											style="height: 200px;" class="image-cover">
									</a>
								</figure>
								<div class="block-4-text p-4">
									<h3>
										<a href="#">${p.name }</a>
									</h3>
									<p class="mb-0 cutTextOneLine">${p.description }</p>
									<c:if test="${p.salePrice != 0 }">
										<span class="text-danger font-weight-italic">
											<strike>${p.priceWithFormat } VNĐ</strike>
										</span>
										<span class="text-primary font-weight-bold">${p.realPrice}
											VNĐ</span>
									</c:if>
									<c:if test="${p.salePrice == 0 }">
										<span class="text-primary font-weight-bold">${p.realPrice }
											VNĐ</span>
									</c:if>

								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>

