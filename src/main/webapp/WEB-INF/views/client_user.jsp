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
				<strong class="text-black">Thông tin khách hàng</strong>
			</div>
		</div>
	</div>
</div>

<div class="site-section">
	<div class="container">
	<div><a href="${pageContext.request.contextPath }/logout" class="btn btn-success mb-2">Đăng xuất</a></div>
		<fieldset style="border: 2px solid black; padding: 10px;">
			<legend>
				<i>Thông tin khách hàng</i>
			</legend>
			<div class="form-group" style="border-bottom: 1px dotted #001;">
				<label>
					<b>
						Username:
						<i>
							<span class="text-success">${accModel.username}</span>
						</i>
					</b>
				</label>
				<br>
				<label>
					<b>
						Ngày đăng ký:
						<i>
							<span class="text-success">
								<fmt:formatDate value="${accModel.created}"
									pattern="dd/MM/yyyy HH:mm:ss" />
							</span>
						</i>
					</b>
				</label>
				<br>
				<a class="btn btn-secondary mb-2 btn-sm"
					href="${pageContext.request.contextPath }/user/view-history-bill">Xem
					lịch sử mua hàng</a>
			</div>
			<div class="form-group">
				<fieldset style="border: 1px solid black; padding: 5px;">
					<legend>Địa chỉ nhận hàng:</legend>
					<label>
						<b>
							Chi tiết:
							<i>
								<span class="text-success">${accModel.detailAddress}</span>
							</i>
						</b>
					</label>
					<br>
					<label>
						<b>
							Xã/Phường:
							<i>
								<span class="text-success">${accModel.commune}</span>
							</i>
						</b>
					</label>
					<br>
					<label>
						<b>
							Quận/huyện:
							<i>
								<span class="text-success">${accModel.district}</span>
							</i>
						</b>
					</label>
					<br>
					<label>
						<b>
							Tỉnh/thành phố:
							<i>
								<span class="text-success">${accModel.province}</span>
							</i>
						</b>
					</label>
				</fieldset>
			</div>
			<div class="form-group">
				<fieldset style="border: 1px solid black; padding: 5px;">
					<legend>Thông tin liên hệ</legend>
					<label>
						<b>
							Email:
							<i>
								<span class="text-success">${accModel.email}</span>
							</i>
						</b>
					</label>
					<br>
					<label>
						<b>
							Số điện thoại:
							<i>
								<span class="text-success">${accModel.phone}</span>
							</i>
						</b>
					</label>
					<br>
				</fieldset>
			</div>
		</fieldset>

	</div>
</div>
