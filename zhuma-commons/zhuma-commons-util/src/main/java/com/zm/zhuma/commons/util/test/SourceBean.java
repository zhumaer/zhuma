package com.zm.zhuma.commons.util.test;

import com.zm.zhuma.commons.util.annotations.Convert;
import com.zm.zhuma.commons.util.annotations.FullToHalf;
import com.zm.zhuma.commons.util.annotations.Trim;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class SourceBean extends BaseSourceBean {

    @Trim
    @FullToHalf
    private String name;

    @Trim
    private String trimName;

    @Convert
    private Set<BaseSourceBean> baseSourceBeanList;

}
