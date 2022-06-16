package cn.stanoswald.eestore.service.impl;

import cn.stanoswald.eestore.entity.Item;
import cn.stanoswald.eestore.entity.ProItem;
import cn.stanoswald.eestore.entity.Product;
import cn.stanoswald.eestore.mapper.ItemMapper;
import cn.stanoswald.eestore.mapper.ProductMapper;
import cn.stanoswald.eestore.service.ProductService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 产品表 服务实现类
 * </p>
 *
 * @author yjw
 * @since 2022-06-16
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ItemMapper itemMapper;

    public List<Product> getProductList() {
        List<Product> productList = productMapper.selectList(Wrappers.emptyWrapper());
        for (Product product : productList) {
            addItemList(product);
        }
        return productList;
    }

    private void addItemList(Product product) {
        List<Item> itemList = itemMapper.selectList(Wrappers.lambdaQuery(Item.class)
                .select(Item::getItemPrice, Item::getItemDiscount)
                .eq(Item::getProductId, product.getProductId())
                .orderByAsc(Item::getItemPrice)
        );
        if(itemList.size()!=0){
            Item item = itemList.get(0);
            ProItem proItem = new ProItem(item.getItemId(), item.getProductId(), item.getItemPrice(), item.getItemDiscount());
            List<ProItem> proItemList = new ArrayList<>();
            proItemList.add(proItem);
            product.setItemList(proItemList);
        }else
            product.setItemList(new ArrayList<>());
    }
}
