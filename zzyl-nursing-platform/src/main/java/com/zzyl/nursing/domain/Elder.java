package com.zzyl.nursing.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.zzyl.common.annotation.Excel;
import com.zzyl.common.core.domain.BaseEntity;

/**
 * 老人对象 elder
 * 
 * @author laoYe
 * @date 2024-11-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("老人实体")
public class Elder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
    * 名称
    */
    @Excel(name = "名称")
    @ApiModelProperty(value = "名称")
    private String name;

    /**
    * 图片
    */
    @Excel(name = "图片")
    @ApiModelProperty(value = "图片")
    private String image;

    /**
    * 身份证号
    */
    @Excel(name = "身份证号")
    @ApiModelProperty(value = "身份证号")
    private String idCardNo;

    /**
    * 性别（0:女  1:男）
    */
    @Excel(name = "性别", readConverterExp = "0=:女,1=:男")
    @ApiModelProperty(value = "性别")
    private Integer sex;

    /**
    * 状态（0：禁用，1:启用  2:请假 3:退住中 4入住中 5已退住）
    */
    @Excel(name = "状态", readConverterExp = "0=：禁用，1:启用,2=:请假,3=:退住中,4=入住中,5=已退住")
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
    * 手机号
    */
    @Excel(name = "手机号")
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
    * 出生日期
    */
    @Excel(name = "出生日期")
    @ApiModelProperty(value = "出生日期")
    private String birthday;

    /**
    * 家庭住址
    */
    @Excel(name = "家庭住址")
    @ApiModelProperty(value = "家庭住址")
    private String address;

    /**
    * 身份证国徽面
    */
    @Excel(name = "身份证国徽面")
    @ApiModelProperty(value = "身份证国徽面")
    private String idCardNationalEmblemImg;

    /**
    * 身份证人像面
    */
    @Excel(name = "身份证人像面")
    @ApiModelProperty(value = "身份证人像面")
    private String idCardPortraitImg;

    /**
    * 床位编号
    */
    @Excel(name = "床位编号")
    @ApiModelProperty(value = "床位编号")
    private String bedNumber;

    /**
    * 床位id
    */
    @Excel(name = "床位id")
    @ApiModelProperty(value = "床位id")
    private Long bedId;

}
