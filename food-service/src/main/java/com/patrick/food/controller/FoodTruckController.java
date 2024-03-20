package com.patrick.food.controller;

import com.patrick.common.reponse.Result;
import com.patrick.food.model.QueryFoodParam;
import com.patrick.food.service.IFoodTruckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/food/truck")
@Tag(name = "食品车管理接口")
public class FoodTruckController {
    @Resource
    private IFoodTruckService foodService;

    @Operation(summary = "多条件查询食品车列表")
    @PostMapping("/list")
    public Result queryList(@RequestBody QueryFoodParam queryFoodParam){
        return foodService.queryList(queryFoodParam);
    }

    @Operation(summary = "根据地址编号查询食品车数据")
    @GetMapping("/{locationId}")
    public Result queryById(@PathVariable Long locationId){
        return foodService.queryById(locationId);
    }

    @Operation(summary = "上传解析csv文件")
    @PostMapping("/processCsv")
    public Result uploadCsvFile(@RequestBody MultipartFile file) {
        return foodService.uploadCsvFile(file);
    }

}
