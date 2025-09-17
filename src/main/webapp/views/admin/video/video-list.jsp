<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý Video</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

    <h2 class="mb-4">📺 Danh sách Video</h2>

    <!-- Form tìm kiếm -->
    <form action="${pageContext.request.contextPath}/video" method="get" class="row g-2 mb-3">
        <input type="hidden" name="action" value="search"/>
        <div class="col-auto">
            <select name="type" class="form-select">
                <option value="title">Tìm theo Title</option>
                <option value="description">Tìm theo Description</option>
            </select>
        </div>
        <div class="col-auto">
            <input type="text" name="keyword" class="form-control" placeholder="Nhập từ khóa"/>
        </div>
        <div class="col-auto">
            <button type="submit" class="btn btn-primary">🔍 Tìm kiếm</button>
        </div>
    </form>

    <!-- Nút thêm Video -->
    <a href="${pageContext.request.contextPath}/admin/video?action=new" class="btn btn-success mb-3">➕ Thêm Video</a>

    <!-- Bảng danh sách -->
    <table class="table table-bordered table-hover align-middle">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Description</th>
                <th>URL</th>
                <th>UserID</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="v" items="${videos}">
                <tr>
                    <td>${v.id}</td>
                    <td>${v.title}</td>
                    <td>${v.description}</td>
                    <td><a href="${v.url}" target="_blank" class="btn btn-sm btn-outline-info">Xem</a></td>
                    <td>${v.user.id}</td>        <%-- hiển thị ID của user --%>
					<td>${v.user.username}</td>  <%-- hoặc hiển thị username --%>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/video?action=edit&id=${v.id}" 
                           class="btn btn-sm btn-primary">Sửa</a>
                        <a href="${pageContext.request.contextPath}/admin/video?action=delete&id=${v.id}" 
                           class="btn btn-sm btn-danger"
                           onclick="return confirm('Bạn có chắc muốn xóa video này?');">Xóa</a>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty videos}">
                <tr>
                    <td colspan="6" class="text-center text-muted">⚠️ Chưa có video nào</td>
                </tr>
            </c:if>
        </tbody>
    </table>

</body>
</html>
