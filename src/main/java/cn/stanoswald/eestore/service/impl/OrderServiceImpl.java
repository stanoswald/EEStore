package cn.stanoswald.eestore.service.impl;

import cn.stanoswald.eestore.entity.Order;
import cn.stanoswald.eestore.entity.OrderItem;
import cn.stanoswald.eestore.mapper.OrderItemMapper;
import cn.stanoswald.eestore.mapper.OrderMapper;
import cn.stanoswald.eestore.service.OrderService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author StanOswald
 * @since 2022-06-15
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    OrderMapper orderMapper;

    @Resource
    OrderItemMapper orderItemMapper;

    @Override
    public String create() {
        return null;
    }

    @Override
    public Order get(String orderId) {
        Order order = orderMapper.selectById(orderId);
        if (order != null) {
            List<OrderItem> orderItemList = orderItemMapper.selectList(Wrappers.lambdaQuery(OrderItem.class)
                    .select(OrderItem::getItemId, OrderItem::getItemCount)
                    .eq(OrderItem::getOrderId, orderId));
            order.setItemList(orderItemList);
        } else
            return null;

        return order;
    }

    @Override
    public List<Order> getAll(String uid) {
        return null;
    }

    @Override
    public Boolean finish(String orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setFinishedTime(LocalDateTime.now());
        return orderMapper.updateById(order) == 1;
    }

    @Override
    public Boolean modifyAddress(String oderId, String Address) {
        return null;
    }

    @Override
    public List<Order> getToBeDelivered() {
        List<Order> orderList = orderMapper.selectList(Wrappers.lambdaQuery(Order.class).isNull(Order::getShipTime));
        if (orderList.size() > 0) {
            Set<String> orderIdSet = orderList.stream().map(Order::getOrderId).collect(Collectors.toSet());
            List<OrderItem> orderItemList = orderItemMapper.selectList(Wrappers.lambdaQuery(OrderItem.class)
                    .select(OrderItem::getItemId, OrderItem::getItemCount,OrderItem::getOrderId)
                    .in(OrderItem::getOrderId, orderIdSet));

            Map<String, List<OrderItem>> resMap = orderItemList.stream().collect(Collectors.groupingBy(OrderItem::getOrderId));
            orderList.forEach(order -> order.setItemList(resMap.get(order.getOrderId())));
        } else
            return null;
        return orderList;
    }

    @Override
    public Boolean ship(String orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setShipTime(LocalDateTime.now());
        return orderMapper.updateById(order) == 1;
    }
}
