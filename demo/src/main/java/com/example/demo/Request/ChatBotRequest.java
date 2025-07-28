package com.example.demo.Request;

public class ChatBotRequest {
    private String message;
    private String feedback;
    private String zaloUserId; // Thêm để nhận zalo_user_id từ chatbot (nếu có)


    public ChatBotRequest() {
    }

    public ChatBotRequest(String message, String feedback, String zaloUserId) {
        this.message = message;
        this.feedback = feedback;
        this.zaloUserId = zaloUserId;
    }

    // Getters and Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
    public String getZaloUserId() { return zaloUserId; }
    public void setZaloUserId(String zaloUserId) { this.zaloUserId = zaloUserId; }
}
