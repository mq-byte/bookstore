import axios from 'axios'


// 每次请求携带cookies信息
axios.defaults.withCredentials = true

var instance = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 6000
  });

  export default instance;