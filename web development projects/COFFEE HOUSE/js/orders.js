// ----------------onload the page--------------
document.body.onload = function () {
  // ------------------------------------------------------------------cart------------------------
  var cart = JSON.parse(localStorage.getItem("My_Orders"));
  if (cart != null) {
    var pro_cart = document.getElementById("cart_pro");
    var cart_items = ``;
    // ---------------------row of table html part--------------------
    for (let index = 0; index < cart.length; index++) {
      cart_items += `
          <tr>
              <td>${cart[index]["name"]}</td>
              <td>${cart[index]["price"]}</td>
              <td><input  class="num_pieces form-control" type="number" min="0" value="${cart[index]["number_pieces"]}"></td>
              <td>${cart[index]["description"]}</td>
              <td><img src="${cart[index]["imgSrc"]}" height="50"></td>
              <td> <button type="button" class="btn-outline-danger btn-lg btn-close" data-bs-dismiss="alert" aria-label="Close">&#x2715</button></td>
          </tr>
          `;
    }
    pro_cart.innerHTML = cart_items;
    var clb = document.getElementsByClassName("btn-close");
    var total = document.getElementById("total_cart");
    var num_priece = document.getElementsByClassName("num_pieces");
    var tot = 0; 
    // -------------------update total price -------------
    for (let ind = 0; ind < cart.length; ind++) {
              
        tot += Number(cart[ind]["price"]) * Number(num_priece[ind].value);
    }
    total.textContent = String(tot);
    for (let k = 0; k < num_priece.length; k++) {
      num_priece[k].addEventListener("change", function () {
         
          tot = 0;
          for (let ind = 0; ind < cart.length; ind++) {
              
              tot += Number(cart[ind]["price"]) * Number(num_priece[ind].value);
          }
        total.textContent = String(tot);
      });
    }

    
    // ------------------------------remove Element--------------------
    for (let index3 = 0; index3 < clb.length; index3++) {
      clb[index3].addEventListener(
        "click",
        function () {
          var newcart = [];
          for (let i = 0; i < cart.length; i++) {
            if (i != index3) {
              newcart.push(cart[i]);
            }
          }

          localStorage.setItem("My_Orders", JSON.stringify(newcart));
          window.location.href = "CART.html";
        },
        false
      );
    }
  }
};
