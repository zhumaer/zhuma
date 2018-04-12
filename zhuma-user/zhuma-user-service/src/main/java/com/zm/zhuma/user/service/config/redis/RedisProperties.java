package com.zm.zhuma.user.service.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="spring.redis")
public class RedisProperties {

	private String host;

	private String password;

	private Integer timeout;

	private Integer port;

	@Autowired
	private Pool pool;

	@Configuration
	@ConfigurationProperties(prefix="spring.redis.pool")
	class Pool {

		private Integer maxIdle;

		private Integer minIdle;

		private Integer maxActive;

		private Integer maxWait;

		private Boolean testOnCreate;

		/**在获取连接的时候检查有效性*/
		private Boolean testOnBorrow;

		/**当调用return Object方法时，是否进行有效性检查*/
		private Boolean testOnReturn;

		/**在空闲时检查有效性*/
		private Boolean testWhileIdle;

		public Integer getMaxIdle() {
			return maxIdle;
		}

		public void setMaxIdle(Integer maxIdle) {
			this.maxIdle = maxIdle;
		}

		public Integer getMinIdle() {
			return minIdle;
		}

		public void setMinIdle(Integer minIdle) {
			this.minIdle = minIdle;
		}

		public Integer getMaxActive() {
			return maxActive;
		}

		public void setMaxActive(Integer maxActive) {
			this.maxActive = maxActive;
		}

		public Integer getMaxWait() {
			return maxWait;
		}

		public void setMaxWait(Integer maxWait) {
			this.maxWait = maxWait;
		}

		public Boolean getTestOnCreate() {
			return testOnCreate;
		}

		public void setTestOnCreate(Boolean testOnCreate) {
			this.testOnCreate = testOnCreate;
		}

		public Boolean getTestOnBorrow() {
			return testOnBorrow;
		}

		public void setTestOnBorrow(Boolean testOnBorrow) {
			this.testOnBorrow = testOnBorrow;
		}

		public Boolean getTestOnReturn() {
			return testOnReturn;
		}

		public void setTestOnReturn(Boolean testOnReturn) {
			this.testOnReturn = testOnReturn;
		}

		public Boolean getTestWhileIdle() {
			return testWhileIdle;
		}

		public void setTestWhileIdle(Boolean testWhileIdle) {
			this.testWhileIdle = testWhileIdle;
		}

	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Pool getPool() {
		return pool;
	}

	public void setPool(Pool pool) {
		this.pool = pool;
	}

}
