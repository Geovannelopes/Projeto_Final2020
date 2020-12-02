package com.example.projeto_final.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Reserva {
    private int codigo;
    private double preco;
    private Cliente cliente;
    private Veiculo veiculo;

    @JsonFormat( pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;
    @JsonFormat( pattern = "dd/MM/yyyy")
    private LocalDate dataFim;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

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
    
    
    public Reserva(LocalDate dataInicio, LocalDate dataFim, Cliente cliente, Veiculo veiculo){
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.preco = calcularPreco();
    }
    
    public double calcularPreco(){
        long dias = dataInicio.until(dataFim, ChronoUnit.DAYS);

        preco = getVeiculo().getDiaria() * dias;
        
        return preco; 
    }

    public Reserva(){}
}
