package com.william.spring.turnoreserva.turno_reserva.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.william.spring.turnoreserva.turno_reserva.dto.GenerarTurnosRequest;
import com.william.spring.turnoreserva.turno_reserva.model.Turno;
import com.william.spring.turnoreserva.turno_reserva.repository.ComercioRepository;
import com.william.spring.turnoreserva.turno_reserva.repository.ServicioRepository;
import com.william.spring.turnoreserva.turno_reserva.service.TurnoService;

import lombok.RequiredArgsConstructor;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/turnos")
@RequiredArgsConstructor
public class TurnoController {

    private final TurnoService turnoService;
    private final ComercioRepository comercioRepository;
    private final ServicioRepository servicioRepository;

    @GetMapping("/comercios")
    public List<?> getComercios() {
        return comercioRepository.findAll();
    }

    @GetMapping("/servicios/{idComercio}")
    public List<?> getServiciosPorComercio(@PathVariable Integer idComercio) {
        return servicioRepository.findByComercioIdComercio(idComercio);
    }



    @PostMapping("/generar")
    public List<Turno> generarTurnos(@RequestBody GenerarTurnosRequest request) {
        return turnoService.generarTurnos(
                LocalDate.parse(request.getFechaInicio()),
                LocalDate.parse(request.getFechaFin()),
                request.getIdServicio()
        );
    }
}
