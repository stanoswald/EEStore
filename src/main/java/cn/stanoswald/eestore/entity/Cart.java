package cn.stanoswald.eestore.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Repository;

/**
 * 购物车表
 *
 * @author yjw
 * @since 2022-06-14
 */
@Data
@Repository
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_cart")
public class Cart extends Model<Cart> {

    /**
     * 购物车id
     */
    @TableId
    private Integer cartId;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 商品id
     */
    private String itemId;

    /**
     * 商品数量
     */
    private Integer itemCount;

}
