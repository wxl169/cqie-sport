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
import com.ruoyi.people.domain.vo.TbTeamVo;
import com.ruoyi.people.domain.bo.TbTeamBo;
import com.ruoyi.people.service.ITbTeamService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 团体管理
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/people/team")
public class TbTeamController extends BaseController {

    private final ITbTeamService iTbTeamService;

    /**
     * 查询团体管理列表
     */
    @SaCheckPermission("people:team:list")
    @GetMapping("/list")
    public TableDataInfo<TbTeamVo> list(TbTeamBo bo, PageQuery pageQuery) {
        return iTbTeamService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出团体管理列表
     */
    @SaCheckPermission("people:team:export")
    @Log(title = "团体管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TbTeamBo bo, HttpServletResponse response) {
        List<TbTeamVo> list = iTbTeamService.queryList(bo);
        ExcelUtil.exportExcel(list, "团体管理", TbTeamVo.class, response);
    }

    /**
     * 获取团体管理详细信息
     *
     * @param teamId 主键
     */
    @SaCheckPermission("people:team:query")
    @GetMapping("/{teamId}")
    public R<TbTeamVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long teamId) {
        return R.ok(iTbTeamService.queryById(teamId));
    }

    /**
     * 新增团体管理
     */
    @SaCheckPermission("people:team:add")
    @Log(title = "团体管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TbTeamBo bo) {
        return toAjax(iTbTeamService.insertByBo(bo));
    }

    /**
     * 修改团体管理
     */
    @SaCheckPermission("people:team:edit")
    @Log(title = "团体管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TbTeamBo bo) {
        return toAjax(iTbTeamService.updateByBo(bo));
    }

    /**
     * 删除团体管理
     *
     * @param teamIds 主键串
     */
    @SaCheckPermission("people:team:remove")
    @Log(title = "团体管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{teamIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] teamIds) {
        return toAjax(iTbTeamService.deleteWithValidByIds(Arrays.asList(teamIds), true));
    }
}
