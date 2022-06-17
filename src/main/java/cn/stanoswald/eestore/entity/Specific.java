package cn.stanoswald.eestore.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 商品详情字段表
 *
 * @author yjw
 * @since 2022-06-16
 */
@Data
@TableName("tbl_specific")
public class Specific {

    /**
     * 规格id
     */
    private Integer specificId;

    /**
     * 规格名
     */
    private String specificName;

}
