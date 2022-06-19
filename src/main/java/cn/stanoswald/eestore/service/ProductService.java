package cn.stanoswald.eestore.service;

import cn.stanoswald.eestore.entity.Item;
import cn.stanoswald.eestore.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 产品表 服务类
 *
 * @author yjw
 * @since 2022-06-16
 */
public interface ProductService extends IService<Product> {
    List<Product> getProductList();

    List<Item> getItemListByProId(String proId);

    List<Product> getProductListByCatId(Integer catId);

    /**
     * 添加产品
     * @param product 产品实体
     * @param productImg 产品图片
     * @return 产品id
     */
    Integer addProduct(Product product, MultipartFile productImg);
}
