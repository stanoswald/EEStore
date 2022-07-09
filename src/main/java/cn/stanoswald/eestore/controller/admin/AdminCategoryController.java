package cn.stanoswald.eestore.controller.admin;

import cn.stanoswald.eestore.entity.CommonResponse;
import cn.stanoswald.eestore.service.impl.CategoryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/api/category")
public class AdminCategoryController {

    @Resource
    private CategoryServiceImpl categoryService;

    //添加分类
    @PostMapping("/add")
    public ResponseEntity<Object> addCategory(@RequestParam("cat_name") String cat_name){
        if(categoryService.addCategory(cat_name)){
            return new CommonResponse.Builder().ok().message("添加成功").data("isTrue", true).build();
        }else {
            return new CommonResponse.Builder().error().message("添加失败").data("isTrue", false).build();
        }
    }

    //删除分类
    @PostMapping("/delete")
    public ResponseEntity<Object> delCategory(@RequestParam("cat_name") String cat_name){
        if(categoryService.delCategory(cat_name)){
            return new CommonResponse.Builder().ok().message("删除成功").data("isTrue", true).build();
        }else {
            return new CommonResponse.Builder().error().message("删除失败").data("isTrue", false).build();
        }
    }
}
