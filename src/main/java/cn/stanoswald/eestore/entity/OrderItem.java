package cn.stanoswald.eestore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单商品关系表
 *
 * @author StanOswald
 * @since 2022-06-15
 */
@Data
@TableName("tbl_order_item")
public class OrderItem {

    /**
     * 订单商品id
     */
    @TableId(value = "order_item_id", type = IdType.AUTO)
    @JsonIgnore
    private Integer orderItemId;

    /**
     * 订单id
     */
    @JsonIgnore
    private String orderId;

    /**
     * 商品id
     */
    private String itemId;

    /**
     * 商品数量
     */
    private Integer itemCount;

    /**
     * 商品购买单价
     */
    private BigDecimal itemPrice;

}
