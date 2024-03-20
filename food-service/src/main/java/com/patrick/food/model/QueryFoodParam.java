package com.patrick.food.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "查询食品车关键参数")
public class QueryFoodParam {
    @Schema(description = "申请人")
    private String applicant;
    @Schema(description = "设施类型")
    private String facilityType;
    @Schema(description = "地址")
    private String address;
    @Schema(description = "状态")
    private String status;
    @Schema(description = "食品细项")
    private String foodItems;
    @Schema(description = "纬度")
    private String latitude;
    @Schema(description = "经度")
    private String longitude;
}
