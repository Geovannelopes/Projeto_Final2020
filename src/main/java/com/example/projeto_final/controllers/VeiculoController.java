package com.example.projeto_final.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.projeto_final.dto.VeiculoDTO;
import com.example.projeto_final.model.Reserva;
import com.example.projeto_final.model.Veiculo;
import com.example.projeto_final.service.VeiculoService;

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
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoServico;

    @GetMapping
    public List<Veiculo> getClientes(){
        return veiculoServico.getAllVeiculos();
    }
    
    @GetMapping("/{codigo}")
    public ResponseEntity<Veiculo> getClienteByCodigo(@PathVariable int codigo){
        Veiculo aux = veiculoServico.getVeiculoBycodigo(codigo);
        return ResponseEntity.ok(aux);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Veiculo> update(@PathVariable int codigo, @RequestBody VeiculoDTO veiculoDTO) {
         
        Veiculo veiculo = veiculoServico.fromDTO(veiculoDTO);
        veiculo.setCodigo(codigo);
        veiculo = veiculoServico.update(veiculo);
        return ResponseEntity.ok(veiculo);
    } 
    
    @GetMapping("/{codigo}/reservas")
    public List<Reserva> getReservaCliente (@PathVariable int codigo){
        Veiculo veiculo = veiculoServico.getVeiculoBycodigo(codigo);
        return veiculo.getReservas();
    }


    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody Veiculo novoVeiculo, HttpServletRequest request,
    UriComponentsBuilder builder){

        Veiculo aux = veiculoServico.save(novoVeiculo);

        UriComponents uriComponents = builder.path(request.getRequestURI()+ "/" + aux.getCodigo()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> remove(@PathVariable int codigo){
        veiculoServico.removeByCodigo(codigo);
        return ResponseEntity.noContent().build();
    }
}
