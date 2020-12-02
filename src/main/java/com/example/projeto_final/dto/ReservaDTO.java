package com.example.projeto_final.dto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.example.projeto_final.model.Cliente;
import com.example.projeto_final.model.Reserva;
import com.example.projeto_final.model.Veiculo;
import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;



public class ReservaDTO {

    @NotNull
    @Future(message = "Data de Incio tem que ser após a data atual")
    @JsonFormat( pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;
    
    @NotNull
    @Future(message = "Data de Incio tem que ser após a data atual")
    @JsonFormat( pattern = "dd/MM/yyyy")
    private LocalDate dataFim;
    
    private Cliente cliente;
    private Veiculo veiculo;

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Optional<Reserva> criarReserva(){
        if(dataFim.isBefore(dataInicio)) {
            return Optional.empty();
        }

        if(dataFim.getDayOfWeek().equals(DayOfWeek.SUNDAY))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Data de entrega não pode ser em um domingo, Graças a deus"));
        if(dataInicio.getDayOfWeek().equals(DayOfWeek.SUNDAY))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Data de Inicio não pode ser em um domingo, Graças a deus"));


        Reserva reserva = new Reserva(dataInicio, dataFim, cliente, veiculo);
        return Optional.of(reserva);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}       
        
   
        
   
        
   
        
   
        
   
        
