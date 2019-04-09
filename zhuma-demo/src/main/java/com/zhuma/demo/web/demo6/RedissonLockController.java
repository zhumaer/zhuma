package com.zhuma.demo.web.demo6;

import com.zhuma.demo.model.bo.PayOrder;
import com.zhuma.demo.service.pay.OrderPaymentService;
import com.zm.zhuma.commons.attributes.model.AttributesChange;
import com.zm.zhuma.commons.web.annotations.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by zhuxiaoma on 2018/12/17
 */
@Slf4j
@ResponseResult
@RestController("demo6RedissonLockController")
@RequestMapping("demo6/lock/{id}")
public class RedissonLockController {

    @Autowired
    private OrderPaymentService orderPaymentService;

    @PostMapping
    boolean lock(@PathVariable("id") Long id) {
        PayOrder payOrder = PayOrder.builder()
                .orderId(id)
                .money(66.66D)
                .build();

        return orderPaymentService.pay(payOrder);
    }

}
