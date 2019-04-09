package com.zhuma.demo.model.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zhuxiaoma on 2018/12/11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayOrder {

    Long orderId;

    Double money;

    String randomNum;
}
