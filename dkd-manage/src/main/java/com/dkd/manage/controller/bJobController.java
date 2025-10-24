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
import com.dkd.manage.domain.bJob;
import com.dkd.manage.service.IbJobService;
import com.dkd.common.utils.poi.ExcelUtil;
import com.dkd.common.core.page.TableDataInfo;

/**
 * 自动补货任务Controller
 * 
 * @author itheima
 * @date 2025-10-24
 */
@RestController
@RequestMapping("/manage/job")
public class bJobController extends BaseController
{
    @Autowired
    private IbJobService bJobService;

    /**
     * 查询自动补货任务列表
     */
    @PreAuthorize("@ss.hasPermi('manage:job:list')")
    @GetMapping("/list")
    public TableDataInfo list(bJob bJob)
    {
        startPage();
        List<bJob> list = bJobService.selectbJobList(bJob);
        return getDataTable(list);
    }

    /**
     * 导出自动补货任务列表
     */
    @PreAuthorize("@ss.hasPermi('manage:job:export')")
    @Log(title = "自动补货任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, bJob bJob)
    {
        List<bJob> list = bJobService.selectbJobList(bJob);
        ExcelUtil<bJob> util = new ExcelUtil<bJob>(bJob.class);
        util.exportExcel(response, list, "自动补货任务数据");
    }

    /**
     * 获取自动补货任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:job:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bJobService.selectbJobById(id));
    }

    /**
     * 新增自动补货任务
     */
    @PreAuthorize("@ss.hasPermi('manage:job:add')")
    @Log(title = "自动补货任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody bJob bJob)
    {
        return toAjax(bJobService.insertbJob(bJob));
    }

    /**
     * 修改自动补货任务
     */
    @PreAuthorize("@ss.hasPermi('manage:job:edit')")
    @Log(title = "自动补货任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody bJob bJob)
    {
        return toAjax(bJobService.updatebJob(bJob));
    }

    /**
     * 删除自动补货任务
     */
    @PreAuthorize("@ss.hasPermi('manage:job:remove')")
    @Log(title = "自动补货任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bJobService.deletebJobByIds(ids));
    }
}
