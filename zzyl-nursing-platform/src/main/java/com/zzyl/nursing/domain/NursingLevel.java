//packageName就是包名
package com.zzyl.nursing.domain;
// 遍历, 遍历导入包列表
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.zzyl.common.annotation.Excel;
import com.zzyl.common.core.domain.BaseEntity;

/**
 * 护理等级对象 nursing_level
 * 
 * @author LaoYe
 * @date 2024-11-09
 */
// 定义一个临时变量 Entity="BaseEntity"
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description="护理等级实体")
public class NursingLevel extends BaseEntity
{
    private static final long serialVersionUID = 1L;
// 遍历表中的每个列
    /** 主键ID */
    @ApiModelProperty("主键ID")
    private Integer id;

    /** 等级名称 */
    @Excel(name = "等级名称")
    @ApiModelProperty("等级名称")
    private String name;

    /** 护理计划ID */
    @Excel(name = "护理计划ID")
    @ApiModelProperty("护理计划ID")
    private Integer lplanId;

    /** 护理费用 */
    @Excel(name = "护理费用")
    @ApiModelProperty("护理费用")
    private BigDecimal fee;

    /** 状态（0：禁用，1：启用） */
    @Excel(name = "状态", readConverterExp = "0=：禁用，1：启用")
    @ApiModelProperty("状态（0：禁用，1：启用）")
    private Integer status;

    /** 等级说明 */
    @Excel(name = "等级说明")
    @ApiModelProperty("等级说明")
    private String description;

}
