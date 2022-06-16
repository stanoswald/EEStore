package cn.stanoswald.eestore.controller;

import cn.stanoswald.eestore.entity.CommonResponse;
import cn.stanoswald.eestore.entity.Item;
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
    private ItemServiceImpl itemService;

    @GetMapping("/getItemList")
    public ResponseEntity<Object> getItemList(){
        try{
            List<Item> list = itemService.getItemList();
            return new CommonResponse.Builder().ok().message("item列表")
                    .data("ItemList",list).build();
        }catch (Exception e){
            return new CommonResponse.Builder().error().build();
        }
    }

    @GetMapping("/getItemById")
    public ResponseEntity<Object> getItemById(@RequestParam("item_id") String item_id){
        try{
            Item item = itemService.getItemById(item_id);
            return new CommonResponse.Builder().ok().message("商品信息")
                    .data("Item",item).build();
        }catch (Exception e){
            return new CommonResponse.Builder().error().build();
        }
    }

}
