package com.mznlmstpa_security.serviceImpl;

import com.mznlmstpa_security.dto.CartDto;
import com.mznlmstpa_security.dto.CartItemDto;
import com.mznlmstpa_security.entity.CartEntity;
import com.mznlmstpa_security.entity.CartItemEntity;
import com.mznlmstpa_security.repository.CartRepo;
import com.mznlmstpa_security.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;

    @Override
    public List<CartEntity> getAll() {
        return this.cartRepo.findAll();
    }

    @Override
    public Optional<CartEntity> getById(String id) {
        if (id.isEmpty()){
            return Optional.empty();
        }
        return this.cartRepo.findById(id);
    }

    @Override
    public Optional<CartEntity> save(CartDto request) {
        if (request == null){
            return Optional.empty();
        }

        CartEntity cart = new CartEntity();
        cart.setId(UUID.randomUUID().toString());
        cart.setInvoiceNo(request.getInvoiceNo());
        cart.setCostumerName(request.getCostumerName());

        double total = 0.0;
        for (CartItemDto itemsDto : request.getCartItems()){
            CartItemEntity itemEntity = new CartItemEntity();
            itemEntity.setId(UUID.randomUUID().toString());
            itemEntity.setCartId(cart.getId());
            itemEntity.setProductId(itemsDto.getProductId());
            itemEntity.setPrice(itemsDto.getPrice());
            itemEntity.setQty(itemsDto.getQty());
            itemEntity.setSubTotal(itemsDto.getQty() * itemsDto.getPrice());

            cart.addCartItem(itemEntity);

            total = total + itemEntity.getSubTotal();
        }
        cart.setTotal(total);

        cart.setCreatedAt(LocalDateTime.now());

        try {
            this.cartRepo.save(cart);
            log.info("Save Cart to database success");
            return Optional.of(cart);
        }catch (Exception e){
            log.error("Save cart to database failed, error{}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<CartEntity> simpleSave(CartDto request) {
        if (request == null){
            log.info("Request is Null");
            return Optional.empty();
        }

        CartEntity cart = new CartEntity(request);
        try {
            this.cartRepo.save(cart);
            log.info("Save cart to database success");
            return Optional.of(cart);
        }catch (Exception e){
            log.error("Save cart to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }
}
