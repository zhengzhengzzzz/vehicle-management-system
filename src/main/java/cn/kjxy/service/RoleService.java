package cn.kjxy.service;

import cn.kjxy.entity.RoleEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RoleService {
    RoleEntity findRoleById(Integer id);
    Map<String, Object> findRoleByName(String name,Integer pageNum,Integer pageSize);
    void addRole(RoleEntity roleEntity);
    void delRole(Integer id);
    void updRole(RoleEntity roleEntity);
    Map<String,Object> getRoleList(Integer pageNow,Integer pageSize);
}
