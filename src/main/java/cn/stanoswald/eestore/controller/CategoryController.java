package cn.stanoswald.eestore.controller;

import cn.stanoswald.eestore.entity.Category;
import cn.stanoswald.eestore.entity.CommonResponse;
import cn.stanoswald.eestore.service.impl.CartServiceImpl;
import cn.stanoswald.eestore.service.impl.CategoryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 产品分类表 前端控制器
 * </p>
 *
 * @author yjw
 * @since 2022-06-15
 */
@Slf4j
@RestController
@RequestMapping("/public/api/category")
public class CategoryController {

    @Resource
    private CategoryServiceImpl categoryService;

    //获取分类列表
    @GetMapping("/get/all")
    public ResponseEntity<Object> getAll(){
        try{
            List<Category> list = categoryService.getAllCategory();
            return new CommonResponse.Builder().ok().message("分类列表").data("Category",list).build();
        }catch (Exception e){
            log.error(e.getMessage());
            return new CommonResponse.Builder().error().build();
        }
    }
}
