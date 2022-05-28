let username = localStorage.getItem('username');// not need word value to give value username direct
let pass = localStorage.getItem('password');
//let sign = document.getElementById('sign');
let getUserName = document.getElementById('username');
let getPassword = document.getElementById('password');
let sign_in = document.querySelector('#sign_in');

sign_in.addEventListener('click' , function (){
  if(getUserName.value ===""||getPassword.value ==="")
  {
    alert("please fill data");
  }
  else{
    console.log(username.value  ,  pass.value)
    if(username  ===getUserName.value &&  pass  ===getPassword.value  ){
      window.location.href="index.html";
     }
    else {
      alert("username or password is wrong??..");
    }
  }
});
