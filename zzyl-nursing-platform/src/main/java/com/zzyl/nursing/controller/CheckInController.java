package com.zzyl.nursing.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.zzyl.common.core.domain.R;
import com.zzyl.nursing.dto.CheckInApplyDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zzyl.common.annotation.Log;
import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.common.enums.BusinessType;
import com.zzyl.nursing.domain.CheckIn;
import com.zzyl.nursing.service.ICheckInService;
import com.zzyl.common.utils.poi.ExcelUtil;
import com.zzyl.common.core.page.TableDataInfo;

/**
 * 入住Controller
 * 
 * @author laoye
 * @date 2024-11-11
 */
@RestController
@RequestMapping("/nursing/checkIn")
@Api(tags = "入住相关接口")
public class CheckInController extends BaseController
{
    @Autowired
    private ICheckInService checkInService;

    /**
     * 查询入住列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:checkIn:list')")
    @GetMapping("/list")
    @ApiOperation(value = "获取入住列表")
    public TableDataInfo<List<CheckIn>> list(CheckIn checkIn)
    {
        startPage();
        List<CheckIn> list = checkInService.selectCheckInList(checkIn);
        return getDataTable(list);
    }

    /**
     * 导出入住列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:checkIn:export')")
    @Log(title = "入住", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("导出入住列表")
    public void export(HttpServletResponse response, CheckIn checkIn)
    {
        List<CheckIn> list = checkInService.selectCheckInList(checkIn);
        ExcelUtil<CheckIn> util = new ExcelUtil<CheckIn>(CheckIn.class);
        util.exportExcel(response, list, "入住数据");
    }

    /**
     * 获取入住详细信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:checkIn:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation("获取入住详细信息")
    public R<CheckIn> getInfo(@ApiParam(value = "入住ID", required = true)
            @PathVariable("id") Long id)
    {
        return R.ok(checkInService.selectCheckInById(id));
    }

    /**
     * 新增入住
     */
    @PreAuthorize("@ss.hasPermi('nursing:checkIn:add')")
    @Log(title = "入住", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增入住")
    public AjaxResult add(@ApiParam(value = "入住实体", required = true) @RequestBody CheckIn checkIn)
    {
        return toAjax(checkInService.insertCheckIn(checkIn));
    }

    /**
     * 修改入住
     */
    @PreAuthorize("@ss.hasPermi('nursing:checkIn:edit')")
    @Log(title = "入住", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改入住")
    public AjaxResult edit(@ApiParam(value = "入住实体", required = true) @RequestBody CheckIn checkIn)
    {
        return toAjax(checkInService.updateCheckIn(checkIn));
    }

    /**
     * 删除入住
     */
    @PreAuthorize("@ss.hasPermi('nursing:checkIn:remove')")
    @Log(title = "入住", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation("删除入住")
    public AjaxResult remove(@ApiParam(value = "要删除的数据id的数组") @PathVariable Long[] ids)
    {
        return toAjax(checkInService.deleteCheckInByIds(ids));
    }

    /**
     * 入住申请
     * @param dto
     * @return
     */
    @PostMapping("/apply")
    public AjaxResult apply(@RequestBody CheckInApplyDto dto){
        checkInService.apply(dto);
        return AjaxResult.success();
    }
}
