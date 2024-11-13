package com.zzyl.nursing.service.impl;

import java.util.List;
import com.zzyl.common.utils.DateUtils;
import com.zzyl.nursing.dto.CheckInApplyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzyl.nursing.mapper.CheckInMapper;
import com.zzyl.nursing.domain.CheckIn;
import com.zzyl.nursing.service.ICheckInService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * 入住Service业务层处理
 * 
 * @author laoye
 * @date 2024-11-11
 */
@Service
public class CheckInServiceImpl extends ServiceImpl<CheckInMapper, CheckIn> implements ICheckInService
{
    @Autowired
    private CheckInMapper checkInMapper;

    /**
     * 查询入住
     * 
     * @param id 入住主键
     * @return 入住
     */
    @Override
    public CheckIn selectCheckInById(Long id)
    {
        return getById(id);
    }

    /**
     * 查询入住列表
     * 
     * @param checkIn 入住
     * @return 入住
     */
    @Override
    public List<CheckIn> selectCheckInList(CheckIn checkIn)
    {
        return checkInMapper.selectCheckInList(checkIn);
    }

    /**
     * 新增入住
     * 
     * @param checkIn 入住
     * @return 结果
     */
    @Override
    public int insertCheckIn(CheckIn checkIn)
    {
        return save(checkIn) ? 1 : 0;
    }

    /**
     * 修改入住
     * 
     * @param checkIn 入住
     * @return 结果
     */
    @Override
    public int updateCheckIn(CheckIn checkIn)
    {
        return updateById(checkIn) ? 1 : 0;
    }

    /**
     * 批量删除入住
     * 
     * @param ids 需要删除的入住主键
     * @return 结果
     */
    @Override
    public int deleteCheckInByIds(Long[] ids)
    {
        return removeByIds(Arrays.asList(ids)) ? 1 : 0;
    }

    /**
     * 删除入住信息
     * 
     * @param id 入住主键
     * @return 结果
     */
    @Override
    public int deleteCheckInById(Long id)
    {
        return removeById(id) ? 1 : 0;
    }

    /**
     * 入住申请
     *
     * @param dto
     */
    @Override
    @Transactional
    public void apply(CheckInApplyDto dto) {
        //1. 校验老人是否已入住
        //2. 更新床位状态为已入住
        //3. 添加或更新老人信息
        //4. 新增合同
        //5. 新增入住信息
        //6. 新增入住配置信息
    }
}
