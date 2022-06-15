package cn.stanoswald.eestore.mapper;

import cn.stanoswald.eestore.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author StanOswald
 * @since 2022-06-15
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
