package cn.stanoswald.eestore.controller;

import cn.stanoswald.eestore.entity.Cart;
import cn.stanoswald.eestore.entity.CommonResponse;
import cn.stanoswald.eestore.service.impl.CartServiceImpl;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 购物车表 前端控制器
 * </p>
 *
 * @author yjw
 * @since 2022-06-14
 */
@RestController
@Slf4j
@RequestMapping("/user/api/cart")
public class CartController {

    @Resource
    private CartServiceImpl cartService;
    //查找购物车信息
    @PostMapping("/get")
    public ResponseEntity<Object> getCartByUid(@AuthenticationPrincipal Jwt jwt) {
        log.info(jwt.toString());
        try {
            List<Cart> list = cartService.findByUid(jwt.getSubject());
            return new CommonResponse.Builder().ok().message("个人购物车").data("ByUidCart", list).build();
        } catch (Exception e) {
            log.info("bug:"+e.getMessage());
            return new CommonResponse.Builder().error().build();
        }
    }

    //删除购物车
    @DeleteMapping("/delete")
    public ResponseEntity<Object> delByIds(@AuthenticationPrincipal Jwt jwt, @RequestParam("cart_id") String cart_id) {
        if (cartService.deleteByIdS(jwt.getSubject(), cart_id)) {
            return new CommonResponse.Builder().ok().message("删除成功").data("isTrue", true).build();
        } else {
            return new CommonResponse.Builder().error().message("删除失败").data("isTrue", false).build();
        }
    }

    //添加购物车
    @PostMapping("/add")
    public ResponseEntity<Object> addCart(@AuthenticationPrincipal Jwt jwt,@RequestParam("item_id") String item_id,@RequestParam("item_count") String item_count) {
        if (cartService.setCart(jwt.getSubject(),item_id, Integer.valueOf(item_count))) {
            return new CommonResponse.Builder().ok().message("添加成功").data("isTrue", true).build();
        } else {
            return new CommonResponse.Builder().error().message("添加失败").data("isTrue", false).build();
        }
    }
}
