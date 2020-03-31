import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import UrlNotFound from "../views/UrlNotFound.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/urlnotfound",
    name: "UrlNotFound",
    component: UrlNotFound
  }
];

const router = new VueRouter({
  mode: "history",
  routes
});

export default router;
