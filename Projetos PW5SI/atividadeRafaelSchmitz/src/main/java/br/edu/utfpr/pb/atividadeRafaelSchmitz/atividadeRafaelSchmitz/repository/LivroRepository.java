package br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.repository;

import br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long>{


    List<Livro> findByAutorNomeContainsOrderByAno(String autor);

    List<Livro> findByAno(int ano);

    List<Livro> findByGeneroDescricaoContainsOrderByAno(String descricao);

    List<Livro> findByIsbnContainsOrNomeContainsOrderByAno(String isbn, String nome);

    List<Livro> findByValorGreaterThanEqual(Double valor);

    List<Livro> findByValorBetweenOrderByAno(Double valorMin, Double valorMax);



}
