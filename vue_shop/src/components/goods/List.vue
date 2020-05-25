<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>生鲜管理</el-breadcrumb-item>
      <el-breadcrumb-item>生鲜列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <el-row :gutter="20">
        <el-col :span="8">
          <el-input
            placeholder="请输入内容"
            v-model="queryInfo.query"
            clearable
            @clear="getgoodList(queryInfo.query)"
          >
            <el-button slot="append" icon="el-icon-search" @click="getgoodList(queryInfo.query)"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="dialogVisible = true">添加生鲜</el-button>
        </el-col>
      </el-row>

      <!-- table表格区域 -->
      <el-table :data="goodslist" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="生鲜名称" prop="name"></el-table-column>
        <el-table-column label="生鲜类别" prop="freshCategory"></el-table-column>
        <el-table-column label="生鲜价格(元)" prop="price" width="95px"></el-table-column>
        <el-table-column label="生鲜数量" prop="amount" width="70px"></el-table-column>
        <el-table-column label="操作" width="130px">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini"></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="removeById(scope.row.id)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pagenum"
        :page-sizes="[2, 4, 6, 8]"
        :page-size="queryInfo.pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        background
      ></el-pagination>
    </el-card>

    <el-dialog title="添加生鲜" :visible.sync="dialogVisible" width="30%" @close="addDialogClosed">
        <el-form :model="ruleForm" ref="ruleForm" label-width="70px">
          <el-form-item label="生鲜种类" prop="freshCategory">
            <el-input v-model="ruleForm.freshCategory"></el-input>
          </el-form-item>
          <el-form-item label="生鲜名称" prop="name">
            <el-input v-model="ruleForm.name"></el-input>
          </el-form-item>
          <el-form-item label="单价" prop="price">
            <el-input v-model="ruleForm.price"></el-input>
          </el-form-item>
          <el-form-item label="数量" prop="amount">
            <el-input v-model="ruleForm.amount"></el-input>
          </el-form-item>
        </el-form>

        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="addFresh">确 定</el-button>
        </span>
      </el-dialog>
  </div>
</template>



<script>
import Qs from 'qs'
export default {
  data() {
    return {
      ruleForm:{
        freshCategory: '',
        name: '',
        price: '',
        amount: ''
      },
      dialogVisible: false,
      // 查询参数对象
      queryInfo: {
        query: '',
        pagenum: 1,
        pagesize: 10
      },
      // 商品列表
      goodslist: [],
      goodslist1: [
        {
          goods_name: '沿途点烟器',
          goods_price: 0,
          goods_weight: 100,
          add_time: 0
        }
      ],
      // 总数据条数
      total: 6
    }
  },
  mounted() {
    this.getAllFresh()
  },
  methods: {
    addDialogClosed() {
      this.$refs.ruleForm.resetFields()
    },
    getgoodList(id) {
      console.log(id)
      if (id === '沿途点烟器') {
        this.goodslist = this.goodslist1
      }
    },
    handleSizeChange(newSize) {
      this.queryInfo.pagesize = newSize
    },
    handleCurrentChange(newPage) {
      this.queryInfo.pagenum = newPage
    },
    async removeById(id) {
      const confirmResult = await this.$confirm(
        '此操作将永久删除该商品, 是否继续?',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).catch(err => err)

      if (confirmResult !== 'confirm') {
        return this.$message.info('已经取消删除！')
      }

      this.$message.success('删除成功！')
      // console.log(" 删除 id" + id);
      this.$http.post("admin-fresh/deleteFreshById", Qs.stringify({id:id})).then(response=>{
        location.reload();
      })
    },
    goAddpage() {
      this.$router.push('/user7/add')
    },
    getAllFresh() {
      var tmpGoodList = []
      this.$http.get('admin-fresh/getAllFreshCategories').then(response => {
        var data = response.data
        for (var i = 0; i < data.length; i++) {
          var name = data[i].name
          this.$http
            .post(
              'admin-fresh/getAllFreshByFreshCategory',
              Qs.stringify({ freshCategory: name })
            )
            .then(res => {
              console.log(res.data)
              if (res.data.length != 0) {
                for (var j = 0; j < res.data.length; j++) {
                  tmpGoodList.push(res.data[j])
                }
              }
            })
        }
        this.goodslist = tmpGoodList
      })
      // goodslist;
    },
    addFresh() {
      this.$http.post('admin-fresh/addFresh', Qs.stringify(this.ruleForm)).then(response=>{
        this.dialogVisible = false
        this.$message.success("添加成功")
        location.reload()
      })
    },
  }
}
</script>
<style lang="less" scoped>
</style>