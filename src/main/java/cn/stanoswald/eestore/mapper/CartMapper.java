package cn.stanoswald.eestore.mapper;

import cn.stanoswald.eestore.entity.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

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
public interface CartMapper extends BaseMapper<Cart> {
    @Select("select * from tbl_cart where uid=#{id}")
    List<Cart> getByUid(String id);

    @Delete("delete from tbl_cart where uid=#{uid} AND cart_id=#{cartId}")
    Boolean delByIds(String uid,String cartId);

    @Insert("insert into tbl_cart(uid,item_id,item_count) VAlUES (#{uid},#{itemId},#{itemCount})")
    Boolean insertByUid(String uid,String itemId,String itemCount);
}
