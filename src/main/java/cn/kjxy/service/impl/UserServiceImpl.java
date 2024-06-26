package cn.kjxy.service.impl;

import cn.kjxy.entity.UserEntity;
import cn.kjxy.mapper.UserMapper;
import cn.kjxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserEntity user = userMapper.findUserByName(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found with username："+username);
        }
        return new LoginUser(user, (List<GrantedAuthority>) getAuthorities(user.getRole().getName()));
    }
    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role)); // 根据实际角色创建权限对象
        return authorities;
    }
    @Override
    public UserEntity findUserByName(String name){
        return userMapper.findUserByName(name);
    }
    @Override
    public Map<String, Object> findUserByName1(String name, Integer pageNum, Integer pageSize) {
        Integer offset = (pageNum - 1) * pageSize;
        List<UserEntity> list = userMapper.findUserByName1(name,offset,pageSize);
        Integer totalCount = userMapper.findUserByNameCount(name);
        if(name.isEmpty()||name==null){
            list = userMapper.findAllUsers();
            totalCount = userMapper.findAllUsersCount();
        }
        Integer totalPages = (totalCount + pageSize - 1) / pageSize;
        Map<String,Object> data = new TreeMap<>();
        data.put("list",list);
        data.put("currentPage",pageNum);
        data.put("totalCount",totalCount);
        data.put("totalPages",totalPages);
        return data;
    }
    @Override
    public UserEntity findUserById(Integer id){return userMapper.findUserById(id);}

    @Override
    public void deleteUser(Integer id){userMapper.deleteUser(id);}
    @Override
    public void updateUser(UserEntity user){userMapper.updateUser(user);}
    @Override
    public Map<String, Object> getAdminAndCommonUsers(Integer pageNum, Integer pageSize){
        Integer offset = (pageNum - 1) * pageSize;
        List<UserEntity> list = userMapper.getAdminAndCommonUsers(offset,pageSize);
        Integer totalCount = userMapper.getAdminAndCommonUsersCount();
        Integer totalPages = (totalCount + pageSize - 1) / pageSize;
        Map<String,Object> data = new TreeMap<>();
        data.put("list",list);
        data.put("currentPage",pageNum);
        data.put("totalCount",totalCount);
        data.put("totalPages",totalPages);
        return data;
    }
    @Override
    public Map<String,Object> getCommonUsers(Integer pageNum,Integer pageSize){
        Integer offset = (pageNum - 1) * pageSize;
        List<UserEntity> list = userMapper.getCommonUsers(offset,pageSize);
        Integer totalCount = userMapper.getCommonUsersCount();
        Integer totalPages = (totalCount + pageSize - 1) / pageSize;
        Map<String,Object> data = new TreeMap<>();
        data.put("list",list);
        data.put("currentPage",pageNum);
        data.put("totalCount",totalCount);
        data.put("totalPages",totalPages);
        return data;
    }
    @Override
    public void saveUser(UserEntity user){userMapper.saveUser(user);}
    @Override
    public List<UserEntity> findUserByRid(Integer rid){return userMapper.findUserByRid(rid);}
}
