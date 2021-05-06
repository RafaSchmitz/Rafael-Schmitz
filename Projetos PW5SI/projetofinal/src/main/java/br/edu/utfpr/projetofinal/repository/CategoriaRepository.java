package br.edu.utfpr.projetofinal.repository;

import br.edu.utfpr.projetofinal.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}