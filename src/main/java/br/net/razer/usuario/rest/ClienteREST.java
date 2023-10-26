package br.net.razer.usuario.rest;

import java.util.ArrayList;
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

import br.net.razer.usuario.dto.ClienteDTO;
import br.net.razer.usuario.model.Cliente;
import br.net.razer.usuario.repository.ClienteRepository;

@CrossOrigin
@RestController
public class ClienteREST {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private ModelMapper mapper;

    public static List<Cliente> listaClientes = new ArrayList<>();

    @GetMapping("/clientes/{id}")
    public ClienteDTO getById(@PathVariable("id") Integer id){
        List<Cliente> listaCliente = repo.findAll();
        Cliente cliente = listaCliente.stream().filter(usu -> usu.getId() == id).findAny().orElse(null);
        if (cliente != null){
            return mapper.map(cliente, ClienteDTO.class);
        }else{
            return null;
        }
    }

    @GetMapping("/clientes")
    public List<ClienteDTO> getAllClients(){

        List<Cliente> lista = repo.findAll();     

        return lista.stream().map(e -> mapper.map(e,ClienteDTO.class)).collect(Collectors.toList());
    }

    @PostMapping("/clientes")
    public ClienteDTO inserir(@RequestBody ClienteDTO cliente) {
        // salva a Entidade convertida do DTO
        repo.save(mapper.map(cliente, Cliente.class));      
        // busca o usuário inserido
        Optional<Cliente> usu = repo.findById(cliente.getId());
        // retorna o DTO equivalente à entidade
        return mapper.map(usu, ClienteDTO.class);
    }

    @PutMapping("/clientes/{id}")
    public ClienteDTO updateUser(@PathVariable Integer id,@RequestBody ClienteDTO cliente){
        Optional<Cliente> optionalUsuario = repo.findById(id);

        if(optionalUsuario.isPresent()){
            Cliente existingCliente = optionalUsuario.get();

            existingCliente.setCpf(cliente.getCpf());
            existingCliente.setNome(cliente.getNome());
            existingCliente.setSobrenome(cliente.getSobrenome());

            repo.save(existingCliente);

            return mapper.map(existingCliente , ClienteDTO.class);
        }else{
            return null;
        }

    }

    @DeleteMapping("/clientes/{id}")
    public ClienteDTO deletaCliente(@PathVariable Integer id){
        Optional<Cliente> optionalCliente = repo.findById(id);

        if(optionalCliente.isPresent()){
            repo.deleteById(id);
        }

        return mapper.map(optionalCliente , ClienteDTO.class);
    }

}
