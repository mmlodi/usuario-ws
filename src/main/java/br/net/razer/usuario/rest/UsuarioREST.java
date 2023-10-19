package br.net.razer.usuario.rest;


import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    static {
        lista.add(new Usuario(1, "administr", "admin", "admin", "ADMIN"));
        lista.add(new Usuario(2, "gerent", "gerent", "gerent", "GERENT"));
        lista.add(new Usuario(3, "function", "func", "func", "func"));
    }

}
