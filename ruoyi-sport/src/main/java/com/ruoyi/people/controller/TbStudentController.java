package com.ruoyi.people.controller;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

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
import com.ruoyi.common.core.validate.QueryGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.people.domain.vo.TbStudentVo;
import com.ruoyi.people.domain.bo.TbStudentBo;
import com.ruoyi.people.service.ITbStudentService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生管理
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/people/student")
public class TbStudentController extends BaseController {

    private final ITbStudentService iTbStudentService;

    /**
     * 查询学生管理列表
     */
    @SaCheckPermission("people:student:list")
    @GetMapping("/list")
    public TableDataInfo<TbStudentVo> list(TbStudentBo bo, PageQuery pageQuery) {
        return iTbStudentService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出学生管理列表
     */
    @SaCheckPermission("people:student:export")
    @Log(title = "学生管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TbStudentBo bo, HttpServletResponse response) {
        List<TbStudentVo> list = iTbStudentService.queryList(bo);
        ExcelUtil.exportExcel(list, "学生管理", TbStudentVo.class, response);
    }

    /**
     * 获取学生管理详细信息
     *
     * @param studentId 主键
     */
    @SaCheckPermission("people:student:query")
    @GetMapping("/{studentId}")
    public R<TbStudentVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long studentId) {
        return R.ok(iTbStudentService.queryById(studentId));
    }

    /**
     * 新增学生管理
     */
    @SaCheckPermission("people:student:add")
    @Log(title = "学生管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TbStudentBo bo) {
        return toAjax(iTbStudentService.insertByBo(bo));
    }

    /**
     * 修改学生管理
     */
    @SaCheckPermission("people:student:edit")
    @Log(title = "学生管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TbStudentBo bo) {
        return toAjax(iTbStudentService.updateByBo(bo));
    }

    /**
     * 删除学生管理
     *
     * @param studentIds 主键串
     */
    @SaCheckPermission("people:student:remove")
    @Log(title = "学生管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{studentIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] studentIds) {
        return toAjax(iTbStudentService.deleteWithValidByIds(Arrays.asList(studentIds), true));
    }
}
