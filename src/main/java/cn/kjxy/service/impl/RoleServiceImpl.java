package cn.kjxy.service.impl;

import cn.kjxy.entity.RoleEntity;
import cn.kjxy.entity.VehicleEntity;
import cn.kjxy.mapper.RoleMapper;
import cn.kjxy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public RoleEntity findRoleById(Integer id){
        return roleMapper.findRoleById(id);
    }
    @Override
    public void addRole(RoleEntity roleEntity){
        roleMapper.addRole(roleEntity);
    }
    @Override
    public void delRole(Integer id){
        roleMapper.deleteRole(id);
    }
    @Override
    public void updRole(RoleEntity roleEntity){
        roleMapper.updateRole(roleEntity);
    }
    @Override
    public Map<String,Object> getRoleList(Integer pageNow,Integer pageSize){
        Integer offset = (pageNow - 1) * pageSize;
        List<RoleEntity> list = roleMapper.getRoleList(offset,pageSize);
        Integer totalCount = roleMapper.getRoleCount();
        Integer totalPages = (totalCount + pageSize - 1) / pageSize;
        Map<String,Object> data = new TreeMap<>();
        data.put("list",list);
        data.put("currentPage",pageNow);
        data.put("totalCount",totalCount);
        data.put("totalPages",totalPages);
        return data;
    }
    @Override
    public Map<String, Object> findRoleByName(String name, Integer pageNum, Integer pageSize){
        Integer offset = (pageNum - 1) * pageSize;
        List<RoleEntity> list = roleMapper.findRoleByName(name,offset,pageSize);
        Integer totalCount = roleMapper.findRoleByNameCount();
        if(name.isEmpty()||name==null){
            list = roleMapper.getRoleList(offset,pageSize);
            totalCount = roleMapper.getRoleCount();
        }
        Integer totalPages = (totalCount + pageSize - 1) / pageSize;
        Map<String,Object> data = new TreeMap<>();
        data.put("list",list);
        data.put("currentPage",pageNum);
        data.put("totalCount",totalCount);
        data.put("totalPages",totalPages);
        return data;
    }
}
