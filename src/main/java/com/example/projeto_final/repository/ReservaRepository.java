package com.example.projeto_final.repository;

import java.util.ArrayList;
import java.util.Optional;

import com.example.projeto_final.model.Cliente;
import com.example.projeto_final.model.Reserva;
import com.example.projeto_final.model.Veiculo;

import org.springframework.stereotype.Component;
@Component
public class ReservaRepository {
    
    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    private static int nextNumero = 1;

    public Optional<Reserva> getReservaByCodigo(int codigo){
        for(Reserva aux : reservas){
            if(aux.getCodigo() == codigo){
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }
    
    public Optional<Reserva> save(Reserva reserva, Cliente cliente, Veiculo veiculo){
        ArrayList<Reserva> reservasVeiculo = veiculo.getReservas();
        if(!reservasVeiculo.isEmpty()){
            for(Reserva aux : reservasVeiculo){
                if(aux.getDataFim().isAfter(reserva.getDataInicio()))
                    return Optional.empty();
            }
        }
        
        reserva.setCodigo(nextNumero++);
        reservas.add(reserva);
        veiculo.addReserva(reserva);
        cliente.addReserva(reserva);
        return Optional.of(reserva);
        
    }
}
