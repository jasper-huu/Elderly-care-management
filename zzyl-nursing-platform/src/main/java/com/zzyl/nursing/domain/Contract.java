package com.zzyl.nursing.domain;

import java.util.Date;
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
 * 合同对象 contract
 * 
 * @author laoYe
 * @date 2024-11-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("合同实体")
public class Contract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**
    * 主键ID
    */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
    * 老人ID
    */
    @Excel(name = "老人ID")
    @ApiModelProperty(value = "老人ID")
    private Long elderId;

    /**
    * 合同名称
    */
    @Excel(name = "合同名称")
    @ApiModelProperty(value = "合同名称")
    private String contractName;

    /**
    * 合同编号
    */
    @Excel(name = "合同编号")
    @ApiModelProperty(value = "合同编号")
    private String contractNumber;

    /**
    * 协议地址（文件路径或URL）
    */
    @ApiModelProperty(value = "合同编号")
    private String agreementPath;

    /**
    * 丙方手机号
    */
    @ApiModelProperty(value = "合同编号")
    private String thirdPartyPhone;

    /**
    * 丙方姓名
    */
    @ApiModelProperty(value = "合同编号")
    private String thirdPartyName;

    /**
    * 老人姓名
    */
    @Excel(name = "老人姓名")
    @ApiModelProperty(value = "老人姓名")
    private String elderName;

    /**
    * 开始时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startDate;

    /**
    * 结束时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endDate;

    /**
    * 状态 (0: 未生效, 1: 已生效, 2: 已过期, 3: 已失效)
    */
    @Excel(name = "状态 (0: 未生效, 1: 已生效, 2: 已过期, 3: 已失效)")
    @ApiModelProperty(value = "状态 (0: 未生效, 1: 已生效, 2: 已过期, 3: 已失效)")
    private Integer status;

    /**
    * 签约日期
    */
    @ApiModelProperty(value = "状态 (0: 未生效, 1: 已生效, 2: 已过期, 3: 已失效)")
    private LocalDateTime signDate;

    /**
    * 解除提交人
    */
    @ApiModelProperty(value = "状态 (0: 未生效, 1: 已生效, 2: 已过期, 3: 已失效)")
    private String terminationSubmitter;

    /**
    * 解除日期
    */
    @ApiModelProperty(value = "状态 (0: 未生效, 1: 已生效, 2: 已过期, 3: 已失效)")
    private Date terminationDate;

    /**
    * 解除协议地址（文件路径或URL）
    */
    @ApiModelProperty(value = "状态 (0: 未生效, 1: 已生效, 2: 已过期, 3: 已失效)")
    private String terminationAgreementPath;

    /**
    * 排序编号
    */
    @ApiModelProperty(value = "状态 (0: 未生效, 1: 已生效, 2: 已过期, 3: 已失效)")
    private Long sortOrder;

}
