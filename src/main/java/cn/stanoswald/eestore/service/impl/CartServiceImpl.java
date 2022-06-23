package cn.stanoswald.eestore.service.impl;

import cn.stanoswald.eestore.entity.Cart;
import cn.stanoswald.eestore.entity.Item;
import cn.stanoswald.eestore.entity.Product;
import cn.stanoswald.eestore.mapper.CartMapper;
import cn.stanoswald.eestore.mapper.ItemMapper;
import cn.stanoswald.eestore.mapper.ProductMapper;
import cn.stanoswald.eestore.service.CartService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车表 服务实现类
 *
 * @author yjw
 * @author StanOswald
 * @since 2022-06-14
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Resource
    private CartMapper cartMapper;

    @Resource
    private ItemMapper itemMapper;

    @Resource
    private ProductMapper productMapper;


    //查询购物车信息
    public List<Cart> findByUid(String uid) {
        List<Cart> cartList = cartMapper.getByUid(uid);
        for (Cart cart : cartList){
            Item item=itemMapper.selectById(cart.getItemId());
            cart.setItemPrice(item.getItemPrice());
            cart.setItemDiscount(item.getItemDiscount());
            cart.setProductId(item.getProductId());
            Product product = productMapper.selectById(item.getProductId());
            cart.setProductName(product.getProductName());
            cart.setProductImg(product.getProductImg());
        }
        return cartList;
    }

    //删除购物车信息
    public Boolean deleteByIdS(String uid, String cartId) {
        return cartMapper.delByIds(uid, cartId);
    }

    //添加购物车信息
    public Boolean setCart(String uid, String itemId, Integer itemCount) {
        Cart cart = new Cart();
        cart.setUid(uid);
        cart.setItemId(itemId);
        cart.setItemCount(itemCount);

        Integer cartId = cartMapper.selectCartIdByUidAndItemId(uid, itemId);
        if (cartId == null)
            return cartMapper.insert(cart) == 1;
        return cartMapper.addItemCountByCartId(cartId) == 1;
    }

    @Override
    public Boolean addCart(String uid, String itemId) {
        return setCart(uid, itemId, 1);
    }


}
