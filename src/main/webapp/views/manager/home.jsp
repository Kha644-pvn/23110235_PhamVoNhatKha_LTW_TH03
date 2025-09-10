<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang Manager</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light container mt-4">

	<h1 class="mb-4">Trang Manager</h1>
	<h4>Chào, ${currentUser.fullname}!</h4>

	<a href="${pageContext.request.contextPath}/category?action=add"
		class="btn btn-success mb-3">Thêm Category</a>

	<table class="table table-bordered table-hover">
		<thead>
			<tr>
				<th>ID</th>
				<th>Tên Category</th>
				<th>Người tạo</th>
				<th>Ảnh</th>
				<th>Thao tác</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="c" items="${categories}">
				<tr>
					<td>${c.id}</td>
					<td>${c.name}</td>
					<td>${c.user.fullname}</td>
					<td><c:if test="${not empty c.imageBase64}">
							<img src="data:image/jpeg;base64,${c.imageBase64}" width="80" />
						</c:if></td>
					<td><a
						href="${pageContext.request.contextPath}/category?action=edit&id=${c.id}"
						class="btn btn-sm btn-primary">Sửa</a> <a
						href="${pageContext.request.contextPath}/category?action=delete&id=${c.id}"
						class="btn btn-sm btn-danger"
						onclick="return confirm('Xóa category này?');">Xóa</a></td>
				</tr>
			</c:forEach>

			<c:if test="${empty categories}">
				<tr>
					<td colspan="5" class="text-center">Chưa có category nào</td>
				</tr>
			</c:if>
		</tbody>
	</table>

</body>
</html>