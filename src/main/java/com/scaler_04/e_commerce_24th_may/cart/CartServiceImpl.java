package com.scaler_04.e_commerce_24th_may.cart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private String cacheKey(String email) { return "cart:" + email; }

    @Override
    public CartDocument getCart(String userEmail) {
        String cached = redisTemplate.opsForValue().get(cacheKey(userEmail));
        if (cached != null) {
            try { return objectMapper.readValue(cached, CartDocument.class); } catch (Exception ignored) {}
        }
        CartDocument cart = cartRepository.findById(userEmail).orElse(new CartDocument(userEmail, new java.util.ArrayList<>()));
        cache(cart);
        return cart;
    }

    @Override
    public CartDocument addItem(String userEmail, CartItem item) {
        CartDocument cart = getCart(userEmail);
        var existing = cart.getItems().stream().filter(i -> i.getProductId().equals(item.getProductId())).findFirst();
        if (existing.isPresent()) {
            existing.get().setQuantity(existing.get().getQuantity() + item.getQuantity());
        } else {
            cart.getItems().add(item);
        }
        return save(cart);
    }

    @Override
    public CartDocument updateItem(String userEmail, CartItem item) {
        CartDocument cart = getCart(userEmail);
        cart.getItems().removeIf(i -> i.getProductId().equals(item.getProductId()));
        cart.getItems().add(item);
        return save(cart);
    }

    @Override
    public CartDocument removeItem(String userEmail, Long productId) {
        CartDocument cart = getCart(userEmail);
        cart.getItems().removeIf(i -> i.getProductId().equals(productId));
        return save(cart);
    }

    @Override
    public void clear(String userEmail) {
        cartRepository.deleteById(userEmail);
        redisTemplate.delete(cacheKey(userEmail));
    }

    private CartDocument save(CartDocument cart) {
        CartDocument saved = cartRepository.save(cart);
        cache(saved);
        return saved;
    }

    private void cache(CartDocument cart) {
        try {
            redisTemplate.opsForValue().set(cacheKey(cart.getId()), objectMapper.writeValueAsString(cart), Duration.ofHours(6));
        } catch (JsonProcessingException ignored) {}
    }
}


