let username = document.querySelector("#username");
let useremail = document.querySelector("#email");
let userpassword = document.querySelector("#password");
let sign_up = document.querySelector("#sign_up");
sign_up.addEventListener("click", function (){

  if(username.value===""||useremail.value===""||userpassword.value===""){
    alert("please fill data");
  }
  else {
    localStorage.setItem("username",username.value);
    localStorage.setItem("email",useremail.value);
    localStorage.setItem("password",userpassword.value);
    setTimeout(()=> {
      window.location.href="login.html";
      },1500);
  }
} );
