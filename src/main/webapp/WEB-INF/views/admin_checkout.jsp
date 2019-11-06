<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:if test="${prePage != null }">
	<div>
		<a class="btn btn-primary mb-2" href="${prePage }">Quay lại</a>
	</div>
</c:if>
<div>
	<div class="card-body">
		<div class="table-responsive">
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
							<td colspan="8" style="text-align: center" class="alert alert-danger">Chưa có đơn hàng nào.</td>
						</tr>
					</c:if>
					<c:forEach items="${checkouts }" var="ch" varStatus="i">
						<tr>
							<td>${i.index+1 }</td>
							<td><b><a href="${pageContext.request.contextPath}/admin/view-user?accId=${ch.account.id }">${ch.account.username }</a></b></td>
							<td><fmt:formatDate value="${ch.created }"
									pattern="dd/MM/yyyy : HH:mm:ss" /></td>
							<td>${ch.type == 0 ? 'Thanh toán khi nhận hàng':'Thanh toán online'}</td>
							<td>${ch.confirm == false ? '<span class="text-danger">Chưa xác nhận</span>':'<span class="text-success">Đã xác nhận</span>'}</td>
							<td>
								<c:choose>
									<c:when test="${ch.status == 0 && ch.confirm == true}"><span class="text-danger">Đang giao hàng</span></c:when>
									<c:when test="${ch.status == 1 }"><span class="text-success">Đã giao hàng</span></c:when>
									<c:when test="${ch.status == 2 }"><span class="text-danger">Đơn hàng bị từ chối</span></c:when>
									<c:otherwise><span class="text-warning">Chưa xác định</span></c:otherwise>
								</c:choose>
							</td>
							<td class="d-flex">
								<a href="${pageContext.request.contextPath }/admin/checkout/detail?checkoutId=${ch.id}" class="btn btn-primary">Chi tiết</a>
								
								<c:if test="${ch.confirm == false }">
									<a href="${pageContext.request.contextPath }/admin/checkout/confirm?checkoutId=${ch.id}&typePage=${typePage}" onclick="return confirm('Xác nhận đơn hàng?')" class="btn btn-warning mx-2">Xác nhận</a>
								</c:if>
								<c:if test="${ch.confirm == true && ch.status == 0 }">
									<a href="${pageContext.request.contextPath }/admin/checkout/complete?checkoutId=${ch.id}&typePage=${typePage}" onclick="return confirm('Xác nhận đơn hàng đã giao thành công?')" class="btn btn-danger mx-2">Hoàn thành</a>
									<a href="${pageContext.request.contextPath }/admin/checkout/deny?checkoutId=${ch.id}&typePage=${typePage}" onclick="return confirm('Từ chối đơn hàng này?')" class="btn btn-danger mx-2">Từ chối</a>
								</c:if>
							</td>
						
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>