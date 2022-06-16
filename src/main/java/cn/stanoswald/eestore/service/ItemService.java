package cn.stanoswald.eestore.service;

import cn.stanoswald.eestore.entity.Item;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author yjw
 * @since 2022-06-15
 */
public interface ItemService extends IService<Item> {
    List<Item> getItemList();

    Item getItemById(String itemId);
}
