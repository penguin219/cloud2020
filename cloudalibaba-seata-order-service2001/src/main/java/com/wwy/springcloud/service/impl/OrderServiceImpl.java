package com.wwy.springcloud.service.impl;

import com.wwy.springcloud.dao.OrderDao;
import com.wwy.springcloud.domain.Order;
import com.wwy.springcloud.service.AccountService;
import com.wwy.springcloud.service.OrderService;
import com.wwy.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    @Override
    public void create(Order order) {
        log.info("---->开始新建订单");
        orderDao.create(order);
        log.info("---->订单微服务开始调用库存，库存减少");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("---->订单微服务开始调用账户，余额减少");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("---->订单微服务修改订单状态");
        orderDao.update(order.getUserId(),order.getStatus());

    }
}
