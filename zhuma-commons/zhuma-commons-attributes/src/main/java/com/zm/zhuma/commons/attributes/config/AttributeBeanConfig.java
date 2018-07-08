package com.zm.zhuma.commons.attributes.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
//@EnableBinding(value=AttributeEventOutputs.class)
public class AttributeBeanConfig implements BeanDefinitionRegistryPostProcessor {

//	@Bean
//	public AttributeEventPublisher<Long> eventPublisher(){
//		DefaultAttributeEventPublisher<Long> publisher = new DefaultAttributeEventPublisher<>();
//		return publisher;
//	}
//
//    @ConditionalOnClass(AttributeDao.class)
//	@Bean
//	public AttributeService<Long> attributeService(AttributeDao<Long> attributeDao) {
//		AttributeServiceImpl<Long> ser = new AttributeServiceImpl<>();
//
//		ser.setAttributeDao(attributeDao);
//		ser.setTable("post_attrs");
//		return ser;
//	}

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        log.info("postProcessBeanDefinitionRegistry start");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

   /* @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        log.info("postProcessBeanFactory start");
        AnnotatedGenericBeanDefinition abd = new AnnotatedGenericBeanDefinition(beanClass);

        ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(abd);
        abd.setScope(scopeMetadata.getScopeName());
        // 可以自动生成name
        String beanName = (name != null ? name : this.beanNameGenerator.generateBeanName(abd, registry));

        AnnotationConfigUtils.processCommonDefinitionAnnotations(abd);

        BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(abd, beanName);
        BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, registry);
    }*/
}