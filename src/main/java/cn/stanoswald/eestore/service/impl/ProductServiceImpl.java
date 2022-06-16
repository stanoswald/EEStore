package cn.stanoswald.eestore.service.impl;

import cn.stanoswald.eestore.entity.*;
import cn.stanoswald.eestore.mapper.ItemMapper;
import cn.stanoswald.eestore.mapper.ItemSpecificMapper;
import cn.stanoswald.eestore.mapper.ProductMapper;
import cn.stanoswald.eestore.mapper.SpecificMapper;
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

    @Resource
    private ItemSpecificMapper itemSpecificMapper;

    @Resource
    private SpecificMapper specificMapper;

    //获取所有商品列表
    public List<Product> getProductList() {
        List<Product> productList = productMapper.selectList(Wrappers.emptyWrapper());
        for (Product product : productList) {
            addItemList(product);
        }
        return productList;
    }

    //获取当前分类的所有商品
    public List<Product> getProductListByCatId(Integer catId){
        List<Product> productList = productMapper.selectList(Wrappers.lambdaQuery(Product.class)
                .eq(Product::getCatId,catId)
        );
        if(productList.size()!=0){
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
        if(itemList.size()!=0){
            Item item = itemList.get(0);
            List<Item> miniItemList = new ArrayList<>();
            miniItemList.add(item);
            product.setItemList(miniItemList);
        }else
            product.setItemList(new ArrayList<>());
    }
    //当前商品的item列表
    public List<Item> getItemListByProId(String proId){
        if(productMapper.selectById(proId)!=null){
            List<Item> itemList = itemMapper.selectList(Wrappers.lambdaQuery(Item.class)
                    .eq(Item::getProductId,proId)
            );
            for (Item item : itemList){
                addItemSpecific(item);
            }
            return itemList;
        }else return null;
    }

    //添加当前item的specific信息
    private void addItemSpecific(Item item){
        List<ItemSpecific> itemSpecificList = itemSpecificMapper.selectList(Wrappers.lambdaQuery(ItemSpecific.class)
                .select(ItemSpecific::getSpecificId,ItemSpecific::getContent)
                .eq(ItemSpecific::getItemId,item.getItemId())
        );
        if(itemSpecificList.size()!=0) {
            for (ItemSpecific itemSpecific : itemSpecificList){
                addSpecific(itemSpecific);
            }
            item.setItemSpecificList(itemSpecificList);
        }else item.setItemSpecificList(new ArrayList<>());
    }

    //添加specific名称
    private void addSpecific(ItemSpecific itemSpecific){
        List<Specific> specificList = specificMapper.selectList(Wrappers.lambdaQuery(Specific.class)
                .select(Specific::getSpecificName)
                .eq(Specific::getSpecificId,itemSpecific.getSpecificId())
        );
        if(specificList.size()!=0){
            itemSpecific.setSpecificName(specificList.get(0).getSpecificName());
        }
    }
}
