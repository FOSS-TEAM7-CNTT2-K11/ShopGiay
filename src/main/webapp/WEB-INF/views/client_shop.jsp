<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="bg-light py-3">
	<div class="container">
		<div class="row">
			<div class="col-md-12 mb-0">
				<a href="${pageContext.request.contextPath }/">Home</a>
				<span class="mx-2 mb-0">/</span>
				<strong class="text-black">Shop</strong>
			</div>
		</div>
	</div>
</div>

<div class="site-section" ng-app="myapp">
	<div class="container" ng-controller='filterController'
		ng-init="fillter()">

		<div class="row mb-5">
			<div class="col-md-9 order-2">

				<div class="row">
					<div class="col-md-12 mb-5">
						<div class="float-md-left mb-4">
							<h2 class="text-black h5">Shop All</h2>
						</div>
						<div class="">
							<div class="btn-group float-right">


								<select class="form-control" ng-model="typeSort"
									ng-change="change()" aria-labelledby="dropdownMenuReference"
									id="mySort">
									<option value="1" selected="selected">None</option>
									<option value="2">Name, A to Z</option>
									<option value="3">Name, Z to A</option>

									<option value="4">Price, Bé to Lớn</option>
									<option value="5">Price, Lớn to Bé</option>
								</select>



							</div>
						</div>
					</div>
				</div>
				<div class="row mb-5">
					<div ng-if="products.length == 0" class="m-auto text-danger">Không
						có sản phẩm nào!</div>
					<div class="col-sm-6 col-lg-4 mb-4" data-aos="fade-up"
						ng-repeat="product in products | orderBy: field : reverse">
						<div class="block-4 text-center border">
							<figure class="block-4-image">
								<a
									href="${pageContext.request.contextPath }/single-shop?productId={{product.id}}"
									title="{{product.name }}">
									<img style="height: 200px;" width="100%" height="100%"
										src="${pageContext.request.contextPath }/images/{{product.thumbnail}}"
										class="img-fluid image-cover">
								</a>
							</figure>
							<div class="block-4-text p-4">
								<h3>
									<a style="display: block;" class="cutTextOneLine"
										href="${pageContext.request.contextPath }/single-shop?productId={{product.id}}">{{product.name}}</a>
								</h3>
								<p class="mb-0 cutTextOneLine">{{product.description}}</p>

								<span ng-switch="product.sale">
									<span ng-switch-when="0" class="text-primary font-weight-bold">{{product.priceWithFormat}}
										VNĐ</span>
									<span ng-switch-default>
										<span class="text-danger font-weight-italic float-left">
											<strike>{{product.priceWithFormat}}</strike>
										</span>
										<span class="text-primary font-weight-bold float-right">{{product.realPrice}}
										</span>
										VNĐ
									</span>
								</span>
							</div>
						</div>
					</div>

				</div>
				<div class="row" data-aos="fade-up">
					<div class="col-md-12 text-center">
						<div class="site-block-27">
							<ul>
								<li>
									<a href="#" ng-click="changePage(pageCurrent - 1)">&lt;</a>
								</li>
								<span ng-repeat="i in rangePage">
									<span ng-if="i == pageCurrent">
										<li class="active">
											<a href="#" ng-click="changePage(i)">{{i}}</a>
										</li>
									</span>
									<span ng-if="i != pageCurrent">
										<li>
											<a href="#"  ng-click="changePage(i)">{{i}}</a>
										</li>
									</span>
								</span>
								<li>
									<a href="#" ng-click="changePage(pageCurrent + 1)">&gt;</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-3 order-1 mb-5 mb-md-0">
				<form>
					<div class="border p-4 rounded mb-4">
						<h3 class="mb-3 h6 text-uppercase text-black d-block">Danh
							mục</h3>
						<ul class="list-unstyled mb-0">
							<c:forEach items="${categories }" var="c">
								<li class="mb-1">
									<input type="checkbox" class="danh-muc" name="categories"
										value="${c.id }">
									<span>${c.name }</span>
								</li>
							</c:forEach>
						</ul>
					</div>

					<div class="border p-4 rounded mb-4">
						<div class="mb-4">
							<h3 class="mb-3 h6 text-uppercase text-black d-block">Giá</h3>
							<div>
								<label>Min: </label>
								<input type="number" value="0" name="priceMin" id="priceMin"
									class="form-control mb-2" min="0">
							</div>
							<div class="">
								<label>Max: </label>
								<input type="number" value="0" name="priceMax" id="priceMax"
									class="form-control" min="0">
							</div>
						</div>


						<div class="mb-4">
							<h3 class="mb-3 h6 text-uppercase text-black d-block">Size</h3>
							<select class="form-control" multiple="multiple" id="sizeSelect">
								<c:forEach items="${sizes }" var="s" varStatus="i">
									<c:if test="${i.index == 0 }">
										<optgroup label="Small">
									</c:if>
									<c:if test="${s.size ==31 }">
										<optgroup label="Medium">
									</c:if>
									<c:if test="${s.size == 41 }">
										<optgroup label="Large">
									</c:if>
									<option title="" value="${s.id }">${s.size }</option>

								</c:forEach>
							</select>
						</div>
						<button ng-click="getFillter()"
							class="btn btn-success form-control">Lọc</button>
					</div>

				</form>
			</div>
		</div>

		<jsp:include page="/WEB-INF/layouts/client_category-by-gender.jsp"></jsp:include>

	</div>
</div>
