package cn.stanoswald.eestore.service;

import cn.stanoswald.eestore.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 产品分类表 服务类
 *
 * @author yjw
 * @since 2022-06-15
 */
public interface CategoryService extends IService<Category> {
    Boolean addCategory(String catName);

    Boolean delCategory(String catName);

    List<Category> getAllCategory();
}
