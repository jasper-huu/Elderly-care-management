package com.zzyl.serve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzyl.serve.domain.NursingPlan;
import com.zzyl.serve.domain.NursingProjectPlan;
import com.zzyl.serve.dto.NursingPlanDto;
import com.zzyl.serve.mapper.NursingPlanMapper;
import com.zzyl.serve.mapper.NursingProjectPlanMapper;
import com.zzyl.serve.vo.NursingPlanVo;
import com.zzyl.serve.vo.NursingProjectPlanVo;
import com.zzyl.serve.service.INursingPlanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 护理计划Service业务层处理
 *
 * @author LaoYe
 * @date 2024-11-09
 */
@Service
public class NursingPlanServiceImpl extends ServiceImpl<NursingPlanMapper, NursingPlan> implements INursingPlanService {
    @Autowired
    private NursingPlanMapper nursingPlanMapper;

    @Resource
    private NursingProjectPlanMapper nursingProjectPlanMapper;

    /**
     * 查询护理计划
     *
     * @param id 护理计划主键
     * @return 护理计划
     */
    @Override
    public NursingPlanVo selectNursingPlanById(Integer id) {
        // 先查询护理计划
        NursingPlan nursingPlan = nursingPlanMapper.selectNursingPlanById(id);
        NursingPlanVo nursingPlanVo = new NursingPlanVo();
        BeanUtils.copyProperties(nursingPlan, nursingPlanVo);
        // 通过护理计划id查询护理计划与护理项目的关系
        List<NursingProjectPlanVo> nursingProjectPlansVo = nursingProjectPlanMapper.selectByPlanId(id);
        nursingPlanVo.setProjectPlans(nursingProjectPlansVo);
        return nursingPlanVo;
    }

    /**
     * 查询护理计划列表
     *
     * @param nursingPlan 护理计划
     * @return 护理计划
     */
    @Override
    public List<NursingPlan> selectNursingPlanList(NursingPlan nursingPlan) {
        return nursingPlanMapper.selectNursingPlanList(nursingPlan);
    }

    /**
     * 新增护理计划
     *
     * @param nursingPlanDto 护理计划
     * @return 结果
     */
    @Override
    @Transactional
    public int insertNursingPlan(NursingPlanDto nursingPlanDto) {
        // 1. 添加护理计划
        NursingPlan nursingPlan = new NursingPlan();
        BeanUtils.copyProperties(nursingPlanDto, nursingPlan);
        int rtn = nursingPlanMapper.insert(nursingPlan);
        // 2. 添加计划与项目的关系
        List<NursingProjectPlan> projectPlans = nursingPlanDto.getProjectPlans();
        if(!CollectionUtils.isEmpty(projectPlans)){
            //设置新增计划的id
            projectPlans.forEach(p -> p.setPlanId(nursingPlan.getId()));
            nursingProjectPlanMapper.batchInsert(projectPlans);
        }
        return rtn;
    }

    /**
     * 修改护理计划
     *
     * @return 结果
     */
    @Override
    @Transactional
    public int updateNursingPlan(NursingPlanDto nursingPlanDto) {
        NursingPlan nursingPlan = new NursingPlan();
        BeanUtils.copyProperties(nursingPlanDto, nursingPlan);
        // 护理计划项目也要更新
        // 先删除旧关系
        nursingProjectPlanMapper.deleteByPlanId(nursingPlan.getId());
        // 添加新关系
        List<NursingProjectPlan> projectPlans = nursingPlanDto.getProjectPlans();
        if(!CollectionUtils.isEmpty(projectPlans)){
            //设置新增计划的id
            projectPlans.forEach(p -> p.setPlanId(nursingPlan.getId()));
            nursingProjectPlanMapper.batchInsert(projectPlans);
        }
        return nursingPlanMapper.updateById(nursingPlan);
    }

    /**
     * 批量删除护理计划
     *
     * @param ids 需要删除的护理计划主键
     * @return 结果
     */
    @Override

    public int deleteNursingPlanByIds(Integer[] ids) {
        // 关系也要删除
        return nursingPlanMapper.deleteBatchIds(List.of(ids));
    }

    /**
     * 删除护理计划信息
     *
     * @param id 护理计划主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteNursingPlanById(Integer id) {
        nursingProjectPlanMapper.deleteByPlanId(id);
        return nursingPlanMapper.deleteById(id);
    }
}
