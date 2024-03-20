package com.patrick.food.service.impl;

import com.opencsv.CSVReader;
import com.patrick.common.reponse.Result;
import com.patrick.food.domain.FoodTruck;
import com.patrick.food.mapper.IFoodTruckMapper;
import com.patrick.food.model.QueryFoodParam;
import com.patrick.food.service.IFoodTruckService;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 食品车业务接口实现类
 */
@Service
public class FoodTruckServiceImpl implements IFoodTruckService {

    private final static int SIZE = 1000;
    @Resource
    private IFoodTruckMapper foodTruckMapper;
    @Override
    public Result queryList(QueryFoodParam queryFoodParam) {
        List<FoodTruck> foodTruckList =  foodTruckMapper.queryList(queryFoodParam);
        return Result.builderSuccess(foodTruckList);
    }

    @Override
    public Result uploadCsvFile(MultipartFile file) {
        // 将MultipartFile对象转换为File对象
        File csvFile = null;
        try {
            csvFile = File.createTempFile("temp", ".csv");
            file.transferTo(csvFile);
            // 创建MappingStrategy对象和CsvToBean对象
            DataInputStream in = new DataInputStream(new FileInputStream(csvFile));
            CSVReader csvReader = new CSVReader(new InputStreamReader(in, "utf-8"));
            // 读取CSV文件数据
            String[] strArray;
            List<FoodTruck> foodTruckList = new ArrayList<>();
            FoodTruck dto = null;
            int i = 0;
            csvReader.skip(1);
            while ((strArray = csvReader.readNext()) != null) {
                //System.out.println(Arrays.deepToString(strs));
                dto = new FoodTruck();
                // 将数据数据设置到实体对象中去
                foodTruckList.add(setData(strArray,dto));
                i++;
                // 当 list 到达单批次最大数量后，执行保存数据操作，并清空list
                if (i==SIZE){
                    foodTruckMapper.insertList(foodTruckList);
                    foodTruckList.clear();
                    i = 0;
                }
            }
            // 跳出循环后，判断里面有没有数据 防止有数据遗漏
            if (CollectionUtils.isNotEmpty(foodTruckList)){
                foodTruckMapper.insertList(foodTruckList);
            }
            // 关闭流
            csvReader.close();
        } catch (Exception e) {
           return Result.builderFail("Upload Failed!!!");
        }

        return Result.builderSuccess("Upload Successful!");
    }

    @Override
    public Result queryById(Long locationId) {
        FoodTruck foodTruck =  foodTruckMapper.selectByPrimaryKey(locationId);
        return Result.builderSuccess(foodTruck);
    }

    public  FoodTruck setData(String[] strArray,FoodTruck dto){
        dto.setLocationId(Long.parseLong(strArray[0].trim()));
        dto.setApplicant(strArray[1].trim());
        dto.setFacilityType(strArray[2].trim());
        dto.setLocationDescription(strArray[4].trim());
        dto.setAddress(strArray[5].trim());
        dto.setStatus(strArray[10].trim());
        dto.setFoodItems(strArray[11].trim());
        dto.setLatitude(strArray[14].trim());
        dto.setLongitude(strArray[15].trim());
        dto.setFirePreventionDistricts(strArray[24].trim());
        dto.setPoliceDistricts(strArray[25].trim());
        dto.setSupervisorDistricts(strArray[26].trim());
        dto.setZipCodes(strArray[27].trim());
        return dto;
    }
}
