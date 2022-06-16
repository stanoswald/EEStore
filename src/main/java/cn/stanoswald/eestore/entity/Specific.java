package cn.stanoswald.eestore.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 商品详情字段表
 * </p>
 *
 * @author yjw
 * @since 2022-06-16
 */
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

    public Integer getSpecificId() {
        return specificId;
    }

    public void setSpecificId(Integer specificId) {
        this.specificId = specificId;
    }
    public String getSpecificName() {
        return specificName;
    }

    public void setSpecificName(String specificName) {
        this.specificName = specificName;
    }

    @Override
    public String toString() {
        return "Specific{" +
            "specificId=" + specificId +
            ", specificName=" + specificName +
        "}";
    }
}
