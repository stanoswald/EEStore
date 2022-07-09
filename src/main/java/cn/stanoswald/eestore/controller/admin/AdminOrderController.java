package cn.stanoswald.eestore.controller.admin;

import cn.stanoswald.eestore.entity.CommonResponse;
import cn.stanoswald.eestore.entity.Order;
import cn.stanoswald.eestore.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/api/order")
public class AdminOrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("get/all")
    public ResponseEntity<Object> getAll() {
        try {
            List<Order> orderList = orderService.adminGetAll();
            return new CommonResponse.Builder().ok().message("获取所有订单成功").data("order_list", orderList).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new CommonResponse.Builder().error().message("获取所有订单失败").build();
        }
    }

    @PostMapping("ship")
    public ResponseEntity<Object> ship(@RequestParam("order_id") String orderId) {
        return orderService.ship(orderId) ?
                new CommonResponse.Builder().ok().message("订单发货成功").build()
                : new CommonResponse.Builder().error().message("订单发货失败").build();
    }

    @PostMapping("modify_address")
    public ResponseEntity<Object> modifyAddress(@RequestParam("order_id") String orderId, @RequestParam("address") String address) {
        return orderService.modifyAddress(orderId, address) ?
                new CommonResponse.Builder().ok().message("收货地址修改成功").build()
                : new CommonResponse.Builder().error().message("收货地址修改失败").build();
    }
}
