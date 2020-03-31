<template>
  <div class="home">
    <h1>Shorten your link</h1>
    <form id="request-form" v-on:submit.prevent="postUrl">
      <label>
        <input
          id="url-input"
          type="url"
          placeholder="Insert your link"
          v-model="request.longUrl"
        />
      </label>
      <p>
        <button id="runtime-button">
          Generate short URL
        </button>
      </p>
    </form>
    <p>
      <label id="reponse-error" v-if="showError">{{ error }}</label>
      <label id="reponse-info" v-if="showInfo">{{ info }}</label>
    </p>
    <p>
      <a
        id="reponse-label"
        v-if="showResponse"
        v-bind:href="shortUrl"
        target="_blank"
        >{{ shortUrl }}</a
      >
    </p>
  </div>
</template>

<script>
export default {
  name: "Home",
  components: {},
  data() {
    return {
      request: {
        longUrl: ""
      },
      shortUrl: "",
      error: "",
      info: "",
      showResponse: false,
      showError: false,
      showInfo: false
    };
  },
  methods: {
    postUrl() {
      this.cleanForm(),
        this.$http
          .post("/url/shorten", this.request)
          .then(resp => {
            if (resp.data != null && resp.data !== "") {
              (this.showResponse = true),
                (this.request.longUrl = ""),
                (this.shortUrl = resp.data.shortUrl);
              if (resp.data.info != null) {
                this.showInfo = true;
                this.info = resp.data.info;
              }
            } else {
              (this.showError = true),
                (this.error = "An error has occurred, please try again later");
            }
          })
          .catch(error => {
            console.log(error);
            if (error.response.status == 400) {
              this.error = error.response.data.errors[0].defaultMessage;
            } else {
              this.error = "An error has occurred, please try again later";
            }
            this.showError = true;
          });
    },
    cleanForm() {
      (this.showError = false),
        (this.showInfo = false),
        (this.showResponse = false),
        (this.shortUrl = ""),
        (this.error = ""),
        (this.info = "");
    }
  }
};
</script>

<style>
#runtime-button {
  /* Font & Text */
  font-family: Lato, sans-serif;
  font-size: 16px;
  font-style: normal;
  font-variant: normal;
  font-weight: 500;
  letter-spacing: 0.5px;
  line-height: 20px;
  text-decoration: none solid rgb(255, 255, 255);
  text-align: center;
  background-color: #31ceb2;
  color: rgb(255, 255, 255);
  /* Box */
  height: 10%;
  width: 17%;
  padding: 14px 30px;
  transition: background-color 0.3s ease 0s;
  border-radius: 4px;
}
h1 {
  /* Font & Text */
  font-family: Montserrat, sans-serif;
  font-size: 56px;
  font-weight: 700;
  line-height: 61px;
  text-align: center;
  color: #404980;
}
#url-input {
  width: 72%;
  height: 60px;
  background: #fff;
  border: 1px solid #d3d4d7;
  border-radius: 8px 8px 8px 8px;
  font-size: 20px;
  color: #36383b;
  padding: 0 20px;
  margin: 0 auto;
  text-align: center;
}
#request-form {
  background-color: #404980;
  width: 80%;
  padding-top: 4%;
  padding-bottom: 1%;
  border-radius: 8px 8px 8px 8px;
  border: 5px solid transparent;
  margin: 0 auto;
}
#reponse-label {
  width: 72%;
  height: 100%;
  background: #404980;
  border: 5px solid #404980;
  border-radius: 8px 8px 8px 8px;
  font-size: 40px;
  color: white;
  padding: 0 20px;
  margin: 0 auto;
  text-align: center;
}
#reponse-error {
  width: 50%;
  height: 60px;
  background: #da0202b3;
  border: 1px solid #d3d4d7;
  border-radius: 8px 8px 8px 8px;
  font-size: 25px;
  color: white;
  padding: 0 20px;
  margin: 0 auto;
  text-align: center;
}
#reponse-info {
  width: 50%;
  height: 60px;
  background: #029edad9;
  border: 1px solid #d3d4d7;
  border-radius: 8px 8px 8px 8px;
  font-size: 25px;
  color: white;
  padding: 0 20px;
  margin: 0 auto;
  text-align: center;
}
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
