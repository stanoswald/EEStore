package cn.stanoswald.eestore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 商品详情字段关系表
 *
 * @author yjw
 * @since 2022-06-16
 */
@Data
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

}
