package com.zzyl.nursing.service.impl;

import java.util.List;

import com.zzyl.common.core.domain.entity.SysUser;
import com.zzyl.common.utils.DateUtils;
import com.zzyl.nursing.domain.NursingProjectPlan;
import com.zzyl.nursing.dto.NursingPlanDto;
import com.zzyl.nursing.mapper.NursingProjectPlanMapper;
import com.zzyl.nursing.vo.NursingPlanVo;
import com.zzyl.nursing.vo.NursingProjectPlanVo;
import com.zzyl.system.mapper.SysUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzyl.nursing.mapper.NursingPlanMapper;
import com.zzyl.nursing.domain.NursingPlan;
import com.zzyl.nursing.service.INursingPlanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

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

    @Autowired
    private SysUserMapper userMapper;

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
     * @param nursingPlan 护理计划
     * @return 结果
     */
    @Override
    public int updateNursingPlan(NursingPlan nursingPlan) {
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
        return nursingPlanMapper.deleteBatchIds(List.of(ids));
    }

    /**
     * 删除护理计划信息
     *
     * @param id 护理计划主键
     * @return 结果
     */
    @Override
    public int deleteNursingPlanById(Integer id) {
        return nursingPlanMapper.deleteById(id);
    }
}
