package com.o2o.rmq.store.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.o2o.rmq.store.bean.Order;
import com.o2o.rmq.store.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService {

	@Transactional(readOnly = true)
	@Override
	public int update(Order order) {
		// TODO Auto-generated method stub
		return 1;
	}

}
