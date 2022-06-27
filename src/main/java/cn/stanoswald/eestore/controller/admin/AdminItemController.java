package cn.stanoswald.eestore.controller.admin;

import cn.stanoswald.eestore.entity.CommonResponse;
import cn.stanoswald.eestore.entity.Item;
import cn.stanoswald.eestore.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author yjw
 * @since 2022-06-15
 */
@RestController
@RequestMapping("/admin/api/item")
public class AdminItemController {

    @Resource
    ItemService itemService;

    @PostMapping("/add")
    public ResponseEntity<Object> addItem(@RequestBody Item item){
        try {
            String itemId=itemService.addItem(item);
            if(itemId!=null){
                return new CommonResponse.Builder().ok().message("商品添加成功").data("Item_id", itemId).build();
            }return new CommonResponse.Builder().error().message("商品添加失败").build();
        }catch (Exception e){
            return new CommonResponse.Builder().error().message("商品添加失败").build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> delItem(@RequestParam("item_id") String itemId){
        try{
            Boolean isDelete = itemService.deleteItem(itemId);
            if(!isDelete){
                return new CommonResponse.Builder().error().message("商品删除失败").build();
            }
            return new CommonResponse.Builder().ok().message("商品删除成功").build();
        }catch (Exception e){
            return new CommonResponse.Builder().error().message("商品删除失败").build();
        }
    }

    @PostMapping("/isSale")
    public ResponseEntity<Object> isSale(@RequestParam("item_id") String itemId,@RequestParam("for_sale") String forSale){
        try{
            Boolean isUpdate = itemService.updateSale(itemId, Boolean.valueOf(forSale));
            if(!isUpdate){
                return new CommonResponse.Builder().error().message("商品寄售状态更新失败").build();
            }
            return new CommonResponse.Builder().ok().message("商品寄售状态更新成功").build();
        }catch (Exception e){
            return new CommonResponse.Builder().error().message("商品寄售状态更新失败").build();
        }
    }
}