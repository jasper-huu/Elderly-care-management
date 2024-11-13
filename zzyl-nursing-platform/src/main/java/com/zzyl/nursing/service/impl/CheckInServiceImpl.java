package com.zzyl.nursing.service.impl;

import java.util.List;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zzyl.common.exception.ServiceException;
import com.zzyl.common.utils.CodeGenerator;
import com.zzyl.common.utils.DateUtils;
import com.zzyl.nursing.domain.Bed;
import com.zzyl.nursing.domain.CheckInConfig;
import com.zzyl.nursing.domain.Contract;
import com.zzyl.nursing.domain.Elder;
import com.zzyl.nursing.dto.CheckInApplyDto;
import com.zzyl.nursing.dto.CheckInContractDto;
import com.zzyl.nursing.mapper.BedMapper;
import com.zzyl.nursing.mapper.CheckInConfigMapper;
import com.zzyl.nursing.mapper.ContractMapper;
import com.zzyl.nursing.mapper.ElderMapper;
import kotlin.jvm.internal.Lambda;
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

    @Autowired
    private ElderMapper elderMapper;

    @Autowired
    private BedMapper bedMapper;

    @Autowired
    private ContractMapper contractMapper;

    @Autowired
    private CheckInConfigMapper checkInConfigMapper;

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
        LambdaQueryWrapper<Elder> elderLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 身份证号码
        elderLambdaQueryWrapper.eq(Elder::getIdCardNo, dto.getCheckInElderDto().getIdCardNo());
            //状态（0：禁用，1:启用  2:请假 3:退住中 4入住中 5已退住）
                //.in(Elder::getStatus, List.of(1,2,3,4));
        Elder elderIn = elderMapper.selectOne(elderLambdaQueryWrapper);
        if (elderIn != null && elderIn.getStatus() >=1 && elderIn.getStatus() <=4) {
            throw new ServiceException("该老人已入住");
        }
        //2. 更新床位状态为已入住
        // 通过床的id查询床位是否被占用
        Bed bed = bedMapper.selectById(dto.getCheckInConfigDto().getBedId());
        if(bed.getBedStatus() == 1){
            throw new ServiceException("该床位已有人了");
        }
        // 更新床位的状态
        LambdaUpdateWrapper<Bed> bedUpdateWrapper = new LambdaUpdateWrapper<>();
        bedUpdateWrapper.set(Bed::getBedStatus, 1);
        // 多线程下需加锁，利用的是数据库行锁，如果更新成功返回值为>=1, 否则返回值为0代表没有更新成功，说明被其他线程更新了该行数据
        bedUpdateWrapper.eq(Bed::getBedStatus, 0);
        bedUpdateWrapper.eq(Bed::getId, bed.getId());
        int updated = bedMapper.update(null, bedUpdateWrapper);
        if(updated == 0){
            throw new ServiceException("该床位已有人了");
        }

        //3. 添加或更新老人信息
        Elder elder = insertOrUpdate(elderIn, bed, dto);

        //4. 新增签约办理
        insertContract(elder, dto);
        //5. 新增入住信息
        CheckIn checkIn = insertCheckIn(elder,bed, dto);
        //6. 新增入住配置信息
        insertCheckInConfig(checkIn.getId(), dto);
    }

    /**
     * 新增入住配置信息
     * @param id
     * @param dto
     */
    private void insertCheckInConfig(Long id, CheckInApplyDto dto) {
        CheckInConfig config = BeanUtil.toBeanIgnoreError(dto.getCheckInConfigDto(), CheckInConfig.class);
        config.setCheckInId(id);
        checkInConfigMapper.insertCheckInConfig(config);
    }

    /**
     * 新增入住信息
     * @param elder
     * @param dto
     * @return
     */
    private CheckIn insertCheckIn(Elder elder, Bed bed, CheckInApplyDto dto) {
        CheckIn checkIn = new CheckIn();
        checkIn.setElderName(elder.getName());
        checkIn.setElderId(elder.getId());
        checkIn.setIdCardNo(elder.getIdCardNo());
        checkIn.setStartDate(dto.getCheckInConfigDto().getStartDate());
        checkIn.setEndDate(dto.getCheckInConfigDto().getEndDate());
        checkIn.setNursingLevelName(dto.getCheckInConfigDto().getNursingLevelName());
        checkIn.setBedNumber(bed.getBedNumber());
        checkIn.setStatus(0);// 已入住
        save(checkIn);
        return checkIn;
    }

    /**
     * 新增签约办理，合同
     * @param elder
     * @param dto
     */
    private void insertContract(Elder elder, CheckInApplyDto dto) {
        String contractNo = "HT" + CodeGenerator.generateContractNumber();
        Contract contract = BeanUtil.toBeanIgnoreError(dto.getCheckInContractDto(), Contract.class);
        contract.setContractNumber(contractNo);
        contract.setElderId(elder.getId());
        contract.setElderName(elder.getName());
        contract.setStatus(0); // 未生效，后期审核与支付后才会生效
        contract.setStartDate(dto.getCheckInConfigDto().getStartDate());
        contract.setEndDate(dto.getCheckInConfigDto().getEndDate());
        contractMapper.insertContract(contract);
    }

    /**
     * 添加或更新老人信息
     * @param elderIn
     * @param bed
     * @param dto
     * @return
     */
    private Elder insertOrUpdate(Elder elderIn, Bed bed, CheckInApplyDto dto) {
        // 创建并拷贝对象属性值，忽略错误的
        // 类型转换
        Elder elder = BeanUtil.toBeanIgnoreError(dto.getCheckInElderDto(), Elder.class);
        elder.setBedId(bed.getId());
        elder.setBedNumber(bed.getBedNumber());
        elder.setStatus(1);// 启用，申请中
        if(null != elderIn){
            // 修改
            elder.setId(elderIn.getId());
            elderMapper.updateById(elder);
        }else{
            // 新增
            elderMapper.insert(elder);
        }
        return elder;
    }
}
