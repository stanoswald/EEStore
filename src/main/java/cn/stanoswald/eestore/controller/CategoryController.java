package cn.stanoswald.eestore.controller;

import cn.stanoswald.eestore.entity.Category;
import cn.stanoswald.eestore.entity.CommonResponse;
import cn.stanoswald.eestore.service.impl.CartServiceImpl;
import cn.stanoswald.eestore.service.impl.CategoryServiceImpl;
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
@RestController
@RequestMapping("/public/api/category")
public class CategoryController {

    @Resource
    private CategoryServiceImpl categoryService;

    //获取分类列表
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll(){
        try{
            List<Category> list = categoryService.getAllCategory();
            return new CommonResponse.Builder().ok().message("分类列表").data("Category",list).build();
        }catch (Exception e){
            return new CommonResponse.Builder().error().build();
        }
    }

    //添加分类
    @PostMapping("/addCategory")
    public ResponseEntity<Object> addCategory(@RequestParam("cat_name") String cat_name){
        if(categoryService.addCategory(cat_name)){
            return new CommonResponse.Builder().ok().message("添加成功").data("isTrue", true).build();
        }else {
            return new CommonResponse.Builder().error().message("添加失败").data("isTrue", false).build();
        }
    }

    //删除分类
    @PostMapping("/delCategory")
    public ResponseEntity<Object> delCategory(@RequestParam("cat_name") String cat_name){
        if(categoryService.delCategory(cat_name)){
            return new CommonResponse.Builder().ok().message("删除成功").data("isTrue", true).build();
        }else {
            return new CommonResponse.Builder().error().message("删除失败").data("isTrue", false).build();
        }
    }
}
