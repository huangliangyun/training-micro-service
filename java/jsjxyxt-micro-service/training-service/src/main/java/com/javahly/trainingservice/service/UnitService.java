package com.javahly.trainingservice.service;

import com.javahly.trainingservice.entity.Unit;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2020/5/2
 */
@Repository
public interface UnitService {

    //查询单位信息
    public List<Unit> getUnits(Integer uStatus);

    //查询单位信息
    public List<Unit> getUnits();

    //添加单位信息
    public int addUnit(Unit unit);

    //更新单位信息
    public int updateUnit(Unit unit);

    //删除单位信息
    public int deleteUnit(Integer uId);

}
