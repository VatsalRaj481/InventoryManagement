package com.inventory.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    private Long productId;

    public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(int reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public void setLowStock(boolean lowStock) {
		this.lowStock = lowStock;
	}

	@OneToOne
    @MapsId
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
    private int reorderLevel;

    @Transient
    private boolean lowStock;

    public boolean isLowStock() {
        return quantity <= reorderLevel;
    }
}
