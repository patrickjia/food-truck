package com.patrick.food.service;

import com.patrick.common.reponse.Result;
import com.patrick.food.model.QueryFoodParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 食品车业务接口类
 */
public interface IFoodTruckService {
    /**
     * 多条件查询食品车数据
     * @param queryFoodParam
     * @return
     */
    Result queryList(QueryFoodParam queryFoodParam);

    /**
     * 上传解析csv文件，并落库
     * @param file
     * @return
     */
    Result uploadCsvFile(MultipartFile file);

    /**
     * 根据地址编号查询食品车数据
     * @param locationId
     * @return
     */
    Result queryById(Long locationId);
}
