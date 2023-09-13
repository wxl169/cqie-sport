package com.ruoyi.match.controller;

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
import com.ruoyi.match.domain.vo.TbCompetitionVo;
import com.ruoyi.match.domain.bo.TbCompetitionBo;
import com.ruoyi.match.service.ITbCompetitionService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 比赛记录 
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/match/match")
public class TbCompetitionController extends BaseController {

    private final ITbCompetitionService iTbCompetitionService;

    /**
     * 查询比赛记录 列表
     */
    @SaCheckPermission("match:match:list")
    @GetMapping("/list")
    public TableDataInfo<TbCompetitionVo> list(TbCompetitionBo bo, PageQuery pageQuery) {
        return iTbCompetitionService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出比赛记录 列表
     */
    @SaCheckPermission("match:match:export")
    @Log(title = "比赛记录 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TbCompetitionBo bo, HttpServletResponse response) {
        List<TbCompetitionVo> list = iTbCompetitionService.queryList(bo);
        ExcelUtil.exportExcel(list, "比赛记录 ", TbCompetitionVo.class, response);
    }

    /**
     * 获取比赛记录 详细信息
     *
     * @param competitionId 主键
     */
    @SaCheckPermission("match:match:query")
    @GetMapping("/{competitionId}")
    public R<TbCompetitionVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long competitionId) {
        return R.ok(iTbCompetitionService.queryById(competitionId));
    }

    /**
     * 新增比赛记录 
     */
    @SaCheckPermission("match:match:add")
    @Log(title = "比赛记录 ", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TbCompetitionBo bo) {
        return toAjax(iTbCompetitionService.insertByBo(bo));
    }

    /**
     * 修改比赛记录 
     */
    @SaCheckPermission("match:match:edit")
    @Log(title = "比赛记录 ", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TbCompetitionBo bo) {
        return toAjax(iTbCompetitionService.updateByBo(bo));
    }

    /**
     * 删除比赛记录 
     *
     * @param competitionIds 主键串
     */
    @SaCheckPermission("match:match:remove")
    @Log(title = "比赛记录 ", businessType = BusinessType.DELETE)
    @DeleteMapping("/{competitionIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] competitionIds) {
        return toAjax(iTbCompetitionService.deleteWithValidByIds(Arrays.asList(competitionIds), true));
    }
}
