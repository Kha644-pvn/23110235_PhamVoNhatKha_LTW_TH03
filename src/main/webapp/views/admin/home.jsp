<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: #f4f6f9;
        }
        .card {
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }
        .table th {
            background-color: #0d6efd;
            color: white;
            text-align: center;
            vertical-align: middle;
        }
        .table td {
            vertical-align: middle;
        }
        .btn-custom {
            border-radius: 8px;
            transition: 0.2s;
        }
        .btn-custom:hover {
            transform: scale(1.05);
        }
    </style>
</head>
<body class="container mt-4">

    <div class="card p-4">
        <h1 class="mb-4 text-primary">Trang Admin</h1>

        <!-- Chào fullname admin -->
        <h4 class="mb-3">👋 Chào, <span class="fw-bold">${currentUser.fullname}</span>!</h4>

        <!-- Nút Thêm Category -->
        <a href="${pageContext.request.contextPath}/category?action=add" 
           class="btn btn-success mb-3 btn-custom">➕ Thêm Category</a>
           
         <a href="${pageContext.request.contextPath}/admin/video?action=list" class="btn btn-primary">Quản lý Video</a>
         

        <!-- Hiển thị danh sách Category -->
        <div class="table-responsive">
            <table class="table table-bordered table-hover align-middle text-center">
                <thead>
                <tr>
                    <th width="10%">ID</th>
                    <th width="30%">Tên Category</th>
                    <th width="30%">Ảnh</th>
                    <th width="30%">Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="c" items="${categories}">
                    <tr>
                        <td>${c.id}</td>
                        <td class="fw-semibold">${c.name}</td>
                        <td>
                            <c:if test="${not empty c.imageBase64}">
                                <img src="data:image/jpeg;base64,${c.imageBase64}" 
                                     class="rounded shadow-sm" width="100" />
                            </c:if>
                        </td>
                        <td>
                            <!-- Chỉ admin sửa/xóa category của chính mình -->
                            <c:if test="${c.user.id == currentUser.id}">
                                <a href="${pageContext.request.contextPath}/category?action=edit&id=${c.id}" 
                                   class="btn btn-sm btn-primary btn-custom me-1">✏️ Sửa</a>
                                <a href="${pageContext.request.contextPath}/category?action=delete&id=${c.id}" 
                                   class="btn btn-sm btn-danger btn-custom" 
                                   onclick="return confirm('Xóa category này?');">🗑 Xóa</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>

                <!-- Nếu danh sách rỗng -->
                <c:if test="${empty categories}">
                    <tr>
                        <td colspan="4" class="text-center text-muted">Bạn chưa tạo category nào</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>

</body>
</html>
