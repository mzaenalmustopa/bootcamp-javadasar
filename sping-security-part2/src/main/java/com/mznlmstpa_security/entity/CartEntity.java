package com.mznlmstpa_security.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mznlmstpa_security.dto.CartDto;
import com.mznlmstpa_security.dto.CartItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_cart")
public class CartEntity {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "costumer_name", length = 100)
    private String costumerName;

    @Column(name = "invoice_no", length = 20)
    private String invoiceNo;

    @Column(name = "total")
    private Double total;

    @Column(name = "created_at", length = 20)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<CartItemEntity> cartItems = new ArrayList<>();

    public void addCartItem(CartItemEntity itemEntity){
        this.cartItems.add(itemEntity);
        itemEntity.setCart(this);
    }

    public CartEntity(CartDto cartDto) {
        this.id = UUID.randomUUID().toString();
        this.costumerName =  cartDto.getCostumerName();
        this.invoiceNo = cartDto.getInvoiceNo();
        this.createdAt = LocalDateTime.now();

        Double  vTotal = 0.0;
        for (CartItemDto itemsDto : cartDto.getCartItems()){
            CartItemEntity cartItemEntity = new CartItemEntity(itemsDto, this.id);
            this.addCartItem(cartItemEntity);
            vTotal += cartItemEntity.getSubTotal();
        }
        this.total = vTotal;
    }
}
