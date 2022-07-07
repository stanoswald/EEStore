package cn.stanoswald.eestore.controller.admin;

import cn.stanoswald.eestore.entity.CommonResponse;
import cn.stanoswald.eestore.entity.Product;
import cn.stanoswald.eestore.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 * 产品表 前端控制器
 * </p>
 *
 * @author yjw
 * @since 2022-06-16
 */
@Slf4j
@RestController
@RequestMapping("/admin/api/product")
public class AdminProductController {

    @Resource
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Object> addProduct(@RequestPart("product") Product product, @RequestPart("img") MultipartFile img) {
        try {
            Integer productId = productService.addProduct(product, img);
            if(productId == null){
                return new CommonResponse.Builder().error().message("产品添加失败").build();
            }
            return new CommonResponse.Builder().ok().message("产品添加成功").data("product_id", productId).build();
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return new CommonResponse.Builder().error().message("产品添加失败："+e.getMessage()).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> delProduct(@RequestParam("product_id") String product_id) {
        try {
            Boolean isDelete = productService.deleteProduct(Integer.valueOf(product_id));
            if(!isDelete){
                return new CommonResponse.Builder().error().message("产品删除失败"+ isDelete).build();
            }
            return new CommonResponse.Builder().ok().message("产品删除成功").build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new CommonResponse.Builder().error().message("产品删除失败").build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateProduct(@RequestPart("product") Product product, @RequestPart("img") MultipartFile img){
        try {
            if(!productService.updateProduct(product,img)){
                return new CommonResponse.Builder().error().message("产品更新失败").build();
            }
            return new CommonResponse.Builder().ok().message("产品更新成功").build();
        }catch (Exception e){
            log.error(e.getMessage());
            return new CommonResponse.Builder().error().message("产品更新失败："+e.getMessage()).build();
        }
    }
}
