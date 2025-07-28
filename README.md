# hekate_api

## Tổng quát

Đây là thư mục dùng để mô tả các yêu cầu về phần mềm cũng như cách chạy dự án.

## Phân tích

Phân tích yêu cầu cơ sở dữ liệu
Dựa trên các API được yêu cầu, cơ sở dữ liệu cần hỗ trợ:

1. Kiểm tra tồn kho: Lưu trữ thông tin sản phẩm và số lượng tồn kho.
2. Tạo đơn hàng: Quản lý thông tin đơn hàng, khách hàng, và chi tiết đơn hàng.
3. Lấy khuyến mãi hiện hành: Quản lý các chương trình khuyến mãi và điều kiện áp dụng.
4. Gửi thông tin giao hàng: Theo dõi trạng thái giao hàng và thông tin vận chuyển.

## cơ sở dữ liệu

<img width="1299" height="829" alt="image" src="https://github.com/user-attachments/assets/7143f7cd-db79-410a-a111-6fadc1c594eb" />

## cách chạy

-cài đặt các thư viện:

    jdk 17: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
    maven: https://maven.apache.org/
    mySQL(Phiên bản 8.0):https://www.mysql.com/downloads/

-Lệnh chạy:

    -mvn:springboot-run
