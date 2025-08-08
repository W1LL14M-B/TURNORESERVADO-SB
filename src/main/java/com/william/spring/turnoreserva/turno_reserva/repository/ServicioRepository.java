package com.william.spring.turnoreserva.turno_reserva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.william.spring.turnoreserva.turno_reserva.model.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
    List<Servicio> findByComercioIdComercio(Integer idComercio);
}
