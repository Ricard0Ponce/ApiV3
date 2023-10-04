package io.swagger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.model.Alumno;
import io.swagger.data.repository.AlumnoRepository;

@Service
public class AlumnoService {

    @Autowired
    private final AlumnoRepository alumnoRepository; // Si se quita el final, la Inyeccion se genera sola
    /*
     * Nota: Si usas final, requieres hacer la inyeccion manualmente
     */

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public Alumno createAlumno(Alumno alumno) {
        return this.alumnoRepository.save(alumno);
    }

}
