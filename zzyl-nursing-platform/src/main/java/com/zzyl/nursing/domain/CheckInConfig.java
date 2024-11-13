package com.zzyl.nursing.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.zzyl.common.annotation.Excel;
import com.zzyl.common.core.domain.BaseEntity;

/**
 * 入住配置对象 check_in_config
 * 
 * @author laoYe
 * @date 2024-11-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("入住配置实体")
public class CheckInConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**
    * 主键ID
    */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
    * 入住表ID
    */
    @Excel(name = "入住表ID")
    @ApiModelProperty(value = "入住表ID")
    private Long checkInId;

    /**
    * 护理等级ID
    */
    @Excel(name = "护理等级ID")
    @ApiModelProperty(value = "护理等级ID")
    private Long nursingLevelId;

    /**
    * 护理等级名称
    */
    @Excel(name = "护理等级名称")
    @ApiModelProperty(value = "护理等级名称")
    private String nursingLevelName;

    /**
    * 费用开始时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "费用开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "费用开始时间")
    private LocalDateTime feeStartDate;

    /**
    * 费用结束时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "费用结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "费用结束时间")
    private LocalDateTime feeEndDate;

    /**
    * 押金（元）
    */
    @Excel(name = "押金", readConverterExp = "元=")
    @ApiModelProperty(value = "押金")
    private BigDecimal deposit;

    /**
    * 护理费用（元/月）
    */
    @Excel(name = "护理费用", readConverterExp = "元=/月")
    @ApiModelProperty(value = "护理费用")
    private BigDecimal nursingFee;

    /**
    * 床位费用（元/月）
    */
    @Excel(name = "床位费用", readConverterExp = "元=/月")
    @ApiModelProperty(value = "床位费用")
    private BigDecimal bedFee;

    /**
    * 医保支付（元/月）
    */
    @Excel(name = "医保支付", readConverterExp = "元=/月")
    @ApiModelProperty(value = "医保支付")
    private BigDecimal insurancePayment;

    /**
    * 政府补贴（元/月）
    */
    @Excel(name = "政府补贴", readConverterExp = "元=/月")
    @ApiModelProperty(value = "政府补贴")
    private BigDecimal governmentSubsidy;

    /**
    * 其他费用（元/月）
    */
    @Excel(name = "其他费用", readConverterExp = "元=/月")
    @ApiModelProperty(value = "其他费用")
    private BigDecimal otherFees;

    /**
    * 排序编号
    */
    @Excel(name = "排序编号")
    @ApiModelProperty(value = "排序编号")
    private Integer sortOrder;

}
