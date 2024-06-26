package cn.kjxy.controller;

import cn.kjxy.entity.UserEntity;
import cn.kjxy.entity.VehicleEntity;
import cn.kjxy.service.UserService;
import cn.kjxy.service.VehicleService;
import cn.kjxy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
public class VehicleController {
    @Autowired
    VehicleService vehicleService;
    @Autowired
    UserService userService;
    @PostMapping("/addVehicle")
    @Transactional
    public Result addVehicle(@RequestBody VehicleEntity vehicleEntity){
        //用Mybatis执行insert语句的时候，插入成功会返回1，不成功则会抛出异常，捕获一下异常就好
        return vehicleService.addVehicle(vehicleEntity);
    }
    @DeleteMapping("/deleteVehicle")
    public Result deleteVehicle(@RequestParam Integer id){
        //Mybatis的删除操作和更新返回值一样，成功返回1，用户不存在返回0，失败则抛异常
           return vehicleService.deleteVehicle(id);
    }
    @PutMapping("/updateVehicle")
    public Result updateVehicle(@RequestBody VehicleEntity vehicleEntity){
        return vehicleService.updateVehicle(vehicleEntity);
    }
    @GetMapping("/getVehicleList")
    public Result getVehicleList(@RequestParam(value = "pageNow",defaultValue = "1") Integer pageNow,
                                              @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserEntity user = userService.findUserByName(username);
        if(user.getRole().getName().equals("管理员") || user.getRole().getName().equals("超级管理员")){
            Map<String,Object> data = vehicleService.getVehicleList(pageNow,pageSize);
            return new Result(200,"查询成功",data);
        }else{
            Map<String,Object> data = vehicleService.getVehiclesByUserId(user.getId(),pageNow,pageSize);
            return new Result(200,"查询成功",data);
        }
    }
    @GetMapping("/findAllVehiclesByParam")
    public Result findAllVehiclesByParam(@RequestParam(value = "pageNow",defaultValue = "1") Integer pageNow,
                                         @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                         @RequestBody VehicleEntity vehicleEntity){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserEntity user = userService.findUserByName(username);
        Integer id = user.getId();
        if(user.getRole().getName().equals("管理员") || user.getRole().getName().equals("超级管理员")){
            Map<String,Object> data = vehicleService.findAllVehiclesByParam(vehicleEntity,pageNow,pageSize);
            return new Result(200,"查询成功",data);
        }else{
            Map<String,Object> data = vehicleService.findAllVehiclesByParamAndUserId(id,vehicleEntity,pageNow,pageSize);
            return new Result(200,"查询成功",data);
        }
    }
    @GetMapping("/getVehicleById")
    public Result getVehicleById(@RequestParam Integer id){
        VehicleEntity data = vehicleService.getVehicleById(id);
        return new Result(200,"查询成功",data);
    }
}
