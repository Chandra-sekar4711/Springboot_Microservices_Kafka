package com.OrderService.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orderproduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="orderid")
    Integer orderid;
    @Column(name ="customername")
    String customername;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "orderproduct_fk")
    List<Product_model> productdetail;
}
