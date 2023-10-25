package br.net.razer.usuario.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.net.razer.usuario.dto.ProdutoDTO;
import br.net.razer.usuario.model.Produto;
import br.net.razer.usuario.repository.ProdutoRepository;

@CrossOrigin
@RestController
public class ProdutoREST {
    
    @Autowired
    private ProdutoRepository repo;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/produtos/{id}")
    public ProdutoDTO buscarPorId(@PathVariable("id") int id){
        Produto produto = repo.findById(id).get();

        return mapper.map(produto, ProdutoDTO.class);
    }
}
