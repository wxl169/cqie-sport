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
import com.ruoyi.people.domain.vo.TbUserVo;
import com.ruoyi.people.domain.bo.TbUserBo;
import com.ruoyi.people.service.ITbUserService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户管理
 *
 * @author ruoyi
 * @date 2023-09-15
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/people/user")
public class TbUserController extends BaseController {

    private final ITbUserService iTbUserService;

    /**
     * 查询用户管理列表
     */
    @SaCheckPermission("people:user:list")
    @GetMapping("/list")
    public TableDataInfo<TbUserVo> list(TbUserBo bo, PageQuery pageQuery) {
        return iTbUserService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出用户管理列表
     */
    @SaCheckPermission("people:user:export")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TbUserBo bo, HttpServletResponse response) {
        List<TbUserVo> list = iTbUserService.queryList(bo);
        ExcelUtil.exportExcel(list, "用户管理", TbUserVo.class, response);
    }

    /**
     * 获取用户管理详细信息
     *
     * @param userId 主键
     */
    @SaCheckPermission("people:user:query")
    @GetMapping("/{userId}")
    public R<TbUserVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long userId) {
        return R.ok(iTbUserService.queryById(userId));
    }

    /**
     * 新增用户管理
     */
    @SaCheckPermission("people:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TbUserBo bo) {
        return toAjax(iTbUserService.insertByBo(bo));
    }

    /**
     * 修改用户管理
     */
    @SaCheckPermission("people:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TbUserBo bo) {
        return toAjax(iTbUserService.updateByBo(bo));
    }

    /**
     * 删除用户管理
     *
     * @param userIds 主键串
     */
    @SaCheckPermission("people:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] userIds) {
        return toAjax(iTbUserService.deleteWithValidByIds(Arrays.asList(userIds), true));
    }
}
