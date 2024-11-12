//packageName就是包名
package com.zzyl.nursing.domain;
// 遍历, 遍历导入包列表
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.zzyl.common.annotation.Excel;
import com.zzyl.common.core.domain.BaseEntity;

/**
 * 护理计划和项目关联对象 nursing_project_plan
 * 
 * @author ruoyi
 * @date 2024-11-09
 */
// 定义一个临时变量 Entity="BaseEntity"
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description="护理计划和项目关联实体")
public class NursingProjectPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;
// 遍历表中的每个列
    /** 主键id */
    @ApiModelProperty("主键id")
    private Integer id;

    /** 计划id */
    @Excel(name = "计划id")
    @ApiModelProperty("计划id")
    private Integer planId;

    /** 项目id */
    @Excel(name = "项目id")
    @ApiModelProperty("项目id")
    private Integer projectId;

    /** 计划执行时间 */
    @Excel(name = "计划执行时间")
    @ApiModelProperty("计划执行时间")
    private String executeTime;

    /** 执行周期 0 天 1 周 2月 */
    @Excel(name = "执行周期 0 天 1 周 2月")
    @ApiModelProperty("执行周期 0 天 1 周 2月")
    private Integer executeCycle;

    /** 执行频次 */
    @Excel(name = "执行频次")
    @ApiModelProperty("执行频次")
    private Integer executeFrequency;

}
