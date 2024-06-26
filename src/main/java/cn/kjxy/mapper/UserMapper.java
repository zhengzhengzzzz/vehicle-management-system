package cn.kjxy.mapper;

import cn.kjxy.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
  UserEntity findUserByName(String name);
  List<UserEntity> findUserByName1(@Param("name") String name,@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);
  List<UserEntity> findAllUsers();
  Integer findUserByNameCount(String name);
  Integer findAllUsersCount();
  UserEntity findUserById(Integer id);
  void deleteUser(Integer id);
  void updateUser(UserEntity user);
  List<UserEntity> getCommonUsers(@Param("offset") Integer pageNum, @Param("pageSize") Integer pageSize);
  List<UserEntity> getAdminAndCommonUsers(@Param("offset") Integer pageNum,@Param("pageSize") Integer pageSize);
  Integer getCommonUsersCount();
  Integer getAdminAndCommonUsersCount();
  void saveUser(UserEntity user);
//  根据rid查找用户
  List<UserEntity> findUserByRid(Integer rid);
}
