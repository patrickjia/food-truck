package com.patrick.food.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "mobile_food_facility_permit")
@Getter
@Setter
@ToString
@Schema(description = "食品车实体")
public class FoodTruck {

    @Id
    @Column(name="location_id")
    @Schema(description = "位置标识")
    private Long locationId;

    @Column(name="applicant")
    @Schema(description = "申请人")
    private String applicant;

    @Column(name="facility_type")
    @Schema(description = "设施类型")
    private String facilityType;

    @Column(name="location_description")
    @Schema(description = "位置描述")
    private String locationDescription;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "食品细项")
    @Column(name="food_items")
    private String foodItems;

    @Schema(description = "纬度")
    private String latitude;

    @Schema(description = "经度")
    private String longitude;

    @Column(name="fire_prevention_districts")
    @Schema(description = "消防预防区")
    private String firePreventionDistricts;

    @Column(name="police_districts")
    @Schema(description = "警察区域")
    private String policeDistricts;

    @Column(name="supervisor_districts")
    @Schema(description = "监事区")
    private String supervisorDistricts;

    @Column(name="zip_codes")
    @Schema(description = "邮政编码")
    private String zipCodes;
}
