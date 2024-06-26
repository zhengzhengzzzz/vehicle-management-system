package cn.kjxy.controller;

import cn.kjxy.entity.UserEntity;
import cn.kjxy.service.UserService;
import cn.kjxy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id){
        userService.deleteUser(id);
        return new Result(200,"删除用户成功");
    }
    @PutMapping("/update")
    public Result update(@RequestBody UserEntity user){
        userService.updateUser(user);
        return new Result(200,"修改用户成功");
    }
    @GetMapping("/get")
    public Result getUsers(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserEntity user = userService.findUserByName(username);
        Map<String,Object> data = new TreeMap<>();
        if(user.getRole().getName().equals("管理员")){
            data = userService.getCommonUsers(pageNum,pageSize);
        }
        if(user.getRole().getName().equals("超级管理员")){
            data = userService.getAdminAndCommonUsers(pageNum,pageSize);
        }
        return new Result(200,"查询成功",data);
    }
    @GetMapping("/findUserById")
    public Result findUserById(@RequestParam Integer id){
        UserEntity data = userService.findUserById(id);
        return new Result(200,"查询成功",data);
    }
    @GetMapping("/findUserByName")
    public Result findUserByName(@RequestParam String name,@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        Map<String,Object> user = userService.findUserByName1(name,pageNum,pageSize);
        return new Result(200,"查询成功",user);
    }
}
