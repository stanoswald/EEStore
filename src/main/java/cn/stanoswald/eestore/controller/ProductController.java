package cn.stanoswald.eestore.controller;

import cn.stanoswald.eestore.entity.CommonResponse;
import cn.stanoswald.eestore.entity.Item;
import cn.stanoswald.eestore.entity.Product;
import cn.stanoswald.eestore.service.ProductService;
import cn.stanoswald.eestore.service.impl.ProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品表 前端控制器
 * </p>
 *
 * @author yjw
 * @since 2022-06-16
 */
@RestController
@RequestMapping("/public/api/product")
public class ProductController {
    @Resource
    private ProductServiceImpl productService;

    @GetMapping("/getProductList")
    public ResponseEntity<Object> getProductList(){
        try{
            List<Product> list = productService.getProductList();
            return new CommonResponse.Builder().ok().message("商品列表")
                    .data("productList",list).build();
        }catch (Exception e){
            return new CommonResponse.Builder().message("查询失败").error().build();
        }
    }

    @PostMapping("/getProductByCatId")
    public ResponseEntity<Object> getProductByCatId(@RequestParam("cat_id") String cat_id){
        try {
            List<Product> list = productService.getProductListByCatId(Integer.valueOf(cat_id));
            return new CommonResponse.Builder().ok().message("当前分类商品")
                    .data("productCategory",list).build();
        }catch (Exception e){
            return new CommonResponse.Builder().message("查询失败").error().build();
        }
    }

    @PostMapping("/getItemListByProId")
    public ResponseEntity<Object> getItemListByProId(@RequestParam("pro_id") String pro_id){
        try{
            Product product = productService.getProductById(pro_id);
            return new CommonResponse.Builder().ok().message("商品子类型")
                    .data("product",product).build();
        }catch (Exception e){
            return new CommonResponse.Builder().message("查询失败").error().build();
        }
    }
}
