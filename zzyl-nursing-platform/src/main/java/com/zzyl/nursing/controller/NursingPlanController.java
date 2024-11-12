package com.zzyl.nursing.controller;

import com.zzyl.common.core.domain.R;
import com.zzyl.nursing.dto.NursingPlanDto;
import com.zzyl.nursing.vo.NursingPlanVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

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
import com.zzyl.nursing.domain.NursingPlan;
import com.zzyl.nursing.service.INursingPlanService;
import com.zzyl.common.utils.poi.ExcelUtil;
import com.zzyl.common.core.page.TableDataInfo;

/**
 * 护理计划Controller
 *
 * @author LaoYe
 * @date 2024-11-09
 */
@RestController
@RequestMapping("/nursing/plan")
@Api(tags = "护理计划管理")
public class NursingPlanController extends BaseController {
    @Autowired
    private INursingPlanService nursingPlanService;

    /**
     * 查询护理计划列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:list')")
    @GetMapping("/list")
    @ApiOperation("查询护理计划列表")
    public TableDataInfo list(NursingPlan nursingPlan) {
        startPage();
        List<NursingPlan> list = nursingPlanService.selectNursingPlanList(nursingPlan);
        return getDataTable(list);
    }

    /**
     * 导出护理计划列表
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:export')")
    @Log(title = "护理计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("导出护理计划列表")
    public void export(HttpServletResponse response, NursingPlan nursingPlan) {
        List<NursingPlan> list = nursingPlanService.selectNursingPlanList(nursingPlan);
        ExcelUtil<NursingPlan> util = new ExcelUtil<NursingPlan>(NursingPlan.class);
        util.exportExcel(response, list, "护理计划数据");
    }

    /**
     * 获取护理计划详细信息
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation("获取护理计划详细信息")
    @ApiImplicitParam(name = "id", value = "护理计划ID", required = true, type = "path", dataType = "Integer")
    public R<NursingPlanVo> getInfo(@PathVariable("id") Integer id) {
        NursingPlanVo vo = nursingPlanService.selectNursingPlanById(id);
        return R.ok(vo);
    }

    /**
     * 新增护理计划
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:add')")
    @Log(title = "护理计划", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增护理计划")
    @ApiImplicitParam(name = "nursingPlan", value = "护理计划对象", type = "body", required = true, dataType = "nursingPlan")
    public AjaxResult add(@RequestBody NursingPlanDto nursingPlanDto) {
        return toAjax(nursingPlanService.insertNursingPlan(nursingPlanDto));
    }

    /**
     * 修改护理计划
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:edit')")
//    @Log(title = "护理计划", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改护理计划")
    @ApiImplicitParam(name = "nursingPlan", value = "护理计划对象", type = "body", required = true, dataType = "nursingPlan")
    public AjaxResult edit(@RequestBody NursingPlan nursingPlan) {
        nursingPlan.setUpdateBy(getUsername());
        return toAjax(nursingPlanService.updateNursingPlan(nursingPlan));
    }

    /**
     * 删除护理计划
     */
    @PreAuthorize("@ss.hasPermi('nursing:plan:remove')")
    @Log(title = "护理计划", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("删除护理计划")
    @ApiImplicitParam(name = "ids", value = "护理计划IDs", required = true, type = "path", dataType = "Integer[]")
    public AjaxResult remove(@PathVariable Integer[] ids) {
        return toAjax(nursingPlanService.deleteNursingPlanByIds(ids));
    }
}
