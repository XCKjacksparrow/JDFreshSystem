<template>
  <div>
    <h3>添加商品</h3>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>添加商品</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <!-- 消息提示 -->
      <el-alert title="添加商品信息" type="info" center show-icon :closable="false"></el-alert>

      <!-- 步骤条组件 -->
      <!-- align-center(居中效果) -->
      <el-steps :space="200" :active="activeIndex - 0" finish-status="success" align-center>
        <el-step title="基本信息"></el-step>
        <el-step title="商品参数"></el-step>
        <el-step title="商品属性"></el-step>
        <el-step title="商品图片"></el-step>
        <el-step title="商品内容"></el-step>
        <el-step title="完成"></el-step>
      </el-steps>

      <!-- tab栏区域:el-tab-pane必须是el-tabs的子节点
      :tab-position="'left'"(设置tab栏为左右结构tab栏)-->
      <!-- 表单：label-position="top"(设置label在文本框上方) -->
      <el-form
        :model="addForm"
        :rules="addFormRules"
        ref="addFormRef"
        label-width="100px"
        label-position="top"
      >
        <el-tabs v-model="activeIndex" :tab-position="'left'" :before-leave="beforeTableLeave">
          <el-tab-pane label="基本信息" name="0">
            <el-form-item label="商品名称" prop="goods_name">
              <el-input v-model="addForm.goods_name"></el-input>
            </el-form-item>
            <el-form-item label="商品价格" prop="goods_price">
              <el-input v-model="addForm.goods_price" type="number"></el-input>
            </el-form-item>
            <el-form-item label="商品重量" prop="goods_weight">
              <el-input v-model="addForm.goods_weight" type="number"></el-input>
            </el-form-item>
            <el-form-item label="商品数量" prop="goods_number">
              <el-input v-model="addForm.goods_number" type="number"></el-input>
            </el-form-item>
            <el-form-item label="商品分类" prop="goods_cat">
              <!-- 选择商品分类的级联选择框 -->
              <el-cascader
                expandTrigger="hover"
                v-model="addForm.goods_cat"
                :options="cateList"
                :props="cateProps"
                @change="handleChange"
                clearable
              ></el-cascader>
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="商品参数" name="1">
            <el-form-item :label="item.attr_name" v-for="item in cateList1" :key="item.attr_id">
              <!-- 复选框组 -->
              <el-checkbox-group v-model="item.attr_vals">
                <el-checkbox :label="cb" v-for="(cb, i) in item.attr_vals" :key="i" border></el-checkbox>
              </el-checkbox-group>
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="商品属性" name="2">
            <el-form-item :label="item.attr_name" v-for="item in onlyTableData" :key="item.attr_id">
              <el-input v-model="item.attr_vals"></el-input>
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="商品图片" name="3">
            <!-- action 表示图片要上传到的后台API地址 -->
            <el-upload
              :action="uploadURL"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              list-type="picture"
              :headers="headerObj"
            >
              <el-button size="small" type="primary">点击上传</el-button>
            </el-upload>
          </el-tab-pane>
          <el-tab-pane label="商品内容" name="4">
             <!-- 富文本编辑器组件 -->
            <quill-editor v-model="addForm.goods_introduce"></quill-editor>
            <!-- 添加商品的按钮 -->
            <el-button type="primary" class="btnAdd" @click="add">添加商品</el-button>
          </el-tab-pane>
        </el-tabs>
      </el-form>
    </el-card>
       <!-- 图片预览 -->
    <el-dialog title="图片预览" :visible.sync="previewVisible" width="50%">
      <img :src="previewPath" alt="" class="previewImg">
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeIndex: '0',

      addForm: {
        goods_name: '',
        goods_price: 0,
        goods_weight: 0,
        goods_number: 0,
        goods_cat: [],
        goods_introduce: ''
      },

      addFormRules: {
        goods_name: [
          { required: true, message: '请输入商品名称', trigger: 'blur' }
        ],
        goods_price: [
          { required: true, message: '请输入商品价格', trigger: 'blur' }
        ],
        goods_weight: [
          { required: true, message: '请输入商品重量', trigger: 'blur' }
        ],
        goods_number: [
          { required: true, message: '请输入商品数量', trigger: 'blur' }
        ],
        goods_cat: [
          { required: true, message: '请选择商品分类', trigger: 'blur' }
        ]
      },

      cateList: [
        {
          cat_deleted: '',
          cat_id: 0,
          cat_level: 0,
          cat_name: '手机传感器',
          cat_pid: 1,
          children: [
            {
              cat_deleted: '',
              cat_id: 1,
              cat_level: 1,
              cat_name: '手机传感器',
              cat_pid: 1,
              children: [
                {
                  cat_deleted: '',
                  cat_id: 2,
                  cat_level: 2,
                  cat_name: '手机传感器',
                  cat_pid: 1
                }
              ]
            }
          ]
        },
        {
          cat_deleted: '',
          cat_id: 3,
          cat_level: 0,
          cat_name: '手机传感器',
          cat_pid: 1,
          children: [
            {
              cat_deleted: '',
              cat_id: 4,
              cat_level: 1,
              cat_name: '手机传感器',
              cat_pid: 1,
              children: [
                {
                  cat_deleted: '',
                  cat_id: 5,
                  cat_level: 2,
                  cat_name: '手机传感器',
                  cat_pid: 1
                }
              ]
            }
          ]
        },
        {
          cat_deleted: '',
          cat_id: 6,
          cat_level: 0,
          cat_name: '手机传感器',
          cat_pid: 1,
          children: [
            {
              cat_deleted: '',
              cat_id: 7,
              cat_level: 1,
              cat_name: '手机传感器',
              cat_pid: 1,
              children: [
                {
                  cat_deleted: '',
                  cat_id: 8,
                  cat_level: 2,
                  cat_name: '手机传感器',
                  cat_pid: 1
                }
              ]
            }
          ]
        },
        {
          cat_deleted: '',
          cat_id: 9,
          cat_level: 0,
          cat_name: '手机传感器',
          cat_pid: 1,
          children: [
            {
              cat_deleted: '',
              cat_id: 10,
              cat_level: 1,
              cat_name: '手机传感器',
              cat_pid: 1,
              children: [
                {
                  cat_deleted: '',
                  cat_id: 11,
                  cat_level: 2,
                  cat_name: '手机传感器',
                  cat_pid: 1
                }
              ]
            }
          ]
        }
      ],
      cateList1: [
        {
          attr_id: 1,
          attr_name: '版式',
          attr_sel: '',
          attr_vals: [
            { 1: '49时4k超薄曲面' },
            { 2: '人工智能,55时4k观影曲面' },
            { 3: '30核HDR49时4k超薄曲面' },
            { 4: ' 人工智能' }
          ],
          attr_write: '',
          cat_id: 0,
          delete_time: ''
        },
        {
          attr_id: 2,
          attr_name: '颜色',
          attr_sel: '',
          attr_vals: [
            { 1: '49时4k超薄曲面' },
            { 2: '人工智能,55时4k观影曲面' },
            { 3: '30核HDR49时4k超薄曲面' },
            { 4: ' 人工智能' }
          ],
          attr_write: '',
          cat_id: 1,
          delete_time: ''
        }
      ],
      cateProps: {
        value: 'cat_id',
        label: 'cat_name',
        children: 'children'
      },
      onlyTableData: [
        {
          attr_id: 2,
          attr_name: '屏幕',
          attr_sel: '',
          attr_vals: '人工智能,55时4k观影曲面',
          attr_write: '',
          cat_id: 1,
          delete_time: ''
        },
        {
          attr_id: 3,
          attr_name: '颜色',
          attr_sel: '',
          attr_vals: '人工智能,55时4k观影曲面',
          attr_write: '',
          cat_id: 1,
          delete_time: ''
        },
        {
          attr_id: 4,
          attr_name: '大小',
          attr_sel: '',
          attr_vals: '人工智能,55时4k观影曲面',
          attr_write: '',
          cat_id: 1,
          delete_time: ''
        }
      ],
      // 图片上传组件的headers请求头对象
      headerObj: {
        Authorization: window.sessionStorage.getItem('token')
      },
      // 上传图片的URL地址
      uploadURL: 'https://jsonplaceholder.typicode.com/posts/',
      previewPath: '',
      previewVisible: false
    }
  },
  methods: {
    async getCateList() {
      const { data: res } = await this.$http.get('categories')

      if (res.meta.status !== 200) {
        return this.$message.error('获取商品分类数据失败')
      }
      this.cateList = res.data
    },
    handleChange() {
      if (this.addForm.goods_cat.length !== 3) {
        this.addForm.goods_cat = []
        return
      }
    },
    beforeTableLeave(activeName, oldActiveName) {
      if (oldActiveName === '0' && this.addForm.goods_cat.length !== 3) {
        this.$message.error('请先选择商品分类')
        return false
      }
    },
    handlePreview(file) {
      console.log(file)
       this.previewPath = file.url
      this.previewVisible = true
     
    },
    handleRemove(file) {
      console.log(file)
      this.$message.success('删除成功')
    },
    add(){
      this.$refs.addFormRef.validate(valid =>{
        if(!valid){
          return this.$message.error('请填写必要的表单项')
        }

        return this.$message.success('添加成功')
      })
    }
  }
}
</script>

<style lang="less" scoped>
.el-checkbox {
  margin: 0 10px 0 0 !important;
}
.previewImg {
  width: 100%;
}

.btnAdd{
  margin-top: 15px;
}
</style>