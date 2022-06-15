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
import java.util.List;

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
    public Boolean finish(String oderId) {
        return null;
    }

    @Override
    public Boolean modifyAddress(String oderId, String Address) {
        return null;
    }

    @Override
    public List<Order> getToBeDelivered() {
        return null;
    }

    @Override
    public Boolean ship(String orderId) {
        return null;
    }
}
