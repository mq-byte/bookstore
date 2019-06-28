<template>
  <div class="login">
    <el-form ref="form" :model="form" label-width="80px">
       <el-form-item label="姓名">
        <el-input v-model="form.userName"></el-input>
      </el-form-item>

       <el-form-item label="密码">
        <el-input show-password v-model="form.password"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">立即登录</el-button>
        <el-button><router-link to="/buyer/regest">没有账号，立即注册</router-link></el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { constants } from 'crypto';
  export default {
    data() {
      return {
        form: {
          userName: '',
          password:''
        }
      }
    },
    methods: {
      onSubmit() {
        this.axios({
           url:'/users/buyer/login',
          method:"POST",
          data:this.form
        }).then(res=>{
          if(res.data.status == "SUCCESS"){
            this.$message.success('登录成功');
            this.$router.push("/home");
          }else{
            this.$message.error(res.data.body);
          }
        })
      }
    }
  }
</script>