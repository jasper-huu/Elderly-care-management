package com.zzyl.nursing.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.zzyl.common.annotation.Excel;
import com.zzyl.common.core.domain.BaseEntity;

/**
 * 护理计划对象 nursing_plan
 *
 * @author jasperhu
 * @date 2024-11-09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("护理计划实体")
public class NursingPlan extends BaseEntity
        {
private static final long serialVersionUID = 1L;

        /** 编号 */
            @ApiModelProperty(value = "主键ID")
            private Long id;
        /** 排序号 */
            @ApiModelProperty(value = "${comment}")
            private Long sortNo;
        /** 名称 */
                @Excel(name = "名称")
            @ApiModelProperty(value = "名称")
            private String planName;
        /** 状态 0禁用 1启用 */
                @Excel(name = "状态 0禁用 1启用")
            @ApiModelProperty(value = "状态 0禁用 1启用")
            private Long status;
                                                                                                                                                                                                            }