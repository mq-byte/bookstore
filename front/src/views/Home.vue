<template>
  <div>

    
  
    <el-row :gutter="20" v-if="">
      <!-- <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
      <el-col :span="8"><div class="grid-content bg-purple"></div></el-col> -->
      <el-col :span="3"><div class="grid-content bg-purple"><el-button type="primary"><router-link to="/buyer/login">登录</router-link></el-button></div></el-col>
      <el-col :span="3"><div class="grid-content bg-purple"><el-button><router-link to="/buyer/regest">注册</router-link></el-button></div></el-col>
    </el-row>

    <el-table
    :data="tableData.filter(data => !search || data.bookName.toLowerCase().includes(search.toLowerCase()))"
    style="width: 100%">

    <el-table-column
      label="图书编号"
      prop="bookId">
    </el-table-column>

    <el-table-column
      label="书名"
      prop="bookName">
    </el-table-column>

    <el-table-column
      label="出版日期"
      prop="publishYear">
    </el-table-column>


    <el-table-column
      label="作者"
      prop="writer">
    </el-table-column>

    <el-table-column
      label="出版社"
      prop="press">
    </el-table-column>

    <el-table-column
      label="评价"
      prop="comment">
    </el-table-column>

    <el-table-column
      label="价格"
      prop="price">
    </el-table-column>

    <el-table-column
      label="库存"
      prop="stockNum">
    </el-table-column>

    <el-table-column
      label="卖家"
      prop="sellerName">
    </el-table-column>

    <el-table-column
      align="right">
      <template slot="header" slot-scope="scope">
        <el-input
          v-model="search"
          size="mini"
          placeholder="输入关键字搜索"/>
      </template>
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="buy(scope.$index, scope.row)">购买</el-button>
        <!-- <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">删除</el-button> -->
      </template>
    </el-table-column>
  </el-table>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        tableData: [],
        search: ''
      }
    },
    mounted(){
      this.selectAll();
    },
    methods: {
      selectAll(){
        this.axios({
          url:'/buyer/goods',
          method:"GET"
        }).then(res=>{
          this.tableData = res.data.body;
        });
      },
      buy(index, row) {
        this.axios({
          url:'/buyer/goods/'+row.bookId+'/'+row.sellerId,
          method:"GET"
        }).then(res=>{
          if(res.data.status == "SUCCESS"){
            this.selectAll();
            this.$message.success("购买成功");
          }else{
            this.$message.error(res.data.body);
          }
        });
      }
    },
  }
</script>