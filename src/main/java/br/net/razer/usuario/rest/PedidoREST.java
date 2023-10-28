package br.net.razer.usuario.rest;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.net.razer.usuario.dto.PedidoDTO;
import br.net.razer.usuario.model.ItemDoPedido;
import br.net.razer.usuario.model.Pedido;
import br.net.razer.usuario.model.Produto;
import br.net.razer.usuario.repository.PedidoRepository;
import br.net.razer.usuario.repository.ProdutoRepository;

@CrossOrigin
@RestController
public class PedidoREST {
    @Autowired
    private PedidoRepository repo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProdutoRepository produtoService;

    @GetMapping("/pedidos/")
    public List<PedidoDTO> listarTodos() {
        List<Pedido> lista = repo.findAll();

        return lista.stream().map(e -> mapper.map(e,PedidoDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/pedidos/{cpf}")
    public List<PedidoDTO> listarPorCpf(@PathVariable("cpf") String cpf) {
        List<Pedido> lista = repo.findByCliente_Cpf(cpf);

        return lista.stream().map(e -> mapper.map(e,PedidoDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/pedidos/produto/{id}")
    public List<PedidoDTO> listarPorIdProduto(@PathVariable("id") Integer id) {
        List<Pedido> lista = repo.findByItems_Produto_Id(id);

        return lista.stream().map(e -> mapper.map(e,PedidoDTO.class)).collect(Collectors.toList());
    }

    @PostMapping("/pedidos/")
    public PedidoDTO inserir(@RequestBody PedidoDTO pedido) {
        Pedido novoPedido = new Pedido();
        novoPedido.setCliente(pedido.getCliente());
        novoPedido.setData(new Date());
        novoPedido.getItems().addAll(pedido.getItems()
            .stream()
            .map(item -> {
                Produto p = produtoService.findById(item.getProduto().getId()).get();
                ItemDoPedido i = new ItemDoPedido();
                i.setProduto(p);
                i.setPedido(novoPedido);
                i.setQuantidade(item.getQuantidade());
                return i;
            })
            .collect(Collectors.toList())
        );

        return mapper.map(repo.save(novoPedido), PedidoDTO.class);
    }
}
