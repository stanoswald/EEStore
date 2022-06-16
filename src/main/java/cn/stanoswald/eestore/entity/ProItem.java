package cn.stanoswald.eestore.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProItem {
    @JsonIgnore
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

    public ProItem(String itemId, Integer productId, BigDecimal itemPrice, BigDecimal itemDiscount) {
        this.itemId = itemId;
        this.productId = productId;
        this.itemPrice = itemPrice;
        this.itemDiscount = itemDiscount;
    }
}
