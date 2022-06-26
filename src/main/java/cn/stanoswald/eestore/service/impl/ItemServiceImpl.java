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
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    public String addItem(Item item){
        try{
            Product product = productMapper.selectById(item.getProductId());
            if (product != null) {
                List<ItemSpecific> itemSpecificList = item.getItemSpecificList();
                if(itemSpecificList != null){
                    for (ItemSpecific itemSpecific : itemSpecificList){
                        itemSpecific.setItemId(item.getItemId());
                        itemSpecificMapper.insert(itemSpecific);
                        if (specificMapper.selectList(Wrappers.lambdaQuery(Specific.class)
                                .eq(Specific::getSpecificName, itemSpecific.getSpecificName())
                        ).isEmpty()) {
                            Specific specific = new Specific();
                            specific.setSpecificName(itemSpecific.getSpecificName());
                            specificMapper.insert(specific);
                        }
                    }
                }
                itemMapper.insert(item);
                return item.getItemId();
            }else return null;
        }catch (Exception e){
            return null;
        }
    }
}

