package cn.kjxy.service;

import cn.kjxy.entity.UserEntity;
import cn.kjxy.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserDetails loadUserByUsername(String username);
    UserEntity findUserByName(String name);
    Map<String, Object> findUserByName1(String name,Integer pageNum,Integer pageSize);
//    List<UserEntity> findAllUsers();
    UserEntity findUserById(Integer id);
    void deleteUser(Integer id);
    void updateUser(UserEntity user);
    Map<String, Object> getAdminAndCommonUsers(Integer pageNum, Integer pageSize);
    Map<String,Object> getCommonUsers(Integer pageNum,Integer pageSize);
    void saveUser(UserEntity user);
    //    根据rid查询用户
    List<UserEntity> findUserByRid(Integer rid);
}
