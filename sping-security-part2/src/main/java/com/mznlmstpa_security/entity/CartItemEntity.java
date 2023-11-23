package com.mznlmstpa_security.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mznlmstpa_security.dto.CartItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_cart_item")
public class CartItemEntity {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "cart_id",length = 36, nullable = false)
    private String cartId;

    @Column(name = "product_id", length = 36, nullable = false)
    private String productId;

    @Column(name = "price")
    private Double price;

    @Column(name = "qty")
    private Double qty;

    @Column(name = "sub_total")
    private Double subTotal;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    @JsonBackReference
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", insertable = false, updatable = false)
    @JsonBackReference
    private CartEntity cart;

    public CartItemEntity(CartItemDto itemsDto, String cartId) {
        this.id = UUID.randomUUID().toString();
        this.cartId = cartId;
        this.productId = itemsDto.getProductId();
        this.price = itemsDto.getPrice();
        this.qty = itemsDto.getQty();
        this.subTotal = itemsDto.getQty() * itemsDto.getPrice();
    }
}
