package br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz;

import br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.model.*;
import br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AtividadeRafaelSchmitzApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AtividadeRafaelSchmitzApplication.class, args);
	}

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private AutorRepository autorRepository;

	@Autowired
	private EditoraRepository editoraRepository;

	@Autowired
	private GeneroRepository generoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Override
	public void run(String... args) throws Exception {

		//AUTOR
		Autor autor1 = new Autor();
		autor1.setNome("Rafael Schmitz");
		salvarAutor(autor1);

		Autor autor2 = new Autor();
		autor2.setNome("João das Neves");
		salvarAutor(autor2);

		//CIDADE
		Cidade cidade1 = new Cidade();
		cidade1.setNome("Pato Branco");
		salvarCidade(cidade1);

		Cidade cidade2 = new Cidade();
		cidade2.setNome("Francisco Beltrão");
		salvarCidade(cidade2);

		//EDITORA
		Editora editora1 = new Editora();
		editora1.setNome("Cultura");
		salvarEditora(editora1);

		Editora editora2 = new Editora();
		editora2.setNome("Popular");
		salvarEditora(editora2);

		//GENERO

		Genero genero1 = new Genero();
		genero1.setDescricao("Ficção");
		salvarGenero(genero1);

		Genero genero2 = new Genero();
		genero2.setDescricao("Romance");
		salvarGenero(genero2);

		//LIVRO
		Livro livro1 = new Livro();
		livro1.setNome("O Alquimista");
		livro1.setAno(2012);
		livro1.setIsbn("1234567891233");
		livro1.setValor(123.2);
		livro1.setAutor(autor1);
		livro1.setCidade(cidade1);
		livro1.setEditora(editora1);
		livro1.setGenero(genero1);
		salvarLivro(livro1);

		Livro livro2 = new Livro();
		livro2.setNome("O Parasita");
		livro2.setAno(2014);
		livro2.setIsbn("0000000000000");
		livro2.setValor(120.0);
		livro2.setAutor(autor2);
		livro2.setCidade(cidade2);
		livro2.setEditora(editora2);
		livro2.setGenero(genero2);
		salvarLivro(livro2);

		listarLivrosfindByAutorNomeContainsOrderByAno();
		listarLivrosfindByAno();
		listarLivrosfindByGeneroDescricaoContainsOrderByAno();
		listarLivrosfindByIsbnContainsOrNomeContainsOrderByAno();
		listarLivrosfindByValorGreaterThanEqual();



	}

	private void salvarAutor(Autor autor) {

		this.autorRepository.save(autor);

		System.out.println("Autor salvo com sucesso! \n " + autor);
	}

	private void salvarGenero(Genero genero) {

		this.generoRepository.save(genero);

		System.out.println("Genero salvo com sucesso! \n " + genero);
	}

	private void salvarEditora(Editora editora) {

		this.editoraRepository.save(editora);

		System.out.println("Editora salva com sucesso! \n " + editora);
	}

	private void salvarLivro(Livro livro) {

		this.livroRepository.save(livro);

		System.out.println("Livro salvo com sucesso! \n " + livro);
	}

	private void salvarCidade(Cidade cidade) {

		this.cidadeRepository.save(cidade);

		System.out.println("Cidade salva com sucesso! \n " + cidade);
	}

	private void listarLivrosfindByAutorNomeContainsOrderByAno() {

		System.out.println("\n *********** LISTA de Livros por nome do Autor ***********");

		this.livroRepository.findByAutorNomeContainsOrderByAno("a")
				.forEach(p -> System.out.println(p));

		System.out.println("\n *********** LISTA de Livros por nome do Autor  ***********");

	}

	private void listarLivrosfindByAno() {

		System.out.println("\n *********** LISTA de Livros por ano***********");

		this.livroRepository.findByAno(2014)
				.forEach(p -> System.out.println(p));

		System.out.println("\n *********** LISTA de Livros por ano***********");

	}

	private void listarLivrosfindByGeneroDescricaoContainsOrderByAno() {

		System.out.println("\n *********** LISTA de Livros por descricao do genero ***********");

		this.livroRepository.findByGeneroDescricaoContainsOrderByAno("Ficção")
				.forEach(p -> System.out.println(p));

		System.out.println("\n *********** LISTA de Livros por descricao do genero  ***********");

	}

	private void listarLivrosfindByIsbnContainsOrNomeContainsOrderByAno() {

		System.out.println("\n *********** LISTA DE Produtos por isbn e titulo ***********");

		this.livroRepository.findByIsbnContainsOrNomeContainsOrderByAno("3", "Alquimista")
				.forEach(p -> System.out.println(p));

		System.out.println("\n *********** LISTA DE Produtos por isbn e titulo ***********");

	}

	private void listarLivrosfindByValorGreaterThanEqual() {

		System.out.println("\n *********** LISTA DE Livros com anos maiores que ***********");

		this.livroRepository.findByValorGreaterThanEqual(121.0)
				.forEach(p -> System.out.println(p));

		System.out.println("\n *********** ISTA DE Livros com anos maiores que ***********");

	}

	private void listarLivrosfindByValorBetweenOrderByAno() {

		System.out.println("\n *********** LISTA DE Livros com valor entre ***********");

		this.livroRepository.findByValorBetweenOrderByAno(121.0,124.0)
				.forEach(p -> System.out.println(p));

		System.out.println("\n *********** ISTA DE Livros com valor entre ***********");

	}

}
