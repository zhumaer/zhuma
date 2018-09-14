package com.zm.zhuma.commons.util.test;

import com.zm.zhuma.commons.util.annotations.FullToHalf;
import com.zm.zhuma.commons.util.annotations.Trim;
import lombok.Data;

@Data
public class BaseSourceBean {
    @Trim
    @FullToHalf
    private String baseName;
}
