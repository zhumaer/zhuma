package com.zhuma.demo.service.pay.impl;

import com.zhuma.demo.model.bo.PayOrder;
import com.zhuma.demo.service.pay.OrderPaymentService;
import com.zm.zhuma.commons.lock.annotation.EasyLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by zhuxiaoma on 2018/12/11
 */
@Slf4j
@Service
public class OrderPaymentServiceImpl implements OrderPaymentService {

    @EasyLock(name = "locks:order-payment:", keys = {"#payOrder.orderId"}, leaseTime = 1000)
    @Override
    public boolean pay(PayOrder payOrder) {
        doSomething(100);
        return true;
    }

    private void doSomething(long costTime) {
        try {
            Thread.sleep(costTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
