package cn.stanoswald.eestore.mapper;

import cn.stanoswald.eestore.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 产品分类表 Mapper 接口
 * </p>
 *
 * @author yjw
 * @since 2022-06-15
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
