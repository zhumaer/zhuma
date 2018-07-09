package com.zhuma.demo.lock;

import com.zm.zhuma.commons.lock.annotation.EasyLock;
import com.zm.zhuma.commons.lock.model.LockType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RSortedSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringJUnit4ClassRunner.class)
public class LockTest {

	@Autowired
	private OrderCreateService orderCreateService;

	@Autowired
	private RedissonClient redissonClient;

	@Test
	public void create() throws Exception {
		orderCreateService.create("1");
	}

	@Test
	public void create2() throws Exception {
		this.createOrder();
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

	@EasyLock(name = "order", keys = "#orderId", lockType = LockType.Reentrant, waitTime = 120, leaseTime = 10)
	private void createOrder() {
		orderCreateService.create("10001L");
	}

	@Test
	public void testClient() {
		RSortedSet<String> sortedSet = redissonClient.getSortedSet("aiyouwei2");
		boolean addFlag = sortedSet.add("啦啦啦");
		Assert.assertNotNull(addFlag);
	}

}
