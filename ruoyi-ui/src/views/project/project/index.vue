<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入项目名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="招收人数 / 队数 根据比赛类型字段判断人数 / 队数" prop="upnum">
        <el-input
          v-model="queryParams.upnum"
          placeholder="请输入招收人数 / 队数 根据比赛类型字段判断人数 / 队数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="裁判员数量" prop="renum">
        <el-input
          v-model="queryParams.renum"
          placeholder="请输入裁判员数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否删除" prop="isCancel">
        <el-input
          v-model="queryParams.isCancel"
          placeholder="请输入是否删除"
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
          v-hasPermi="['project:project:add']"
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
          v-hasPermi="['project:project:edit']"
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
          v-hasPermi="['project:project:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['project:project:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="projectList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="项目id 项目表的主键" align="center" prop="projectId" v-if="false"/>
      <el-table-column label="项目编号" align="center" prop="number" />
      <el-table-column label="项目名称" align="center" prop="name" />
      <el-table-column label="比赛类型 0：个人，1：团体" align="center" prop="type" />
      <el-table-column label="男子/女子赛 0：女子，1：男子" align="center" prop="gtype" />
      <el-table-column label="招收人数 / 队数 根据比赛类型字段判断人数 / 队数" align="center" prop="upnum" />
      <el-table-column label="裁判员数量" align="center" prop="renum" />
      <el-table-column label="是否删除" align="center" prop="isCancel" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['project:project:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['project:project:remove']"
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

    <!-- 添加或修改项目管理 对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目编号" prop="number">
          <el-input v-model="form.number" placeholder="请输入项目编号" />
        </el-form-item>
        <el-form-item label="项目名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="规则介绍" prop="introduction">
          <el-input v-model="form.introduction" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="招收人数 / 队数 根据比赛类型字段判断人数 / 队数" prop="upnum">
          <el-input v-model="form.upnum" placeholder="请输入招收人数 / 队数 根据比赛类型字段判断人数 / 队数" />
        </el-form-item>
        <el-form-item label="裁判员数量" prop="renum">
          <el-input v-model="form.renum" placeholder="请输入裁判员数量" />
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
import { listProject, getProject, delProject, addProject, updateProject } from "@/api/project/project";

export default {
  name: "Project",
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
      // 项目管理 表格数据
      projectList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: undefined,
        type: undefined,
        gtype: undefined,
        upnum: undefined,
        renum: undefined,
        isCancel: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        projectId: [
          { required: true, message: "项目id 项目表的主键不能为空", trigger: "blur" }
        ],
        number: [
          { required: true, message: "项目编号不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "项目名称不能为空", trigger: "blur" }
        ],
        introduction: [
          { required: true, message: "规则介绍不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "比赛类型 0：个人，1：团体不能为空", trigger: "change" }
        ],
        gtype: [
          { required: true, message: "男子/女子赛 0：女子，1：男子不能为空", trigger: "change" }
        ],
        upnum: [
          { required: true, message: "招收人数 / 队数 根据比赛类型字段判断人数 / 队数不能为空", trigger: "blur" }
        ],
        renum: [
          { required: true, message: "裁判员数量不能为空", trigger: "blur" }
        ],
        isCancel: [
          { required: true, message: "是否删除不能为空", trigger: "blur" }
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
    /** 查询项目管理 列表 */
    getList() {
      this.loading = true;
      listProject(this.queryParams).then(response => {
        this.projectList = response.rows;
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
        projectId: undefined,
        number: undefined,
        name: undefined,
        introduction: undefined,
        type: undefined,
        gtype: undefined,
        upnum: undefined,
        renum: undefined,
        isCancel: undefined,
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
      this.ids = selection.map(item => item.projectId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加项目管理 ";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.loading = true;
      this.reset();
      const projectId = row.projectId || this.ids
      getProject(projectId).then(response => {
        this.loading = false;
        this.form = response.data;
        this.open = true;
        this.title = "修改项目管理 ";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.buttonLoading = true;
          if (this.form.projectId != null) {
            updateProject(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.buttonLoading = false;
            });
          } else {
            addProject(this.form).then(response => {
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
      const projectIds = row.projectId || this.ids;
      this.$modal.confirm('是否确认删除项目管理 编号为"' + projectIds + '"的数据项？').then(() => {
        this.loading = true;
        return delProject(projectIds);
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
      this.download('project/project/export', {
        ...this.queryParams
      }, `project_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
