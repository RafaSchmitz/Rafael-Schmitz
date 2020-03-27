package br.edu.utfpr.pb.aula3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.pb.aula3.model.Serie;

public interface SerieRepository 
			extends JpaRepository<Serie, Long>{

}
