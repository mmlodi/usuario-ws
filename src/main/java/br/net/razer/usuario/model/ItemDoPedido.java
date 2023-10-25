package br.net.razer.usuario.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name="item_do_pedido")
public class ItemDoPedido implements Serializable {
    @EmbeddedId
    private ItemDoPedidoKey id = new ItemDoPedidoKey();

    @ManyToOne
    @MapsId("idProduto")
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idPedido")
    @JoinColumn(name = "id_pedido")
    @JsonBackReference
    private Pedido pedido;

    @Column(name = "quantidade")
    private int quantidade;

    public ItemDoPedido(ItemDoPedidoKey id, Produto produto, Pedido pedido, int quantidade) {
        this.id = id;
        this.produto = produto;
        this.pedido = pedido;
        this.quantidade = quantidade;
    }
    public ItemDoPedido() {
    }
    public ItemDoPedidoKey getId() {
        return id;
    }
    public void setId(ItemDoPedidoKey id) {
        this.id = id;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}