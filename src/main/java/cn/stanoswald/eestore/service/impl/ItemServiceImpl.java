package cn.stanoswald.eestore.service.impl;

import cn.stanoswald.eestore.entity.Item;
import cn.stanoswald.eestore.entity.ItemSpecific;
import cn.stanoswald.eestore.entity.Product;
import cn.stanoswald.eestore.entity.Specific;
import cn.stanoswald.eestore.mapper.ItemMapper;
import cn.stanoswald.eestore.mapper.ItemSpecificMapper;
import cn.stanoswald.eestore.mapper.ProductMapper;
import cn.stanoswald.eestore.mapper.SpecificMapper;
import cn.stanoswald.eestore.service.ItemService;
import cn.stanoswald.eestore.service.SpecificService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品表 服务实现类
 *
 * @author yjw
 * @since 2022-06-15
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {
    @Resource
    ProductMapper productMapper;

    @Resource
    ItemMapper itemMapper;

    @Resource
    ItemSpecificMapper itemSpecificMapper;

    @Resource
    SpecificMapper specificMapper;

    @Resource
    SpecificService specificService;

    @Transactional
    @Override
    public String addItem(Item item){
        try{
            Product product = productMapper.selectById(item.getProductId());
            if (product != null) {
                itemMapper.insert(item);
                List<ItemSpecific> itemSpecificList = item.getItemSpecificList();
                if(itemSpecificList != null){
                    for (ItemSpecific itemSpecific : itemSpecificList){
                        itemSpecific.setItemId(item.getItemId());
                        Integer specificId=specificService.addSpecific(itemSpecific.getSpecificName());
                        if(specificId==null){
                            specificId=specificMapper.selectOne(Wrappers.lambdaQuery(Specific.class)
                                    .eq(Specific::getSpecificName,itemSpecific.getSpecificName())
                            ).getSpecificId();
                        }
                        itemSpecific.setSpecificId(specificId);
                        itemSpecificMapper.insert(itemSpecific);
                    }
                }
                if(itemMapper.selectById(item.getItemId())!=null){
                    return item.getItemId();
                }
            }
            return null;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException();
        }
    }

    @Transactional
    @Override
    public Boolean deleteItem(String itemId){
        try {
            List<ItemSpecific> itemSpecificList = itemSpecificMapper.selectList(Wrappers.lambdaQuery(ItemSpecific.class)
                    .eq(ItemSpecific::getItemId,itemId)
            );
            if(itemSpecificList!=null){
                for (ItemSpecific itemSpecific : itemSpecificList){
                    itemSpecificMapper.deleteById(itemSpecific);
                }
            }
            return itemMapper.deleteById(itemId) == 1;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException();
        }
    }

    @Transactional
    @Override
    public Boolean updateSale(String itemId, Boolean saleStatus){
        Item item =new Item();
        item.setItemId(itemId);
        Boolean forSale = itemMapper.selectById(itemId).getForSale();
        if(!saleStatus.equals(forSale)){
            item.setForSale(saleStatus);
        }else return false;
        return itemMapper.updateById(item) == 1;
    }
}

