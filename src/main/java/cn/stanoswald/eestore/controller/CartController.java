package cn.stanoswald.eestore.controller;

import cn.stanoswald.eestore.entity.Cart;
import cn.stanoswald.eestore.entity.CommonResponse;
import cn.stanoswald.eestore.service.impl.CartServiceImpl;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/user/cart")
public class CartController {

    @Resource
    private CartServiceImpl cartService;

    //查找购物车信息
    @PostMapping("/getByUid")
    public ResponseEntity<Object> getCartByUid(@RequestParam("uid") String uid) {
        try {
            List<Cart> list = cartService.findByUid(uid);
            return new CommonResponse.Builder().ok().message("个人购物车").data("ByUidCart", list).build();
        } catch (Exception e) {
            return new CommonResponse.Builder().error().build();
        }
    }

    //删除购物车
    @DeleteMapping("/delByIds")
    public ResponseEntity<Object> delByIds(@RequestParam("uid") String uid, @RequestParam("cart_id") String cart_id) {
        if (cartService.deleteByIdS(uid, cart_id)) {
            return new CommonResponse.Builder().ok().message("删除成功").data("isTrue", true).build();
        } else {
            return new CommonResponse.Builder().error().message("删除失败").data("isTrue", false).build();
        }
    }

    //添加购物车
    @PostMapping("/addCart")
    public ResponseEntity<Object> addCart(@RequestParam Map<String, String> params) {
        if (cartService.setCart(params.get("uid"), params.get("item_id"), Integer.valueOf(params.get("item_count")))) {
            return new CommonResponse.Builder().ok().message("添加成功").data("isTrue", true).build();
        } else {
            return new CommonResponse.Builder().error().message("添加失败").data("isTrue", false).build();
        }
    }
}
