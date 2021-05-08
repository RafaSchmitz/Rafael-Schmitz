
function atualizarCarrinho(){
    var containerCarrinho = document.getElementById('Cart');
    containerCarrinho.innerHTML = "";
    items.map((val)=>{
        if(val.qtd > 0) {
            containerCarrinho.innerHTML += `

          
        
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




