package com.OrderService.Util;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Customer_model {


    Integer cid;
    String cname;
    String cage;
    String caddress;
    String ccity;
    Long cpno;

}
