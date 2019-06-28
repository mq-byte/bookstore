import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'

Vue.use(Router)

const checkToken = (to, from, next) => {
  console.log(document.cookie);
  next();
}


const router = new Router({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      redirect:'/home'
    },
    {
      path: '/seller/regest',
      name: 'regest',
      component: () => import('./views/seller/Regest.vue')
    },
    {
      path: '/seller/login',
      name: 'login',
      component: () => import('./views/seller/Login.vue')
    },
    {
      path: '/buyer/regest',
      name: 'bregest',
      component: () => import('./views/buyer/Regest.vue')
    },
    {
      path: '/buyer/login',
      name: 'blogin',
      component: () => import('./views/buyer/Login.vue')
    },
    {
      path: '/seller/stock',
      name: 'strock',
      component: () => import('./views/stock/Stock.vue'),
      beforeEnter:checkToken
    },
    {
      path: '/about',
      name: 'about',
      component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    },
    {
      path: '/home',
      name: 'home',
      component: () => import('./views/Home.vue')
    },
  ]
});


export default router;
