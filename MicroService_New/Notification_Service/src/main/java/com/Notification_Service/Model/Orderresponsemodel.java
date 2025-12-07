package com.Notification_Service.Model;

import java.util.List;

public class Orderresponsemodel {


    private Integer orderid ;

    private Integer customerid;

    private String customername;

    public List<?> productresponse;



    public Orderresponsemodel() {

    }



    public Orderresponsemodel(Integer orderid, Integer customerid, String customername,
                              List<?> productresponse) {
        super();
        this.orderid = orderid;
        this.customerid = customerid;
        this.customername = customername;
        this.productresponse = productresponse;
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
                + customername + ", Productresponse=" + productresponse + "]";
    }


}
