package cn.stanoswald.eestore.controller;

import cn.stanoswald.eestore.entity.CommonResponse;
import cn.stanoswald.eestore.entity.Order;
import cn.stanoswald.eestore.service.OrderService;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单表 前端控制器
 *
 * @author StanOswald
 * @since 2022-06-15
 */
@RestController
@RequestMapping("/user/api/order")
public class OrderController {

    @Resource
    OrderService orderService;

    @PostMapping("create")
    public ResponseEntity<Object> create(@AuthenticationPrincipal Jwt jwt, @RequestBody Order order) {
        order.setUid(jwt.getSubject());
        String orderId = orderService.create(order);
        return StringUtils.isNotEmpty(orderId) ?
                new CommonResponse.Builder().ok().message("订单创建成功").data("order_id", orderId).build()
                : new CommonResponse.Builder().error().message("订单创建失败").build();
    }

    @PostMapping("finish")
    public ResponseEntity<Object> finish(@RequestParam("order_id") String orderId) {
        return orderService.finish(orderId) ?
                new CommonResponse.Builder().ok().message("订单收货成功").build()
                : new CommonResponse.Builder().error().message("订单收货失败").build();
    }

    @GetMapping("get")
    public ResponseEntity<Object> get(@RequestParam("order_id") String orderId) {
        try {
            Order order = orderService.get(orderId);
            return new CommonResponse.Builder().ok().message("订单获取成功").data("order", order).build();
        } catch (RuntimeException e) {
            return new CommonResponse.Builder(HttpStatus.INTERNAL_SERVER_ERROR).message("订单获取失败").build();
        }
    }

    @GetMapping("get/all")
    public ResponseEntity<Object> getAll(@AuthenticationPrincipal Jwt jwt) {
        try {
            List<Order> orderList = orderService.getAll(jwt.getSubject());
            return new CommonResponse.Builder().ok().message("用户全部订单获取成功").data("order_list", orderList).build();
        } catch (RuntimeException e) {
            return new CommonResponse.Builder().error().message("用户全部订单获取失败").build();
        }
    }

    @GetMapping("get/to_be_delivered")
    public ResponseEntity<Object> getToBeDelivered() {
        try {
            List<Order> toBeDelivered = orderService.getToBeDelivered();
            return new CommonResponse.Builder().ok().message("待发货订单获取成功").data("orders", toBeDelivered).build();
        } catch (RuntimeException e) {
            return new CommonResponse.Builder().error().message("待发货订单获取失败").build();
        }
    }

    @PostMapping("modify_address")
    public ResponseEntity<Object> modifyAddress(@RequestParam("order_id") String orderId, @RequestParam("address") String address) {
        return orderService.modifyAddress(address, orderId) ?
                new CommonResponse.Builder().ok().message("收货地址修改成功").build()
                : new CommonResponse.Builder().error().message("收货地址修改失败").build();
    }

    @PostMapping("ship")
    public ResponseEntity<Object> ship(@RequestParam("order_id") String orderId) {
        return orderService.ship(orderId) ?
                new CommonResponse.Builder().ok().message("订单发货成功").build()
                : new CommonResponse.Builder().error().message("订单发货失败").build();
    }
}
