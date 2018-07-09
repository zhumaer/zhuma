package com.zhuma.demo.lock;

import com.zm.zhuma.commons.lock.annotation.EasyLock;
import com.zm.zhuma.commons.lock.model.LockType;

public interface OrderCreateService {

	void create(String orderId);
}
