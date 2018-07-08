package com.zhuma.demo.config.bean;

import com.zm.zhuma.commons.attributes.mapper.AttributeMapper;
import com.zm.zhuma.commons.attributes.service.AttributeService;
import com.zm.zhuma.commons.attributes.service.impl.AttributeServiceImpl;
import com.zm.zhuma.commons.web.aspect.RestControllerAspect;
import com.zm.zhuma.commons.web.decoder.FeignErrorDecoder;
import com.zm.zhuma.commons.web.handler.GlobalExceptionHandler;
import com.zm.zhuma.commons.web.handler.ResponseResultHandler;
import com.zm.zhuma.user.token.service.impl.LoginTokenCacheServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@ConditionalOnClass(value = { LoginTokenCacheServiceImpl.class, RedisTemplate.class })
public class BeanConfig {

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Bean
    public FeignErrorDecoder feignErrorDecoder() {
        return new FeignErrorDecoder();
    }

    @Bean
    public RestControllerAspect restControllerAspect() {
        return new RestControllerAspect();
    }

    @Bean
    public ResponseResultHandler responseResultHandler() {
        return new ResponseResultHandler();
    }

    @Bean
	public AttributeService<String> userAttributeService(AttributeMapper<String> attributeDao) {
		AttributeService<String> userAttributeService = new AttributeServiceImpl<>("user_attr", attributeDao, null);
		return userAttributeService;
	}
}
