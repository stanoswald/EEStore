package cn.stanoswald.eestore.controller;

import cn.stanoswald.eestore.entity.CommonResponse;
import cn.stanoswald.eestore.entity.Item;
import cn.stanoswald.eestore.service.ItemService;
import cn.stanoswald.eestore.service.impl.ItemServiceImpl;
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
            return new CommonResponse.Builder().error().message("查询失败").build();
        }
    }

}
