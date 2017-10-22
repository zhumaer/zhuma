package com.zhuma.demo.configs;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableConfigurationProperties(DruidConfig.DruidProperties.class)
@ConditionalOnClass(DruidDataSource.class)
@ConditionalOnProperty(prefix = "druid", name = "url")
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class DruidConfig {

    @Autowired
    private DruidProperties properties;

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        if (properties.getInitialSize() > 0) {
            dataSource.setInitialSize(properties.getInitialSize());
        }
        if (properties.getMinIdle() > 0) {
            dataSource.setMinIdle(properties.getMinIdle());
        }
        if (properties.getMaxActive() > 0) {
            dataSource.setMaxActive(properties.getMaxActive());
        }
        dataSource.setTestOnBorrow(properties.isTestOnBorrow());
        try {
            dataSource.init();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }

    @ConfigurationProperties(prefix = "druid")
    public class DruidProperties {
    	private String url;
    	private String username;
    	private String password;
    	private String driverClass;

    	private int maxActive;
    	private int minIdle;
    	private int initialSize;
    	private boolean testOnBorrow;

    	public String getUrl() {
    		return url;
    	}

    	public void setUrl(String url) {
    		this.url = url;
    	}

    	public String getUsername() {
    		return username;
    	}

    	public void setUsername(String username) {
    		this.username = username;
    	}

    	public String getPassword() {
    		return password;
    	}

    	public void setPassword(String password) {
    		this.password = password;
    	}

    	public String getDriverClass() {
    		return driverClass;
    	}

    	public void setDriverClass(String driverClass) {
    		this.driverClass = driverClass;
    	}

    	public int getMaxActive() {
    		return maxActive;
    	}

    	public void setMaxActive(int maxActive) {
    		this.maxActive = maxActive;
    	}

    	public int getMinIdle() {
    		return minIdle;
    	}

    	public void setMinIdle(int minIdle) {
    		this.minIdle = minIdle;
    	}

    	public int getInitialSize() {
    		return initialSize;
    	}

    	public void setInitialSize(int initialSize) {
    		this.initialSize = initialSize;
    	}

    	public boolean isTestOnBorrow() {
    		return testOnBorrow;
    	}

    	public void setTestOnBorrow(boolean testOnBorrow) {
    		this.testOnBorrow = testOnBorrow;
    	}
    }
}
