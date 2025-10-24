package com.dkd.manage.controller;

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
import com.dkd.common.annotation.Log;
import com.dkd.common.core.controller.BaseController;
import com.dkd.common.core.domain.AjaxResult;
import com.dkd.common.enums.BusinessType;
import com.dkd.manage.domain.TaskType;
import com.dkd.manage.service.ITaskTypeService;
import com.dkd.common.utils.poi.ExcelUtil;
import com.dkd.common.core.page.TableDataInfo;

/**
 * 工单类型Controller
 * 
 * @author itheima
 * @date 2025-10-24
 */
@RestController
@RequestMapping("/manage/taskType")
public class TaskTypeController extends BaseController
{
    @Autowired
    private ITaskTypeService taskTypeService;

    /**
     * 查询工单类型列表
     */
    @PreAuthorize("@ss.hasPermi('manage:taskType:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskType taskType)
    {
        startPage();
        List<TaskType> list = taskTypeService.selectTaskTypeList(taskType);
        return getDataTable(list);
    }

    /**
     * 导出工单类型列表
     */
    @PreAuthorize("@ss.hasPermi('manage:taskType:export')")
    @Log(title = "工单类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TaskType taskType)
    {
        List<TaskType> list = taskTypeService.selectTaskTypeList(taskType);
        ExcelUtil<TaskType> util = new ExcelUtil<TaskType>(TaskType.class);
        util.exportExcel(response, list, "工单类型数据");
    }

    /**
     * 获取工单类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:taskType:query')")
    @GetMapping(value = "/{typeId}")
    public AjaxResult getInfo(@PathVariable("typeId") Long typeId)
    {
        return success(taskTypeService.selectTaskTypeByTypeId(typeId));
    }

    /**
     * 新增工单类型
     */
    @PreAuthorize("@ss.hasPermi('manage:taskType:add')")
    @Log(title = "工单类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskType taskType)
    {
        return toAjax(taskTypeService.insertTaskType(taskType));
    }

    /**
     * 修改工单类型
     */
    @PreAuthorize("@ss.hasPermi('manage:taskType:edit')")
    @Log(title = "工单类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskType taskType)
    {
        return toAjax(taskTypeService.updateTaskType(taskType));
    }

    /**
     * 删除工单类型
     */
    @PreAuthorize("@ss.hasPermi('manage:taskType:remove')")
    @Log(title = "工单类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{typeIds}")
    public AjaxResult remove(@PathVariable Long[] typeIds)
    {
        return toAjax(taskTypeService.deleteTaskTypeByTypeIds(typeIds));
    }
}
