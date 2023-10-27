package br.net.razer.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.net.razer.usuario.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
