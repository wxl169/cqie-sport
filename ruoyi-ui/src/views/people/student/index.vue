<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="班级id" prop="classId">
        <el-input
          v-model="queryParams.classId"
          placeholder="请输入班级id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学院id" prop="collegeId">
        <el-input
          v-model="queryParams.collegeId"
          placeholder="请输入学院id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否为运动员 0：不是，1：是" prop="isAthlete">
        <el-input
          v-model="queryParams.isAthlete"
          placeholder="请输入是否为运动员 0：不是，1：是"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学号" prop="studentNumber">
        <el-input
          v-model="queryParams.studentNumber"
          placeholder="请输入学号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="性别 1：男，0：女" prop="gender">
        <el-input
          v-model="queryParams.gender"
          placeholder="请输入性别 1：男，0：女"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证号 18位号码" prop="idnumber">
        <el-input
          v-model="queryParams.idnumber"
          placeholder="请输入身份证号 18位号码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="phoneNumber">
        <el-input
          v-model="queryParams.phoneNumber"
          placeholder="请输入联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="出生日期" prop="birthday">
        <el-date-picker clearable
          v-model="queryParams.birthday"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择出生日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="运动会信用 默认100，若出现多次退选，舞弊等行为，扣除一定分数" prop="creditScore">
        <el-input
          v-model="queryParams.creditScore"
          placeholder="请输入运动会信用 默认100，若出现多次退选，舞弊等行为，扣除一定分数"
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
          v-hasPermi="['people:student:add']"
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
          v-hasPermi="['people:student:edit']"
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
          v-hasPermi="['people:student:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['people:student:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="studentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="学生id 学生表的主键" align="center" prop="studentId" v-if="true"/>
      <el-table-column label="班级id" align="center" prop="classId" />
      <el-table-column label="学院id" align="center" prop="collegeId" />
      <el-table-column label="是否为运动员 0：不是，1：是" align="center" prop="isAthlete" />
      <el-table-column label="学号" align="center" prop="studentNumber" />
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="性别 1：男，0：女" align="center" prop="gender" />
      <el-table-column label="身份证号 18位号码" align="center" prop="idnumber" />
      <el-table-column label="联系电话" align="center" prop="phoneNumber" />
      <el-table-column label="出生日期" align="center" prop="birthday" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.birthday, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="运动会信用 默认100，若出现多次退选，舞弊等行为，扣除一定分数" align="center" prop="creditScore" />
      <el-table-column label="其他 备用字段" align="center" prop="other" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['people:student:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['people:student:remove']"
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

    <!-- 添加或修改学生管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="班级id" prop="classId">
          <el-input v-model="form.classId" placeholder="请输入班级id" />
        </el-form-item>
        <el-form-item label="学院id" prop="collegeId">
          <el-input v-model="form.collegeId" placeholder="请输入学院id" />
        </el-form-item>
        <el-form-item label="是否为运动员 0：不是，1：是" prop="isAthlete">
          <el-input v-model="form.isAthlete" placeholder="请输入是否为运动员 0：不是，1：是" />
        </el-form-item>
        <el-form-item label="学号" prop="studentNumber">
          <el-input v-model="form.studentNumber" placeholder="请输入学号" />
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="性别 1：男，0：女" prop="gender">
          <el-input v-model="form.gender" placeholder="请输入性别 1：男，0：女" />
        </el-form-item>
        <el-form-item label="身份证号 18位号码" prop="idnumber">
          <el-input v-model="form.idnumber" placeholder="请输入身份证号 18位号码" />
        </el-form-item>
        <el-form-item label="联系电话" prop="phoneNumber">
          <el-input v-model="form.phoneNumber" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="出生日期" prop="birthday">
          <el-date-picker clearable
            v-model="form.birthday"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择出生日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="运动会信用 默认100，若出现多次退选，舞弊等行为，扣除一定分数" prop="creditScore">
          <el-input v-model="form.creditScore" placeholder="请输入运动会信用 默认100，若出现多次退选，舞弊等行为，扣除一定分数" />
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
import { listStudent, getStudent, delStudent, addStudent, updateStudent } from "@/api/people/student";

export default {
  name: "Student",
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
      // 学生管理表格数据
      studentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        classId: undefined,
        collegeId: undefined,
        isAthlete: undefined,
        studentNumber: undefined,
        name: undefined,
        gender: undefined,
        idnumber: undefined,
        phoneNumber: undefined,
        birthday: undefined,
        creditScore: undefined,
        other: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        studentId: [
          { required: true, message: "学生id 学生表的主键不能为空", trigger: "blur" }
        ],
        classId: [
          { required: true, message: "班级id不能为空", trigger: "blur" }
        ],
        collegeId: [
          { required: true, message: "学院id不能为空", trigger: "blur" }
        ],
        isAthlete: [
          { required: true, message: "是否为运动员 0：不是，1：是不能为空", trigger: "blur" }
        ],
        studentNumber: [
          { required: true, message: "学号不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
        gender: [
          { required: true, message: "性别 1：男，0：女不能为空", trigger: "blur" }
        ],
        idnumber: [
          { required: true, message: "身份证号 18位号码不能为空", trigger: "blur" }
        ],
        phoneNumber: [
          { required: true, message: "联系电话不能为空", trigger: "blur" }
        ],
        birthday: [
          { required: true, message: "出生日期不能为空", trigger: "blur" }
        ],
        creditScore: [
          { required: true, message: "运动会信用 默认100，若出现多次退选，舞弊等行为，扣除一定分数不能为空", trigger: "blur" }
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
    /** 查询学生管理列表 */
    getList() {
      this.loading = true;
      listStudent(this.queryParams).then(response => {
        this.studentList = response.rows;
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
        studentId: undefined,
        classId: undefined,
        collegeId: undefined,
        isAthlete: undefined,
        studentNumber: undefined,
        name: undefined,
        gender: undefined,
        idnumber: undefined,
        phoneNumber: undefined,
        birthday: undefined,
        creditScore: undefined,
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
      this.ids = selection.map(item => item.studentId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加学生管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.loading = true;
      this.reset();
      const studentId = row.studentId || this.ids
      getStudent(studentId).then(response => {
        this.loading = false;
        this.form = response.data;
        this.open = true;
        this.title = "修改学生管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.buttonLoading = true;
          if (this.form.studentId != null) {
            updateStudent(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.buttonLoading = false;
            });
          } else {
            addStudent(this.form).then(response => {
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
      const studentIds = row.studentId || this.ids;
      this.$modal.confirm('是否确认删除学生管理编号为"' + studentIds + '"的数据项？').then(() => {
        this.loading = true;
        return delStudent(studentIds);
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
      this.download('people/student/export', {
        ...this.queryParams
      }, `student_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
