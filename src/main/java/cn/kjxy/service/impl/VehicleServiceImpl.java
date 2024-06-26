package cn.kjxy.service.impl;

import cn.kjxy.entity.VehicleEntity;
import cn.kjxy.mapper.VehicleMapper;
import cn.kjxy.service.VehicleService;
import cn.kjxy.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleMapper vehicleMapper;
    @Override
    public Result addVehicle(VehicleEntity vehicleEntity){
         if(vehicleEntity.getUser_id()==null){
             return new Result(404,"传入用户ID不能为空");
         }
         vehicleMapper.addVehicle(vehicleEntity);
         return new Result(200,"车辆添加成功");
    }
    @Override
    public Result deleteVehicle(Integer id){
        if(id == null){
            return new Result(404,"请传入要删除车辆的ID");
        }
        if(vehicleMapper.getVehicleById(id) == null){
            return new Result(404,"请传入正确的车辆ID");
        }
            vehicleMapper.deleteVehicle(id);
        return new Result(200,"车辆删除成功");
    }
    @Override
    public Result updateVehicle(VehicleEntity vehicleEntity){
        if(vehicleEntity.getId() == null){
            return new Result(404,"传入车辆ID不能为空");
        }
        if(vehicleMapper.getVehicleById(vehicleEntity.getId()) == null){
            return new Result(404,"请传入正确的车辆ID");
        }
        if(vehicleEntity.getUser_id()==null){
            return new Result(404,"传入用户ID不能为空");
        }
         vehicleMapper.updateVehicle(vehicleEntity);
        return new Result(200,"车辆修改成功");
    }
    @Override
    public Map<String,Object> getVehicleList(Integer pageNow,Integer pageSize){
        Integer offset = (pageNow - 1) * pageSize; //计算偏移量
        List<VehicleEntity> list = vehicleMapper.getVehicleList(offset,pageSize);
        Integer totalCount = vehicleMapper.getVehicleCount();
        Integer totalPages = (totalCount + pageSize - 1) / pageSize;
        Map<String,Object> data = new TreeMap<>();
        data.put("list",list);
        data.put("currentPage",pageNow);
        data.put("totalCount",totalCount);
        data.put("totalPages",totalPages);
        return data;
    }

    @Override
    public Map<String, Object> getVehiclesByUserId(Integer id, Integer pageNow, Integer pageSize){
        Integer offset = (pageNow - 1) * pageSize;
        List<VehicleEntity> list = vehicleMapper.getVehiclesByUserId(id,offset,pageSize);
        Integer totalCount = vehicleMapper.getVehicleCountByUserId(id);
        Integer totalPages = (totalCount + pageSize - 1) / pageSize;
        Map<String,Object> data = new TreeMap<>();
        data.put("list",list);
        data.put("currentPage",pageNow);
        data.put("totalCount",totalCount);
        data.put("totalPages",totalPages);
        return data;
    }
    @Override
    public Map<String,Object> findAllVehiclesByParam(VehicleEntity vehicleEntity,Integer pageNow,Integer pageSize){
        Integer offset = (pageNow - 1) * pageSize;

        Map<String, Object> params = new HashMap<>();
        params.put("vehicleEntity", vehicleEntity);
        params.put("offset", offset);
        params.put("pageSize", pageSize);

        List<VehicleEntity> list = vehicleMapper.findAllVehiclesByParam(params);
        Integer totalCount = vehicleMapper.findAllVehiclesByParamCount(vehicleEntity);
        Integer totalPages = (totalCount + pageSize - 1) / pageSize;
        Map<String,Object> data = new TreeMap<>();
        data.put("list",list);
        data.put("currentPage",pageNow);
        data.put("totalCount",totalCount);
        data.put("totalPages",totalPages);
        return data;
    }

    @Override
    public Map<String,Object> findAllVehiclesByParamAndUserId(Integer id,VehicleEntity vehicleEntity,Integer pageNow,Integer pageSize){
        Integer offset = (pageNow - 1) * pageSize;

        Map<String, Object> params = new HashMap<>();
        params.put("vehicleEntity", vehicleEntity);
        params.put("offset", offset);
        params.put("pageSize", pageSize);

        List<VehicleEntity> list = vehicleMapper.findAllVehiclesByParamAndUserId(id,params);
        Integer totalCount = vehicleMapper.findAllVehiclesByParamAndUserIdCount(id,vehicleEntity);
        Integer totalPages = (totalCount + pageSize - 1) / pageSize;
        Map<String,Object> data = new TreeMap<>();
        data.put("list",list);
        data.put("currentPage",pageNow);
        data.put("totalCount",totalCount);
        data.put("totalPages",totalPages);
        return data;
    }
    @Override
    public VehicleEntity getVehicleById(Integer id){
        return vehicleMapper.getVehicleById(id);
    }
}
