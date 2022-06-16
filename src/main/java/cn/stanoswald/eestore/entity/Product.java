package cn.stanoswald.eestore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 * 产品表
 * </p>
 *
 * @author yjw
 * @since 2022-06-16
 */
@Data
@NoArgsConstructor
@TableName("tbl_product")
public class Product {

    /**
     * 产品id
     */
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }
    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    @Override
    public String toString() {
        return "Product{" +
            "productId=" + productId +
            ", catId=" + catId +
            ", productName=" + productName +
            ", productDetail=" + productDetail +
            ", productImg=" + productImg +
        "}";
    }
}
