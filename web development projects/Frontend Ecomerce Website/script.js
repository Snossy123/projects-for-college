let user_info = document.getElementById('userInfo');
let user = document.getElementById('user');
let sign = document.getElementById('sign');
let logout = document.getElementById('logout');
let username = localStorage.getItem('username');
let product_sec = document.querySelector('.products_section');

if(username){
  sign.remove();
  user_info.style = 'display: inline-flex;\n' +
    '  margin: 3px';
  user.innerHTML = username;
}

logout.onclick = function (){

  localStorage.clear();
  window.location.href="register.html";
}






//products section
let products =[{
  img:'https://images.unsplash.com/photo-1593642702821-c8da6771f0c6?ixid=MnwxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=889&q=80"',
  titel:'Dell XPS 2021',
  desc:'High-quality and meticulously crafted from premium materials like machined aluminum and carbon fiber, XPS laptops are durable and beautiful.',
  price:'3000$'},{
  img:'https://images.unsplash.com/photo-1588382472578-8d8b337b277a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80',
  titel:'Dell XPS 2021',
  desc:'High-quality and meticulously crafted from premium materials like machined aluminum and carbon fiber, XPS laptops are durable and beautiful.',
  price:'3000$'},{
  img:'https://images.unsplash.com/photo-1604005366359-2f8f2a044336?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80',
  titel:'Dell XPS 2021',
  desc:'High-quality and meticulously crafted from premium materials like machined aluminum and carbon fiber, XPS laptops are durable and beautiful.',
  price:'3000$'},{
  img:'https://images.unsplash.com/photo-1542978709-19c95dc3bc7e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=667&q=80',
  titel:'Dell XPS 2021',
  desc:'High-quality and meticulously crafted from premium materials like machined aluminum and carbon fiber, XPS laptops are durable and beautiful.',
  price:'3000$'}]

function drawProductUI(){
  let productUI = products.map((item)=>{
    return `
        <div class="product">
          <div class="product_img"><img alt="#" src="${item.img}"></div>
          <div class="details">
            <div class="product_description">
              <h1>"${item.titel}"</h1>
              <p>"${item.desc}"</p>
              <span><b>"${item.price}"</b></span>
            </div>

            <div class="product_action">
              <button class="addToCard">Buy
                <i class="fas fa-cart-plus"></i>
                </button>
               <i class="far fa-heart"></i>
            </div>
          </div>
        </div>
    `
  })
  product_sec.innerHTML = productUI;
}

drawProductUI();

var customerView =[
  {
    img: "https://image.freepik.com/free-photo/people-emotions-beauty-lifestyle-concept-cheerful-woman-expresses-happiness-expresses-friendly-attitude-being-polite-talking-with-customer-wears-spectacles-poloneck-feels-very-glad_273609-42883.jpg",
    name: "Doha Mohamed",
    review: "Modern Man Giving a Smiling Emoticon Rating,Client's Satisfaction Surveys on Mobile Phone. Front"
  },{
    img:'https://image.freepik.com/free-photo/happy-young-woman-holding-shopping-bags-yellow-wall_231208-10092.jpg',
    name:'Essra Khaled',
    review:'Customer Experiences Concept. Modern Man Giving a Smiling Emoticon Rating, Positive Review via Smartphone.'
  },{
    img:'https://image.freepik.com/free-photo/beautilful-young-woman-carrying-shopping-bags-with-credit-card-isolated-white-background_231208-1851.jpg',
    name:'Saad Waleed',
    review:'Customer Experiences Concept. Modern Man Giving a Smiling Emoticon Rating, Positive Review via Smartphone.'
  },{
    img:'https://image.freepik.com/free-photo/full-length-portrait-happy-excited-girl-bright-colorful-clothes-holding-shopping-bags-while-standing-showing-peace-gesture-isolated_231208-5946.jpg',
    name:'Shawkat Sayed',
    review:'Customer Experiences Concept. Modern Man Giving a Smiling Emoticon Rating, Positive Review via Smartphone.'
}
]

function drawCustomerUI(cust){
  // document.getElementById('custom_review').style.display='flex';
  document.getElementById('cust_name').textContent=cust.name;
  document.getElementById('cust_img').src=cust.img;
  document.getElementById('p_review').textContent=cust.review;
}
var ind = 0;
drawCustomerUI(customerView[ind]);

var leftarow = document.getElementById("leftarwo");
var rightarow = document.getElementById("rightarwo");



leftarow.addEventListener('click',function () {
  if(ind == customerView.length-1 )
    ind = -1;
  ind++;
  drawCustomerUI(customerView[ind]);

});


rightarow.addEventListener('click',function () {
  if(ind == 0)
    ind =customerView.length;
  ind--;
  drawCustomerUI(customerView[ind]);
});


