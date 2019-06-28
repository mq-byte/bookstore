<template>
  <div>
    <div>
      <el-button type="primary" @click="showadd">新增</el-button>
    </div>

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
          @click="handleEdit(scope.$index, scope.row)">修改</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>


  <el-dialog title="图书管理" :visible.sync="dialogFormVisible">
    <el-form :model="form">

      <el-form-item label="书名" :label-width="formLabelWidth">
        <el-input :disabled="!isAdd" v-model="form.bookName" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="出版日期" :label-width="formLabelWidth">
        <el-date-picker
        :disabled="!isAdd"
          v-model="form.publishYear"
          type="date"
          placeholder="选择日期" />
      </el-form-item>

      <el-form-item label="作者" :label-width="formLabelWidth">
        <el-input :disabled="!isAdd" v-model="form.writer" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="出版社" :label-width="formLabelWidth">
        <el-input :disabled="!isAdd" v-model="form.press" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="评价" :label-width="formLabelWidth">
        <el-input v-model="form.comment" :disabled="!isAdd" autocomplete="off"></el-input>
      </el-form-item>


      <el-form-item label="价格" :label-width="formLabelWidth">
        <el-input-number v-model="form.price" :precision="2" :step="0.1" :min="0" :max="1000"></el-input-number>
      </el-form-item>


      <el-form-item label="库存数" :label-width="formLabelWidth">
        <el-input-number v-model="form.stockNum" :min="1" :max="999999" label="描述文字"></el-input-number>
      </el-form-item>

    
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="submit">确 定</el-button>
    </div>
  </el-dialog>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        tableData: [],
        search: '',
        dialogFormVisible: false,
        form: {
          bookName: '',
          publishYear: '',
          writer: '',
          press: '',
          comment: '',
          price: 0,
          stockNum: 0
        },
        formLabelWidth: '120px',
        isAdd:true
      }
    },
    mounted(){
      this.selectAll();
    },
    methods: {
      showupdate:function(obj){
        this.isAdd = false;
        this.form = JSON.parse(JSON.stringify(obj));
        this.dialogFormVisible = true;
      },
      selectAll(){
        this.axios({
          url:'/book/stock',
          method:"GET"
        }).then(res=>{
          this.tableData = res.data.body;
        });
      },
      showadd:function(){
        this.isAdd = true;
        this.form = {
          bookName: '',
          publishYear: '',
          writer: '',
          press: '',
          comment: '',
          price: 0,
          stockNum: 0
        };
        this.dialogFormVisible = true;
      },
      handleEdit(index, row) {
        this.showupdate(row);
      },
      handleDelete(index, row) {
        this.axios({
          url:'/book/stock/'+row.bookId,
          method:"DELETE"
        }).then(res=>{
          if(res.data.status == "SUCCESS"){
            this.selectAll();
            this.$message.success("删除成功");
          }else{
            this.$message.error(res.data.body);
          }
          
        })
      },
      add(){
        if(
          this.form.bookName == '' ||
          this.form.publishYear == '' ||
          this.form.writer == '' ||
          this.form.press == '' ||
          this.form.comment == '' 
        ){
          this.$message.error("请填写完整信息");
        }else{
          this.axios({
          url:'/book/stock',
          method:"POST",
          data:this.form
        }).then(res=>{
          if(res.data.status == "SUCCESS"){
            this.selectAll();
            this.dialogFormVisible = false;
            this.$message.success("添加成功");
          }else{
            this.$message.error(res.data.body);
          }
          
        })
          
        }
      },
      update(){
        if(
          this.form.bookName == '' ||
          this.form.publishYear == '' ||
          this.form.writer == '' ||
          this.form.press == '' ||
          this.form.comment == '' 
        ){
          this.$message.error("请填写完整信息");
        }else{
          this.axios({
          url:'/book/stock',
          method:"PUT",
          data:this.form
        }).then(res=>{
          if(res.data.status == "SUCCESS"){
            this.selectAll();
            this.dialogFormVisible = false;
            this.$message.success("修改成功");
          }else{
            this.$message.error(res.data.body);
          }
          
        })
        }
      },
      submit(){
        if(this.isAdd){
          this.add();
        }else{
          this.update();
        }
        
      }
    },
  }
</script>