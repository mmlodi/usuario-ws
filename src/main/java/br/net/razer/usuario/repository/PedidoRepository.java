package br.net.razer.usuario.repository;

import br.net.razer.usuario.model.Pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    public List<Pedido> findByCliente_Cpf(String cpf);
    public List<Pedido> findByItems_Produto_Id(Integer id);
}
