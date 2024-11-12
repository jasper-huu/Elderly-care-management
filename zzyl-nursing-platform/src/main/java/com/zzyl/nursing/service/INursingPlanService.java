package com.zzyl.nursing.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzyl.nursing.domain.NursingPlan;
import com.zzyl.nursing.dto.NursingPlanDto;
import com.zzyl.nursing.vo.NursingPlanVo;

/**
 * 护理计划Service接口
 * 
 * @author LaoYe
 * @date 2024-11-09
 */
public interface INursingPlanService extends IService<NursingPlan>
{
    /**
     * 查询护理计划
     * 
     * @param id 护理计划主键
     * @return 护理计划
     */
    public NursingPlanVo selectNursingPlanById(Integer id);

    /**
     * 查询护理计划列表
     * 
     * @param nursingPlan 护理计划
     * @return 护理计划集合
     */
    public List<NursingPlan> selectNursingPlanList(NursingPlan nursingPlan);

    /**
     * 新增护理计划
     * 
     * @param nursingPlanDto 护理计划
     * @return 结果
     */
    public int insertNursingPlan(NursingPlanDto nursingPlanDto);

    /**
     * 修改护理计划
     * 
     * @param nursingPlan 护理计划
     * @return 结果
     */
    public int updateNursingPlan(NursingPlan nursingPlan);

    /**
     * 批量删除护理计划
     * 
     * @param ids 需要删除的护理计划主键集合
     * @return 结果
     */
    public int deleteNursingPlanByIds(Integer[] ids);

    /**
     * 删除护理计划信息
     * 
     * @param id 护理计划主键
     * @return 结果
     */
    public int deleteNursingPlanById(Integer id);
}
