package br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.service;


import br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.model.Genero;

import java.util.List;

public interface GeneroService extends CrudService<Genero, Long>{

	List<Genero> findByNomeLike(String nome);
}

