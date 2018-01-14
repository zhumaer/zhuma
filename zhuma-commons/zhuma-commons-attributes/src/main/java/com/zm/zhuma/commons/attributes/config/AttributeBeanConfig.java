package com.zm.zhuma.commons.attributes.config;

import com.zm.zhuma.commons.attributes.event.AttributeEventOutputs;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;


//@MapperScan(basePackages = { "com.dx.*.**.*.dao","cn.get88.xuegutang.adschudule.dao" })
@Configuration
@EnableBinding(value=AttributeEventOutputs.class)
public class AttributeBeanConfig {

//	@Bean
//	public AttributeEventPublisher<Long> eventPublisher(){
//		DefaultAttributeEventPublisher<Long> publisher = new DefaultAttributeEventPublisher<>();
//		return publisher;
//	}
//	
//	@Bean
//	public AttributeService<Long> attributeService(AttributeDao<Long> attributeDao) {
//		AttributeServiceImpl<Long> ser = new AttributeServiceImpl<>();
//
//		ser.setAttributeDao(attributeDao);
//		ser.setTable("post_attrs");
//		return ser;
//	}

}