<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>参数列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <!-- 警告区域 -->
      <el-alert show-icon title="注意：只允许为第三级分类设置相关参数！" type="warning" :closable="false"></el-alert>

      <!-- 选择商品分类区域 -->
      <el-row class="cat_opt">
        <el-col>
          <span>选择商品分类：</span>
          <!-- 选择商品分类的级联选择框 -->
          <el-cascader
            expand-trigger="hover"
            :options="catelist"
            :props="cateProps"
            v-model="selectedCateKeys"
            @change="handleChange"
          ></el-cascader>
        </el-col>
      </el-row>

      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="动态参数" name="many">
          <el-button
            type="primary"
            size="mini"
            :disabled="isBtnDisabled"
            @click="addDialogVisible=true"
          >添加参数</el-button>
          <!-- 动态参数表格 -->
          <el-table :data="manylist" border stripe>
            <!-- 展开行 -->
            <el-table-column type="expand">
              <template slot-scope="scope">
                <!-- 循环渲染Tag标签 -->
                <el-tag v-for="(item, i) in scope.row.attr_vals" :key="i" closable @close="handleClose(i, scope.row)">{{item}}</el-tag>
                <!-- 输入的文本框 -->
                <el-input
                  class="input-new-tag"
                  v-if="scope.row.inputVisible"
                  v-model="scope.row.inputValue"
                  ref="saveTagInput"
                  size="small"
                  @keyup.enter.native="handleInputConfirm(scope.row)"
                  @blur="handleInputConfirm(scope.row)"
                ></el-input>
                <!-- 添加按钮 -->
                <el-button
                  v-else
                  class="button-new-tag"
                  size="small"
                  @click="showInput(scope.row)"
                >+ New Tag</el-button>
              </template>
            </el-table-column>
            <!-- 索引列 -->
            <el-table-column type="index"></el-table-column>
            <el-table-column label="参数名称" prop="attr_name"></el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="primary"
                  icon="el-icon-edit"
                  @click="showEditDialog(scope.row.attr_id)"
                >编辑</el-button>
                <el-button
                  size="mini"
                  type="danger"
                  icon="el-icon-delete"
                  @click="removeParams(scope.row.attr_id)"
                >删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="静态属性" name="only">
          <el-button
            type="primary"
            size="mini"
            :disabled="isBtnDisabled"
            @click="addDialogVisible=true"
          >添加属性</el-button>
          <el-table :data="manylist" border stripe>
            <!-- 展开行 -->
            <el-table-column type="expand">
              <template slot-scope="scope">
                <!-- 循环渲染Tag标签 -->
                <el-tag v-for="(item, i) in scope.row.attr_vals" :key="i" closable @close="handleClose(i, scope.row)">{{item}}</el-tag>
                <!-- 输入的文本框 -->
                <el-input
                  class="input-new-tag"
                  v-if="scope.row.inputVisible"
                  v-model="scope.row.inputValue"
                  ref="saveTagInput"
                  size="small"
                  @keyup.enter.native="handleInputConfirm(scope.row)"
                  @blur="handleInputConfirm(scope.row)"
                ></el-input>
                <!-- 添加按钮 -->
                <el-button
                  v-else
                  class="button-new-tag"
                  size="small"
                  @click="showInput(scope.row)"
                >+ New Tag</el-button>
              </template>
           
            </el-table-column>
            <!-- 索引列 -->
            <el-table-column type="index"></el-table-column>
            <el-table-column label="属性名称" prop="attr_name"></el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="primary"
                  icon="el-icon-edit"
                  @click="showEditDialog(scope.row.attr_id)"
                >编辑</el-button>
                <el-button
                  size="mini"
                  type="danger"
                  icon="el-icon-delete"
                  @click="removeParams(scope.row.attr_id)"
                >删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </el-card>

    <!-- 添加参数的对话框 -->
    <el-dialog
      :title="'添加' + titleText"
      :visible.sync="addDialogVisible"
      width="50%"
      @close="addDialogClosed"
    >
      <!-- 添加参数的对话框 -->
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="100px">
        <el-form-item :label="titleText" prop="attr_name">
          <el-input v-model="addForm.attr_name"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addParams">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 修改参数的对话框 -->
    <el-dialog
      :title="'修改' + titleText"
      :visible.sync="editDialogVisible"
      width="50%"
      @close="editDialogClosed"
    >
      <!-- 添加参数的对话框 -->
      <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="100px">
        <el-form-item :label="titleText" prop="attr_name">
          <el-input v-model="editForm.attr_name"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editParams">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      catelist: [
        {
          'cat-id': 1,
          'cat-name': '大家电',
          'cat-pid': '电视',
          'cat-level': 0,
          'cat-deleted': false,
          children: [
            {
              'cat-id': 2,
              'cat-name': '电视',
              'cat-pid': '电视',
              'cat-level': 1,
              'cat-deleted': false,
              children: [
                {
                  'cat-id': 3,
                  'cat-name': '曲面电视',
                  'cat-pid': '电视',
                  'cat-level': 2,
                  'cat-deleted': false
                }
              ]
            }
          ]
        },
        {
          'cat-id': 1,
          'cat-name': '人工智能',
          'cat-pid': '电视',
          'cat-level': 0,
          'cat-deleted': false,
          children: [
            {
              'cat-id': 2,
              'cat-name': '机器人',
              'cat-pid': '电视',
              'cat-level': 1,
              'cat-deleted': false,
              children: [
                {
                  'cat-id': 3,
                  'cat-name': '曲面电视',
                  'cat-pid': '电视',
                  'cat-level': 2,
                  'cat-deleted': false
                }
              ]
            }
          ]
        },
        {
          'cat-id': 1,
          'cat-name': '大家电',
          'cat-pid': '电视',
          'cat-level': 0,
          'cat-deleted': false,
          children: [
            {
              'cat-id': 2,
              'cat-name': '电视',
              'cat-pid': '电视',
              'cat-level': 1,
              'cat-deleted': false,
              children: [
                {
                  'cat-id': 3,
                  'cat-name': '曲面电视',
                  'cat-pid': '电视',
                  'cat-level': 2,
                  'cat-deleted': false
                }
              ]
            }
          ]
        },
        {
          'cat-id': 1,
          'cat-name': '大家电',
          'cat-pid': '电视',
          'cat-level': 0,
          'cat-deleted': false,
          children: [
            {
              'cat-id': 2,
              'cat-name': '电视',
              'cat-pid': '电视',
              'cat-level': 1,
              'cat-deleted': false,
              children: [
                {
                  'cat-id': 3,
                  'cat-name': '曲面电视',
                  'cat-pid': '电视',
                  'cat-level': 2,
                  'cat-deleted': false
                }
              ]
            }
          ],
          children: [
            {
              'cat-id': 2,
              'cat-name': '电视',
              'cat-pid': '电视',
              'cat-level': 1,
              'cat-deleted': false,
              children: [
                {
                  'cat-id': 3,
                  'cat-name': '曲面电视',
                  'cat-pid': '电视',
                  'cat-level': 2,
                  'cat-deleted': false
                }
              ]
            }
          ],
          children: [
            {
              'cat-id': 2,
              'cat-name': '电视',
              'cat-pid': '电视',
              'cat-level': 1,
              'cat-deleted': false,
              children: [
                {
                  'cat-id': 3,
                  'cat-name': '曲面电视',
                  'cat-pid': '电视',
                  'cat-level': 2,
                  'cat-deleted': false
                }
              ]
            }
          ],
          children: [
            {
              'cat-id': 2,
              'cat-name': '电视',
              'cat-pid': '电视',
              'cat-level': 1,
              'cat-deleted': false,
              children: [
                {
                  'cat-id': 3,
                  'cat-name': '曲面电视',
                  'cat-pid': '电视',
                  'cat-level': 2,
                  'cat-deleted': false
                }
              ]
            }
          ]
        },
        {
          'cat-id': 1,
          'cat-name': '大家电',
          'cat-pid': '电视',
          'cat-level': 0,
          'cat-deleted': false,
          children: [
            {
              'cat-id': 2,
              'cat-name': '电视',
              'cat-pid': '电视',
              'cat-level': 1,
              'cat-deleted': false,
              children: [
                {
                  'cat-id': 3,
                  'cat-name': '曲面电视',
                  'cat-pid': '电视',
                  'cat-level': 2,
                  'cat-deleted': false
                }
              ]
            }
          ],
          children: [
            {
              'cat-id': 2,
              'cat-name': '电视',
              'cat-pid': '电视',
              'cat-level': 1,
              'cat-deleted': false,
              children: [
                {
                  'cat-id': 3,
                  'cat-name': '曲面电视',
                  'cat-pid': '电视',
                  'cat-level': 2,
                  'cat-deleted': false
                }
              ],
              children: [
                {
                  'cat-id': 3,
                  'cat-name': '曲面电视',
                  'cat-pid': '电视',
                  'cat-level': 2,
                  'cat-deleted': false
                }
              ],
              children: [
                {
                  'cat-id': 3,
                  'cat-name': '曲面电视',
                  'cat-pid': '电视',
                  'cat-level': 2,
                  'cat-deleted': false
                }
              ],
              children: [
                {
                  'cat-id': 3,
                  'cat-name': '曲面电视',
                  'cat-pid': '电视',
                  'cat-level': 2,
                  'cat-deleted': false
                }
              ]
            }
          ],
          children: [
            {
              'cat-id': 2,
              'cat-name': '电视',
              'cat-pid': '电视',
              'cat-level': 1,
              'cat-deleted': false,
              children: [
                {
                  'cat-id': 3,
                  'cat-name': '曲面电视',
                  'cat-pid': '电视',
                  'cat-level': 2,
                  'cat-deleted': false
                }
              ]
            }
          ],
          children: [
            {
              'cat-id': 2,
              'cat-name': '电视',
              'cat-pid': '电视',
              'cat-level': 1,
              'cat-deleted': false,
              children: [
                {
                  'cat-id': 3,
                  'cat-name': '曲面电视',
                  'cat-pid': '电视',
                  'cat-level': 2,
                  'cat-deleted': false
                }
              ]
            }
          ],
          children: [
            {
              'cat-id': 2,
              'cat-name': '电视',
              'cat-pid': '电视',
              'cat-level': 1,
              'cat-deleted': false,
              children: [
                {
                  'cat-id': 3,
                  'cat-name': '曲面电视',
                  'cat-pid': '电视',
                  'cat-level': 2,
                  'cat-deleted': false
                }
              ],
              children: [
                {
                  'cat-id': 3,
                  'cat-name': '曲面电视',
                  'cat-pid': '电视',
                  'cat-level': 2,
                  'cat-deleted': false
                }
              ],
              children: [
                {
                  'cat-id': 3,
                  'cat-name': '曲面电视',
                  'cat-pid': '电视',
                  'cat-level': 2,
                  'cat-deleted': false
                }
              ],
              children: [
                {
                  'cat-id': 3,
                  'cat-name': '曲面电视',
                  'cat-pid': '电视',
                  'cat-level': 2,
                  'cat-deleted': false
                }
              ]
            }
          ],
          children: [
            {
              'cat-id': 2,
              'cat-name': '电视',
              'cat-pid': '电视',
              'cat-level': 1,
              'cat-deleted': false,
              children: [
                {
                  'cat-id': 3,
                  'cat-name': '曲面电视',
                  'cat-pid': '电视',
                  'cat-level': 2,
                  'cat-deleted': false
                }
              ],
              children: [
                {
                  'cat-id': 3,
                  'cat-name': '曲面电视',
                  'cat-pid': '电视',
                  'cat-level': 2,
                  'cat-deleted': false
                }
              ],
              children: [
                {
                  'cat-id': 3,
                  'cat-name': '曲面电视',
                  'cat-pid': '电视',
                  'cat-level': 2,
                  'cat-deleted': false
                }
              ],
              children: [
                {
                  'cat-id': 3,
                  'cat-name': '曲面电视',
                  'cat-pid': '电视',
                  'cat-level': 2,
                  'cat-deleted': false
                }
              ],
              children: [
                {
                  'cat-id': 3,
                  'cat-name': '曲面电视',
                  'cat-pid': '电视',
                  'cat-level': 2,
                  'cat-deleted': false
                }
              ]
            }
          ]
        }
      ],
      manylist: [
        {
          attr_id: 1,
          attr_name: 'cpu',
          cat_id: 1,
          attr_sel: 'only',
          attr_write: 'manual',
          attr_vals: [
            { 1: '49时4k超薄曲面' },
            { 2: '人工智能,55时4k观影曲面' },
            { 3: '30核HDR49时4k超薄曲面' },
            { 4: ' 人工智能' }
          ],
          inputVisible: false,
          inputValue: ''
        }
      ],
      // 级联选择框的配置对象
      cateProps: {
        value: 'cat-id',
        label: 'cat-name',
        children: 'children'
      },
      selectedCateKeys: [],
      activeName: 'many',
      addDialogVisible: false,
      addForm: {
        attr_name: ''
      },
      addFormRules: {
        attr_name: [
          { required: true, message: '请输入参数名称', trigger: 'blur' }
        ]
      },
      editForm: {
        attr_name: ''
      },
      editFormRules: {
        attr_name: [
          { required: true, message: '请输入参数名称', trigger: 'blur' }
        ]
      },
      editDialogVisible: false
    }
  },
  methods: {
    handleChange() {
      if(this.selectedCateKeys.length !== 3)
      this.manylist=[]
      this.getParamsData()
    },
    handleClick() {
      console.log(this.activeName)
      this.getParamsData()
    },
    getParamsData() {
      if (this.selectedCateKeys.length !== 3) {
        return
      }
      console.log(this.selectedCateKeys)
    },
    addDialogClosed() {
      this.$refs.addFormRef.resetFields()
    },
    addParams() {
      this.$refs.addFormRef.validate(valid => {
        if (!valid) return
        this.$message.success('增加成功')
        this.addDialogVisible = false
      })
    },
    showEditDialog(id) {
      for (var i = 0; i < this.manylist.length; i++) {
        if (id === this.manylist[i].attr_id) {
          this.editForm.attr_name = this.manylist[i].attr_name
        }
      }
      this.editDialogVisible = true
    },
    editParams() {
      this.$refs.editFormRef.validate(valid => {
        if (!valid) return
        return this.$message.success('修改成功')
      })
      this.editDialogVisible = false
    },
    editDialogClosed() {
      this.$refs.editFromRef.resetFields()
    },
    async removeParams(id) {
      const confirmResult = await this.$confirm(
        '此操作将永久删除该文件, 是否继续?',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).catch(err => err)
      if (confirmResult !== 'confirm') {
        return this.$message.info('已取消删除！')
      }
      this.$message.success('删除参数成功！')
    },
    handleInputConfirm(row) {
      //当用户在文本框中按下enter键或者焦点离开时都会触发执行
      //判断用户在文本框中输入的内容是否合法
      if (row.inputValue.trim().length === 0) {
        row.inputValue = ''
        row.inputVisible = false
        return
      }
      row.attr_vals.push(row.inputValue.trim())
      row.inputValue = ''
      row.inputVisible = false
      this.$message.success('增加参数项成功')
    },
    showInput(row) {
      row.inputVisible = true
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus()
      })
    },
    handleClose(i,row){
       row.attr_vals.splice(i, 1)
       this.$message.success('删除成功')

    }
  },
  computed: {
    isBtnDisabled() {
      if (this.selectedCateKeys.length !== 3) {
        return true
      }
      return false
    },
    titleText() {
      if (this.activeName === 'many') {
        return '动态参数'
      }
      return '静态属性'
    }
  }
}
</script>
<style lang="less" scoped>
.cat_opt {
  margin: 15px 0;
}

.el-tag {
  margin: 10px;
}

.input-new-tag {
  width: 120px;
}
</style>