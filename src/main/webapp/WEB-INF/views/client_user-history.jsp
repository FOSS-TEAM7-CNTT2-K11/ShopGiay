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
				<a href="${pageContext.request.contextPath  }/user">Thông tin khách hàng</a>
				<span class="mx-2 mb-0">/</span>
				<strong class="text-black">Lịch sử mua hàng</strong>
			</div>
		</div>
	</div>
</div>

<div>
	<div class="site-section">
		<div class="container">
		<div><a href="${pageContext.request.contextPath }/user" class="btn btn-primary mb-2">Quay lại</a></div>
			<table class="table table-bordered" id="dataTable">
				<thead>
					<tr>
						<th>STT</th>
						<th>Tên khách hàng</th>
						<th>Ngày tạo</th>
						<th>Phương thức thanh toán</th>
						<th>Đã xác nhận</th>
						<th>Trạng thái</th>
						<th>More</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th>STT</th>
						<th>Tên khách hàng</th>
						<th>Ngày tạo</th>
						<th>Phương thức thanh toán</th>
						<th>Đã xác nhận</th>
						<th>Trạng thái</th>
						<th>More</th>
					</tr>
				</tfoot>
				<tbody>
					<c:if test="${checkouts.size() == 0 }">
						<tr>
							<td colspan="8" style="text-align: center" class="alert alert-danger">Không có đơn hàng nào.</td>
						</tr>
					</c:if>
					<c:forEach items="${checkouts }" var="ch" varStatus="i">
						<tr>
							<td>${i.index+1 }</td>
							<td><b>${ch.account.username }</b></td>
							<td><fmt:formatDate value="${ch.created }"
									pattern="dd/MM/yyyy : HH:mm:ss" /></td>
							<td>${ch.type == 0 ? 'Thanh toán khi nhận hàng':'Thanh toán online'}</td>
							<td>${ch.confirm == false ? '<span>Chưa xác nhận</span>':'<span>Đã xác nhận</span>'}</td>
							<td>
								<c:choose>
									<c:when test="${ch.status == 0 && ch.confirm == true}"><span>Đang giao hàng</span></c:when>
									<c:when test="${ch.status == 1 }"><span>Đã giao hàng</span></c:when>
									<c:when test="${ch.status == 2 }"><span>Đơn hàng bị từ chối</span></c:when>
									<c:otherwise><span>Chưa xác định</span></c:otherwise>
								</c:choose>
							</td>
							<td class="d-flex">
								<a href="${pageContext.request.contextPath }/user/view-history-bill/detail?checkoutId=${ch.id}" class="btn btn-primary mr-2">Chi tiết</a>
								
								
								<c:if test="${ch.confirm == false}">
									<a href="${pageContext.request.contextPath }/user/view-history-bill//deny?checkoutId=${ch.id}" onclick="return confirm('Hủy đơn hàng này?')" class="btn btn-danger">Hủy đơn hàng</a>
								</c:if>
							</td>
						
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>