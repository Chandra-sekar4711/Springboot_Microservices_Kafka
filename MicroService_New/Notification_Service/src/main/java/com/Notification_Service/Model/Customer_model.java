package com.Notification_Service.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    public class Customer_model {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "cid")
        Integer cid;
        @Column(name = "cname")
        String cname;
        @Column(name = "cage")
        String cage;
        @Column(name = "caddress")
        String caddress;
        @Column(name = "ccity")
        String ccity;
        @Column(name = "cpno")
        Long cpno;

    }
