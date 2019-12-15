<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script
	src="${pageContext.request.contextPath }/static/_client/js/my-js.js"></script>

<div class="bg-light py-3">
	<div class="container">
		<div class="row">
			<div class="col-md-12 mb-0">
				<a href="${pageContext.request.contextPath }/">Home</a>
				<span class="mx-2 mb-0">/</span>
				<strong class="text-black">${product.name }</strong>
			</div>
		</div>
	</div>
</div>


<c:if test="${message != null}">
	<script>
		alert("${message.message}");
	</script>
</c:if>
<div class="site-section">
	<div class="container">
		<div class="row pb-5">
			<div class="col-md-6">
				<div class="main-image mb-5">
					<img id="mainIamge"
						src="${pageContext.request.contextPath}/images/${product.thumbnail}"
						alt="${product.name }" width="100%" height="100%">
				</div>

				<div class="sub-images">
					<img
						src="${pageContext.request.contextPath}/images/${product.thumbnail}"
						alt="${product.name }" width="100%" height="100%"
						onclick="changeImages('${pageContext.request.contextPath}/images/${product.thumbnail}')">
				</div>

				<c:forEach items="${product.productImages }" var="image">
					<div class="sub-images">
						<img
							src="${pageContext.request.contextPath}/images/${image.image}"
							alt="${product.name }" width="100%" height="100%"
							onclick="changeImages('${pageContext.request.contextPath}/images/${image.image}')">
					</div>
				</c:forEach>
			</div>
			<div class="col-md-6">
				<h2 class="text-black">${product.name }</h2>


				<p class="h4">
					<c:if test="${product.salePrice != 0 }">
						<span class="text-danger font-weight-italic mr-5">
							<strike>${product.priceWithFormat } VNĐ</strike>
						</span>
					</c:if>
					<span class="text-primary font-weight-bold">${product.realPrice }
						VNĐ</span>
				</p>
				<script type="text/javascript"
					src="${pageContext.request.contextPath }/static/_client/js/my-js.js"></script>
				<c:choose>

					<c:when test="${product.productDetails.size() ==0}">
						<div class="text-danger">Sản phẩm tạm hết hàng</div>
						<a
							href="${pageContext.request.contextPath }/wish/add?productId=${product.id}"
							title="Thêm vào danh sách yêu thích của bạn">
							<span style="width: 30px;" class="icon icon-heart-o"></span>
						</a>
						<a href="${pageContext.request.contextPath }/shop"
							class="buy-now btn btn-sm btn-success my-2">Tiếp tục mua sắm</a>
					</c:when>

					<c:otherwise>
						<!--  Bat dau form -->
						<form
							action="${pageContext.request.contextPath }/cart/add-to-cart"
							method="post">

							<input type="hidden" name="productId" value="${product.id }">

							<!-- Chọn size -->
							<div id="sizeSelect" class="mb-2">
								<span>Chọn size: </span>
								<select id="selectSize" onchange="setColor()" name="size">
									<c:forEach var="sc" items="${sizeColors}">
										<option value="${sc.size }" label="${sc.size }">
									</c:forEach>
								</select>
							</div>

							<!--  Chọn màu-->
							<div id="colorSelect" class="mb-2">
								Chọn màu:
								<c:forEach items="${sizeColors}" var="sc" varStatus="i">
									<c:if test="${i.index == 0}">
										<span class="color-group">
									</c:if>
									<c:if test="${i.index > 0}">
										<span class="color-group" style="display: none">
									</c:if>

									<c:forEach items="${sc.getSizeHaveColor() }" var="c"
										varStatus="j">

										<input type="radio" name="color" value="${c}">

										<div
											style="display: inline-block; background: ${c}; width: 20px; height: 20px;"
											class="mr-3"></div>
									</c:forEach>
									</span>
								</c:forEach>
							</div>

							<!-- Số lượng -->
							<div class="mb-5">
								<div class="input-group mb-3" style="max-width: 120px;">
									<div class="input-group-prepend">
										<button class="btn btn-outline-primary js-btn-minus"
											type="button">&minus;</button>
									</div>
									<input type="text" class="form-control text-center" value="1"
										placeholder="" aria-label="Example text with button addon"
										aria-describedby="button-addon1" name="amout">
									<div class="input-group-append">
										<button class="btn btn-outline-primary js-btn-plus"
											type="button">&plus;</button>
									</div>
								</div>
							</div>

							<script type="text/javascript">
								window.onload = setChecked(0);
							</script>

							<p>
								<a class="mr-2"
									href="${pageContext.request.contextPath }/wish/add?productId=${product.id}"
									title="Thêm vào danh sách yêu thích của bạn">
									<span style="font-size: 30px; color:red;" class="icon icon-heart-o"></span>
								</a>
								<button type="submit"
									class="buy-now btn btn-sm btn-primary mr-2">Thêm vào
									giỏ hàng</button>
								<a href="${pageContext.request.contextPath }/shop"
									class="buy-now btn btn-sm btn-success">Tiếp tục mua sắm</a>
							</p>
						</form>
						<!-- Ket thuc form -->
					</c:otherwise>
				</c:choose>


				<p class="mt-4">
					Mô tả:
					<br>${product.description }</p>

			</div>
		</div>
		<div class="row comment-frame" ng-app="myapp">
			<div class="ml-2 comment" ng-controller='HandleComment'
				ng-init="getcm(${product.id })">

				<div class="text-black h4" style="width: 100%">
					Bình luận
					<hr>
				</div>

				<!-- all comment -->
				<div class="ml-3" ng-repeat="c in comments ">
					<!-- cha + con -->
					<div class="pb-3">


						<!--  cha-->
						<div
							style="background: #80808014; border-radius: 10px; padding: 0 5px 5px 0;"
							class="">

							<!-- avatar -->
							<div class="mr-3"
								style="width: 50px; height: 50px; object-fit: cover; object-position: center; display: inline-block;">
								<img width="100%" height="100%" alt=""
									src="${pageContext.request.contextPath }/static/_client/images/default.png">
							</div>
							<!-- /avatar -->

							<!-- username -->
							<div style="display: inline-block;">
								<span style="color: black; font-size: 20px; font-weight: 500;"
									class="mr-5"> {{c.accountName}}</span>
							</div>
							<!-- /username -->
							<br>

							<!-- content -->
							<span class="ml-5 mb-0 d-block">{{c.content}}</span>
							<!-- /content -->
						</div>

						<!-- Reply -->
						<span class="ml-3 mr-5" style="color: gray; font-size: 12px;">Ngày
							tạo: {{c.created}}</span>
						<a id="btn-abc" href="#content{{$index}}"
							class="d-inline-block  mb-2"
							ng-click="setReplyForm(c.id, $index, c.accountName)">Reply</a>
						<!-- /Reply -->
						<!-- /cha -->


						<!-- con -->
						<div ng-repeat="child in c.commentChildrens">
							<div class="ml-4">
								<div
									style="background: #80808014; border-radius: 10px; padding: 0 5px 5px 0;">

									<!-- avatar con -->
									<div class="mr-3"
										style="width: 50px; height: 50px; object-fit: cover; object-position: center; display: inline-block;">
										<img width="100%" height="100%" alt=""
											src="${pageContext.request.contextPath }/static/_client/images/default.png">
									</div>
									<!-- /avatar con-->

									<!-- username con-->
									<div style="display: inline-block;">
										<span style="color: black; font-size: 20px; font-weight: 500;"
											class="mr-5"> {{child.accountName}} </span>
									</div>
									<!-- /username con-->
									<br>

									<!-- content con-->
									<span class="ml-5 mb-0 d-block">{{child.content}}</span>
									<!-- /content con-->
								</div>
								<!-- Reply con -->
								<span class="ml-3 mr-5" style="color: gray; font-size: 12px;">Ngày
									tạo: {{child.created}}</span>
								<a id="btn-abc" href="#content{{$parent.$index}}"
									class="d-inline-block mb-2"
									ng-click="setReplyForm(c.id, $parent.$index, child.accountName)">Reply</a>
								<!-- /Reply -->
							</div>
						</div>
						<!-- /con -->
						<!-- reply form -->
						<div class="ml-4 reply-form" style="display: none;"
							id="replyForm{{$index}}">
							<form>
								<input class="form-control" type="number" value="${account.id}"
									hidden="" name="accountId">
								<input class="form-control" name="productId" type="number"
									value="${product.getId()}" hidden="">
								<input class="form-control" type="text" value="0"
									id="commentParentID{{$index}}" name="parentId" hidden="">
								<textarea class="form-control" placeholder="Viết trả lời..."
									name="content" id="content{{$index}}"
									style="border-radius: 10px;" rows="2"></textarea>
								<button class="btn btn-success" ng-click="saveComment($index)">Gửi</button>
							</form>
						</div>
						<!-- /reply form -->
					</div>
					<!-- /comment -->
				</div>
			</div>
		</div>
	</div>
</div>






<<jsp:include page="/WEB-INF/layouts/client_new-product.jsp"></jsp:include>
