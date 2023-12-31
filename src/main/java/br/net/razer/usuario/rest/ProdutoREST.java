package br.net.razer.usuario.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.net.razer.usuario.dto.ProdutoDTO;
import br.net.razer.usuario.model.Produto;
import br.net.razer.usuario.repository.ProdutoRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ProdutoREST {
    
    @Autowired
    private ProdutoRepository repo;

    @Autowired
    private ModelMapper mapper;

    @GetMapping(value = "/produtos/{id}", produces = "application/json;charset=UTF-8")
    public ProdutoDTO buscarPorId(@PathVariable("id") int id){
        Produto produto = repo.findById(id).get();

        return mapper.map(produto, ProdutoDTO.class);
    }

    @GetMapping(value = "/produtos/", produces = "application/json;charset=UTF-8")
    public List<ProdutoDTO> getAllProducts(){

        List<Produto> lista = repo.findAll();     

        return lista.stream().map(e -> mapper.map(e,ProdutoDTO.class)).collect(Collectors.toList());
    }

    @PostMapping(value = "/produtos/", produces = "application/json;charset=UTF-8")
    public ProdutoDTO inserir(@RequestBody ProdutoDTO produto) {
        // salva a Entidade convertida do DTO
        Produto p = mapper.map(produto, Produto.class);
        repo.save(p);      
        // busca o usuário inserido
        Optional<Produto> produt = repo.findById(p.getId());
        // retorna o DTO equivalente à entidade
        return mapper.map(produt, ProdutoDTO.class);
    }


    @PutMapping(value = "/produtos/{id}", produces = "application/json;charset=UTF-8")
    public ProdutoDTO updateUser(@PathVariable int id,@RequestBody ProdutoDTO produto){
        Optional<Produto> optionalProduto = repo.findById(id);

        if(optionalProduto.isPresent()){
            Produto existingProduto = optionalProduto.get();

            existingProduto.setDescricao(produto.getDescricao());

            repo.save(existingProduto);

            return mapper.map(existingProduto , ProdutoDTO.class);
            
        }else{
            return null;
        }

    }

    @DeleteMapping(value = "/produtos/{id}", produces = "application/json;charset=UTF-8")
    public ProdutoDTO deletaProduto(@PathVariable Integer id){
        Optional<Produto> optionalProduto = repo.findById(id);

        if(optionalProduto.isPresent()){
            repo.deleteById(id);
        }

        return mapper.map(optionalProduto , ProdutoDTO.class);
    }
}
