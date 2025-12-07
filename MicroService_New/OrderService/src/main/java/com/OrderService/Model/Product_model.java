package com.OrderService.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product_model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pid")
    private Integer pid;

    @Column(name = "pname")
    private String pname;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "category")
    private String category;

    @Column(name = "inventoryCount")
    private Integer inventoryCount;

    @Column(name = "createdAt")
    private LocalDate createdAt;

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "orderproduct_fk")
    Orderproduct orderproduct_fk;
}
