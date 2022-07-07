package cn.stanoswald.eestore.controller;

import cn.stanoswald.eestore.entity.CommonResponse;
import cn.stanoswald.eestore.entity.Item;
import cn.stanoswald.eestore.entity.ItemSpecific;
import cn.stanoswald.eestore.service.ItemService;
import cn.stanoswald.eestore.service.impl.ItemServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author yjw
 * @since 2022-06-15
 */
@Slf4j
@RestController
@RequestMapping("/public/api/item")
public class ItemController {

    @Resource
    ItemService itemService;

    @GetMapping("/get/all")
    public ResponseEntity<Object> getAll(){
        try{
            List<Item> itemList = itemService.getAllItem();
            return new CommonResponse.Builder().ok().message("商品列表").data("ItemList",itemList).build();
        }catch (Exception e){
            log.error(e.getMessage());
            return new CommonResponse.Builder().error().message("查询失败").build();
        }
    }

    @GetMapping("/get/itemSpecific")
    public ResponseEntity<Object> getItemSpecific(){
        try{
            List<ItemSpecific> itemSpecificList = itemService.getItemSpecific();
            if(itemSpecificList!=null) {
                return new CommonResponse.Builder().ok().message("商品详情字段表").data("itemSpecificList",itemSpecificList).build();
            }
            return new CommonResponse.Builder().error().message("查询失败").build();
        }catch (Exception e){
            log.error(e.getMessage());
            return new CommonResponse.Builder().error().message("查询失败").build();
        }
    }

}
