package io.swagger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import io.swagger.model.Alumno;
import io.swagger.model.AlumnoDTOLogin;
import io.swagger.model.AlumnoDTOid;
import io.swagger.model.AlumnosLoginBody;
import io.swagger.data.repository.AlumnoRepository;
import java.util.function.Consumer;

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

    public List<AlumnoDTOid> getAllAlumnos() {
        List<Alumno> alumnoList = alumnoRepository.findAll();
        List<AlumnoDTOid> listaFinal = new ArrayList<>();
        for (Alumno alumno : alumnoList) {
            AlumnoDTOid varTemporal = new AlumnoDTOid();
            varTemporal.setId(alumno.getId());
            varTemporal.setMatricula(alumno.getMatricula());
            varTemporal.setNombres(alumno.getNombres());
            varTemporal.setApellidoPaterno(alumno.getApellidoPaterno());
            varTemporal.setApellidoMaterno(alumno.getApellidoMaterno());
            varTemporal.setGenero(alumno.getGenero());
            varTemporal.setEmail(alumno.getEmail());
            varTemporal.setTelefonoMovil(alumno.getTelefonoMovil());
            listaFinal.add(varTemporal);
        }
        return listaFinal;
    }

    public AlumnoDTOLogin loginAlumno(AlumnosLoginBody aLogin) {
        boolean encontrado = false;
        // Traer la lista y comparar con los elementos para ver si estan los datos
        AlumnoDTOLogin alumnoFinal = new AlumnoDTOLogin();
        List<Alumno> alumnoList = alumnoRepository.findAll();
        for (Alumno alumno : alumnoList) {
            // compareTo regresa entero, si es 0 quiere decir que son iguales
            if (alumno.getMatricula().compareTo(aLogin.getMatricula()) == 0
                    && alumno.getPassword().compareTo(aLogin.getPassword()) == 0) {
                alumnoFinal.setNombre(alumno.getNombres());
                alumnoFinal.setApellidoPaterno(alumno.getApellidoPaterno());
                alumnoFinal.setApellidoMaterno(alumno.getApellidoMaterno());
                alumnoFinal.setMatricula(alumno.getMatricula());
                alumnoFinal.setId(alumno.getId());
                encontrado = true;
            }
        }
        if (encontrado == true) {
            return alumnoFinal;
        } else {
            return null;
        }
    }// Termina metodo AlumnoDTOLogin

}// Termina clase AlumnoService.
