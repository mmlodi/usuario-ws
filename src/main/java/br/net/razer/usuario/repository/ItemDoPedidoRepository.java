package br.net.razer.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.net.razer.usuario.model.ItemDoPedido;
import br.net.razer.usuario.model.ItemDoPedidoKey;

public interface ItemDoPedidoRepository extends JpaRepository<ItemDoPedido, ItemDoPedidoKey> {
    
}
