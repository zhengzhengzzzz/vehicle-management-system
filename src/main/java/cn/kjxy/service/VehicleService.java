package cn.kjxy.service;

import cn.kjxy.entity.VehicleEntity;
import cn.kjxy.utils.Result;

import java.util.List;
import java.util.Map;

public interface VehicleService {
    Result addVehicle(VehicleEntity vehicleEntity);
    Result deleteVehicle(Integer id);
    Result updateVehicle(VehicleEntity vehicleEntity);
    VehicleEntity getVehicleById(Integer id);
    Map<String,Object> getVehicleList(Integer pageNow, Integer pageSize);
    Map<String,Object> getVehiclesByUserId(Integer id,Integer pageNow,Integer pageSize);
    Map<String,Object> findAllVehiclesByParam(VehicleEntity vehicleEntity,Integer pageNow,Integer pageSize);
    Map<String,Object> findAllVehiclesByParamAndUserId(Integer id,VehicleEntity vehicleEntity,Integer pageNow,Integer pageSize);
}
