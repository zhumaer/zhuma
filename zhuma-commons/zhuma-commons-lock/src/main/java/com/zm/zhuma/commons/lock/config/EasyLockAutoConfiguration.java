package com.zm.zhuma.commons.lock.config;

import com.zm.zhuma.commons.lock.helper.BusinessKeyHelper;
import com.zm.zhuma.commons.lock.handler.EasyLockAspectHandler;
import com.zm.zhuma.commons.lock.helper.LockInfoHelper;
import io.netty.channel.nio.NioEventLoopGroup;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import com.zm.zhuma.commons.lock.service.impl.LockFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.ClassUtils;

/**
 * @desc 自动配置类
 *
 * @author zhumaer
 * @since 6/27/2018 11:29 PM
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties(RedissonProperties.class)
@Import({EasyLockAspectHandler.class})
public class EasyLockAutoConfiguration {

    @Autowired
    private RedissonProperties redissonProperties;

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean
    RedissonClient redisson() throws Exception {
        Config config = new Config();
        if(redissonProperties.getClusterServer()!=null){
            config.useClusterServers().setPassword(redissonProperties.getPassword())
                    .addNodeAddress(redissonProperties.getClusterServer().getNodeAddresses());
        }else {
            config.useSingleServer().setAddress(redissonProperties.getAddress())
                    .setDatabase(redissonProperties.getDatabase())
                    .setPassword(redissonProperties.getPassword());
        }
        Codec codec=(Codec) ClassUtils.forName(redissonProperties.getCodec(),ClassUtils.getDefaultClassLoader()).newInstance();
        config.setCodec(codec);
        config.setEventLoopGroup(new NioEventLoopGroup());
        return Redisson.create(config);
    }

    @Bean
    public LockInfoHelper lockInfoProvider(){
        return new LockInfoHelper();
    }

    @Bean
    public BusinessKeyHelper businessKeyProvider(){
        return new BusinessKeyHelper();
    }

    @Bean
    public LockFactory lockFactory(){
        return new LockFactory();
    }
}
