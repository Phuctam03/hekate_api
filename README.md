# Hekate API - Backend Service

Chào mừng bạn đến với repository của Hekate API. Đây là một dự án backend được xây dựng bằng Java và Spring Boot, có nhiệm vụ cung cấp các API để phục vụ cho các ứng dụng khác.

## 📌 Mục lục
- [Giới thiệu](#-giới-thiệu)
- [Công nghệ sử dụng](#-công-nghệ-sử-dụng)
- [Cấu trúc dự án](#-cấu-trúc-dự-án)
- [Phân tích Yêu cầu Cơ sở dữ liệu](#-phân-tích-yêu-cầu-cơ-sở-dữ-liệu)
  - [Sơ đồ Cơ sở dữ liệu (ERD)](#sơ-đồ-cơ-sở-dữ-liệu-erd)
- [Hướng dẫn Cài đặt & Chạy](#-hướng-dẫn-cài-đặt--chạy)
  - [1. Yêu cầu hệ thống](#1-yêu-cầu-hệ-thống)
  - [2. Các bước cài đặt](#2-các-bước-cài-đặt)
  - [3. Build và Chạy ứng dụng](#3-build-và-chạy-ứng-dụng)

---

## 🌟 Giới thiệu

Hekate API được thiết kế để trở thành một hệ thống backend mạnh mẽ, có khả năng mở rộng và dễ bảo trì. Dự án tuân thủ các quy tắc phát triển phần mềm hiện đại và sử dụng Maven để quản lý các gói phụ thuộc và quy trình build.

## 🛠️ Công nghệ sử dụng

- **Ngôn ngữ:** Java 17
- **Framework:** Spring Boot
- **Build Tool:** Apache Maven
- **Database:** MySQL 8.0

## 📂 Cấu trúc dự án

Dự án tuân theo cấu trúc của một dự án Maven tiêu chuẩn:
```markdown
.
├── .mvn/
│ └── wrapper/
├── src/
│ ├── main/
│ │ ├── java/ # Nơi chứa mã nguồn Java của ứng dụng.
│ │ └── resources/ # Chứa file cấu hình (application.properties).
│ └── test/
│ └── java/ # Nơi chứa các bài kiểm thử.
├── .gitignore
├── mvnw & mvnw.cmd # Maven Wrapper để build và chạy dự án.
├── pom.xml # File định nghĩa các gói phụ thuộc và quy trình build.
└── README.md # Tài liệu hướng dẫn bạn đang đọc.
```
## 🗃️ Phân tích Yêu cầu Cơ sở dữ liệu

Dựa trên các chức năng được yêu cầu, cơ sở dữ liệu của hệ thống cần được thiết kế để hỗ trợ các nghiệp vụ sau:

1.  **Quản lý Sản phẩm & Tồn kho:** Cần các bảng để lưu trữ thông tin sản phẩm (tên, mô tả, giá...) và số lượng tồn kho hiện có.
2.  **Quản lý Đơn hàng:** Cần các bảng để quản lý thông tin khách hàng, thông tin đơn hàng (ngày tạo, trạng thái...) và chi tiết các sản phẩm trong từng đơn hàng.
3.  **Quản lý Khuyến mãi:** Cần bảng để lưu trữ các chương trình khuyến mãi, mã giảm giá và các điều kiện áp dụng.
4.  **Quản lý Giao hàng:** Cần các trường trong bảng đơn hàng để theo dõi trạng thái giao hàng, mã vận đơn và thông tin của đơn vị vận chuyển.

### Sơ đồ Cơ sở dữ liệu (ERD)

![Sơ đồ Cơ sở dữ liệu](image.png)

## 🚀 Hướng dẫn Cài đặt & Chạy

Để thiết lập và chạy dự án này trên máy của bạn, vui lòng làm theo các bước dưới đây.

### 1. Yêu cầu hệ thống

Trước khi bắt đầu, hãy đảm bảo bạn đã cài đặt các công cụ sau:

-   **Java Development Kit (JDK) 17:** [Tải tại đây](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
-   **MySQL Server 8.0:** [Tải tại đây](https://www.mysql.com/downloads/)
-   **Git:** [Tải tại đây](https://git-scm.com/downloads)

> **Lưu ý:** Bạn không cần phải cài đặt Maven thủ công. Dự án này đã tích hợp sẵn **Maven Wrapper** (`mvnw`), nó sẽ tự động tải về phiên bản Maven cần thiết.

### 2. Các bước cài đặt

1.  **Clone repository về máy:**
    ```bash
    git clone https://github.com/Phuctam03/hekate_api.git
    cd hekate_api
    ```

2.  **Cấu hình Cơ sở dữ liệu:**
    -   Đảm bảo MySQL server của bạn đang chạy.
    -   Tạo một database mới cho dự án, ví dụ: `hekate_db`.
    -   Mở file `src/main/resources/application.properties`.
    -   Cập nhật các thông tin kết nối đến cơ sở dữ liệu của bạn:
        ```properties
        # Cấu hình kết nối MySQL
        spring.datasource.url=jdbc:mysql://localhost:3306/hekate_db
        spring.datasource.username=your_mysql_username
        spring.datasource.password=your_mysql_password
        spring.jpa.hibernate.ddl-auto=update
        ```

### 3. Build và Chạy ứng dụng

Sử dụng Maven Wrapper (`mvnw`) để chạy ứng dụng.

- **Trên macOS hoặc Linux:**
  ```bash
  ./mvnw spring-boot:run
  ```
- **Trên Windows (sử dụng Command Prompt hoặc PowerShell):**
  ```bash
  .\mvnw.cmd spring-boot:run
  ```
Sau khi chạy lệnh trên, ứng dụng sẽ được khởi động và máy chủ sẽ lắng nghe tại cổng mặc định là 8080. Bạn có thể truy cập vào `http://localhost:8080` để kiểm tra.
