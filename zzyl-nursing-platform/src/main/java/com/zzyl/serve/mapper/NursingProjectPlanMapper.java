package com.zzyl.serve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzyl.serve.domain.NursingProjectPlan;
import com.zzyl.serve.vo.NursingProjectPlanVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 护理计划和项目关联Mapper接口
 * 
 * @author ruoyi
 * @date 2024-11-09
 */
@Mapper
public interface NursingProjectPlanMapper extends BaseMapper<NursingProjectPlan>
{
    /**
     * 查询护理计划和项目关联
     * 
     * @param id 护理计划和项目关联主键
     * @return 护理计划和项目关联
     */
    public NursingProjectPlan selectNursingProjectPlanById(Integer id);

    /**
     * 查询护理计划和项目关联列表
     * 
     * @param nursingProjectPlan 护理计划和项目关联
     * @return 护理计划和项目关联集合
     */
    public List<NursingProjectPlan> selectNursingProjectPlanList(NursingProjectPlan nursingProjectPlan);

    /**
     * 新增护理计划和项目关联
     * 
     * @param nursingProjectPlan 护理计划和项目关联
     * @return 结果
     */
    public int insertNursingProjectPlan(NursingProjectPlan nursingProjectPlan);

    /**
     * 修改护理计划和项目关联
     * 
     * @param nursingProjectPlan 护理计划和项目关联
     * @return 结果
     */
    public int updateNursingProjectPlan(NursingProjectPlan nursingProjectPlan);

    /**
     * 删除护理计划和项目关联
     * 
     * @param id 护理计划和项目关联主键
     * @return 结果
     */
    public int deleteNursingProjectPlanById(Integer id);

    /**
     * 批量删除护理计划和项目关联
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNursingProjectPlanByIds(Integer[] ids);

    void batchInsert(@Param("projectPlans") List<NursingProjectPlan> projectPlans);

    /**
     * 通过护理计划id查询护理计划项目关联
     * @param id
     * @return
     */
    @Select("select id, plan_id, project_id, execute_time, execute_cycle, execute_frequency from nursing_project_plan \n" +
        "where plan_id = #{id}")
    List<NursingProjectPlanVo> selectByPlanId(Integer id);

    /**
     * 通过护理计划id删除护理计划项目关联
     * @param planId
     */
    @Delete("delete from nursing_project_plan where plan_id = #{planId}")
    void deleteByPlanId(Integer planId);
}
