package com.OrderService.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Orderresponsemodel {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid")
	private Integer orderid ;
    
    @Column(name = "customerid")
    private Integer customerid;
    
    @Column(name = "customername")
    private String customername;

	public Orderresponsemodel() {
    	
    }

	public Orderresponsemodel(Integer orderid, Integer customerid, String customername
                              ) {
		super();
		this.orderid = orderid;
		this.customerid = customerid;
		this.customername = customername;

	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public Integer getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	@Override
	public String toString() {
		return "Orderresponsemodel [orderid=" + orderid + ", customerid=" + customerid + ", customername="
				+ customername +  "]";
	}

}
