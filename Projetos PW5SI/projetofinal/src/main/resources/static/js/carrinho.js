let pedidoProdutos = [];

function adicionarProduto() {
    let pedidoProduto = new Object();
    pedidoProduto.id = new Object();
    pedidoProduto.id.produto = new Object();

    pedidoProduto.id.produto.id = $('#ProductId').data('id');
    pedidoProduto.id.produto.nome = $('#ProductName').data('nome');
    pedidoProduto.valor = $('#ProductValor').data('valor');
    pedidoProduto.quantidade = $('#quantidade').val();

    pedidoProdutos.push(pedidoProduto);
    adicionarLinhaCarrinho( criarLinha(pedidoProduto) );
}

function removerProduto() {
    // TO-DO
}

function criarLinha(pedidoProduto) {
    return `
    <tr id="${pedidoProduto.id.produto.id}">
    	<td name="produto"><span>${pedidoProduto.id.produto.nome}</span></td>
		<td name="quantidade" style="text-align: right"><span>${pedidoProduto.quantidade}</span></td>
    	<td name="valor" style="text-align: right">
    		<span>${new Intl.NumberFormat('pt-BR',{ style: 'currency', currency: 'BRL'})
        .format(pedidoProduto.valor)}</span>
    	</td>
    	<td name="valor" style="text-align: right">
    		<span>${new Intl.NumberFormat('pt-BR',{ style: 'currency', currency: 'BRL'})
        .format(pedidoProduto.valor * pedidoProduto.quantidade)}</span>
    	</td>
    	<td><a onclick="removerProduto(this, event);"><i class="fa fa-trash ml-2" title="Remover produto" data-toggle="tooltip"></i></a></td>
    </tr>
    `;

}

function adicionarLinhaCarrinho(linha) {

    if ($('#tbCompraProdutos tbody') == 0)
        $('#tbCompraProdutos').append('<tbody> </tbody>');

    $('#tbCompraProdutos tbody').append(linha);
}

function finalizarCompra(urlDestino) {
    let compra = new Object();
    compra.compraProdutos = compraProdutos;
    compra.fornecedor = new Object();
    compra.fornecedor.id = $('#fornecedor').val();
    compra.notaFiscal = $('#notaFiscal').val();
    compra.observacoes = $('#observacoes').val();

    $.ajax({
        type: $('#frm').attr('method'),
        url: $('#frm').attr('action'),
        contentType : 'application/json',
        data : JSON.stringify(compra),
        success: function() {
            Swal.fire({
                title: 'Salvo!',
                text: 'Registro salvo com sucesso!',
                type: 'success'
            }).then((result) => {
                    window.location = urlDestino;
                }
            );//FIM swal()
        },
        error: function(data) {
            console.log(data);
            Swal.fire('Errou!', 'Falha ao salvar registro!', 'error');
        }
    }); //FIM ajax()
    return false;
 }


