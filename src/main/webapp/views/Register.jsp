<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light d-flex justify-content-center align-items-center vh-100">
<div class="card shadow p-4" style="width: 400px;">
    <h3 class="text-center mb-3">Đăng ký</h3>

    <!-- Thông báo lỗi -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <!-- Thông báo thành công -->
    <c:if test="${not empty success}">
        <div class="alert alert-success">
            ${success}
            <div class="mt-2 text-center">
                <a href="${pageContext.request.contextPath}/views/Login.jsp" class="btn btn-primary btn-sm">Đăng nhập ngay</a>
            </div>
        </div>
    </c:if>

    <!-- Chỉ hiển thị form nếu chưa đăng ký thành công -->
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
            <button type="submit" class="btn btn-success w-100">Đăng ký</button>
        </form>
        <div class="text-center mt-3">
            <a href="${pageContext.request.contextPath}/Views/Login.jsp">Đăng nhập</a>
        </div>
    </c:if>
</div>
</body>
</html>