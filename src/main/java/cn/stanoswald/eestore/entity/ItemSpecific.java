package cn.stanoswald.eestore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 商品详情字段关系表
 * </p>
 *
 * @author yjw
 * @since 2022-06-16
 */
@TableName("tbl_item_specific")
public class ItemSpecific {

    /**
     * 商品规格id
     */
    @TableId(value = "item_specific_id", type = IdType.AUTO)
    private Integer itemSpecificId;

    /**
     * 商品id
     */
    private String itemId;

    /**
     * 详情id
     */
    private Integer specificId;

    /**
     * 规格名
     */
    @TableField(exist = false)
    private String specificName;

    /**
     * 详情内容
     */
    private String content;

    public Integer getItemSpecificId() {
        return itemSpecificId;
    }

    public void setItemSpecificId(Integer itemSpecificId) {
        this.itemSpecificId = itemSpecificId;
    }
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public Integer getSpecificId() {
        return specificId;
    }

    public void setSpecificId(Integer specificId) {
        this.specificId = specificId;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ItemSpecific{" +
            "itemSpecificId=" + itemSpecificId +
            ", itemId=" + itemId +
            ", specificId=" + specificId +
            ", content=" + content +
        "}";
    }

    public String getSpecificName() {
        return specificName;
    }

    public void setSpecificName(String specificName) {
        this.specificName = specificName;
    }
}
