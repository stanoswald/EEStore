package cn.stanoswald.eestore.service;

import cn.stanoswald.eestore.entity.Specific;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品详情字段表 服务类
 *
 * @author yjw
 * @since 2022-06-16
 */
public interface SpecificService extends IService<Specific> {

    @Transactional
    Integer addSpecific(String specificName);

    @Transactional
    Boolean deleteSpecific(Integer specificId);
}
