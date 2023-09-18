<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目名称" prop="projectId">
        <el-select v-model="form.projectId" placeholder="请选择项目名称" clearable>
          <el-option
            v-for="dict in dict.type.project_name"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="裁判员id" prop="refereeId">
        <el-input
          v-model="queryParams.refereeId"
          placeholder="请输入裁判员id"
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
      <el-form-item label="安排信息单元id" prop="infoId">
        <el-input
          v-model="queryParams.infoId"
          placeholder="请输入安排信息单元id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类别" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择类别" clearable>
          <el-option
            v-for="dict in dict.type.match_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['match:arrangement:add']"
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
          v-hasPermi="['match:arrangement:edit']"
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
          v-hasPermi="['match:arrangement:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['match:arrangement:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="arrangementList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="安排id" align="center" prop="arrangementId" v-if="true"/>
      <el-table-column label="项目名称" align="center" prop="projectId">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.project_name" :value="scope.row.projectId"/>
        </template>
      </el-table-column>
      <el-table-column label="裁判员id" align="center" prop="refereeId" />
      <el-table-column label="运动员id / 团体id" align="center" prop="typeId" />
      <el-table-column label="安排信息单元id" align="center" prop="infoId" />
      <el-table-column label="类别" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.match_type" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="是否取消" align="center" prop="isCancel">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.is_cancel" :value="scope.row.isCancel"/>
        </template>
      </el-table-column>
      <el-table-column label="取消原因" align="center" prop="reason" />
      <el-table-column label="其他备用字段" align="center" prop="other" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['match:arrangement:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['match:arrangement:remove']"
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

    <!-- 添加或修改安排 对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目名称" prop="projectId">
          <el-select v-model="form.projectId" placeholder="请选择项目名称">
            <el-option
              v-for="dict in dict.type.project_name"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="裁判员id" prop="refereeId">
          <el-input v-model="form.refereeId" placeholder="请输入裁判员id" />
        </el-form-item>
        <el-form-item label="运动员id / 团体id" prop="typeId">
          <el-input v-model="form.typeId" placeholder="请输入运动员id / 团体id" />
        </el-form-item>
        <el-form-item label="安排信息单元id" prop="infoId">
          <el-input v-model="form.infoId" placeholder="请输入安排信息单元id" />
        </el-form-item>
        <el-form-item label="类别" prop="type">
          <el-select v-model="form.type" placeholder="请选择类别">
            <el-option
              v-for="dict in dict.type.match_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否取消 0：取消，1：未取消" prop="isCancel">
          <el-select v-model="form.isCancel" placeholder="请选择是否取消 0：取消，1：未取消">
            <el-option
              v-for="dict in dict.type.is_cancel"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="取消原因 若未取消则为NULL" prop="reason">
          <el-input v-model="form.reason" placeholder="请输入取消原因 若未取消则为NULL" />
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
import { listArrangement, getArrangement, delArrangement, addArrangement, updateArrangement } from "@/api/match/arrangement";

export default {
  name: "Arrangement",
  dicts: ['is_cancel', 'project_name', 'match_type'],
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
      // 安排 表格数据
      arrangementList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectId: undefined,
        refereeId: undefined,
        typeId: undefined,
        infoId: undefined,
        type: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        projectId: [
          { required: true, message: "项目名称不能为空", trigger: "change" }
        ],
        refereeId: [
          { required: true, message: "裁判员id不能为空", trigger: "blur" }
        ],
        typeId: [
          { required: true, message: "运动员id / 团体id不能为空", trigger: "blur" }
        ],
        infoId: [
          { required: true, message: "安排信息单元id不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "类别不能为空", trigger: "change" }
        ],
        isCancel: [
          { required: true, message: "是否取消 0：取消，1：未取消不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询安排 列表 */
    getList() {
      this.loading = true;
      listArrangement(this.queryParams).then(response => {
        this.arrangementList = response.rows;
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
        arrangementId: undefined,
        projectId: undefined,
        refereeId: undefined,
        typeId: undefined,
        infoId: undefined,
        type: undefined,
        isCancel: undefined,
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
      this.ids = selection.map(item => item.arrangementId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加安排 ";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.loading = true;
      this.reset();
      const arrangementId = row.arrangementId || this.ids
      getArrangement(arrangementId).then(response => {
        this.loading = false;
        this.form = response.data;
        this.open = true;
        this.title = "修改安排 ";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.buttonLoading = true;
          if (this.form.arrangementId != null) {
            updateArrangement(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.buttonLoading = false;
            });
          } else {
            addArrangement(this.form).then(response => {
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
      const arrangementIds = row.arrangementId || this.ids;
      this.$modal.confirm('是否确认删除安排 编号为"' + arrangementIds + '"的数据项？').then(() => {
        this.loading = true;
        return delArrangement(arrangementIds);
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
      this.download('match/arrangement/export', {
        ...this.queryParams
      }, `arrangement_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
