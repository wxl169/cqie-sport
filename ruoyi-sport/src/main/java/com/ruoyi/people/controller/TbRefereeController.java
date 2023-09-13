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
import com.ruoyi.people.domain.vo.TbRefereeVo;
import com.ruoyi.people.domain.bo.TbRefereeBo;
import com.ruoyi.people.service.ITbRefereeService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 裁判员管理
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/people/referee")
public class TbRefereeController extends BaseController {

    private final ITbRefereeService iTbRefereeService;

    /**
     * 查询裁判员管理列表
     */
    @SaCheckPermission("people:referee:list")
    @GetMapping("/list")
    public TableDataInfo<TbRefereeVo> list(TbRefereeBo bo, PageQuery pageQuery) {
        return iTbRefereeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出裁判员管理列表
     */
    @SaCheckPermission("people:referee:export")
    @Log(title = "裁判员管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TbRefereeBo bo, HttpServletResponse response) {
        List<TbRefereeVo> list = iTbRefereeService.queryList(bo);
        ExcelUtil.exportExcel(list, "裁判员管理", TbRefereeVo.class, response);
    }

    /**
     * 获取裁判员管理详细信息
     *
     * @param refereeId 主键
     */
    @SaCheckPermission("people:referee:query")
    @GetMapping("/{refereeId}")
    public R<TbRefereeVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long refereeId) {
        return R.ok(iTbRefereeService.queryById(refereeId));
    }

    /**
     * 新增裁判员管理
     */
    @SaCheckPermission("people:referee:add")
    @Log(title = "裁判员管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TbRefereeBo bo) {
        return toAjax(iTbRefereeService.insertByBo(bo));
    }

    /**
     * 修改裁判员管理
     */
    @SaCheckPermission("people:referee:edit")
    @Log(title = "裁判员管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TbRefereeBo bo) {
        return toAjax(iTbRefereeService.updateByBo(bo));
    }

    /**
     * 删除裁判员管理
     *
     * @param refereeIds 主键串
     */
    @SaCheckPermission("people:referee:remove")
    @Log(title = "裁判员管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{refereeIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] refereeIds) {
        return toAjax(iTbRefereeService.deleteWithValidByIds(Arrays.asList(refereeIds), true));
    }
}
