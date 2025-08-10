package com.william.spring.turnoreserva.turno_reserva.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.william.spring.turnoreserva.turno_reserva.model.Comercio;
import com.william.spring.turnoreserva.turno_reserva.model.Servicio;
import com.william.spring.turnoreserva.turno_reserva.model.Turno;
import com.william.spring.turnoreserva.turno_reserva.repository.ServicioRepository;
import com.william.spring.turnoreserva.turno_reserva.repository.TurnoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TurnoServiceImpl implements TurnoService {

    private final ServicioRepository servicioRepository;
    private final TurnoRepository turnoRepository;
    private final JdbcTemplate jdbcTemplate;
 

    @Override
    public List<Turno> generarTurnos(LocalDate fechaInicio, LocalDate fechaFin, Integer idServicio) {
        Servicio servicio = servicioRepository.findById(idServicio)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        Comercio comercio = servicio.getComercio();
        List<Turno> turnosGenerados = new ArrayList<>();

        for (LocalDate fecha = fechaInicio; !fecha.isAfter(fechaFin); fecha = fecha.plusDays(1)) {

        String call = "CALL generar_turnos(?, ?, ?)";
        jdbcTemplate.update(call, fechaInicio, fechaFin, idServicio);




            LocalTime horaActual = servicio.getHoraApertura();
            while (horaActual.plusMinutes(servicio.getDuracionMinutos()).isBefore(servicio.getHoraCierre()) ||
                   horaActual.plusMinutes(servicio.getDuracionMinutos()).equals(servicio.getHoraCierre())) {

                Turno turno = new Turno();
                turno.setServicio(servicio);
                turno.setComercio(comercio);
                turno.setFecha(fecha);
                turno.setHoraInicio(horaActual);
                turno.setHoraFin(horaActual.plusMinutes(servicio.getDuracionMinutos()));

                turnosGenerados.add(turnoRepository.save(turno));

                horaActual = horaActual.plusMinutes(servicio.getDuracionMinutos());
            }
        }

        return turnosGenerados;
    }


    @Override
    public List<Turno> obtenerTurnosExistentes() {
        return turnoRepository.findAll();
        //throw new UnsupportedOperationException("Unimplemented method 'obtenerTurnosExistentes'");
    }
}