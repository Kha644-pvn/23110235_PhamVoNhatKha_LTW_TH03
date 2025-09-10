<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách Category</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

<h2 class="mb-3">Danh sách Category</h2>

<c:if test="${currentUser.roleid == 1}">
    <a href="${pageContext.request.contextPath}/manager/home" class="btn btn-warning mb-3">⬅ Quay về Manager Home</a>
</c:if>

<table class="table table-striped">
    <thead>
        <tr>
            <th>ID</th>
            <th>Tên</th>
            <c:if test="${currentUser.roleid == 1 && param.view == 'all'}">
                <th>Người tạo</th>
            </c:if>
            <th>Ảnh</th>
            <th>Thao tác</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="c" items="${categories}">
            <tr>
                <td>${c.id}</td>
                <td>${c.name}</td>
                <c:if test="${currentUser.roleid == 1 && param.view == 'all'}">
                    <td>${c.user.fullname}</td>
                </c:if>
                <td>
                    <c:if test="${c.imageBase64 != null}">
                        <img src="data:image/jpeg;base64,${c.imageBase64}" width="60" height="60"/>
                    </c:if>
                </td>
                <td>
                    <c:if test="${c.user.id == currentUser.id || currentUser.roleid == 1}">
                        <a href="${pageContext.request.contextPath}/category?action=edit&id=${c.id}" class="btn btn-sm btn-primary">Sửa</a>
                        <a href="${pageContext.request.contextPath}/category?action=delete&id=${c.id}" class="btn btn-sm btn-danger">Xóa</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<a href="${pageContext.request.contextPath}/category?action=add" class="btn btn-success">Thêm Category</a>

</body>
</html>