package cn.stanoswald.eestore.service.impl;

import cn.stanoswald.eestore.entity.Item;
import cn.stanoswald.eestore.entity.Order;
import cn.stanoswald.eestore.entity.OrderItem;
import cn.stanoswald.eestore.mapper.ItemMapper;
import cn.stanoswald.eestore.mapper.OrderItemMapper;
import cn.stanoswald.eestore.mapper.OrderMapper;
import cn.stanoswald.eestore.service.OrderService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 订单表 服务实现类
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

    @Resource
    ItemMapper itemMapper;

    @Transactional
    @Override
    public String create(Order order) {
        try {
            order.setOrderId(UUID.randomUUID().toString());
            order.setCreateTime(LocalDateTime.now());
            orderMapper.insert(order);
            order.getItemList().stream()
                    .peek(orderItem -> orderItem.setOrderId(order.getOrderId()))
                    .forEach(orderItemMapper::insert);
            for (OrderItem orderItem : order.getItemList()){
                reduceItem(orderItem.getItemId(),orderItem.getItemCount());
            }
            return order.getOrderId();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Order get(String orderId) {
        try {
            Order order = orderMapper.selectById(orderId);
            if (Optional.ofNullable(order).isEmpty())
                return null;
            List<OrderItem> orderItemList = orderItemMapper.selectList(Wrappers.lambdaQuery(OrderItem.class)
                    .select(OrderItem::getItemId, OrderItem::getItemCount, OrderItem::getItemPrice)
                    .eq(OrderItem::getOrderId, orderId));
            order.setItemList(orderItemList);
            return order;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Order> getAll(String uid) {
        try {
            List<Order> orderList = orderMapper.selectList(Wrappers.lambdaQuery(Order.class).eq(Order::getUid, uid));
            if (orderList.size() == 0)
                return null;
            return addOrderItemsToOrders(orderList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Boolean finish(String orderId) {
        try {
            Order order = new Order();
            order.setOrderId(orderId);
            order.setFinishedTime(LocalDateTime.now());
            return orderMapper.updateById(order) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean modifyAddress(String orderId, String address) {
        try {
            Order order = new Order();
            order.setOrderId(orderId);
            order.setAddress(address);
            return orderMapper.updateById(order) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Order> getToBeDelivered() {
        try {
            List<Order> orderList = orderMapper.selectList(Wrappers.lambdaQuery(Order.class).isNull(Order::getShipTime));
            if (orderList.size() == 0)
                return null;
            return addOrderItemsToOrders(orderList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Boolean ship(String orderId) {
        try {
            Order order = new Order();
            order.setOrderId(orderId);
            order.setShipTime(LocalDateTime.now());
            return orderMapper.updateById(order) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @NotNull
    private List<Order> addOrderItemsToOrders(List<Order> orderList) {
        Set<String> orderIdSet = orderList.stream().map(Order::getOrderId).collect(Collectors.toSet());
        List<OrderItem> orderItemList = orderItemMapper.selectList(Wrappers.lambdaQuery(OrderItem.class)
                .select(info -> !info.getColumn().equals("item_order_id"))
                .in(OrderItem::getOrderId, orderIdSet));
        Map<String, List<OrderItem>> resMap = orderItemList.stream().collect(Collectors.groupingBy(OrderItem::getOrderId));
        orderList.forEach(order -> order.setItemList(resMap.get(order.getOrderId())));
        return orderList;
    }

    private Boolean reduceItem(String itemId,Integer item_count){
        Item item = new Item();
        item.setItemId(itemId);
        Integer itemStock = itemMapper.selectById(itemId).getItemStock();
        if(itemStock >= item_count) {
            item.setItemStock((itemStock - item_count));
        }else {
            return false;
        }
        return itemMapper.updateById(item) == 1;
    }
}
