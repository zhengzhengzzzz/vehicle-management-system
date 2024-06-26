package cn.kjxy.mapper;

import cn.kjxy.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleMapper {
    RoleEntity findRoleById(Integer id);
    List<RoleEntity> findRoleByName(String name,Integer offset,Integer pageSize);
    Integer findRoleByNameCount();
    Integer getRoleCount();
    void addRole(RoleEntity roleEntity);
    void deleteRole(Integer id);
    void updateRole(RoleEntity roleEntity);
    List<RoleEntity> getRoleList(@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);
}
