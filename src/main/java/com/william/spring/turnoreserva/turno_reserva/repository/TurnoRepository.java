package com.william.spring.turnoreserva.turno_reserva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.william.spring.turnoreserva.turno_reserva.model.Turno;

public interface TurnoRepository extends JpaRepository<Turno, Integer> {
    List<Turno> findByServicioIdServicio(Integer idServicio);
}
