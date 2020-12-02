package com.example.projeto_final.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.projeto_final.dto.ClienteDTO;
import com.example.projeto_final.dto.ReservaDTO;
import com.example.projeto_final.model.Cliente;
import com.example.projeto_final.model.Reserva;
import com.example.projeto_final.service.ClienteService;
import com.example.projeto_final.service.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteServico;

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Cliente> getClientes(){
        return clienteServico.getAllClientes();
    }
    
    @GetMapping("/{codigo}")
    public ResponseEntity<Cliente> getClienteByCodigo(@PathVariable int codigo){
        Cliente aux = clienteServico.getClienteByCodigo(codigo);
        return ResponseEntity.ok(aux);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ClienteDTO novoCliente, HttpServletRequest request,
    UriComponentsBuilder builder){

        Cliente aux = clienteServico.save(clienteServico.fromDTO(novoCliente));

        UriComponents uriComponents = builder.path(request.getRequestURI()+ "/" + aux.getCodigo()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }


   
    @PostMapping("/{codigoCliente}/veiculos/{codigoVeiculo}")
    public ResponseEntity<Reserva> saveReserva(@PathVariable int codigoCliente, 
    @PathVariable int codigoVeiculo, @Valid @RequestBody ReservaDTO novaReserva,
    HttpServletRequest request, UriComponentsBuilder builder) {

        Reserva reserva = reservaService.save(codigoCliente, codigoVeiculo, novaReserva);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + 
        reserva.getCodigo()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping("/{codigo}/reservas")
    public List<Reserva> getReservaCliente (@PathVariable int codigo){
        Cliente cliente = clienteServico.getClienteByCodigo(codigo);
        return cliente.getReservas();
    }
    
    @PutMapping("/{codigo}")
    public ResponseEntity<Cliente> update(@PathVariable int codigo, @RequestBody ClienteDTO clienteDTO) {
         
        Cliente cliente = clienteServico.fromDTO(clienteDTO);
        cliente.setCodigo(codigo);
        cliente = clienteServico.update(cliente);
        return ResponseEntity.ok(cliente);
    }
   
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> remove(@PathVariable int codigo){
        clienteServico.removeByCodigo(codigo);
        return ResponseEntity.noContent().build();
    }

}
