package cn.stanoswald.eestore.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 购物车表
 * </p>
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

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    public String toString() {
        return "Cart{" +
            "cartId=" + cartId +
            ", uid=" + uid +
            ", itemId=" + itemId +
            ", itemCount=" + itemCount +
        "}";
    }
}
