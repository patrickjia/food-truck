package com.patrick.food.mapper;

import com.patrick.common.mapper.MyMapper;
import com.patrick.common.reponse.Result;
import com.patrick.food.domain.FoodTruck;
import com.patrick.food.model.QueryFoodParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * 食品车数据持久化层接口
 */
@Mapper
public interface IFoodTruckMapper extends MyMapper<FoodTruck> {

    /**
     * 多条件查询食品车数据
     * @param queryFoodParam
     * @return
     */
    List<FoodTruck> queryList(QueryFoodParam queryFoodParam);

}
