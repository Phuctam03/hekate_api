## OVERVIEW

Dự án Xây dụng bán hàng tích hợp zalo OA xử lí các vấn về tư vấn khách hàng và tạo đơn hàng tự động cho khách

## Công nghệ sử dụng

-SpringBoot

-MySQL

## Phân tích dữ liệu

Dựa trên các API được yêu cầu, cơ sở dữ liệu cần hỗ trợ:

1. Kiểm tra tồn kho: Lưu trữ thông tin sản phẩm và số lượng tồn kho.
2. Tạo đơn hàng: Quản lý thông tin đơn hàng, khách hàng, và chi tiết đơn hàng.
3. Lấy khuyến mãi hiện hành: Quản lý các chương trình khuyến mãi và điều kiện áp dụng.
4. Gửi thông tin giao hàng: Theo dõi trạng thái giao hàng và thông tin vận chuyển.

## Sơ đồ luồng hệ thống

<img width="582" height="381" alt="image" src="https://github.com/user-attachments/assets/c194d258-2b3d-4b48-9256-be5f91a437ed" />

## Cấu trúc thư mục SpringBoot

<img width="550" height="942" alt="image" src="https://github.com/user-attachments/assets/3fbe54c6-47f0-4000-a749-3f90d0d2d7aa" />

## Sơ đồ thực thể và các mối quan hệ

<img width="654" height="443" alt="image" src="https://github.com/user-attachments/assets/569e6758-ead0-4377-8436-996f2fc3b68c" />

## Cách chạy dự án SpringBoot

-Điều kiện cần:

+MySQL phiên bản (8.x) trở lên: https://www.mysql.com/downloads/

+jdk 17: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html

+Maven repository: https://maven.apache.org/download.cgi?.

-Khởi động:

+Chạy MySQL

+Chạy dự án SpringBoot với lệnh : mvn SpringBoot:run

## Cách Test Api với postman

+Kiểm tra hàng tồn kho:

-API:http://localhost:8080/api/stock/check?productId=2&warehouseId=1&quantity=10

+productID: id sản phẩm

+warehouse: id kho hàng lưu trữ sản phẩm

+quantity: số lượng đáp ứng của sản phẩm trong kho hàng

+Tạo đơn hàngL

-API:http://localhost:8080/api/orders/create

thêm dữ liệu json body 

    "zaloUserId": "zalo123",

    "items": [
        {
            "productId": 1,
            "quantity": 5,
            "warehouseId": 1
        }
    ],
    "promotionIds": [1,2]

+zaloUserId: id người dùng từ zalo nếu tích hợp zalo OA

+items: các sản phẩm trong đơn hàng

+promotionsIds: các id mã giảm giá áp dụng cho đơn hàng

+Lấy các khuyến mãi hiện hành

-API:http://localhost:8080/api/promotions/current?totalAmount=600000

+totalAmount: Tổng số tiền đơn hàng có thể giảm giá là phải tầm 6000000

+Gửi Thông tin giao hàng

-API:http://localhost:8080/api/shipping/send

thêm dữ liệu json body 


    "orderId": 4,
    "shippingAddress": "123 Đường Láng, Hà Nội",
    "shippingPartner": "GHN",
    "trackingNumber": "GHN123456",
    "estimatedDeliveryDate": "2025-07-30T12:00:00"

+orderId:id đơn hàng muốn vận chuyển

+shippingAddress: địa chỉ vận chuyển

+shippingPartner: thức vận chuyển (ở đây là giao hàng nhanh)

+trackingNumber: mã đơn vận chuyển

+estimatedDeliveryDate: Ngày vận chuyển


