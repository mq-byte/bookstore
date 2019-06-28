<template>
  <div class="regest" style="margin: 50px auto;width:50%;">
    <el-form ref="form" :model="form" label-width="80px">

      <el-form-item label="姓名">
        <el-input v-model="form.userName"></el-input>
      </el-form-item>

      <el-form-item label="性别">
        <el-radio-group v-model="form.sex">
          <el-radio label="男"></el-radio>
          <el-radio label="女"></el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="密码">
        <el-input show-password v-model="form.password"></el-input>
      </el-form-item>


      <el-form-item label="确认密码">
        <el-input show-password v-model="form.repassword"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">立即注册</el-button>
        <el-button><router-link to="/buyer/login">已有账号，立即登录</router-link></el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        form: {
          userName: '',
          sex: '',
          password:'',
          repassword:''
        }
      }
    },
    methods: {
      onSubmit() {
        for(let v in this.form){
          if(this.form[v] == ''){
            this.$message.error('信息不完整');
            return;
          }
        }

        if(this.form.password != this.form.repassword){
            this.$message.error('密码不一致');
            return;
        }
        this.axios({
          url:'/users/buyer/regest',
          method:"POST",
          data:this.form
        }).then(res=>{
          if(res.data.status == "SUCCESS"){
            this.$message.success('注册成功,请登录');
            this.$router.push("/buyer/login");
          }else{
            this.$message.error(res.data.body);
          }
          
        })
      }
    }
  }
</script>