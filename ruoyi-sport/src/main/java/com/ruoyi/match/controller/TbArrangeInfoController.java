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
import com.ruoyi.match.domain.vo.TbArrangeInfoVo;
import com.ruoyi.match.domain.bo.TbArrangeInfoBo;
import com.ruoyi.match.service.ITbArrangeInfoService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 安排信息单元
 *
 * @author ruoyi
 * @date 2023-09-13
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/match/arrangeInfo")
public class TbArrangeInfoController extends BaseController {

    private final ITbArrangeInfoService iTbArrangeInfoService;

    /**
     * 查询安排信息单元 列表
     */
    @SaCheckPermission("match:arrangeInfo:list")
    @GetMapping("/list")
    public TableDataInfo<TbArrangeInfoVo> list(TbArrangeInfoBo bo, PageQuery pageQuery) {
        return iTbArrangeInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出安排信息单元 列表
     */
    @SaCheckPermission("match:arrangeInfo:export")
    @Log(title = "安排信息单元 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TbArrangeInfoBo bo, HttpServletResponse response) {
        List<TbArrangeInfoVo> list = iTbArrangeInfoService.queryList(bo);
        ExcelUtil.exportExcel(list, "安排信息单元 ", TbArrangeInfoVo.class, response);
    }

    /**
     * 获取安排信息单元 详细信息
     *
     * @param arrangeInfoId 主键
     */
    @SaCheckPermission("match:arrangeInfo:query")
    @GetMapping("/{arrangeInfoId}")
    public R<TbArrangeInfoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long arrangeInfoId) {
        return R.ok(iTbArrangeInfoService.queryById(arrangeInfoId));
    }

    /**
     * 新增安排信息单元
     */
    @SaCheckPermission("match:arrangeInfo:add")
    @Log(title = "安排信息单元 ", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TbArrangeInfoBo bo) {
        return toAjax(iTbArrangeInfoService.insertByBo(bo));
    }

    /**
     * 修改安排信息单元
     */
    @SaCheckPermission("match:arrangeInfo:edit")
    @Log(title = "安排信息单元 ", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TbArrangeInfoBo bo) {
        return toAjax(iTbArrangeInfoService.updateByBo(bo));
    }

    /**
     * 删除安排信息单元
     *
     * @param arrangeInfoIds 主键串
     */
    @SaCheckPermission("match:arrangeInfo:remove")
    @Log(title = "安排信息单元 ", businessType = BusinessType.DELETE)
    @DeleteMapping("/{arrangeInfoIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] arrangeInfoIds) {
        return toAjax(iTbArrangeInfoService.deleteWithValidByIds(Arrays.asList(arrangeInfoIds), true));
    }

    //测试分支提交
}
