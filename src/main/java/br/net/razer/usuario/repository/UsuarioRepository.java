package br.net.razer.usuario.repository;

import br.net.razer.usuario.model.Usuario;

import org.springframework.data.jpa.repository.*;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    public Usuario findByLogin(String login);
}