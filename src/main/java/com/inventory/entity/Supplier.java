package com.inventory.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;

    private String name;
    private String contactInfo;
    
    @OneToMany(mappedBy = "supplier",cascade= CascadeType.ALL)
    private List<Product> products;
    
	public Object getName() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getContactInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setName(Object name2) {
		// TODO Auto-generated method stub
		
	}
	public void setContactInfo(Object contactInfo2) {
		// TODO Auto-generated method stub
		
	}
	public Object getProductsSupplied() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setProductsSupplied(Object productsSupplied) {
		// TODO Auto-generated method stub
		
	}
}