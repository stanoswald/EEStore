package cn.stanoswald.eestore.mapper;

import cn.stanoswald.eestore.entity.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 购物车表 Mapper 接口
 * </p>
 *
 * @author yjw
 * @since 2022-06-14
 */
@Mapper
@Repository
public interface CartMapper extends BaseMapper<Cart> {
    @Select("select * from tbl_cart where uid=#{id}")
    List<Cart> getByUid(String id);

    @Delete("delete from tbl_cart where uid=#{uid} AND cart_id=#{cartId}")
    Boolean delByIds(String uid, String cartId);

    @Select("SELECT cart_id FROM tbl_cart WHERE uid=#{uid} AND item_id=#{itemId}")
    Integer selectCartIdByUidAndItemId(String uid, String itemId);

    @Update("UPDATE tbl_cart SET item_count=item_count+1 WHERE cart_id=#{cartId}")
    Integer addItemCountByCartId(Integer cartId);
}
