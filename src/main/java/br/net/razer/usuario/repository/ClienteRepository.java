package br.net.razer.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.net.razer.usuario.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Integer>{
}
