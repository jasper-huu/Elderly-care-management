package com.zzyl.nursing.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.zzyl.common.annotation.Log;
import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.common.enums.BusinessType;
import com.zzyl.nursing.domain.NursingProject;
import com.zzyl.nursing.service.INursingProjectService;
import com.zzyl.common.utils.poi.ExcelUtil;
import com.zzyl.common.core.page.TableDataInfo;
import io.swagger.annotations.*;
/**
 * 护理项目Controller
 *
 * @author ruoyi
 * @date 2024-11-07
 */
@Api(tags = "Nursing Project Controller", description = "护理项目管理")
@RestController
@RequestMapping("/nursing/project")
public class NursingProjectController extends BaseController {

    @Autowired
    private INursingProjectService nursingProjectService;

    /**
     * 查询护理项目列表
     */
    @ApiOperation(value = "获取护理项目列表", notes = "返回护理项目的分页列表")
    @PreAuthorize("@ss.hasPermi('nursing:project:list')")
    @GetMapping("/list")
    public TableDataInfo list(@ApiParam(value = "护理项目过滤条件") NursingProject nursingProject) {
        startPage();
        List<NursingProject> list = nursingProjectService.selectNursingProjectList(nursingProject);
        return getDataTable(list);
    }

    /**
     * 导出护理项目列表
     */
    @ApiOperation(value = "导出护理项目列表", notes = "导出符合条件的护理项目数据到Excel")
    @PreAuthorize("@ss.hasPermi('nursing:project:export')")
    @Log(title = "护理项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response,
                       @ApiParam(value = "护理项目过滤条件") NursingProject nursingProject) {
        List<NursingProject> list = nursingProjectService.selectNursingProjectList(nursingProject);
        ExcelUtil<NursingProject> util = new ExcelUtil<>(NursingProject.class);
        util.exportExcel(response, list, "护理项目数据");
    }

    /**
     * 获取护理项目详细信息
     */
    @ApiOperation(value = "获取护理项目详细信息", notes = "通过ID获取护理项目详细信息")
    @ApiImplicitParam(name = "id", value = "护理项目ID", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("@ss.hasPermi('nursing:project:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(nursingProjectService.selectNursingProjectById(id));
    }

    /**
     * 新增护理项目
     */
    @ApiOperation(value = "新增护理项目", notes = "创建一个新的护理项目")
    @PreAuthorize("@ss.hasPermi('nursing:project:add')")
    @Log(title = "护理项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@ApiParam(value = "护理项目对象", required = true) @RequestBody NursingProject nursingProject) {
        return toAjax(nursingProjectService.insertNursingProject(nursingProject));
    }

    /**
     * 修改护理项目
     */
    @ApiOperation(value = "修改护理项目", notes = "更新指定的护理项目信息")
    @PreAuthorize("@ss.hasPermi('nursing:project:edit')")
    @Log(title = "护理项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@ApiParam(value = "护理项目对象", required = true) @RequestBody NursingProject nursingProject) {
        return toAjax(nursingProjectService.updateNursingProject(nursingProject));
    }

    /**
     * 删除护理项目
     */
    @ApiOperation(value = "删除护理项目", notes = "删除指定的护理项目")
    @ApiImplicitParam(name = "ids", value = "护理项目ID数组", required = true, allowMultiple = true, dataType = "Long", paramType = "path")
    @PreAuthorize("@ss.hasPermi('nursing:project:remove')")
    @Log(title = "护理项目", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(nursingProjectService.deleteNursingProjectByIds(ids));
    }
}
