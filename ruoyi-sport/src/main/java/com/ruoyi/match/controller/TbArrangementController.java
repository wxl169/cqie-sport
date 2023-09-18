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
import com.ruoyi.match.domain.vo.TbArrangementVo;
import com.ruoyi.match.domain.bo.TbArrangementBo;
import com.ruoyi.match.service.ITbArrangementService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 安排
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/match/arrangement")
public class TbArrangementController extends BaseController {

    private final ITbArrangementService iTbArrangementService;

    /**
     * 查询安排 列表
     */
    @SaCheckPermission("match:arrangement:list")
    @GetMapping("/list")
    public TableDataInfo<TbArrangementVo> list(TbArrangementBo bo, PageQuery pageQuery) {
        return iTbArrangementService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出安排 列表
     */
    @SaCheckPermission("match:arrangement:export")
    @Log(title = "安排 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TbArrangementBo bo, HttpServletResponse response) {
        List<TbArrangementVo> list = iTbArrangementService.queryList(bo);
        ExcelUtil.exportExcel(list, "安排 ", TbArrangementVo.class, response);
    }

    /**
     * 获取安排 详细信息
     *
     * @param arrangementId 主键
     */
    @SaCheckPermission("match:arrangement:query")
    @GetMapping("/{arrangementId}")
    public R<TbArrangementVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long arrangementId) {
        return R.ok(iTbArrangementService.queryById(arrangementId));
    }

    /**
     * 新增安排
     */
    @SaCheckPermission("match:arrangement:add")
    @Log(title = "安排 ", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TbArrangementBo bo) {
        return toAjax(iTbArrangementService.insertByBo(bo));
    }

    /**
     * 修改安排
     */
    @SaCheckPermission("match:arrangement:edit")
    @Log(title = "安排 ", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TbArrangementBo bo) {
        return toAjax(iTbArrangementService.updateByBo(bo));
    }

    /**
     * 删除安排
     *
     * @param arrangementIds 主键串
     */
    @SaCheckPermission("match:arrangement:remove")
    @Log(title = "安排 ", businessType = BusinessType.DELETE)
    @DeleteMapping("/{arrangementIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] arrangementIds) {
        return toAjax(iTbArrangementService.deleteWithValidByIds(Arrays.asList(arrangementIds), true));
    }
}
