package com.zzyl.nursing.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzyl.nursing.domain.Floor;
import com.zzyl.nursing.vo.TreeVo;

import java.util.List;

/**
 * 楼层Service接口
 * 
 * @author ruoyi
 * @date 2024-04-26
 */
public interface IFloorService extends IService<Floor>
{
    /**
     * 查询楼层
     * 
     * @param id 楼层主键
     * @return 楼层
     */
    public Floor selectFloorById(Long id);

    /**
     * 新增楼层
     * 
     * @param floor 楼层
     * @return 结果
     */
    public int insertFloor(Floor floor);

    /**
     * 修改楼层
     * 
     * @param floor 楼层
     * @return 结果
     */
    public int updateFloor(Floor floor);

    /**
     * 批量删除楼层
     * 
     * @param ids 需要删除的楼层主键集合
     * @return 结果
     */
    public int deleteFloorByIds(Long[] ids);

    /**
     * 删除楼层信息
     * 
     * @param id 楼层主键
     * @return 结果
     */
    public int deleteFloorById(Long id);

    /**
     * 获取所有楼层房间可用的床位
     * @param status
     * @return
     */
    List<TreeVo> getRoomAndBedByBedStatus(Integer status);
}
