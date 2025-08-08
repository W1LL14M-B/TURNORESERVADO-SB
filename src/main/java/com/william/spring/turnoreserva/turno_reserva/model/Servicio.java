package com.william.spring.turnoreserva.turno_reserva.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.AllArgsConstructor;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalTime;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "servicio")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idServicio;

    @ManyToOne
    @JoinColumn(name = "id_comercio", nullable = false)
    private Comercio comercio;

    private String nombre;

    private LocalTime horaApertura;
    private LocalTime horaCierre;
    private Integer duracionMinutos;
}
