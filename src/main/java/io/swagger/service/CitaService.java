package io.swagger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.data.repository.AlumnoRepository;
import io.swagger.data.repository.CitaRepository;
import io.swagger.model.Alumno;
import io.swagger.model.Cita;
import java.util.Optional;

@Service
public class CitaService {
    @Autowired
    private final CitaRepository citaRepository; // Si se le agrega final, se debe realizar la inyeccion
    @Autowired
    private final AlumnoRepository alumnoRepository;

    public CitaService(CitaRepository citaRepository, AlumnoRepository alumnoRepository) {
        this.citaRepository = citaRepository;
        this.alumnoRepository = alumnoRepository;
    }

    // Agregamos un metodo para crear una cita
    public Cita createCita(Cita cita, Long idLong) {
        Integer i = Integer.valueOf(idLong.intValue()); // Pasa de Long a Integer
        if (cita != null) {
            System.out.println("Buscamos al alumno con el ID:" + i);
            Optional<Alumno> optionalAlumno = alumnoRepository.findById(i);
            if (optionalAlumno.isPresent()) {
                Alumno alumno = optionalAlumno.get(); // Obtiene el objeto Alumno de Optional si está presente
                // Realiza operaciones con el objeto Alumno
                System.out.println("\nAl parecer se encontro al Alumno con el nombre: " + alumno.getNombres());
                cita.setAlumno(alumno); // No se si esto es correcto , en teoria se asigna el alumno
                return this.citaRepository.save(cita);
                // NOTA: LA CREACION DE LA CITA SIRVE PERO HAY QUE MAPPEAR BIEN LAS RESPUESTAS.
                // APARECE COMO 201 CUANDO NO SE HA CREADO LA CITA
                // SI SE LE PASA UN ID INEXISTENTE NO ALMACENA EN LA BASE
                // SI SE LE PASA UN ID EXISTENTE SI ALMACENA EN LA BASE PERO
                // GUARDA EL ID COMO NULL.
            } else {
                // No se encontró un Alumno con el ID proporcionado
                return null;
            }
        } else {
            return null;
        }
    }
}
