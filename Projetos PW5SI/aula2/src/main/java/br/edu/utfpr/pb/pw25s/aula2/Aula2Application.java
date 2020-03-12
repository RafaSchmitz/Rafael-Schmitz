package br.edu.utfpr.pb.pw25s.aula2;

<<<<<<< Updated upstream
=======
import br.edu.utfpr.pb.pw25s.aula2.br.edu.utfpr.model.Categoria;
import br.edu.utfpr.pb.pw25s.aula2.repository.CategoriaRepository;
>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

<<<<<<< Updated upstream
import br.edu.utfpr.pb.pw25s.aula2.model.Categoria;
import br.edu.utfpr.pb.pw25s.aula2.repository.CategoriaRepository;

@SpringBootApplication
public class Aula2Application implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
=======
import java.sql.SQLOutput;

@SpringBootApplication
public class Aula2Application implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

>>>>>>> Stashed changes
	public static void main(String[] args) {
		SpringApplication.run(Aula2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
<<<<<<< Updated upstream
	
		Categoria c1 = new Categoria();
		c1.setDescricao("Categoria Teste");
		
		salvarCategoria(c1);
		c1.setDescricao("Games");
		salvarCategoria(c1);
		
		Categoria c2 = new Categoria();
		c2.setDescricao("Eletrônicos");
		salvarCategoria(c2);
		
		listarCategorias();
	}
	
	
	private void salvarCategoria(Categoria categoria) {
		
		this.categoriaRepository.save(categoria);
		
		System.out.println("Categoria salva com sucesso! \n " + categoria );
	}
	
	private void listarCategorias() {
		System.out.println("\n *********** LISTA DE CATEGORIAS ***********");
		this.categoriaRepository.findAll().forEach(c -> 
			System.out.println(c)
		);
		System.out.println("\n *********** LISTA DE CATEGORIAS ***********");
	}
	
	
=======
		Categoria c1 = new Categoria();
		c1.setDescricao("Categoria 1");

		SalvarCategoria(c1);

		Categoria c2 = new Categoria();
		c2.setDescricao("Categoria 2");

		SalvarCategoria(c2);

		ListarCategoria();

		ListarCategoriaByDescricao();
	}

	public void SalvarCategoria(Categoria categoria){

		this.categoriaRepository.save(categoria);
		System.out.println("Categoria salva com sucesso! \n" + categoria);

	}

	private void ListarCategoria(){
		System.out.println("**********************Lista de Categorias**********************");
		this.categoriaRepository.findAll().forEach(c -> System.out.println(c));
	}

	private void ListarCategoriaByDescricao(){
		System.out.println("**********************Lista de Categorias Like**********************");
		this.categoriaRepository.findByDescricaoLike("%2%").forEach(c -> System.out.println(c));
	}
>>>>>>> Stashed changes
}
