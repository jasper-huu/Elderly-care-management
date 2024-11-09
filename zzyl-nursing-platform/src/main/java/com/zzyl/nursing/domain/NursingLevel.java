package com.zzyl.nursing.domain;

import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.zzyl.common.annotation.Excel;
import com.zzyl.common.core.domain.BaseEntity;

/**
 * 护理等级对象 nursing_level
 *
 * @author jasperhu
 * @date 2024-11-09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("护理等级实体")
public class NursingLevel extends BaseEntity
        {
private static final long serialVersionUID = 1L;

        /** 主键ID */
            @ApiModelProperty(value = "主键ID")
            private Long id;
        /** 等级名称 */
                @Excel(name = "等级名称")
            @ApiModelProperty(value = "等级名称")
            private String name;
        /** 护理计划ID */
                @Excel(name = "护理计划ID")
            @ApiModelProperty(value = "护理计划ID")
            private Long lplanId;
        /** 护理费用 */
                @Excel(name = "护理费用")
            @ApiModelProperty(value = "护理费用")
            private BigDecimal fee;
        /** 状态（0：禁用，1：启用） */
                @Excel(name = "状态", readConverterExp = "0=：禁用，1：启用")
            @ApiModelProperty(value = "状态")
            private Integer status;
        /** 等级说明 */
                @Excel(name = "等级说明")
            @ApiModelProperty(value = "等级说明")
            private String description;
                                                                                                                                                                                                            }