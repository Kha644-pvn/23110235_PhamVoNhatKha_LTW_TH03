<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quên mật khẩu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #cfd9df 0%, #e2ebf0 100%);
        }
        .card {
            border-radius: 20px;
            padding: 30px;
        }
        .form-control {
            border-radius: 12px;
        }
        .btn-custom {
            border-radius: 12px;
            transition: 0.3s;
        }
        .btn-custom:hover {
            background-color: #0b5ed7 !important;
            transform: scale(1.02);
        }
        .link-custom {
            text-decoration: none;
            font-weight: 500;
        }
        .link-custom:hover {
            text-decoration: underline;
            color: #0d6efd;
        }
    </style>
</head>
<body class="d-flex justify-content-center align-items-center vh-100">
<div class="card shadow-lg bg-white" style="width: 420px;">
    <h3 class="text-center mb-4 fw-bold text-primary">Quên mật khẩu</h3>

    <!-- Hiển thị thông báo lỗi -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger text-center">${error}</div>
    </c:if>

    <!-- Hiển thị thành công -->
    <c:if test="${not empty success}">
        <div class="alert alert-success text-center">${success}</div>
        <a href="${pageContext.request.contextPath}/views/Login.jsp" 
           class="btn btn-primary w-100 mt-3 rounded-pill">Quay về trang Đăng nhập</a>
    </c:if>

    <!-- Hiển thị form nếu chưa thành công -->
    <c:if test="${empty success}">
        <form action="${pageContext.request.contextPath}/user" method="post">
            <input type="hidden" name="action" value="forgot">
            <div class="mb-3">
                <label class="form-label">Họ tên</label>
                <input type="text" class="form-control" name="fullname" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Số điện thoại</label>
                <input type="text" class="form-control" name="phone" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Tên đăng nhập</label>
                <input type="text" class="form-control" name="username" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Mật khẩu mới</label>
                <input type="password" class="form-control" name="newPassword" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Xác nhận mật khẩu</label>
                <input type="password" class="form-control" name="confirmPassword" required>
            </div>
            <button type="submit" class="btn btn-primary w-100 btn-custom">Đặt lại mật khẩu</button>
        </form>
        <div class="text-center mt-3">
            <a href="${pageContext.request.contextPath}/views/Login.jsp" class="link-custom">Quay về đăng nhập</a>
        </div>
    </c:if>
</div>
</body>
</html>
