package br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.service.impl;

import br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.model.Genero;
import br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.repository.GeneroRepository;
import br.edu.utfpr.pb.atividadeRafaelSchmitz.atividadeRafaelSchmitz.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GeneroServiceImpl extends CrudServiceImpl<Genero, Long>
	implements GeneroService {

	@Autowired
	private GeneroRepository generoRepository;
	
	@Override
	protected JpaRepository<Genero, Long> getRepository() {
		return generoRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Genero> findByNomeLike(String nome) {
		return generoRepository.findByNomeLike(nome);
	}

}
