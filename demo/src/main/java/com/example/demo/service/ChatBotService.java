package com.example.demo.service;


import com.example.demo.Request.OrderItemRequest;
import com.example.demo.Request.ChatBotRequest;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Promotion;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class ChatBotService {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(ChatBotService.class);

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private OrderService orderService;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String CHATBOT_API_URL = "https://l1af2027-cocacolavietnambackend.hf.space/chat";

    public Object processChatbotRequest(ChatBotRequest request) {
        try {
            // Gọi API chatbot
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
            HttpEntity<ChatBotRequest> entity = new HttpEntity<>(request, headers);
            ResponseEntity<String> response = restTemplate.exchange(CHATBOT_API_URL, HttpMethod.POST, entity, String.class);

            // Kiểm tra mã trạng thái
            if (!response.getStatusCode().is2xxSuccessful()) {
                logger.error("Chatbot API returned non-2xx status: {}, response: {}", response.getStatusCode(), response.getBody());
                throw new RuntimeException("Chatbot API failed with status: " + response.getStatusCode());
            }

            // Log response để debug
            logger.info("Chatbot API response: {}", response.getBody());

            // Parse JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonResponse = mapper.readTree(response.getBody());

            // Kiểm tra object "nlu"
            JsonNode nluNode = jsonResponse.get("nlu");
            if (nluNode == null) {
                logger.error("Key 'nlu' not found in chatbot response: {}", response.getBody());
                throw new RuntimeException("Key 'nlu' not found in chatbot response");
            }

            // Kiểm tra key "intent" trong "nlu"
            JsonNode intentNode = nluNode.get("intent");
            if (intentNode == null) {
                logger.error("Key 'intent' not found in chatbot response: {}", response.getBody());
                throw new RuntimeException("Key 'intent' not found in chatbot response");
            }

            String intent = intentNode.asText();
            logger.info("Parsed intent: {}", intent);

            // Xử lý dựa trên intent
            switch (intent) {
                case "promotion":
                    // Giả sử totalAmount từ giỏ hàng hoặc mặc định
                    BigDecimal totalAmount = new BigDecimal("800000");
                    List<Promotion> promotions = promotionService.getCurrentPromotions(totalAmount);
                    return promotions;

                case "" +
                             "er":
                    // Lấy zaloUserId từ request hoặc mặc định
                    String zaloUserId = request.getZaloUserId() != null ? request.getZaloUserId() : "zalo123";
                    List<OrderItemRequest> items = Arrays.asList(
                            new OrderItemRequest(1, 3, 1), // 3 áo thun trắng
                            new OrderItemRequest(2, 2, 1)  // 2 quần jeans xanh
                    );
                    List<Integer> promotionIds = Arrays.asList(1, 2); // Mặc định mã giảm giá
                    Orders order = orderService.createOrder(zaloUserId, items, promotionIds);
                    return order;

                default:
                    logger.warn("Unknown intent: {}", intent);
                    throw new RuntimeException("Unknown intent: " + intent);
            }
        } catch (Exception e) {
            logger.error("Error processing chatbot request: {}", e.getMessage(), e);
            throw new RuntimeException("Error processing chatbot request: " + e.getMessage());
        }
    }
}
