package cn.stanoswald.eestore.controller.admin;

import cn.stanoswald.eestore.entity.CommonResponse;
import cn.stanoswald.eestore.entity.Order;
import cn.stanoswald.eestore.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
