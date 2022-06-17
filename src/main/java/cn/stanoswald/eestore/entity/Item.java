package cn.stanoswald.eestore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品表
 *
 * @author yjw
 * @since 2022-06-15
 */
@Data
@TableName("tbl_item")
public class Item {

    /**
     * 商品id
     */
    @TableId(value = "item_id")
    private String itemId;

    /**
     * 产品id
     */
    @JsonIgnore
    private Integer productId;

    /**
     * 商品价格
     */
    private BigDecimal itemPrice;

    /**
     * 商品折扣
     */
    private BigDecimal itemDiscount;

    /**
     * 商品选项
     */
    private String itemOption;

    /**
     * 库存数
     */
    private Integer itemStock;

    /**
     * 可售
     */
    private Boolean forSale;

    @TableField(exist = false)
    private List<ItemSpecific> itemSpecificList;

}
