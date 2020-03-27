package br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.repository;

import br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
    List<Genero> findByNomeLike(String nome);
}
