// --------------initial products-----------------
let products = [
  {
    name: "product1",
    price: 100,
    description: "white coffe",
    code: "656",
    imgSrc: "images/product1.jpg",
    id: 1,
  },
  {
    name: "product2",
    price: 120,
    description: "white coffe",
    code: "561",
    imgSrc: "images/product2.jpg",
    id: 2,
  },
  {
    name: "product3",
    price: 550,
    description: "white coffe",
    code: "4522",
    imgSrc: "images/product3.jpg",
    id: 3,
  },
  {
    name: "product4",
    price: 140,
    description: "white coffe",
    code: "569",
    imgSrc: "images/product4.jpg",
    id: 4,
  },
  {
    name: "product5",
    price: 179,
    description: "white coffe",
    code: "548",
    imgSrc: "images/product5.jpg",
    id: 5,
  },
];
let orders = [];
var numproducts = 0;
localStorage.setItem("numproducts", numproducts);
// ------------------------add to list------------------------
function addToList(item) {
  // var item = event.cucurrentTarget.myParam;
  numproducts++;
  localStorage.setItem("numproducts", numproducts);

  var pro_shop = document.getElementById(item);
  var pro = {};
  for (let index = 0; index < products.length; index++) {
    if (products[index]["id"] === item) {
      var flage = false;
      for (let i = 0; i < orders.length; i++) {
        if (orders[i]["id"] === item) {
          orders[i]["number_pieces"] += 1;

          flage = true;
          break;
        }
      }
      if (!flage) {
        pro = products[index];
        var order = {
          name: pro["name"],
          price: pro["price"],
          description: pro["description"],
          code: pro["code"],
          imgSrc: pro["imgSrc"],
          id: pro["id"],
          number_pieces: 1,
        };
        orders.push(order);
      }

      localStorage.setItem("My_Orders", JSON.stringify(orders));
      break;
    }
  }

  var selectable = document.getElementById("bill");
  var creatrow = document.createElement("tr");
  var proName = document.createElement("td");
  var proPrice = document.createElement("td");
  proName.append(pro_shop.children[1].children[0].textContent);
  proPrice.append(pro_shop.children[1].children[1].children[0].textContent);
  creatrow.append(proName);
  creatrow.append(proPrice);
  selectable.append(creatrow);

  var total = document.getElementById("total");

  total.textContent = String(
    Number(total.textContent) +
      Number(pro_shop.children[1].children[1].children[0].textContent)
  );
}
// -------------------------continue to shooooooooopping------------------
// var conShopp = document.getElementsByClassName("btn-outline-danger");
// conShopp.addEventListener("click", function(){
//   window.location.href = "SHOP.html";
// });
// ----------------onload the page--------------
document.body.onload = function () {
  var colo = JSON.parse(localStorage.getItem("my_data"));
  if (colo != null) {
    for (let index = 0; index < colo.length; index++) {
      products.push(colo[index]);
    }
  }
  var pro_box = document.getElementById("product_box");
  var product_items = ``;
  for (let index = 0; index < products.length; index++) {
    product_items += `
        <div class="col-3 mt-5">
            <div class="card" id="${products[index]["id"]}">
              <img src="${products[index]["imgSrc"]}" class="card-img-top" />
              <div class="card-body">
                <h5 class="card-title text-center">${products[index]["name"]}</h5>
                <div class="input-group mb-3">
                  <span class="input-group-text" id="basic-addon1">${products[index]["price"]}</span>
                  <button
                    type="button"
                    class="btn btn-secondary btn-sm addToList">
                    ADD TO CART
                  </button>
                </div>
              </div>
            </div>
          </div>
        `;
  }
  console.log(window.location.href);
  if (window.location.href.indexOf("SHOP.html")) {
    pro_box.innerHTML = product_items;
  }
  var sbm = document.getElementsByClassName("addToList");
  for (let index = 0; index < sbm.length; index++) {
    sbm[index].addEventListener(
      "click",
      function () {
        addToList(products[index]["id"]);
      },
      false
    );
  }
};

localStorage.setItem("numproducts", numproducts);

totalPro = 5;
function upload_product() {
  totalPro++;
  var new_pro_name = document.getElementById("name");
  var new_pro_price = document.getElementById("price");
  var new_pro_desc = document.getElementById("description");
  var new_pro_code = document.getElementById("code");
  var new_pro_img = document.getElementById("inputGroupFile02");
  var imgSr = document.getElementById("new_img");
  var file = new_pro_img.files[0];
  var reader = new FileReader();

  reader.addEventListener(
    "load",
    function () {
      imgSr.src = reader.result;
    },
    false
  );
  if (file) reader.readAsDataURL(file);

  var newPro = {
    name: new_pro_name.value,
    price: new_pro_price.value,
    description: new_pro_desc.value,
    code: new_pro_code.value,
    imgSrc: imgSr.src,
    id: totalPro.value,
    number_pieces: 1,
  };

  products.push(newPro);
  var newProductAdded = [];
  for (let v = 5; v < products.length; v++) {
    newProductAdded.push(products[v]);
  }
  //  console.log(newPro);
  localStorage.setItem("my_data", JSON.stringify(newProductAdded));
}
