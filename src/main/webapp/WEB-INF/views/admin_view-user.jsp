<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:if test="${prePage != null }">
	<div style="text-align: left;">
		<a href="${prePage}" class="btn btn-primary mb-2">Quay lại</a>
	</div>
</c:if>
<div>

	<fieldset style="border: 2px solid black; padding: 10px;">
		<legend><i>Thông tin khách hàng</i></legend>
		<div class="form-group" style="border-bottom: 1px dotted #001;">
			<label><b>Họ và tên: <i><span class="text-success">${accModel.username}</span></i></b></label><br>
			<label><b>Ngày đăng ký: <i><span class="text-success"><fmt:formatDate value="${accModel.created}" pattern="dd/MM/yyyy HH:mm:ss"/></span></i></b></label><br>		
			<a class="btn btn-secondary mb-2 btn-sm" href="${pageContext.request.contextPath }/admin/view-history-user?accountId=${accModel.id}">Xem lịch sử mua hàng</a>
		</div>
		<div class="form-group">
			<fieldset style="border: 1px solid black; padding: 5px;">
				<legend>Địa chỉ nhận hàng:</legend>
				<label><b>Chi tiết: <i><span class="text-success">${accModel.detailAddress}</span></i></b></label><br>
				<label><b>Xã/Phường: <i><span class="text-success">${accModel.commune}</span></i></b></label><br>
				<label><b>Quận/huyện: <i><span class="text-success">${accModel.district}</span></i></b></label><br>
				<label><b>Tỉnh/thành phố: <i><span
							class="text-success">${accModel.province}</span></i></b></label>
			</fieldset>
		</div>
		<div class="form-group">
			<fieldset style="border: 1px solid black; padding: 5px;">
				<legend>Thông tin liên hệ</legend>
				<label><b>Email: <i><span class="text-success">${accModel.email}</span></i></b></label><br>
				<label><b>Số điện thoại: <i><span class="text-success">${accModel.phone}</span></i></b></label><br>
			</fieldset>
		</div>
	</fieldset>

</div>