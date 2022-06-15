package cn.stanoswald.eestore.service.impl;

import cn.stanoswald.eestore.entity.OrderItem;
import cn.stanoswald.eestore.mapper.OrderItemMapper;
import cn.stanoswald.eestore.service.OrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单商品关系表 服务实现类
 * </p>
 *
 * @author StanOswald
 * @since 2022-06-15
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}
