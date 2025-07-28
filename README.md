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
=======

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

<img width="1305" height="870" alt="image" src="https://github.com/user-attachments/assets/cdb76b49-139c-4ab1-aeab-505426b7ad88" />

## 🚀 Hướng dẫn Cài đặt & Chạy
Bạn có hai cách để thiết lập và chạy dự án này:

1.  **Chạy bằng Docker (Khuyến nghị):** Cách nhanh nhất và dễ dàng nhất, không cần cài đặt Java hay MySQL thủ công trên máy của bạn.
2.  **Chạy trên máy cục bộ (Truyền thống):** Hữu ích khi bạn muốn phát triển và gỡ lỗi trực tiếp trên hệ điều hành của mình.

---

### Cách 1: Chạy bằng Docker & Docker Compose (Khuyến nghị)

Phương pháp này sẽ tự động tạo và kết nối ứng dụng của bạn với một cơ sở dữ liệu MySQL, tất cả đều nằm trong các container biệt lập.

#### Yêu cầu

-   Đã cài đặt **Docker** và **Docker Compose**. [Tải tại đây](https://www.docker.com/get-started/)
-   **Git**.

#### Các bước thực hiện

1.  **Clone repository về máy:**
    ```bash
    git clone https://github.com/Phuctam03/hekate_api.git
    cd hekate_api
    ```

2.  **Khởi động ứng dụng và cơ sở dữ liệu:**
    Chạy lệnh sau từ thư mục gốc của dự án:
    ```bash
    docker-compose up --build
    ```
    -   Lệnh này sẽ:
        -   Xây dựng Docker image cho ứng dụng Spring Boot của bạn từ `Dockerfile`.
        -   Tải về và khởi chạy một container MySQL.
        -   Tự động kết nối ứng dụng của bạn với container database.
    -   Để chạy ngầm, sử dụng `docker-compose up -d`.

3.  **Kiểm tra:**
    Sau khi các log đã ổn định, ứng dụng của bạn sẽ sẵn sàng tại `http://localhost:8080`.

4.  **Dừng ứng dụng:**
    Nhấn `Ctrl + C` trong cửa sổ terminal, hoặc chạy lệnh sau nếu bạn đã chạy ngầm:
    ```bash
    docker-compose down
    ```

---

### Cách 2: Chạy trên máy cục bộ (Truyền thống)

Sử dụng cách này nếu bạn muốn kiểm soát hoàn toàn môi trường phát triển trên máy của mình.
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
 ### ⚠️ Lưu ý Quan trọng về Bảo mật

> **Lưu ý:** Trong một môi trường sản phẩm thực tế (production), các thông tin nhạy cảm (secret keys) như mật khẩu cơ sở dữ liệu hoặc khóa API bắt buộc phải được quản lý một cách an toàn thông qua các biến môi trường (trong file `.env`) hoặc các công cụ quản lý bí mật chuyên dụng (ví dụ: HashiCorp Vault, AWS Secrets Manager...).
> 
> Tuy nhiên, vì lý do thời gian và để đơn giản hóa cho bản demo này, một vài chi tiết cấu hình có thể được đặt trực tiếp trong file `docker-compose.yml`. Vui lòng không áp dụng cách làm này cho các dự án thực tế.