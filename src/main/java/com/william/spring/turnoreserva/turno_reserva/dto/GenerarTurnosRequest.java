package com.william.spring.turnoreserva.turno_reserva.dto;

import lombok.Data;

@Data
public class GenerarTurnosRequest {
    private String fechaInicio;
    private String fechaFin;
    private Integer idServicio;
}
