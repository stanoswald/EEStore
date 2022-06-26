package cn.stanoswald.eestore.service;

import cn.stanoswald.eestore.entity.Item;
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
}
