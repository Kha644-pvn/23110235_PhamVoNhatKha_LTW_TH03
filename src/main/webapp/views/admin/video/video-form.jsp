<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Video Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: #f4f6f9;
        }
        .card {
            border-radius: 15px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        .form-control, .form-select, textarea {
            border-radius: 10px;
        }
        .btn-custom {
            border-radius: 10px;
            transition: 0.2s;
        }
        .btn-custom:hover {
            transform: scale(1.05);
        }
    </style>
</head>
<body class="d-flex justify-content-center align-items-center vh-100">
<div class="card p-4" style="width: 500px;">
    <h3 class="text-center mb-4 text-primary">
        <c:if test="${video != null}">✏️ Sửa Video</c:if>
        <c:if test="${video == null}">➕ Thêm Video</c:if>
    </h3>

    <form action="<c:url value='/admin/video'/>" method="post">
        <c:if test="${video != null}">
            <input type="hidden" name="id" value="${video.id}"/>
        </c:if>

        <div class="mb-3">
            <label class="form-label">Tiêu đề</label>
            <input type="text" name="title" value="${video.title}" class="form-control" required/>
        </div>

        <div class="mb-3">
            <label class="form-label">Mô tả</label>
            <textarea name="description" rows="4" class="form-control">${video.description}</textarea>
        </div>

        <div class="mb-3">
            <label class="form-label">URL</label>
            <input type="text" name="url" value="${video.url}" class="form-control" required/>
        </div>

        <div class="mb-3">
            <label class="form-label">User ID</label>
            <input type="text" name="userid" value="${video.userid}" class="form-control" required/>
        </div>

        <div class="d-flex justify-content-between">
            <button type="submit" class="btn btn-success btn-custom px-4">💾 Lưu</button>
            <a href="${pageContext.request.contextPath}/admin/video?action=list" class="btn btn-secondary btn-custom px-4">⬅ Quay lại</a>
        </div>
    </form>
</div>
</body>
</html>
