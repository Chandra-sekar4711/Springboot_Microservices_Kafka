package com.OrderService.Service;

import com.OrderService.Model.Orderproduct;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface OrderService {
    Orderproduct createOrder(Map<String,Object> req);
}
