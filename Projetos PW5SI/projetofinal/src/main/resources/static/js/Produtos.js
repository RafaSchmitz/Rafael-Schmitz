const items = [
    {
        id:0,
        nome: "HP Chromebook 11",
        valor: 199.99,
        img: "../img/products/chrome-book-11.jpg",
        qtd: 0
    },

    {
        id:1,
        nome: "HP Chromebook 14",
        valor: 209.99,
        img: "../img/products/chrome-book-14.jpg",
        qtd: 0
    },

    {
        id:2,
        nome: "Asus Chromebook",
        valor: 299.99,
        img: "../img/products/chrome-book-asus.jpg",
        qtd: 0
    },

    {
        id:3,
        nome: "iPad Air",
        valor: 449.99,
        img: "../img/products/ipad-air.jpg",
        qtd: 0
    },

    {
        id:4,
        nome: "iPad Mini",
        valor: 399.99,
        img: "../img/products/ipad-mini.jpg",
        qtd: 0
    },

    {
        id:5,
        nome: "Mi Pad 2",
        valor: 199.99,
        img: "../img/products/mi-pad-2.jpg",
        qtd: 0
    },

    {
        id:6,
        nome: "Surface Pro",
        valor: 199.99,
        img: "../img/products/surface-pro.jpg",
        qtd: 0
    },

    {
        id:7,
        nome: "Lenovo Yoga",
        valor: 199.99,
        img: "../img/products/lenovo-yoga.jpg",
        qtd: 0
    },

    {
        id:8,
        nome: "ASUS Transformer",
        valor: 199.99,
        img: "../img/products/asus-transformer.jpg",
        qtd: 0
    }

]

function inicializar(){
    var containerProdutos = document.getElementById('Products');
    items.map((val)=>{
        containerProdutos.innerHTML += `

              <div class="col-sm-6 col-md-4 product">
              <a href="#favorites" class="favorites" data-favorite="inactive"><i class="ion-ios-heart-outline"></i></a>
              <a href="./"><img src="`+val.img+`"/></a>

              <div class="content">
                <h1 class="h4">`+val.nome+`</h1>
                <p class="price">R$`+val.valor+`</p>
                <label>Laptops</label>

                <a href="../product/" class="btn btn-link"> Details</a>
                <button class="btn btn-primary btn-rounded btn-sm" key="`+val.id+`" name="addCart"> <i class="ion-bag"></i> Add to cart</button>
              </div>
            </div>

          `;

    })
}
inicializar();

function atualizarCarrinho(){
    var containerCarrinho = document.getElementById('Cart');
    containerCarrinho.innerHTML = "";
    items.map((val)=>{
        if(val.qtd > 0) {
            containerCarrinho.innerHTML += `

          <div class="media">
            <div class="media-left">
              <a href="#">
                <img class="media-object" src="`+val.img+`" alt=`+val.nome+`/>
              </a>
            </div>
            <div class="media-body">
              <h2 class="h4 media-heading">`+val.nome+`</h2>
              <label>Laptops</label>
              <p class="price">R$`+val.valor+`</p>
            </div>
            <div class="controls">
              <div class="input-group">
                <span class="input-group-btn">
                  <button class="btn btn-default btn-sm " name="cart-item-qtdMenos" type="button" data-action="minus"><i class="ion-minus-round"></i></button>
                </span>
                <input type="text" class="form-control input-sm cart-item-qtd" placeholder="Qty" value="1" readonly="">
                <span class="input-group-btn">
                  <button class="btn btn-default btn-sm " name="cart-item-qtdMais"  type="button" data-action="plus"><i class="ion-plus-round"></i></button>
                </span>
              </div><!-- /input-group -->

              <a href="#remove"> <i class="ion-trash-b"></i> Remove </a>
            </div>
          </div>
          
        
          `;
        }


    })
}


var links = document.getElementsByName('addCart');

for (i = 0; i< links.length; i++){
    links[i].addEventListener("click", function (){

        let key = this.getAttribute('key');
        items[key].qtd++;

        atualizarCarrinho();


    })
}




