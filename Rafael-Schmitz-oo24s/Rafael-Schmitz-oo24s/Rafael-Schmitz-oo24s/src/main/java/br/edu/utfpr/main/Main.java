/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.main;

import br.edu.utfpr.dao.CategoriaDao;
import br.edu.utfpr.dao.CidadeDao;
import br.edu.utfpr.dao.ClienteDao;
import br.edu.utfpr.dao.EnderecoClienteDao;
import br.edu.utfpr.dao.ProdutoDao;
import br.edu.utfpr.dao.QuartoDao;
import br.edu.utfpr.dao.ReservaQuartoClienteDao;
import br.edu.utfpr.model.Categoria;
import br.edu.utfpr.model.Cidade;
import br.edu.utfpr.model.Cliente;
import br.edu.utfpr.model.Contatos;
import br.edu.utfpr.model.EnderecoCliente;
import br.edu.utfpr.model.Produto;
import br.edu.utfpr.model.Quarto;
import br.edu.utfpr.model.ReservaQuartoCliente;
import br.edu.utfpr.model.TipoQuarto;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Rafa
 */
public class Main {

    public static void main(String[] args) {
        new Main();
        System.exit(0);
    }

    public Main() {
        //Inserindo Cliente e contato
        inserirClienteContato();
        inserirClienteEndereco();
        inserirCategoria();
        inserirProduto();
        inserirReservaQuarto();
    }

    private void inserirClienteContato() {
        try {
            ClienteDao clienteDao = new ClienteDao();

            Cliente cliente = new Cliente();
            cliente.setNome("Cliente teste Contato");
            cliente.setCpf("22233344455");
            cliente.setRg("123456789");
            cliente.setNumPassaporte("12345678");

            cliente.setContatos(new ArrayList<>());

            Contatos c1 = new Contatos();
            c1.setCliente(cliente);
            c1.setNome("Rafael");
            c1.setTelefone("46 3333 2222");
            c1.setEmail("Rafasch@live.com");

            cliente.getContatos().add(c1);

            clienteDao.insert(cliente);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void inserirCategoria() {
        CategoriaDao categoriaDao = new CategoriaDao();

        Categoria categoria = new Categoria();
        categoria.setDescricao("Categoria 1");

        try {
            categoriaDao.insert(categoria);
            System.out.println("Categoria: " + categoria.getId() + " inserida com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void inserirProduto() {
        ProdutoDao produtoDao = new ProdutoDao();

        Produto produto = new Produto();
        produto.setNome("Monitor 20pol.");
        produto.setDescricao("Descrição do monitor de 20pol.");
        produto.setValor(100D);

        CategoriaDao categoriaDao = new CategoriaDao();
        //Categoria categoria = categoriaDao.getById( 1 );
        //produto.setCategoria( categoria );
        produto.setCategoria(categoriaDao.getOne(1));

        // INSERT SEGUNDO PRODUTO
        Produto p2 = new Produto();
        p2.setNome("Produto 2");
        p2.setDescricao("Descrição do Produto 2...");
        p2.setValor(999D);
        p2.setCategoria(categoriaDao.getOne(1));

        try {
            produtoDao.insert(produto);
            System.out.println("Produto " + produto.getId()
                    + " inserido com sucesso!");
            // INSERT SEGUNDO PRODUTO
            produtoDao.insert(p2);
            System.out.println("Produto " + p2.getId()
                    + " inserido com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void inserirReservaQuarto() {

    }

    private void inserirClienteEndereco() {
        try {
            ClienteDao clienteDao = new ClienteDao();
            CidadeDao cidadeDao = new CidadeDao();
            QuartoDao quartoDao = new QuartoDao();
            EnderecoClienteDao enderecoClienteDao = new EnderecoClienteDao();
            ReservaQuartoClienteDao reservaQuartoClienteDao = new ReservaQuartoClienteDao();
                    

            Cliente cliente = new Cliente();
            cliente.setNome("Cliente teste Endereco");
            cliente.setCpf("00000000001");
            cliente.setRg("123456789");
            cliente.setNumPassaporte("12345678");

            cliente.setContatos(new ArrayList<>());

            Contatos c1 = new Contatos();
            c1.setCliente(cliente);
            c1.setNome("Rafaels");
            c1.setTelefone("46 3333 2222");
            c1.setEmail("Rafasch@live.com");

            EnderecoCliente e1 = new EnderecoCliente();

            e1.setRua("Tocantins");
            e1.setBairro("Centro");
            e1.setCidade(cidadeDao.getOne(3775));
            e1.setCliente(cliente);
            
            Quarto quarto = new Quarto();
            
            quarto.setNumQuarto(01);
            quarto.setQtdCamas(01);
            quarto.setQtdPessoas(01);
            quarto.setTipoQuarto(TipoQuarto.ECONOMICO);
                       
            cliente.setEnderecoCliente(e1);

            cliente.getContatos().add(c1);

            clienteDao.insert(cliente); 
            quartoDao.insert(quarto);
            System.out.println("Cliente Endereço: " + cliente.getId() + " inserido com sucesso!");
            
            ReservaQuartoCliente reservaQuartoCliente = new ReservaQuartoCliente();
            reservaQuartoCliente.setCliente(cliente);
            reservaQuartoCliente.setQuarto(quarto);
            reservaQuartoCliente.setDtReserva(LocalDate.now());
            reservaQuartoCliente.setDtIni(LocalDate.now());
            reservaQuartoCliente.setMotivo("Ferias");
            reservaQuartoCliente.setVlrDiaria(200D);
            
            
            reservaQuartoClienteDao.insert(reservaQuartoCliente);
            
            System.out.println("Reserva: " + reservaQuartoCliente.getId() + " efetuada com sucesso!");
           

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
