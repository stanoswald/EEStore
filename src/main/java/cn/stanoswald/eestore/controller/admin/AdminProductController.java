package cn.stanoswald.eestore.controller.admin;

import cn.stanoswald.eestore.entity.CommonResponse;
import cn.stanoswald.eestore.entity.Product;
import cn.stanoswald.eestore.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
@RestController
@RequestMapping("/admin/api/product")
public class AdminProductController {

    @Resource
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Object> addProduct(@RequestParam("product") String productJsonStr, @RequestParam("img") MultipartFile img) {
        try {
            ObjectMapper mapper = new ObjectMapper().setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());
            Product product = mapper.readerFor(Product.class).readValue(productJsonStr);
            Integer productId = productService.addProduct(product, img);
            return new CommonResponse.Builder().ok().message("商品添加成功").data("product_id", productId).build();
        } catch (JsonProcessingException e) {
            return new CommonResponse.Builder().error().message("商品添加失败").build();
        }
    }
}
