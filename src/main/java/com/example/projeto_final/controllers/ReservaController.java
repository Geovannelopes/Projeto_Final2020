package com.example.projeto_final.controllers;

import com.example.projeto_final.model.Reserva;
import com.example.projeto_final.service.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    
    @Autowired
    private ReservaService reservaService;

    @GetMapping("/{codigo}")
    public ResponseEntity<Reserva> getReservaByCodigo(@PathVariable int codigo){
        Reserva aux = reservaService.getReservaByCodigo(codigo);
        return ResponseEntity.ok(aux);
    }
}
