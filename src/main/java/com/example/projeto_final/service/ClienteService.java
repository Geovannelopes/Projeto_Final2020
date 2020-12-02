package com.example.projeto_final.service;

import java.util.List;
import java.util.Optional;

import com.example.projeto_final.dto.ClienteDTO;
import com.example.projeto_final.model.Cliente;
import com.example.projeto_final.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository repositorio;

    public Cliente fromDTO(ClienteDTO dto){
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEndereco(dto.getEndereco());
        cliente.setCpf(dto.getCpf());
        return cliente;
    }

    public List<Cliente> getAllClientes(){
        return repositorio.getAllClientes();
    }

    public Cliente getClienteByCodigo( int codigo){
        Optional<Cliente> op = repositorio.getClienteByCodigo(codigo);
        return op.orElseThrow( () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,"Cliente não cadastrado" + codigo
                             ));
    }

    public Cliente save(Cliente cliente){
        return repositorio.save(cliente);
    }

    public void removeByCodigo(int codigo){
        Cliente cliente = getClienteByCodigo(codigo);
        
        if(cliente.getReservas() == null)
            repositorio.remove(cliente);
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Não é possível deletar o cliente... Você não quer que ele roube seu carro, não é mesmo???"));    
        
    }

    public Cliente update(Cliente cliente){

        getClienteByCodigo(cliente.getCodigo());

        return repositorio.update(cliente);
    }
}
