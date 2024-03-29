package cn.stanoswald.eestore.controller.admin;

import cn.stanoswald.eestore.entity.CommonResponse;
import cn.stanoswald.eestore.entity.Specific;
import cn.stanoswald.eestore.service.SpecificService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品详情字段表 前端控制器
 *
 * @author yjw
 * @since 2022-06-16
 */
@Slf4j
@RestController
@RequestMapping("/admin/api/specific")
public class SpecificController {

    @Resource
    SpecificService specificService;

    @PostMapping("/add")
    public ResponseEntity<Object> addSpecific(@RequestParam("specific_name") String specificName){
        try {
            Integer specificId = specificService.addSpecific(specificName);
            if(specificId==null){
                return new CommonResponse.Builder().error().message("字段添加失败").build();
            }
            return new CommonResponse.Builder().ok().message("字段添加成功").data("specificId",specificId).build();
        }catch (Exception e){
            log.error(e.getMessage());
            return new CommonResponse.Builder().error().message("字段添加失败："+e.getMessage()).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> delSpecific(@RequestParam("specific_id") String specificId){
        try {
            Boolean isDelete = specificService.deleteSpecific(Integer.valueOf(specificId));
            if(!isDelete){
                return new CommonResponse.Builder().error().message("字段删除失败").build();
            }
            return new CommonResponse.Builder().ok().message("字段删除成功").build();
        }catch (Exception e){
            log.error(e.getMessage());
            return new CommonResponse.Builder().error().message("字段删除失败："+e.getMessage()).build();
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<Object> getAll(){
        try {
            List<Specific> specifics = specificService.getAll();
            return new CommonResponse.Builder().ok().message("字段列表").data("specificList",specifics).build();
        }catch (Exception e){
            return new CommonResponse.Builder().error().message("查询失败").build();
        }
    }
}
