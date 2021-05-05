
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




