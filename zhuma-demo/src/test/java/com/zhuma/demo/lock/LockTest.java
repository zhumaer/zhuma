package com.zhuma.demo.lock;

import com.zm.zhuma.commons.exceptions.BusinessException;
import com.zm.zhuma.commons.lock.annotation.EasyLock;
import com.zm.zhuma.commons.lock.model.LockType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RSortedSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringJUnit4ClassRunner.class)
public class LockTest {

	@Autowired
	private OrderCreateService orderCreateService;

	@Autowired
	private RedissonClient redissonClient;

	@Autowired
	private JedisPool jedisPool;

	@Autowired
	Jedis jedis;

	@Test
	public void createOrderTest() throws InterruptedException {
		String orderId = "2018-7-13";
		String cacheKey = "locks:create_order:" + orderId;
		long waitTime = 0L;
		long leaseTime = 20L;

		RLock rLock = redissonClient.getLock(cacheKey);
		boolean tryLockSuccess = rLock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
		if (!tryLockSuccess) {
			throw new BusinessException("正在下单，请勿重复提交");
		}

		try {
			orderCreateService.create(orderId);
		} finally {
			if(rLock.isHeldByCurrentThread()){
				rLock.unlockAsync();
			}
		}

	}

	@Test
	public void jedisCreateOrderTest() {
		String orderId = "2018-7-13";
		String cacheKey = "locks:create_order:" + orderId;

		Long result = jedis.setnx(cacheKey, "true");
		if (result != 1) {
			throw new BusinessException("正在下单，请勿重复提交");
		}

		try {
			orderCreateService.create(orderId);
		} finally {
			jedis.del(cacheKey);
		}
	}

	@Test
	public void jedisCreateOrderTest2() {
		String orderId = "2018-7-13";
		String cacheKey = "locks:create_order:" + orderId;
		int expireSec = 10;

		Long result = jedis.setnx(cacheKey, "true");
		if (result == 1) {
			jedis.expire(cacheKey, expireSec);
		}

		if (result != 1) {
			throw new BusinessException("正在下单，请勿重复提交");
		}

		try {
			orderCreateService.create(orderId);
		} finally {
			jedis.del(cacheKey);
		}
	}

	@Test
	public void jedisCreateOrderTest3() {
		String orderId = "2018-7-13";
		String cacheKey = "locks:create_order:" + orderId;
		int expireSec = 10;
		String requestId = UUID.randomUUID().toString();

		Long result = jedis.setnx(cacheKey, requestId);
		if (result == 1) {
			jedis.expire(cacheKey, expireSec);
		}

		if (result != 1) {
			throw new BusinessException("正在下单，请勿重复提交");
		}

		try {
			orderCreateService.create(orderId);
		} finally {
			if (requestId.equals(jedis.get(cacheKey))) {
				jedis.del(cacheKey);
			}
		}
	}

	private static final String LOCK_SUCCESS = "OK";
	private static final String SET_IF_NOT_EXIST = "NX";
	private static final String SET_WITH_EXPIRE_TIME = "PX";

	@Test
	public void jedisCreateOrderTest4() {
		String orderId = "2018-7-13";
		String cacheKey = "locks:create_order:" + orderId;
		int expireSec = 10;
		String requestId = UUID.randomUUID().toString();

		String result = jedis.set(cacheKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireSec);
		if (!LOCK_SUCCESS.equals(result)) {
			throw new BusinessException("正在下单，请勿重复提交");
		}

		try {
			orderCreateService.create(orderId);
		} finally {
			this.releaseLock(cacheKey, requestId);
		}
	}

	public boolean releaseLock(String cacheKey, String requestId) {
		String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
		Object result = jedis.eval(script, Collections.singletonList(cacheKey), Collections.singletonList(requestId));

		if (Objects.equals(RELEASE_SUCCESS, result)) {
			return true;
		}
		return false;

	}

	private static final Long RELEASE_SUCCESS = 1L;

	@Test
	public void create2() throws Exception {
	}

	@Test
	public void batchCreate() throws Exception {
		for (int i =1 ; i< 50; i++) {
			Runnable runer = () -> {
				log.info("runer i:{}", Thread.currentThread().getName());
				orderCreateService.create("10001");
			};
			new Thread(runer).start();
		}
		Thread.yield();
		log.info("batchCreate end");
	}


	@Test
	public void testClient() {
		RSortedSet<String> sortedSet = redissonClient.getSortedSet("aiyouwei2");
		boolean addFlag = sortedSet.add("啦啦啦");
		Assert.assertNotNull(addFlag);
	}

}
