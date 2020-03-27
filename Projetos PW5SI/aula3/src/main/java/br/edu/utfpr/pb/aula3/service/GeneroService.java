package br.edu.utfpr.pb.aula3.service;

import java.util.List;

import br.edu.utfpr.pb.aula3.model.Genero;

public interface GeneroService extends CrudService<Genero, Long>{

	List<Genero> findByNomeLike(String nome);
}
