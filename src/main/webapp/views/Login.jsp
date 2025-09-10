<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body
	class="bg-light d-flex justify-content-center align-items-center vh-100">
	<div class="card shadow p-4" style="width: 400px;">
		<h3 class="text-center mb-3">Đăng nhập</h3>

		<!-- Hiển thị lỗi nếu có -->
		<c:if test="${not empty error}">
			<div class="alert alert-danger">${error}</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/user" method="post">
			<input type="hidden" name="action" value="login" />
			<div class="mb-3">
				<label class="form-label">Tên đăng nhập</label> <input type="text"
					class="form-control" name="username" required>
			</div>
			<div class="mb-3">
				<label class="form-label">Mật khẩu</label> <input type="password"
					class="form-control" name="password" required>
			</div>
			<button type="submit" class="btn btn-primary w-100">Đăng
nhập</button>
		</form>

		<div class="text-center mt-3">
			<a href="${pageContext.request.contextPath}/Views/Register.jsp">Đăng
				ký</a> | <a
				href="${pageContext.request.contextPath}/Views/ForgotPass.jsp">Quên
				mật khẩu?</a>
		</div>
	</div>
</body>
</html>