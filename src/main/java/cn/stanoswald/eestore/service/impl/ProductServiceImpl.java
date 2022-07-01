package cn.stanoswald.eestore.service.impl;

import cn.stanoswald.eestore.entity.Item;
import cn.stanoswald.eestore.entity.ItemSpecific;
import cn.stanoswald.eestore.entity.Product;
import cn.stanoswald.eestore.entity.Specific;
import cn.stanoswald.eestore.mapper.ItemMapper;
import cn.stanoswald.eestore.mapper.ItemSpecificMapper;
import cn.stanoswald.eestore.mapper.ProductMapper;
import cn.stanoswald.eestore.mapper.SpecificMapper;
import cn.stanoswald.eestore.service.ProductService;
import cn.stanoswald.eestore.util.ImgUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 产品表 服务实现类
 *
 * @author yjw
 * @since 2022-06-16
 */
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ItemMapper itemMapper;

    @Resource
    private ItemSpecificMapper itemSpecificMapper;

    @Resource
    private SpecificMapper specificMapper;

    //获取所有商品列表
    @Override
    public List<Product> getProductList() {
        List<Product> productList = productMapper.selectList(Wrappers.lambdaQuery(Product.class)
                .select(Product::getProductId, Product::getProductName, Product::getProductImg)
        );
        for (Product product : productList) {
            addItemList(product);
        }
        return productList;
    }

    @Override
    public List<Product> getAllProduct(){
        List<Product> productList = productMapper.selectList(Wrappers.emptyWrapper());
        for (Product product : productList){
            List<Item> itemList = itemMapper.selectList(Wrappers.lambdaQuery(Item.class)
                    .eq(Item::getProductId,product.getProductId())
                    .select(Item::getItemId,Item::getItemOption)
            );
            product.setItemList(itemList);
        }
        return productList;
    }

    //获取当前分类的所有商品
    @Override
    public List<Product> getProductListByCatId(Integer catId) {
        List<Product> productList = productMapper.selectList(Wrappers.lambdaQuery(Product.class)
                .select(Product::getProductId, Product::getProductName, Product::getProductImg)
                .eq(Product::getCatId, catId)
        );
        if (productList.size() != 0) {
            for (Product product : productList) {
                addItemList(product);
            }
        }
        return productList;
    }

    //添加商品最低价及其折扣
    private void addItemList(Product product) {
        List<Item> itemList = itemMapper.selectList(Wrappers.lambdaQuery(Item.class)
                .select(Item::getItemPrice, Item::getItemDiscount)
                .eq(Item::getProductId, product.getProductId())
                .orderByAsc(Item::getItemPrice)
        );
        if (itemList.size() != 0) {
            Item item = itemList.get(0);
            product.setMiniItemPrice(item.getItemPrice());
            product.setMiniItemDiscount(item.getItemDiscount());
        } else
            product.setItemList(new ArrayList<>());
    }

    //获取当前商品详情
    @Override
    public Product getProductById(String proId) {
        Product product = productMapper.selectById(proId);
        List<Item> itemList = getItemListByProId(proId);
        product.setItemList(itemList);
        return product;
    }

    //当前商品的item列表
    public List<Item> getItemListByProId(String proId) {
        if (productMapper.selectById(proId) != null) {
            List<Item> itemList = itemMapper.selectList(Wrappers.lambdaQuery(Item.class)
                    .eq(Item::getProductId, proId)
            );
            for (Item item : itemList) {
                addItemSpecific(item);
            }
            return itemList;
        } else return null;
    }

    //添加当前item的specific信息
    private void addItemSpecific(Item item) {
        List<ItemSpecific> itemSpecificList = itemSpecificMapper.selectList(Wrappers.lambdaQuery(ItemSpecific.class)
                .select(ItemSpecific::getSpecificId, ItemSpecific::getContent)
                .eq(ItemSpecific::getItemId, item.getItemId())
        );
        if (itemSpecificList.size() != 0) {
            for (ItemSpecific itemSpecific : itemSpecificList) {
                addSpecific(itemSpecific);
            }
            item.setItemSpecificList(itemSpecificList);
        } else item.setItemSpecificList(new ArrayList<>());
    }

    //添加specific名称
    private void addSpecific(ItemSpecific itemSpecific) {
        List<Specific> specificList = specificMapper.selectList(Wrappers.lambdaQuery(Specific.class)
                .select(Specific::getSpecificName)
                .eq(Specific::getSpecificId, itemSpecific.getSpecificId())
        );
        if (specificList.size() != 0) {
            itemSpecific.setSpecificName(specificList.get(0).getSpecificName());
        }
    }

    @Transactional
    @Override
    public Integer addProduct(Product product, MultipartFile productImg) {
        try {
//            log.info(product.toString());
            if(productMapper.selectCount(Wrappers.lambdaQuery(Product.class).eq(Product::getProductName,product.getProductName()))!=0){
                return null;
            }
            if(productImg!=null) {
                addProductImg(product, productImg);
            }
            productMapper.insert(product);
            List<Item> itemList = product.getItemList();
            if(itemList!=null){
                for (Item item : itemList) {
                    item.setProductId(product.getProductId());
                    itemMapper.insert(item);
                    List<ItemSpecific> itemSpecificList = item.getItemSpecificList();
                    if(itemSpecificList!=null){
                        for (ItemSpecific itemSpecific : itemSpecificList) {
                            itemSpecific.setItemId(item.getItemId());
                            if (specificMapper.selectList(Wrappers.lambdaQuery(Specific.class)
                                    .eq(Specific::getSpecificName, itemSpecific.getSpecificName())
                            ).isEmpty()) {
                                Specific specific = new Specific();
                                specific.setSpecificName(itemSpecific.getSpecificName());
                                specificMapper.insert(specific);
                            }
                            Specific specific =specificMapper.selectOne(Wrappers.lambdaQuery(Specific.class)
                                    .eq(Specific::getSpecificName,itemSpecific.getSpecificName())
                            );
                            itemSpecific.setSpecificId(specific.getSpecificId());
                            itemSpecificMapper.insert(itemSpecific);
                        }
                    }
                }
            }
            if (productMapper.selectById(product.getProductId()) != null) {
                return product.getProductId();
            } else return null;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            throw new RuntimeException();
        }
    }

    public void addProductImg(Product product, MultipartFile file) throws IOException {
        URL url = ImgUtil.saveProductImage(product.getProductName(), file);
        if (StringUtils.isNotEmpty(url.toString())) {
            product.setProductImg(url.toString());
        }
    }

    @Transactional
    @Override
    public Boolean deleteProduct(Integer product_id){
        try {
            List<Item> itemList = itemMapper.selectList(Wrappers.lambdaQuery(Item.class)
                    .eq(Item::getProductId, product_id)
            );
            if (itemList != null) {
                for (Item item : itemList) {
                    List<ItemSpecific> itemSpecificList = itemSpecificMapper.selectList(Wrappers.lambdaQuery(ItemSpecific.class)
                            .eq(ItemSpecific::getItemId, item.getItemId())
                    );
                    if (itemSpecificList != null) {
                        for (ItemSpecific itemSpecific : itemSpecificList) {
                            itemSpecificMapper.deleteById(itemSpecific);
                        }
                    }
                    itemMapper.deleteById(item);
                }
            }
            return productMapper.deleteById(product_id) == 1;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException();
        }
    }

    @Transactional
    @Override
    public Boolean updateProduct(Product product, MultipartFile productImg){
        try {
//            log.info(productImg.toString());
            if(productImg!=null) {
                addProductImg(product, productImg);
            }
            return productMapper.updateById(product) == 1;
        }catch (Exception e){
            return false;
        }
    }
}
