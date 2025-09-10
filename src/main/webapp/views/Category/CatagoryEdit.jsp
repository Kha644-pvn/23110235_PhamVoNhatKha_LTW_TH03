<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sửa Category</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

<h2>Sửa Category</h2>

<form method="post"
      action="${pageContext.request.contextPath}/category?action=save"
      enctype="multipart/form-data">
    <input type="hidden" name="id" value="${category.id}">
    <div class="mb-3">
        <label class="form-label">Tên</label>
        <input type="text" class="form-control" name="name" value="${category.name}" required>
    </div>
    <div class="mb-3">
        <label class="form-label">Ảnh hiện tại</label><br>
        <c:if test="${not empty category.image}">
            <img src="${pageContext.request.contextPath}/uploads/${category.image}"
                 alt="${category.name}"
                 style="width: 100px; height: auto; border:1px solid #ccc; padding:2px;">
        </c:if>
    </div>
    <div class="mb-3">
        <label class="form-label">Chọn ảnh mới (nếu muốn thay)</label>
        <input type="file" class="form-control" name="image">
    </div>
    <button type="submit" class="btn btn-primary">Cập nhật</button>
    <a href="${pageContext.request.contextPath}/category?action=list" class="btn btn-secondary">Hủy</a>
</form>

</body>
</html>