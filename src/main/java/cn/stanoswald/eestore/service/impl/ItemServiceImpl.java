package cn.stanoswald.eestore.service.impl;

import cn.stanoswald.eestore.entity.Item;
import cn.stanoswald.eestore.mapper.ItemMapper;
import cn.stanoswald.eestore.service.ItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author yjw
 * @since 2022-06-15
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {
    @Resource
    private ItemMapper itemMapper;

    //商品列表
    public List<Item> getItemList(){
        return itemMapper.selectList(null);
    }

    //单个商品信息
    public Item getItemById(String itemId){
        return itemMapper.selectById(itemId);
    }

}

