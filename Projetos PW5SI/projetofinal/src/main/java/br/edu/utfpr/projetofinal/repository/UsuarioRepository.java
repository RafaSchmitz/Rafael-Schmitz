package br.edu.utfpr.projetofinal.repository;

import br.edu.utfpr.projetofinal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);

}