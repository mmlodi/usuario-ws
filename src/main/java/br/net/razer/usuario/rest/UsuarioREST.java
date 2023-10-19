package br.net.razer.usuario.rest;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.net.razer.usuario.model.Usuario;

@CrossOrigin
@RestController
public class UsuarioREST {
    public static List<Usuario> lista = new ArrayList<>();
    
    //metodos
    @GetMapping("/usuarios")
    public List<Usuario> getAllUsers(){
        return lista;
    }

    @GetMapping("/usuarios/{id}")
    public Usuario getUserById( @PathVariable("id") int id){
        Usuario user = lista.stream().filter(usu -> usu.getId() == id).findAny().orElse(null);
        return user;
    }

    @PostMapping("/usuario")
    public Usuario inserirUsuario(@RequestBody Usuario usuario){
        Usuario user = lista.stream().max(Comparator.comparing(Usuario::getId)).orElse(null);
        if (user == null) user.setId(1);
        else 
            user.setId(user.getId() + 1);
        lista.add(user);    

        return user;
        
    }

    static {
        lista.add(new Usuario(1, "administr", "admin", "admin", "ADMIN"));
        lista.add(new Usuario(2, "gerent", "gerent", "gerent", "GERENT"));
        lista.add(new Usuario(3, "function", "func", "func", "func"));
    }

}
