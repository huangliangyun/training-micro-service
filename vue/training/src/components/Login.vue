<template>

  <div id="login_content">

    <div id="window">
      <div>
        <label class="training_title">微服务架构专业实训管理系统</label>
      </div>
      <div class="page page-front">

        <div class="page-content">

          <div class="input-row">
            <label class="label fadeIn">用户名</label>
            <input v-model="loginForm.username" id="username" type="text" class="input fadeIn delay1"/>
          </div>
          <div class="input-row">
            <label class="label fadeIn delay2">密码</label>
            <input v-model="loginForm.password" id="password" type="password" class="input fadeIn delay3"/>
          </div>
          <div class="input-row perspective">
            <button id="submit" class="button load-btn fadeIn delay4" @click="submitClick">
              <span class="default"><i class="ion-arrow-right-b"></i>登录</span>
              <div class="load-state">
                <div class="ball"></div>
                <div class="ball"></div>
                <div class="ball"></div>
              </div>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import {postRequest} from "../utils/api"
  import {getRequest} from "../utils/api"
  import {putRequest} from "../utils/api"

  export default {
    name: "Login",
    data() {
      return {
        loginForm: {
          username: '201611104033',
          password: '123'
        },
      }
    },
    methods: {
      submitClick: function () {
        this.$axios.get('/basic-service/v1/info/system?flag=1').then( (res)=>{
          sessionStorage.setItem('currentWeek',JSON.stringify(res.data.result.week));
        }).catch( (err)=>{
          console.log(err);
        });

        postRequest('/uaa-service/v1/uaa/oauth/token', {
          username: this.loginForm.username,
          password: this.loginForm.password,
          grant_type: 'password',
          scope: 'server',
          client_id: 'client-service',
          client_secret: '123456',
        }).then(resp => {
          console.log(resp);
          if (resp.status === 200) {
            //成功
            if (resp.data.resultCode === 200) {
              this.$notify({
                title: '登录成功',
                type: 'success',
                duration: '2000',
                message:'欢迎使用'
              });
              sessionStorage.setItem('username', this.loginForm.username);
              sessionStorage.setItem('role', resp.data.result.role[0].authority);
              var role = resp.data.result.role;
              //角色数组
              var roles = '';
              for (var i in role) {
                roles += role[i].authority;
              }
              //alert(roles);
              sessionStorage.setItem('roles', roles);
              sessionStorage.setItem('username', resp.data.result.userId);
              sessionStorage.setItem('token', resp.data.result.token);
              //alert(sessionStorage.getItem("token") + "：" + sessionStorage.getItem("username"))
              this.$router.push({path: '/home'});
            }
            if(resp.data.resultCode===401){
              this.$notify({
                type:'error',
                title:'登录失败',
                message:'账号或密码错误',
              })
            }
          }
        });


        /* if (this.loginForm.username === '201611104031' && this.loginForm.password === '123') {
           sessionStorage.setItem('username', this.loginForm.username);
           sessionStorage.setItem('role', '6');
           this.$router.push('/home');
           this.$notify({
             message: '登录成功',
             type: 'success',
             duration: '2000'
           });
         }*/
      }
    }
  }
</script>

