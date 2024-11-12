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
 * 护理计划对象 nursing_plan
 * 
 * @author LaoYe
 * @date 2024-11-09
 */
// 定义一个临时变量 Entity="BaseEntity"
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description="护理计划实体")
public class NursingPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;
// 遍历表中的每个列
    /** 编号 */
    @ApiModelProperty("编号")
    private Integer id;

    /** 排序号 */
    @ApiModelProperty("排序号")
    private Integer sortNo;

    /** 名称 */
    @Excel(name = "名称")
    @ApiModelProperty("名称")
    private String planName;

    /** 状态 0禁用 1启用 */
    @Excel(name = "状态 0禁用 1启用")
    @ApiModelProperty("状态 0禁用 1启用")
    private Integer status;

}
