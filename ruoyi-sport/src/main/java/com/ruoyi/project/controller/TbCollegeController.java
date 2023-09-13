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
import com.ruoyi.project.domain.vo.TbCollegeVo;
import com.ruoyi.project.domain.bo.TbCollegeBo;
import com.ruoyi.project.service.ITbCollegeService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学院管理
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/project/college")
public class TbCollegeController extends BaseController {

    private final ITbCollegeService iTbCollegeService;

    /**
     * 查询学院管理列表
     */
    @SaCheckPermission("project:college:list")
    @GetMapping("/list")
    public TableDataInfo<TbCollegeVo> list(TbCollegeBo bo, PageQuery pageQuery) {
        return iTbCollegeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出学院管理列表
     */
    @SaCheckPermission("project:college:export")
    @Log(title = "学院管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TbCollegeBo bo, HttpServletResponse response) {
        List<TbCollegeVo> list = iTbCollegeService.queryList(bo);
        ExcelUtil.exportExcel(list, "学院管理", TbCollegeVo.class, response);
    }

    /**
     * 获取学院管理详细信息
     *
     * @param collegeId 主键
     */
    @SaCheckPermission("project:college:query")
    @GetMapping("/{collegeId}")
    public R<TbCollegeVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long collegeId) {
        return R.ok(iTbCollegeService.queryById(collegeId));
    }

    /**
     * 新增学院管理
     */
    @SaCheckPermission("project:college:add")
    @Log(title = "学院管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TbCollegeBo bo) {
        return toAjax(iTbCollegeService.insertByBo(bo));
    }

    /**
     * 修改学院管理
     */
    @SaCheckPermission("project:college:edit")
    @Log(title = "学院管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TbCollegeBo bo) {
        return toAjax(iTbCollegeService.updateByBo(bo));
    }

    /**
     * 删除学院管理
     *
     * @param collegeIds 主键串
     */
    @SaCheckPermission("project:college:remove")
    @Log(title = "学院管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{collegeIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] collegeIds) {
        return toAjax(iTbCollegeService.deleteWithValidByIds(Arrays.asList(collegeIds), true));
    }
}
