package io.swagger.data.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.data.jpa.repository.JpaRepository;
import io.swagger.model.Cita;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    // Encuentra una cita a partir de una fecha y hora
    public Cita findByFechaAndHora(LocalDate fecha, LocalTime hora);

}
