package com.ruoyi.project.controller;

import java.util.List;
import java.util.Arrays;

import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.domain.vo.TbClassVo;
import com.ruoyi.project.domain.bo.TbClassBo;
import com.ruoyi.project.service.ITbClassService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 班级管理
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/project/class")
public class TbClassController extends BaseController {

    private final ITbClassService iTbClassService;

    /**
     * 查询班级管理列表
     */
    @SaCheckPermission("project:class:list")
    @GetMapping("/list")
    public TableDataInfo<TbClassVo> list(TbClassBo bo, PageQuery pageQuery) {
        return iTbClassService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出班级管理列表
     */
    @SaCheckPermission("project:class:export")
    @Log(title = "班级管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TbClassBo bo, HttpServletResponse response) {
        List<TbClassVo> list = iTbClassService.queryList(bo);
        ExcelUtil.exportExcel(list, "班级管理", TbClassVo.class, response);
    }

    /**
     * 获取班级管理详细信息
     *
     * @param classId 主键
     */
    @SaCheckPermission("project:class:query")
    @GetMapping("/{classId}")
    public R<TbClassVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long classId) {
        return R.ok(iTbClassService.queryById(classId));
    }

    /**
     * 新增班级管理
     */
    @SaCheckPermission("project:class:add")
    @Log(title = "班级管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TbClassBo bo) {
        return toAjax(iTbClassService.insertByBo(bo));
    }

    /**
     * 修改班级管理
     */
    @SaCheckPermission("project:class:edit")
    @Log(title = "班级管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TbClassBo bo) {
        return toAjax(iTbClassService.updateByBo(bo));
    }

    /**
     * 删除班级管理
     *
     * @param classIds 主键串
     */
    @SaCheckPermission("project:class:remove")
    @Log(title = "班级管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{classIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] classIds) {
        return toAjax(iTbClassService.deleteWithValidByIds(Arrays.asList(classIds), true));
    }
}
