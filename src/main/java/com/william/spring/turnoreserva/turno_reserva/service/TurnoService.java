package com.william.spring.turnoreserva.turno_reserva.service;

import java.time.LocalDate;

import java.util.List;

import com.william.spring.turnoreserva.turno_reserva.model.Turno;

public interface TurnoService {
    List<Turno> generarTurnos(LocalDate fechaInicio, LocalDate fechaFin, Integer idServicio);

    List<Turno> obtenerTurnosExistentes();
}

