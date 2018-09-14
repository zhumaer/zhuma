package com.zm.zhuma.commons.util.test;

import com.zm.zhuma.commons.util.annotations.FullToHalf;
import com.zm.zhuma.commons.util.annotations.Trim;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SourceBean2 extends BaseSourceBean{

    @Trim
    @FullToHalf
    private String name;

    @Trim
    private String trimName;
}
