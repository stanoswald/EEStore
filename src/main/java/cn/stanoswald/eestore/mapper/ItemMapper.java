package cn.stanoswald.eestore.mapper;

import cn.stanoswald.eestore.entity.Item;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author yjw
 * @since 2022-06-15
 */
@Mapper
public interface ItemMapper extends BaseMapper<Item> {

}
