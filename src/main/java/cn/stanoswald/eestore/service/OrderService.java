package cn.stanoswald.eestore.service;

import cn.stanoswald.eestore.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author StanOswald
 * @since 2022-06-15
 */
public interface OrderService extends IService<Order> {

    /**
     * 创建订单
     * @return 订单ID
     */
    String create();

    /**
     * 获取订单
     * @param orderId 订单Id
     * @return 订单实体
     */
    Order get(String orderId);

    /**
     * 用户获取所有订单
     * @param uid 用户ID
     * @return 用户订单列表
     */
    List<Order> getAll(String uid);

    /**
     * 确认收货
     * @param oderId 订单Id
     * @return 确认是否成功
     */
    Boolean finish(String oderId);

    /**
     * 修改收货地址
     * @param oderId 订单ID
     * @param Address 待修改地址
     * @return 修改地址是否成功
     */
    Boolean modifyAddress(String oderId, String Address);

    /**
     * 管理员获取待发货订单
     * @return 待发货订单列表
     */
    List<Order> getToBeDelivered();


    /**
     * 发货
     * @param orderId 订单ID
     * @return 发货是否成功
     */
    Boolean ship(String orderId);

}