<style scoped>
  html, body {
    height: 100%;
  }
  #login_content{
    background: black;
  }

  body {
   background-image: -webkit-radial-gradient(ellipse closest-side, #ffffff, #d4e2af);
    -moz-perspective: 1000px;
    -webkit-perspective: 1000px;
    perspective: 1000px;
    font-family: 'Open Sans';
    font-weight: 300;
  }

  *, *::after, *::before {
    -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
  }

  .training_title {
    font-family: sans-serif;
    text-transform: uppercase;
    font-size: 32px;
    margin: -30px auto;
    text-align: center;
    color: #101636;
    display: block;
    cursor: pointer;

  }

  #window {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    margin: auto;
    height: 350px;
    width: 450px;
    color: #FFF;
    -webkit-animation: 1.5s window ease-in-out backwards;
    -moz-animation: 1.5s window ease-in-out backwards;
    animation: 1.5s window ease-in-out backwards;
    -moz-transform-style: preserve-3d;
    -webkit-transform-style: preserve-3d;
    transform-style: preserve-3d;
    -moz-transition-property: height, width;
    -o-transition-property: height, width;
    -webkit-transition-property: height, width;
    transition-property: height width;
  }

  #window, #window .page, #window .page-content {
    -moz-transition-duration: 1s;
    -o-transition-duration: 1s;
    -webkit-transition-duration: 1s;
    transition-duration: 1s;
  }

  #window.flip {
    height: 460px;
    width: 350px;
  }

  #window.flip .page-front {
    -moz-transform: rotateX(180deg);
    -ms-transform: rotateX(180deg);
    -webkit-transform: rotateX(180deg);
    transform: rotateX(180deg);
  }

  #window.flip .page-back {
    -moz-transform: rotateX(360deg);
    -ms-transform: rotateX(360deg);
    -webkit-transform: rotateX(360deg);
    transform: rotateX(360deg);
  }

  #window.flip .page-back .page-content {
    opacity: 1;
  }

  #window.flip .page-back .avatar, #window.flip .page-back .welcome, #window.flip .page-back .perspective {
    opacity: 1;
    -moz-transform: none;
    -ms-transform: none;
    -webkit-transform: none;
    transform: none;
    -moz-transition-duration: 0.6s;
    -o-transition-duration: 0.6s;
    -webkit-transition-duration: 0.6s;
    transition-duration: 0.6s;
  }

  #window.flip .page-back .avatar {
    -moz-transition-delay: 0.8s;
    -o-transition-delay: 0.8s;
    -webkit-transition-delay: 0.8s;
    transition-delay: 0.8s;
  }

  #window.flip .page-back .welcome {
    -moz-transition-delay: 0.8s;
    -o-transition-delay: 0.8s;
    -webkit-transition-delay: 0.8s;
    transition-delay: 0.8s;
  }

  #window.flip .page-back .perspective {
    -moz-transition-delay: 0.9s;
    -o-transition-delay: 0.9s;
    -webkit-transition-delay: 0.9s;
    transition-delay: 0.9s;
  }

  #window .page {
    position: absolute;
    top: 20px;
    left: 0;
    right: 0;
    bottom: 0;
    overflow: hidden;
    -moz-transform-style: preserve-3d;
    -webkit-transform-style: preserve-3d;
    transform-style: preserve-3d;
    -moz-backface-visibility: hidden;
    -webkit-backface-visibility: hidden;
    backface-visibility: hidden;
    -moz-transform: translateZ(0);
    -ms-transform: translateZ(0);
    -webkit-transform: translateZ(0);
    transform: translateZ(0);
    background: #444;
    background-image: -moz-linear-gradient(bottom, #444444, #333333);
    background-image: -webkit-linear-gradient(bottom, #444444, #333333);
    background-image: linear-gradient(to top, #444444, #333333);
    -moz-border-radius: 6px;
    -webkit-border-radius: 6px;
    border-radius: 6px;
    padding: 60px;
  }

  #window .page-back {
    text-align: center;
    -moz-transform: rotateX(180deg);
    -ms-transform: rotateX(180deg);
    -webkit-transform: rotateX(180deg);
    transform: rotateX(180deg);
  }

  #window .page-back .page-content {
    opacity: 0;
  }

  #window .page-back .avatar {
    height: 150px;
    width: 150px;
    border: 5px solid #FFF;
    margin: 30px auto 40px auto;
    display: block;
    opacity: 0;
    -moz-transform: scale(0.1);
    -ms-transform: scale(0.1);
    -webkit-transform: scale(0.1);
    transform: scale(0.1);
    -moz-border-radius: 1000px;
    -webkit-border-radius: 1000px;
    border-radius: 1000px;
  }

  #window .page-back .welcome {
    font-size: 22px;
    margin-bottom: 40px;
  }

  #window .page-back .welcome, #window .page-back .perspective {
    opacity: 0;
    -moz-transform: translateY(-30px);
    -ms-transform: translateY(-30px);
    -webkit-transform: translateY(-30px);
    transform: translateY(-30px);
  }

  #window .input-row {
    margin: 0 0 30px;
  }

  #window .input-row:last-of-type {
    margin-bottom: 0;
  }

  #window .perspective {
    -moz-perspective: 1000px;
    -webkit-perspective: 1000px;
    perspective: 1000px;
  }

  #window .label {
    font-family: sans-serif;
    text-transform: uppercase;
    font-size: 12px;
    margin: 0 0 10px 0;
    color: #FFF;
    display: block;
    cursor: pointer;
  }

  #window .input {
    padding: 0 15px;
    height: 40px;
    line-height: 40px;
    outline: none;
    border: 0;
    color: black;
    background: #FFF;
    display: block;
    width: 100%;
    -moz-border-radius: 3px;
    -webkit-border-radius: 3px;
    border-radius: 3px;
    -moz-transition-duration: 0.2s;
    -o-transition-duration: 0.2s;
    -webkit-transition-duration: 0.2s;
    transition-duration: 0.2s;
  }

  #window .input:focus, #window .input.fyll-focus {
    -moz-box-shadow: 0 0 0 3px #13bfe1;
    -webkit-box-shadow: 0 0 0 3px #13bfe1;
    box-shadow: 0 0 0 3px #13bfe1;
  }

  #window .button {
    height: 50px;
    line-height: 50px;
    padding: 0 20px;
    margin: 0;
    text-align: center;
    color: #FFF;
    border: 0;
    display: block;
    width: 100%;
    outline: none;
    cursor: pointer;
    overflow: hidden;
    font-size: 17px;
    background: #13bfe1;
    -moz-border-radius: 3px;
    -webkit-border-radius: 3px;
    border-radius: 3px;
    background-image: -webkit-linear-gradient(bottom, #13bfe1, #13bfe1);
    -moz-box-shadow: inset 0 1px 0 0 rgba(255, 255, 255, 0.4), 0 0 4px rgba(0, 0, 0, 0.5);
    -webkit-box-shadow: inset 0 1px 0 0 rgba(255, 255, 255, 0.4), 0 0 4px rgba(0, 0, 0, 0.5);
    box-shadow: inset 0 1px 0 0 rgba(255, 255, 255, 0.4), 0 0 4px rgba(0, 0, 0, 0.5);
    -moz-transition-duration: 0.1s;
    -o-transition-duration: 0.1s;
    -webkit-transition-duration: 0.1s;
    transition-duration: 0.1s;
    -moz-transform-style: preserve-3d;
    -webkit-transform-style: preserve-3d;
    transform-style: preserve-3d;
    -moz-transform-origin: bottom 50%;
    -ms-transform-origin: bottom 50%;
    -webkit-transform-origin: bottom 50%;
    transform-origin: bottom 50%;
    -moz-transform: translateZ(0);
    -ms-transform: translateZ(0);
    -webkit-transform: translateZ(0);
    transform: translateZ(0);
    -moz-backface-visibility: hidden;
    -webkit-backface-visibility: hidden;
    backface-visibility: hidden;
  }

  #window .button i {
    margin-right: 10px;
  }

  #window .button:active, #window .button.fyll-focus {
    background: #13bfe1;
    background-image: -moz-linear-gradient(bottom, #13bfe1, #13bfe1);
    background-image: -webkit-linear-gradient(bottom, #13bfe1, #13bfe1);
    -moz-transform: rotateX(20deg);
    -ms-transform: rotateX(20deg);
    -webkit-transform: rotateX(20deg);
    transform: rotateX(20deg);
  }

  #window .button.inline {
    width: auto;
    display: inline-block;
  }

  #window .button.load-btn .default, #window .button.load-btn .load-state {
    -moz-transition-duration: 0.2s;
    -o-transition-duration: 0.2s;
    -webkit-transition-duration: 0.2s;
    transition-duration: 0.2s;
  }

  #window .button.load-btn .load-state {
    position: absolute;
    top: -50px;
    left: 0;
    right: 0;
    height: 100%;
    text-align: center;
    line-height: 50px;
    pointer-events: none;
  }

  #window .button.load-btn .load-state .ball {
    height: 10px;
    width: 10px;
    margin-right: 10px;
    background: #FFF;
    display: inline-block;
    -moz-border-radius: 1000px;
    -webkit-border-radius: 1000px;
    border-radius: 1000px;
    -webkit-animation: ballBounce 0.3s alternate infinite;
    -moz-animation: ballBounce 0.3s alternate infinite;
    animation: ballBounce 0.3s alternate infinite;
    -webkit-animation-timing-function: cubic-bezier(0.2, 0.4, 0.3, 1);
    -moz-animation-timing-function: cubic-bezier(0.2, 0.4, 0.3, 1);
    animation-timing-function: cubic-bezier(0.2, 0.4, 0.3, 1);
    -moz-transition-duration: 0.3s;
    -o-transition-duration: 0.3s;
    -webkit-transition-duration: 0.3s;
    transition-duration: 0.3s;
  }

  #window .button.load-btn .load-state .ball:nth-child(2n) {
    -webkit-animation-delay: 0.05s;
    -moz-animation-delay: 0.05s;
    animation-delay: 0.05s;
  }

  #window .button.load-btn .load-state .ball:nth-child(3n) {
    -webkit-animation-delay: 0.1s;
    -moz-animation-delay: 0.1s;
    animation-delay: 0.1s;
  }

  #window .button.load-btn .load-state .ball:last-child {
    margin-right: 0;
  }

  #window .button.load-btn.done .ball {
    opacity: 0;
  }

  #window .button.load-btn.loading .default {
    -moz-transform: translateY(50px);
    -ms-transform: translateY(50px);
    -webkit-transform: translateY(50px);
    transform: translateY(50px);
    opacity: 0;
  }

  #window .button.load-btn.loading .load-state {
    -moz-transform: translateY(50px);
    -ms-transform: translateY(50px);
    -webkit-transform: translateY(50px);
    transform: translateY(50px);
  }

  .browser-warning {
    background: rgba(0, 0, 0, 0.7);
    padding: 15px 0;
    color: #FFF;
    position: absolute;
    top: 50px;
    left: 0;
    right: 0;
    margin: auto;
    width: 530px;
    text-align: center;
    -moz-border-radius: 3px;
    -webkit-border-radius: 3px;
    border-radius: 3px;
    -moz-transition-duration: 0.5s;
    -o-transition-duration: 0.5s;
    -webkit-transition-duration: 0.5s;
    transition-duration: 0.5s;
    -moz-transition-timing-function: cubic-bezier(0.015, 0.695, 0.34, 1.365);
    -o-transition-timing-function: cubic-bezier(0.015, 0.695, 0.34, 1.365);
    -webkit-transition-timing-function: cubic-bezier(0.015, 0.695, 0.34, 1.365);
    transition-timing-function: cubic-bezier(0.015, 0.695, 0.34, 1.365);
  }

  .browser-warning.hidden {
    opacity: 0;
    -moz-transform: scale(0.2);
    -ms-transform: scale(0.2);
    -webkit-transform: scale(0.2);
    transform: scale(0.2);
  }

  .fadeIn {
    -webkit-animation: 1s fadeIn cubic-bezier(0.015, 0.695, 0.34, 1.365) backwards;
    -moz-animation: 1s fadeIn cubic-bezier(0.015, 0.695, 0.34, 1.365) backwards;
    animation: 1s fadeIn cubic-bezier(0.015, 0.695, 0.34, 1.365) backwards;
    -webkit-animation-delay: 1.5s;
    -moz-animation-delay: 1.5s;
    animation-delay: 1.5s;
  }

  .delay1 {
    -webkit-animation-delay: 1.6s;
    -moz-animation-delay: 1.6s;
    animation-delay: 1.6s;
  }

  .delay2 {
    -webkit-animation-delay: 1.7s;
    -moz-animation-delay: 1.7s;
    animation-delay: 1.7s;
  }

  .delay3 {
    -webkit-animation-delay: 1.8s;
    -moz-animation-delay: 1.8s;
    animation-delay: 1.8s;
  }

  .delay4 {
    -webkit-animation-delay: 1.9s;
    -moz-animation-delay: 1.9s;
    animation-delay: 1.9s;
  }

  @-webkit-keyframes window {
    0% {
      -moz-transform: scale(0) rotateX(360deg);
      -ms-transform: scale(0) rotateX(360deg);
      -webkit-transform: scale(0) rotateX(360deg);
      transform: scale(0) rotateX(360deg);
      height: 150px;
      width: 150px;
    }

    50% {
      -moz-transform: none;
      -ms-transform: none;
      -webkit-transform: none;
      transform: none;
      height: 150px;
    }

    75% {
      height: 350px;
      width: 150px;
    }

    100% {
      width: 450px;
    }
  }

  @-moz-keyframes window {
    0% {
      -moz-transform: scale(0) rotateX(360deg);
      -ms-transform: scale(0) rotateX(360deg);
      -webkit-transform: scale(0) rotateX(360deg);
      transform: scale(0) rotateX(360deg);
      height: 150px;
      width: 150px;
    }

    50% {
      -moz-transform: none;
      -ms-transform: none;
      -webkit-transform: none;
      transform: none;
      height: 150px;
    }

    75% {
      height: 350px;
      width: 150px;
    }

    100% {
      width: 450px;
    }
  }

  @keyframes window {
    0% {
      -moz-transform: scale(0) rotateX(360deg);
      -ms-transform: scale(0) rotateX(360deg);
      -webkit-transform: scale(0) rotateX(360deg);
      transform: scale(0) rotateX(360deg);
      height: 150px;
      width: 150px;
    }

    50% {
      -moz-transform: none;
      -ms-transform: none;
      -webkit-transform: none;
      transform: none;
      height: 150px;
    }

    75% {
      height: 350px;
      width: 150px;
    }

    100% {
      width: 450px;
    }
  }

  @-webkit-keyframes fadeIn {
    0% {
      opacity: 0;
      -moz-transform: translateY(30px);
      -ms-transform: translateY(30px);
      -webkit-transform: translateY(30px);
      transform: translateY(30px);
    }

    100% {
      opacity: 1;
      -moz-transform: none;
      -ms-transform: none;
      -webkit-transform: none;
      transform: none;
    }
  }

  @-moz-keyframes fadeIn {
    0% {
      opacity: 0;
      -moz-transform: translateY(30px);
      -ms-transform: translateY(30px);
      -webkit-transform: translateY(30px);
      transform: translateY(30px);
    }

    100% {
      opacity: 1;
      -moz-transform: none;
      -ms-transform: none;
      -webkit-transform: none;
      transform: none;
    }
  }

  @keyframes fadeIn {
    0% {
      opacity: 0;
      -moz-transform: translateY(30px);
      -ms-transform: translateY(30px);
      -webkit-transform: translateY(30px);
      transform: translateY(30px);
    }

    100% {
      opacity: 1;
      -moz-transform: none;
      -ms-transform: none;
      -webkit-transform: none;
      transform: none;
    }
  }

  @-webkit-keyframes ballBounce {
    0% {
      -moz-transform: translateY(25%);
      -ms-transform: translateY(25%);
      -webkit-transform: translateY(25%);
      transform: translateY(25%);
    }

    100% {
      -moz-transform: translateY(-75%);
      -ms-transform: translateY(-75%);
      -webkit-transform: translateY(-75%);
      transform: translateY(-75%);
    }
  }

  @-moz-keyframes ballBounce {
    0% {
      -moz-transform: translateY(25%);
      -ms-transform: translateY(25%);
      -webkit-transform: translateY(25%);
      transform: translateY(25%);
    }

    100% {
      -moz-transform: translateY(-75%);
      -ms-transform: translateY(-75%);
      -webkit-transform: translateY(-75%);
      transform: translateY(-75%);
    }
  }

  @keyframes ballBounce {
    0% {
      -moz-transform: translateY(25%);
      -ms-transform: translateY(25%);
      -webkit-transform: translateY(25%);
      transform: translateY(25%);
    }

    100% {
      -moz-transform: translateY(-75%);
      -ms-transform: translateY(-75%);
      -webkit-transform: translateY(-75%);
      transform: translateY(-75%);
    }
  }


</style>
