package com.example.projeto_final.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public class ClienteDTO {
    
    @NotBlank(message = "O Nome é necessario")
    @Length(min=4,max = 200, message = "Nome mínimo de 4 e o máximo de 200 caracteres!")
    private String nome;

    @NotBlank(message = "Endereço é obrigatorio!")
    @Length(min=4,max = 200, message = "Endereço mínimo de 4 e o máximo de 200 caracteres!")
    private String endereco;

    @NotBlank(message = "CPF é obrigatorio!")
    @CPF
    private String cpf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
}
