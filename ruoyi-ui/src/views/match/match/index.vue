<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="安排id" prop="arrangementId">
        <el-input
          v-model="queryParams.arrangementId"
          placeholder="请输入安排id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="运动员id / 团体id" prop="typeId">
        <el-input
          v-model="queryParams.typeId"
          placeholder="请输入运动员id / 团体id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="比赛结果" prop="result">
        <el-input
          v-model="queryParams.result"
          placeholder="请输入比赛结果"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="成绩" prop="score">
        <el-input
          v-model="queryParams.score"
          placeholder="请输入成绩"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="成绩是否有效 0：无效，1：有效" prop="isEffective">
        <el-input
          v-model="queryParams.isEffective"
          placeholder="请输入成绩是否有效 0：无效，1：有效"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="成绩无效原因 若有效则为NULL" prop="reason">
        <el-input
          v-model="queryParams.reason"
          placeholder="请输入成绩无效原因 若有效则为NULL"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="其他 备用字段" prop="other">
        <el-input
          v-model="queryParams.other"
          placeholder="请输入其他 备用字段"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['match:match:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['match:match:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['match:match:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['match:match:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="matchList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="比赛id 比赛表的主键" align="center" prop="competitionId" v-if="true"/>
      <el-table-column label="安排id" align="center" prop="arrangementId" />
      <el-table-column label="运动员id / 团体id" align="center" prop="typeId" />
      <el-table-column label="类别 0：个人赛，1：团体赛" align="center" prop="type" />
      <el-table-column label="比赛结果" align="center" prop="result" />
      <el-table-column label="成绩" align="center" prop="score" />
      <el-table-column label="成绩是否有效 0：无效，1：有效" align="center" prop="isEffective" />
      <el-table-column label="成绩无效原因 若有效则为NULL" align="center" prop="reason" />
      <el-table-column label="其他 备用字段" align="center" prop="other" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['match:match:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['match:match:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改比赛记录 对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="安排id" prop="arrangementId">
          <el-input v-model="form.arrangementId" placeholder="请输入安排id" />
        </el-form-item>
        <el-form-item label="运动员id / 团体id" prop="typeId">
          <el-input v-model="form.typeId" placeholder="请输入运动员id / 团体id" />
        </el-form-item>
        <el-form-item label="比赛结果" prop="result">
          <el-input v-model="form.result" placeholder="请输入比赛结果" />
        </el-form-item>
        <el-form-item label="成绩" prop="score">
          <el-input v-model="form.score" placeholder="请输入成绩" />
        </el-form-item>
        <el-form-item label="成绩是否有效 0：无效，1：有效" prop="isEffective">
          <el-input v-model="form.isEffective" placeholder="请输入成绩是否有效 0：无效，1：有效" />
        </el-form-item>
        <el-form-item label="成绩无效原因 若有效则为NULL" prop="reason">
          <el-input v-model="form.reason" placeholder="请输入成绩无效原因 若有效则为NULL" />
        </el-form-item>
        <el-form-item label="其他 备用字段" prop="other">
          <el-input v-model="form.other" placeholder="请输入其他 备用字段" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :loading="buttonLoading" type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMatch, getMatch, delMatch, addMatch, updateMatch } from "@/api/match/match";

export default {
  name: "Match",
  data() {
    return {
      // 按钮loading
      buttonLoading: false,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 比赛记录 表格数据
      matchList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        arrangementId: undefined,
        typeId: undefined,
        type: undefined,
        result: undefined,
        score: undefined,
        isEffective: undefined,
        reason: undefined,
        other: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        competitionId: [
          { required: true, message: "比赛id 比赛表的主键不能为空", trigger: "blur" }
        ],
        arrangementId: [
          { required: true, message: "安排id不能为空", trigger: "blur" }
        ],
        typeId: [
          { required: true, message: "运动员id / 团体id不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "类别 0：个人赛，1：团体赛不能为空", trigger: "change" }
        ],
        result: [
          { required: true, message: "比赛结果不能为空", trigger: "blur" }
        ],
        score: [
          { required: true, message: "成绩不能为空", trigger: "blur" }
        ],
        isEffective: [
          { required: true, message: "成绩是否有效 0：无效，1：有效不能为空", trigger: "blur" }
        ],
        reason: [
          { required: true, message: "成绩无效原因 若有效则为NULL不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "录入时间不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "更新时间不能为空", trigger: "blur" }
        ],
        other: [
          { required: true, message: "其他 备用字段不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询比赛记录 列表 */
    getList() {
      this.loading = true;
      listMatch(this.queryParams).then(response => {
        this.matchList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        competitionId: undefined,
        arrangementId: undefined,
        typeId: undefined,
        type: undefined,
        result: undefined,
        score: undefined,
        isEffective: undefined,
        reason: undefined,
        createTime: undefined,
        updateTime: undefined,
        other: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.competitionId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加比赛记录 ";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.loading = true;
      this.reset();
      const competitionId = row.competitionId || this.ids
      getMatch(competitionId).then(response => {
        this.loading = false;
        this.form = response.data;
        this.open = true;
        this.title = "修改比赛记录 ";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.buttonLoading = true;
          if (this.form.competitionId != null) {
            updateMatch(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.buttonLoading = false;
            });
          } else {
            addMatch(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.buttonLoading = false;
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const competitionIds = row.competitionId || this.ids;
      this.$modal.confirm('是否确认删除比赛记录 编号为"' + competitionIds + '"的数据项？').then(() => {
        this.loading = true;
        return delMatch(competitionIds);
      }).then(() => {
        this.loading = false;
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      }).finally(() => {
        this.loading = false;
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('match/match/export', {
        ...this.queryParams
      }, `match_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
