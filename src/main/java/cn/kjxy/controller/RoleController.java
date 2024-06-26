package cn.kjxy.controller;

import cn.kjxy.entity.RoleEntity;
import cn.kjxy.entity.UserEntity;
import cn.kjxy.service.RoleService;
import cn.kjxy.service.UserService;
import cn.kjxy.utils.Result;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @PostMapping("/add")
    public Result add(@RequestBody RoleEntity roleEntity){
        roleService.addRole(roleEntity);
        return new Result(200,"添加成功");
    }
    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id){
        List<UserEntity> users = userService.findUserByRid(id);
        if(!users.isEmpty()){
            return new Result(201,"请先删除角色对应的用户");
        }else{
            roleService.delRole(id);
            return new Result(200,"删除成功");
        }
    }
    @PutMapping("update")
    public Result update(@RequestBody RoleEntity roleEntity){
        if(roleEntity.getId()==null){
            return new Result(404,"id不能为空");
        }
        roleService.updRole(roleEntity);
        return new Result(200,"修改成功");
    }
    @GetMapping("/get")
    public Result getPages(@RequestParam(value = "pageNow",defaultValue = "1") Integer pageNow,
                           @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        Map<String,Object> data = roleService.getRoleList(pageNow,pageSize);
        return new Result(200,"查询成功",data);
    }
    @GetMapping("/findRoleById")
    public Result findRoleById(@RequestParam Integer id){
        RoleEntity roleEntity = roleService.findRoleById(id);
        return new Result(200,"查询成功",roleEntity);
    }
    @GetMapping("/findRoleByName")
    public Result findRoleByName(@RequestParam String name,@RequestParam Integer pageNum,Integer pageSize){
        Map<String,Object> data = roleService.findRoleByName(name,pageNum,pageSize);
        return new Result(200,"查询成功",data);
    }
}
