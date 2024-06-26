package cn.kjxy.mapper;

import cn.kjxy.entity.VehicleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface VehicleMapper {
//    增加一条车辆信息
    void addVehicle(VehicleEntity vehicleEntity);
//    删除一条车辆信息
    void deleteVehicle(Integer id);
//    根据id修改指定的车辆信息
    void updateVehicle(VehicleEntity vehicleEntity);
//    分页查询
    List<VehicleEntity> getVehicleList(@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);
//    获取总共有多少条车辆记录
    Integer getVehicleCount();
//    根据id获取车辆
    VehicleEntity getVehicleById(Integer id);
//    根据用户id查找不同的车辆
    List<VehicleEntity> getVehiclesByUserId(@Param("id") Integer id,@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);
//    根据用户id获取总共有多少条车辆信息
    Integer getVehicleCountByUserId(Integer id);
//    模糊查询
    List<VehicleEntity> findAllVehiclesByParam(Map<String, Object> params);
    List<VehicleEntity> findAllVehiclesByParamAndUserId(@Param("id") Integer id,@Param("params") Map<String,Object> params);
    Integer findAllVehiclesByParamCount(VehicleEntity vehicleEntity);
    Integer findAllVehiclesByParamAndUserIdCount(@Param("id") Integer id,@Param("vehicleEntity") VehicleEntity vehicleEntity);
}
