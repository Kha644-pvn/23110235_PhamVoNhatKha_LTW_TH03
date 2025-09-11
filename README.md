# LTW - User Management Web Application

LTW là ứng dụng web quản lý người dùng, hỗ trợ đăng nhập/đăng ký tài khoản, phân quyền theo vai trò, và quản lý danh mục (Category). Giao diện hiện đại, responsive trên mọi thiết bị và lưu trữ dữ liệu trên SQL Server thông qua Hibernate JPA.

---

## 📌 Tính năng

- **Đăng nhập / Đăng ký** với kiểm tra dữ liệu hợp lệ.
- **Phân quyền theo vai trò**: Admin, Manager, User.
- **Quản lý Category**: Thêm, Sửa, Xóa.
- **Hibernate ORM** mapping tự động giữa Entity và bảng DB.
- **Giao diện UI** responsive, tối ưu trải nghiệm người dùng.

---

## 📌 Công nghệ sử dụng

- **Ngôn ngữ**: Java 17+
- **Framework**: Jakarta Servlet & JSP
- **ORM**: Hibernate JPA
- **Cơ sở dữ liệu**: SQL Server
- **UI**: Bootstrap 5
- **Build tool**: Maven
- **Server**: Apache Tomcat 9+

---

## 📌 Cấu trúc dự án

Controller/ # Xử lý request/response
Models/ # Entity: User, Category
Service/ # Business logic
WebContent/Views/ # Giao diện JSP
META-INF/persistence.xml # Cấu hình kết nối DB


---

## 📌 Cài đặt & chạy

1. **Tạo database** SQL Server: `jpast56`.
2. **Cập nhật username/password** trong `persistence.xml`.
3. **Build dự án**:

```bash
mvn clean install
Deploy trên Tomcat với context path /LTW.

Truy cập ứng dụng:

Đăng nhập: http://localhost:8080/TH02/views/Login.jsp

Đăng ký: http://localhost:8080/TH02/views/Register.jsp
