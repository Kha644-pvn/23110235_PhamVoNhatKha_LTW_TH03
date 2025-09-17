<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #74ebd5 0%, #9face6 100%);
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
            background-color: #218838 !important;
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
    <h3 class="text-center mb-4 fw-bold text-primary">Đăng ký tài khoản</h3>

    <!-- Thông báo lỗi -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger text-center">${error}</div>
    </c:if>

    <!-- Thông báo thành công -->
    <c:if test="${not empty success}">
        <div class="alert alert-success text-center">
            ${success}
            <div class="mt-3">
                <a href="${pageContext.request.contextPath}/views/Login.jsp" 
                   class="btn btn-primary btn-sm rounded-pill px-4">Đăng nhập ngay</a>
            </div>
        </div>
    </c:if>

    <!-- Form -->
    <c:if test="${empty success}">
        <form action="${pageContext.request.contextPath}/user" method="post">
            <input type="hidden" name="action" value="register" />
            <div class="mb-3">
                <label class="form-label">Tên đăng nhập</label>
                <input type="text" class="form-control" name="username" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Mật khẩu</label>
                <input type="password" class="form-control" name="password" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Họ tên</label>
                <input type="text" class="form-control" name="fullname" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Số điện thoại</label>
                <input type="text" class="form-control" name="sdt" required>
            </div>
            <button type="submit" class="btn btn-success w-100 btn-custom">Đăng ký</button>
        </form>
        <div class="text-center mt-3">
            <a href="${pageContext.request.contextPath}/Views/Login.jsp" class="link-custom">Đã có tài khoản? Đăng nhập</a>
        </div>
    </c:if>
</div>
</body>
</html>
