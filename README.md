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

4. Giải thích từng endpoint
a. GET /api/stock/check
Chức năng: Kiểm tra số lượng tồn kho của một sản phẩm tại một kho cụ thể.
Input:
Query params:
productId: ID sản phẩm (ví dụ: 1 cho "Áo thun trắng").
warehouseId: ID kho (ví dụ: 1).
quantity: Số lượng cần kiểm tra (ví dụ: 10).
Output:
Thành công (200 OK): Trả về thông tin tồn kho nếu đủ hàng.
json

{
    "inventoryId": 1,
    "product": { "productId": 1, "name": "Áo thun trắng" },
    "quantity": 100,
    "warehouseId": 1,
    "lastUpdated": "2025-07-28T15:41:00"
}
Lỗi (400 Bad Request): Nếu không đủ hàng hoặc sản phẩm/kho không tồn tại.
Mục đích: Dùng để kiểm tra khả năng đáp ứng đơn hàng trước khi tạo (ví dụ: trong giỏ hàng trên Zalo OA).
Test Script: Kiểm tra status 200, inventoryId, productId, quantity, và warehouseId.
b. GET /api/promotions/current
Chức năng: Lấy danh sách khuyến mãi hiện hành dựa trên tổng giá trị đơn hàng.
Input:
Query param:
totalAmount: Tổng giá trị đơn hàng (ví dụ: 600000 VND).
Output:
Thành công (200 OK): Trả về mảng các khuyến mãi thỏa mãn (isActive=true, trong thời gian hiệu lực, totalAmount >= minOrderValue).
json

[
    {
        "promotionId": 1,
        "name": "Giảm 10%",
        "discountType": "PERCENTAGE",
        "discountValue": 10.0,
        "minOrderValue": 500000.0
    },
    {
        "promotionId": 2,
        "name": "Giảm 50K",
        "discountType": "FIXED_AMOUNT",
        "discountValue": 50000.0,
        "minOrderValue": 300000.0
    }
]
Lỗi (400 Bad Request): Nếu totalAmount không hợp lệ.
Mục đích: Hiển thị các khuyến mãi khả dụng cho khách hàng khi đặt hàng.
Test Script: Kiểm tra status 200, mảng khuyến mãi có 2 phần tử, chứa "Giảm 10%" và "Giảm 50K", không chứa "Giảm 30% cho đơn 7M".
c. POST /api/v1/orders/create
Chức năng: Tạo đơn hàng mới với thông tin khách hàng, sản phẩm, và khuyến mãi.
Input:
Body JSON:
json

{
    "zaloUserId": "zalo123",
    "items": [
        { "productId": 1, "quantity": 3, "warehouseId": 1 },
        { "productId": 2, "quantity": 2, "warehouseId": 1 }
    ],
    "promotionIds": [1, 2]
}
zaloUserId: Định danh khách hàng.
items: Danh sách sản phẩm (ID, số lượng, kho).
promotionIds: Danh sách ID khuyến mãi.
Output:
Thành công (200 OK): Trả về thông tin đơn hàng.
json

{
    "orderId": 1,
    "customer": { "customerId": 1, "name": "Nguyen Van A", "zaloUserId": "zalo123" },
    "promotions": [
        { "promotionId": 1, "name": "Giảm 10%" },
        { "promotionId": 2, "name": "Giảm 50K" }
    ],
    "totalAmount": 970000.0,
    "status": "PENDING",
    "orderDetails": [
        { "productId": 1, "quantity": 3, "warehouseId": 1, "subtotal": 600000.0 },
        { "productId": 2, "quantity": 2, "warehouseId": 1, "subtotal": 600000.0 }
    ]
}
Lỗi (400 Bad Request): Nếu khách hàng, sản phẩm, hoặc tồn kho không hợp lệ.
Mục đích: Tạo đơn hàng và trừ tồn kho, áp dụng khuyến mãi.
Test Script: Kiểm tra status 200, orderId, zaloUserId, totalAmount=970000, trạng thái PENDING, và lưu orderId vào biến môi trường.
d. POST /api/v1/shipping/send
Chức năng: Gửi thông tin vận chuyển cho đơn hàng.
Input:
Body JSON:
json

{
    "orderId": "{{orderId}}",
    "shippingAddress": "123 Đường Láng, Hà Nội",
    "shippingPartner": "GHN",
    "trackingNumber": "GHN123456",
    "estimatedDeliveryDate": "2025-07-30T12:00:00"
}
orderId: ID đơn hàng (từ /api/v1/orders/create).
Các trường khác: Thông tin vận chuyển.
Output:
Thành công (200 OK): Trả về thông tin vận chuyển.
json

Thu gọn

Bọc lại

Sao chép
{
    "deliveryId": 1,
    "order": { "orderId": 1 },
    "shippingAddress": "123 Đường Láng, Hà Nội",
    "shippingStatus": "PREPARING",
    "shippingPartner": "GHN",
    "trackingNumber": "GHN123456",
    "estimatedDeliveryDate": "2025-07-30T12:00:00"
}
Lỗi (400 Bad Request): Nếu orderId không tồn tại.
Mục đích: Lưu thông tin vận chuyển để theo dõi đơn hàng.
Test Script: Kiểm tra status 200, deliveryId, orderId, và các trường vận chuyển.
e. POST /api/v1/orders/{id}/cancel
Chức năng: Hủy đơn hàng và hoàn lại tồn kho.
Input:
Path param: id (ID đơn hàng, ví dụ: {{orderId}}).
Output:
Thành công (200 OK): Trả về thông tin đơn hàng đã hủy.
json
{
    "orderId": 1,
    "customer": { "customerId": 1, "name": "Nguyen Van A", "zaloUserId": "zalo123" },
    "promotions": [
        { "promotionId": 1, "name": "Giảm 10%" },
        { "promotionId": 2, "name": "Giảm 50K" }
    ],
    "totalAmount": 970000.0,
    "status": "CANCELLED",
    "orderDetails": [
        { "productId": 1, "quantity": 3, "warehouseId": 1, "subtotal": 600000.0 },
        { "productId": 2, "quantity": 2, "warehouseId": 1, "subtotal": 600000.0 }
    ]
}
Lỗi (400 Bad Request): Nếu orderId không tồn tại hoặc đơn hàng không ở trạng thái PENDING/CONFIRMED.
Mục đích: Hủy đơn hàng và hoàn lại tồn kho cho các sản phẩm trong OrderDetails.
Test Script: Kiểm tra status 200, orderId, trạng thái CANCELLED, totalAmount, và warehouseId.
