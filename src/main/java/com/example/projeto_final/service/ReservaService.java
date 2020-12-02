package com.example.projeto_final.service;

import java.util.ArrayList;
import java.util.Optional;

import com.example.projeto_final.dto.ReservaDTO;
import com.example.projeto_final.model.Cliente;
import com.example.projeto_final.model.Reserva;
import com.example.projeto_final.model.Veiculo;
import com.example.projeto_final.repository.ReservaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repositorio;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VeiculoService veiculoService;

    public Reserva fromDTO(ReservaDTO dto){
        Optional<Reserva> op = dto.criarReserva();
        
        return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data invalida, não pode voltar no tempo"));
    }

    public Reserva getReservaByCodigo(int codigo){
        Optional<Reserva> op = repositorio.getReservaByCodigo(codigo);
    return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva nao cadastrada: " + codigo));
    }
    
    public Reserva save(int codigoCliente, int codigoVeiculo, ReservaDTO reservaDTO) {
        Cliente cliente = clienteService.getClienteByCodigo(codigoCliente);
        Veiculo veiculo = veiculoService.getVeiculoBycodigo(codigoVeiculo);
        reservaDTO.setCliente(cliente);
        reservaDTO.setVeiculo(veiculo);
        Reserva reserva = fromDTO(reservaDTO);
        Optional<Reserva> op = repositorio.save(reserva, cliente, veiculo);

        return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "O carro já está reservado durante este tempo: "));
    }

    

    public ReservaDTO toDTO(Reserva reserva){
        ReservaDTO dto = new ReservaDTO();
        dto.setDataInicio(reserva.getDataInicio());  
        dto.setDataFim(reserva.getDataFim());
        return dto;
    }

    public ArrayList<ReservaDTO> toListDTO(ArrayList<Reserva> reservas){
        ArrayList<ReservaDTO> dtoList = new ArrayList<ReservaDTO>();

        for(Reserva reserva: reservas){
            dtoList.add(toDTO(reserva));
        }

        return dtoList;
    }
}
