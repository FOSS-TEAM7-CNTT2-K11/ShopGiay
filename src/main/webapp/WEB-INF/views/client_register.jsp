<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="site-section">
	<div class="container">
		<div class="row mb-5">
			<form action="${pageContext.request.contextPath }/register"
				method="post">
				<div class="form-group">
					<label>
						<b>Username:</b>
					</label>
					<input class="form-control" type="text" required="required" name="username" placeholder="username...">
				</div>
			</form>
		</div>
	</div>
</div>