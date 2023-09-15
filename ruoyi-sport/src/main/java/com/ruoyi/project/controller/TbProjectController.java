package com.ruoyi.project.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.domain.bo.TbProjectBo;
import com.ruoyi.project.domain.vo.TbProjectVo;
import com.ruoyi.project.service.ITbProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 项目管理
 *
 * @author ruoyi
 * @date 2023-09-14
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/project/project")
public class TbProjectController extends BaseController {

    private final ITbProjectService iTbProjectService;

    /**
     * 查询项目管理 列表
     */
    @SaCheckPermission("project:project:list")
    @GetMapping("/list")
    public TableDataInfo<TbProjectVo> list(TbProjectBo bo, PageQuery pageQuery) {
        return iTbProjectService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出项目管理 列表
     */
    @SaCheckPermission("project:project:export")
    @Log(title = "项目管理 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TbProjectBo bo, HttpServletResponse response) {
        List<TbProjectVo> list = iTbProjectService.queryList(bo);
        ExcelUtil.exportExcel(list, "项目管理 ", TbProjectVo.class, response);
    }

    /**
     * 获取项目管理 详细信息
     *
     * @param projectId 主键
     */
    @SaCheckPermission("project:project:query")
    @GetMapping("/{projectId}")
    public R<TbProjectVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long projectId) {
        return R.ok(iTbProjectService.queryById(projectId));
    }

    /**
     * 新增项目管理
     */
    @SaCheckPermission("project:project:add")
    @Log(title = "项目管理 ", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TbProjectBo bo) {
        return toAjax(iTbProjectService.insertByBo(bo));
    }

    /**
     * 修改项目管理
     */
    @SaCheckPermission("project:project:edit")
    @Log(title = "项目管理 ", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TbProjectBo bo) {
        return toAjax(iTbProjectService.updateByBo(bo));
    }

    /**
     * 删除项目管理
     *
     * @param projectIds 主键串
     */
    @SaCheckPermission("project:project:remove")
    @Log(title = "项目管理 ", businessType = BusinessType.DELETE)
    @DeleteMapping("/{projectIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] projectIds) {
        return toAjax(iTbProjectService.deleteWithValidByIds(Arrays.asList(projectIds), true));
    }
}
