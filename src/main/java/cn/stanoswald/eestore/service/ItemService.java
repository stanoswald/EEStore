package cn.stanoswald.eestore.service;

import cn.stanoswald.eestore.entity.Item;
import cn.stanoswald.eestore.entity.ItemSpecific;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品表 服务类
 *
 * @author yjw
 * @since 2022-06-15
 */
public interface ItemService extends IService<Item> {
    String addItem(Item item);

    @Transactional
    Boolean deleteItem(String itemId);

    Boolean updateSale(String itemId, Boolean saleStatus);

    List<Item> getAllItem();

    Integer addItemSpecific(ItemSpecific itemSpecific);

    @Transactional
    Boolean delItemSpecific(Integer itemSpecificId);

    @Transactional
    Boolean updateItemSpecific(ItemSpecific itemSpecific);

    List<ItemSpecific> getItemSpecific();
}
