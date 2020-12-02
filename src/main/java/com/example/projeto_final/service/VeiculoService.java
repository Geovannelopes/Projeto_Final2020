package com.example.projeto_final.service;

import java.util.List;
import java.util.Optional;

import com.example.projeto_final.dto.VeiculoDTO;
import com.example.projeto_final.model.Veiculo;
import com.example.projeto_final.repository.VeiculoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VeiculoService {
    
    @Autowired
    private VeiculoRepository repositorio;

    public Veiculo fromDTO(VeiculoDTO dto){
        Veiculo veiculo = new Veiculo();

        veiculo.setDiaria(dto.getDiaria());

        return veiculo;
    }

    public List<Veiculo> getAllVeiculos(){
        return repositorio.getAllVeiculos();
    }

    public Veiculo getVeiculoBycodigo(int codigo){
        Optional<Veiculo> op = repositorio.getVeiculoByCodigo(codigo);
        return op.orElseThrow( () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,"Veiculo não cadastrado" + codigo
                             ));
    }

    public Veiculo save(Veiculo veiculo) {
        return repositorio.save(veiculo);
    }

    public void removeByCodigo(int codigo){
        Veiculo veiculo = getVeiculoBycodigo(codigo);
        
        if(veiculo.getReservas() == null)
            repositorio.remove(veiculo);
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Não é possível deletar o veiculo"));    
        
    }

    public Veiculo update( Veiculo veiculo){
        getVeiculoBycodigo(veiculo.getCodigo());
        return repositorio.update(veiculo);
    }
     
}
