package cn.stanoswald.eestore.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>
 * 商品表
 * </p>
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

    /**
     * 商品选项
     */
    @JsonIgnore
    private String itemOption;

    /**
     * 库存数
     */
    @JsonIgnore
    private Integer itemStock;

    /**
     * 可售
     */
    @JsonIgnore
    private Boolean forSale;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }
    public BigDecimal getItemDiscount() {
        return itemDiscount;
    }

    public void setItemDiscount(BigDecimal itemDiscount) {
        this.itemDiscount = itemDiscount;
    }
    public String getItemOption() {
        return itemOption;
    }

    public void setItemOption(String itemOption) {
        this.itemOption = itemOption;
    }
    public Integer getItemStock() {
        return itemStock;
    }

    public void setItemStock(Integer itemStock) {
        this.itemStock = itemStock;
    }
    public Boolean getForSale() {
        return forSale;
    }

    public void setForSale(Boolean forSale) {
        this.forSale = forSale;
    }

//    @Override
//    public String toString() {
//        return "Item{" +
//            "itemId=" + itemId +
//            ", productId=" + productId +
//            ", itemPrice=" + itemPrice +
//            ", itemDiscount=" + itemDiscount +
//            ", itemOption=" + itemOption +
//            ", itemStock=" + itemStock +
//            ", forSale=" + forSale +
//        "}";
//    }
}
