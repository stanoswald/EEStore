package cn.stanoswald.eestore.controller;

import cn.stanoswald.eestore.entity.CommonResponse;
import cn.stanoswald.eestore.entity.Order;
import cn.stanoswald.eestore.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author StanOswald
 * @since 2022-06-15
 */
@RestController
@RequestMapping("/user/api/order")
public class OrderController {

    @Resource
    OrderService orderService;

    @RequestMapping("/create")
    @PostMapping
    public ResponseEntity<Object> create() {
        return null;
    }

    @RequestMapping("/get")
    @GetMapping
    public ResponseEntity<Object> get(@RequestParam("order_id") String orderId) {
        Order order = orderService.get(orderId);
        return new CommonResponse.Builder().ok().message("订单获取成功").data("order", order).build();
    }

    @RequestMapping("/finish")
    @PostMapping
    public ResponseEntity<Object> finish() {
        return null;
    }
}
