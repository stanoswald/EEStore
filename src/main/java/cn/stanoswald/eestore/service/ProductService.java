package cn.stanoswald.eestore.service;

import cn.stanoswald.eestore.entity.Item;
import cn.stanoswald.eestore.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品表 服务类
 * </p>
 *
 * @author yjw
 * @since 2022-06-16
 */
public interface ProductService extends IService<Product> {
    List<Product> getProductList();

    List<Item> getItemListByProId(String proId);

    List<Product> getProductListByCatId(Integer catId);
}
