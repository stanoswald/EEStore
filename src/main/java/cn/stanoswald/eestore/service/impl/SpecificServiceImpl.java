package cn.stanoswald.eestore.service.impl;

import cn.stanoswald.eestore.entity.ItemSpecific;
import cn.stanoswald.eestore.entity.Specific;
import cn.stanoswald.eestore.mapper.ItemSpecificMapper;
import cn.stanoswald.eestore.mapper.SpecificMapper;
import cn.stanoswald.eestore.service.SpecificService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SpecificServiceImpl extends ServiceImpl<SpecificMapper, Specific>implements SpecificService {

    @Resource
    SpecificMapper specificMapper;

    @Resource
    ItemSpecificMapper itemSpecificMapper;

    @Transactional
    @Override
    public Integer addSpecific(String specificName){
        if (specificMapper.selectList(Wrappers.lambdaQuery(Specific.class)
                .eq(Specific::getSpecificName,specificName)
        ).isEmpty()) {
            Specific specific =new Specific();
            specific.setSpecificName(specificName);
            specificMapper.insert(specific);
            if(specificMapper.selectList(Wrappers.lambdaQuery(Specific.class)
                    .eq(Specific::getSpecificName,specificName)
            ).isEmpty()){
                return null;
            }
            return specific.getSpecificId();
        }else return null;
    }

    @Transactional
    @Override
    public Boolean deleteSpecific(Integer specificId){
        try{
            List<ItemSpecific> itemSpecificList = itemSpecificMapper.selectList(Wrappers.lambdaQuery(ItemSpecific.class)
                    .eq(ItemSpecific::getSpecificId,specificId)
            );
            if(itemSpecificList!=null){
                for (ItemSpecific itemSpecific : itemSpecificList){
                    itemSpecificMapper.deleteById(itemSpecific);
                }
            }
            return specificMapper.deleteById(specificId) == 1;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Specific> getAll(){
        return specificMapper.selectList(Wrappers.emptyWrapper());
    }
}
