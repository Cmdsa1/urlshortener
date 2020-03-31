import Vue from "vue";
import axios from "axios";

axios.defaults.baseURL = "http://localhost:8081";

Vue.use({
  install(Vue) {
    Vue.prototype.$http = axios.create({
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      auth: {
        username: "frontend",
        password: "ol12!*Ac"
      }
    });
  }
});
