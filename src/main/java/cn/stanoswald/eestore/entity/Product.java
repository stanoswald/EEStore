package cn.stanoswald.eestore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 产品表
 *
 * @author yjw
 * @since 2022-06-16
 */
@Data
@TableName("tbl_product")
public class Product {

    /**
     * 产品id
     */
    @TableId(value = "product_id")
    private Integer productId;

    /**
     * 分类id
     */
    private Integer catId;

    /**
     * 产品名
     */
    private String productName;

    /**
     * 产品详情
     */
    private String productDetail;

    /**
     * 产品图片
     */
    private String productImg;

    @TableField(exist = false)
    private List<Item> itemList;
}
