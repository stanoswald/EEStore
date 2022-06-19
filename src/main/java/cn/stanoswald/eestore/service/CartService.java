package cn.stanoswald.eestore.service;

import cn.stanoswald.eestore.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 购物车表 服务类
 *
 * @author yjw
 * @since 2022-06-14
 */
public interface CartService extends IService<Cart> {

    List<Cart> findByUid(String uid);

    Boolean deleteByIdS(String uid, String cartId);

    Boolean setCart(String uid, String itemId, Integer itemCount);

    Boolean addCart(String uid,String itemId);

}
