package cn.stanoswald.eestore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 产品分类表
 *
 * @author yjw
 * @since 2022-06-15
 */
@TableName("tbl_category")
public class Category {

    /**
     * 分类id
     */
    @TableId(value = "cat_id", type = IdType.AUTO)
    private Integer catId;

    /**
     * 分类名
     */
    private String catName;

}
