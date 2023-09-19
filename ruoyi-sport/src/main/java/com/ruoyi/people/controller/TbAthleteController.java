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
import com.ruoyi.people.domain.vo.TbAthleteVo;
import com.ruoyi.people.domain.bo.TbAthleteBo;
import com.ruoyi.people.service.ITbAthleteService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 运动员管理 
 *
 * @author ruoyi
 * @date 2023-09-19
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/people/athletes")
public class TbAthleteController extends BaseController {

    private final ITbAthleteService iTbAthleteService;

    /**
     * 查询运动员管理 列表
     */
    @SaCheckPermission("people:athletes:list")
    @GetMapping("/list")
    public TableDataInfo<TbAthleteVo> list(TbAthleteBo bo, PageQuery pageQuery) {
        return iTbAthleteService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出运动员管理 列表
     */
    @SaCheckPermission("people:athletes:export")
    @Log(title = "运动员管理 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TbAthleteBo bo, HttpServletResponse response) {
        List<TbAthleteVo> list = iTbAthleteService.queryList(bo);
        ExcelUtil.exportExcel(list, "运动员管理 ", TbAthleteVo.class, response);
    }

    /**
     * 获取运动员管理 详细信息
     *
     * @param athleteId 主键
     */
    @SaCheckPermission("people:athletes:query")
    @GetMapping("/{athleteId}")
    public R<TbAthleteVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long athleteId) {
        return R.ok(iTbAthleteService.queryById(athleteId));
    }

    /**
     * 新增运动员管理 
     */
    @SaCheckPermission("people:athletes:add")
    @Log(title = "运动员管理 ", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TbAthleteBo bo) {
        return toAjax(iTbAthleteService.insertByBo(bo));
    }

    /**
     * 修改运动员管理 
     */
    @SaCheckPermission("people:athletes:edit")
    @Log(title = "运动员管理 ", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TbAthleteBo bo) {
        return toAjax(iTbAthleteService.updateByBo(bo));
    }

    /**
     * 删除运动员管理 
     *
     * @param athleteIds 主键串
     */
    @SaCheckPermission("people:athletes:remove")
    @Log(title = "运动员管理 ", businessType = BusinessType.DELETE)
    @DeleteMapping("/{athleteIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] athleteIds) {
        return toAjax(iTbAthleteService.deleteWithValidByIds(Arrays.asList(athleteIds), true));
    }
}
