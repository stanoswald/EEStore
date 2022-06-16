package cn.stanoswald.eestore.service.impl;

import cn.stanoswald.eestore.entity.Category;
import cn.stanoswald.eestore.mapper.CategoryMapper;
import cn.stanoswald.eestore.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 产品分类表 服务实现类
 *
 * @author yjw
 * @since 2022-06-15
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    //添加分类
    public Boolean addCategory(String catName){
        Category category =new Category();
        category.setCatName(catName);
        if(categoryMapper.selectByMap(Map.of("cat_name",catName)).isEmpty()) {
            return categoryMapper.insert(category) == 1;
        }else {
            return false;
        }
    }

    //删除分类
    public Boolean delCategory(String catName){
        return categoryMapper.deleteByMap(Map.of("cat_name",catName)) == 1;
    }

    //获取所有分类
    public List<Category> getAllCategory(){
        return categoryMapper.selectList(null);
    }


}
