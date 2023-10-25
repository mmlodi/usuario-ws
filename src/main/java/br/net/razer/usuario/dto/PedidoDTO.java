package br.net.razer.usuario.dto;

import java.util.Date;
import java.util.List;

import br.net.razer.usuario.model.Cliente;
import br.net.razer.usuario.model.ItemDoPedido;

public class PedidoDTO {
    private int id;
    private Date data;
    private Cliente cliente;
    private List<ItemDoPedido> items;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public List<ItemDoPedido> getItems() {
        return items;
    }
    public void setItems(List<ItemDoPedido> items) {
        this.items = items;
    }

}
