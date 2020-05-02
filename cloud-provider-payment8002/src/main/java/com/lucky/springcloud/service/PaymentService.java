package com.lucky.springcloud.service;

import com.lucky.springcloud.entity.Payment;

public interface PaymentService {
    Payment getPayment(Long id);
    int create(Payment payment);
}
