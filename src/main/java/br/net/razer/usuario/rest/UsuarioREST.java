package br.net.razer.usuario.rest;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.net.razer.usuario.model.Usuario;
import br.net.razer.usuario.repository.UsuarioRepository;

@CrossOrigin
@RestController
public class UsuarioREST {
    
    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private ModelMapper mapper;

    public static List<Usuario> lista = new ArrayList<>();
    
    //metodos
    @GetMapping("/usuarios")
    public List<UsuarioDTO> listarTodos() {
        List<Usuario> lista = repo.findAll();

        // Converte lista de Entity para lista DTO
        return lista.stream().map(e -> mapper.map(e,UsuarioDTO.class))
        .collect(Collectors.toList());
    }

    @GetMapping("/usuarios/{id}")
    public Usuario getUserById( @PathVariable("id") int id){
        Usuario user = lista.stream().filter(usu -> usu.getId() == id).findAny().orElse(null);
        return user;
    }

    @PostMapping("/usuarios")
    public UsuarioDTO inserir(@RequestBody UsuarioDTO usuario) {

        // salva a Entidade convertida do DTO
        repo.save(mapper.map(usuario, Usuario.class));
        
        // busca o usuário inserido
        Usuario usu = repo.findById(usuario.getId());
        // retorna o DTO equivalente à entidade
        return mapper.map(usu, UsuarioDTO.class);

    }

    @PutMapping("/usuarios/{id}")
    public UsuarioDTO updateUser(@PathVariable Long id,@RequestBody UsuarioDTO usuario){
        Optional<Usuario> optionalUsuario = repo.findById(id);

        if(optionalUsuario.isPresent()){
            Usuario existingUsuario = optionalUsuario.get();

            existingUsuario.setLogin(usuario.getLogin());
            existingUsuario.setNome(usuario.getNome());
            existingUsuario.setPerfil(usuario.getPerfil());
            existingUsuario.setSenha(usuario.getSenha());

            repo.save(existingUsuario);

            return mapper.map(existingUsuario , UsuarioDTO.class);
        }else{
            return null;
        }

    }

}
