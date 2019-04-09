package com.zhuma.demo.service.pay;

import com.zhuma.demo.model.bo.PayOrder;

/**
 * Created by zhuxiaoma on 2018/12/11
 */
public interface OrderPaymentService {

    boolean pay(PayOrder payOrder);

}
