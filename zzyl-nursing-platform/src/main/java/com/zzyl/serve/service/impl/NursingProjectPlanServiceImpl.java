package com.zzyl.serve.service.impl;

import java.util.List;
import com.zzyl.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzyl.serve.mapper.NursingProjectPlanMapper;
import com.zzyl.serve.domain.NursingProjectPlan;
import com.zzyl.serve.service.INursingProjectPlanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Arrays;

/**
 * 护理计划和项目关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-04
 */
@Service
public class NursingProjectPlanServiceImpl extends ServiceImpl<NursingProjectPlanMapper, NursingProjectPlan> implements INursingProjectPlanService
{
    @Autowired
    private NursingProjectPlanMapper nursingProjectPlanMapper;

    /**
     * 查询护理计划和项目关联
     * 
     * @param id 护理计划和项目关联主键
     * @return 护理计划和项目关联
     */
    @Override
    public NursingProjectPlan selectNursingProjectPlanById(Long id)
    {
        return getById(id);
    }

    /**
     * 查询护理计划和项目关联列表
     * 
     * @param nursingProjectPlan 护理计划和项目关联
     * @return 护理计划和项目关联
     */
    @Override
    public List<NursingProjectPlan> selectNursingProjectPlanList(NursingProjectPlan nursingProjectPlan)
    {
        return nursingProjectPlanMapper.selectNursingProjectPlanList(nursingProjectPlan);
    }

    /**
     * 新增护理计划和项目关联
     * 
     * @param nursingProjectPlan 护理计划和项目关联
     * @return 结果
     */
    @Override
    public int insertNursingProjectPlan(NursingProjectPlan nursingProjectPlan)
    {
        return save(nursingProjectPlan) ? 1 : 0;
    }

    /**
     * 修改护理计划和项目关联
     * 
     * @param nursingProjectPlan 护理计划和项目关联
     * @return 结果
     */
    @Override
    public int updateNursingProjectPlan(NursingProjectPlan nursingProjectPlan)
    {
        return updateById(nursingProjectPlan) ? 1 : 0;
    }

    /**
     * 批量删除护理计划和项目关联
     * 
     * @param ids 需要删除的护理计划和项目关联主键
     * @return 结果
     */
    @Override
    public int deleteNursingProjectPlanByIds(Long[] ids)
    {
        return removeByIds(Arrays.asList(ids)) ? 1 : 0;
    }

    /**
     * 删除护理计划和项目关联信息
     * 
     * @param id 护理计划和项目关联主键
     * @return 结果
     */
    @Override
    public int deleteNursingProjectPlanById(Long id)
    {
        return removeById(id) ? 1 : 0;
    }
}
