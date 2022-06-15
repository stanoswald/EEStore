package cn.stanoswald.eestore.service.impl;

import cn.stanoswald.eestore.entity.Cart;
import cn.stanoswald.eestore.mapper.CartMapper;
import cn.stanoswald.eestore.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author yjw
 * @since 2022-06-14
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Resource
    private Cart cart;

    @Resource
    private CartMapper cartMapper;

    //查询购物车信息
    public List<Cart> findByUid(String uid) {
        return cartMapper.getByUid(uid);
    }

    //删除购物车信息
    public Boolean deleteByIdS(String uid,String cartId){
        return cartMapper.delByIds(uid,cartId);
    }

    //添加购物车信息
    public Boolean addCart(String uid,String itemId,String itemCount){
        return cartMapper.insertByUid(uid,itemId,itemCount);
    }
}
