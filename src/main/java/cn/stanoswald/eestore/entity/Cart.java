package cn.stanoswald.eestore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

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
    @TableId(value = "cart_id",type= IdType.AUTO)
    private Integer cartId;

    /**
     * 产品id
     */
    @TableField(exist = false)
    private Integer productId;

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

    /**
     * 商品价格
     */
    @TableField(exist = false)
    private BigDecimal itemPrice;

    /**
     * 商品折扣
     */
    @TableField(exist = false)
    private BigDecimal itemDiscount;

    /**
     * 产品名
     */
    @TableField(exist = false)
    private String productName;
    /**
     * 产品图片
     */
    @TableField(exist = false)
    private String productImg;
}
