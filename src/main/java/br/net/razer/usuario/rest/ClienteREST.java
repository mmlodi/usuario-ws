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

    @GetMapping(value = "/clientes/{id}", produces = "application/json;charset=UTF-8")
    public ClienteDTO getById(@PathVariable("id") Integer id){
        Cliente cliente = repo.findById(id).get();

        if (cliente != null){
            return mapper.map(cliente, ClienteDTO.class);
        }else{
            return null;
        }
    }

    @GetMapping(value = "/clientes/cpf/{cpf}", produces = "application/json;charset=UTF-8")
    public ClienteDTO getByCpf(@PathVariable("cpf") String cpf){
        Cliente cliente = repo.findByCpf(cpf);

        if (cliente != null){
            return mapper.map(cliente, ClienteDTO.class);
        }else{
            return null;
        }
    }

    @GetMapping(value = "/clientes/", produces = "application/json;charset=UTF-8")
    public List<ClienteDTO> getAllClients(){

        List<Cliente> lista = repo.findAll();     

        return lista.stream().map(e -> mapper.map(e,ClienteDTO.class)).collect(Collectors.toList());
    }

    @PostMapping(value = "/clientes/", produces = "application/json;charset=UTF-8")
    public ClienteDTO inserir(@RequestBody ClienteDTO cliente) {
        // salva a Entidade convertida do DTO
        Cliente c = mapper.map(cliente, Cliente.class);
        repo.save(c);      
        // busca o usuário inserido
        Optional<Cliente> usu = repo.findById(c.getId());
        // retorna o DTO equivalente à entidade
        return mapper.map(usu, ClienteDTO.class);
    }

    @PutMapping(value = "/clientes/{id}", produces = "application/json;charset=UTF-8")
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

    @DeleteMapping(value = "/clientes/{id}", produces = "application/json;charset=UTF-8")
    public ClienteDTO deletaCliente(@PathVariable Integer id){
        Optional<Cliente> optionalCliente = repo.findById(id);

        if(optionalCliente.isPresent()){
            repo.deleteById(id);
        }

        return mapper.map(optionalCliente , ClienteDTO.class);
    }

}
