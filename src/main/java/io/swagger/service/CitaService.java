package io.swagger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.data.repository.AlumnoRepository;
import io.swagger.data.repository.CitaRepository;
import io.swagger.data.repository.PsiquiatraRepository;
import io.swagger.model.Alumno;
import io.swagger.model.Cita;
import io.swagger.model.Psiquiatra;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Service
public class CitaService {
    @Autowired
    private final CitaRepository citaRepository; // Si se le agrega final, se debe realizar la inyeccion
    @Autowired
    private final AlumnoRepository alumnoRepository;
    @Autowired
    private final PsiquiatraRepository psiquiatraRepository;

    public CitaService(CitaRepository citaRepository, AlumnoRepository alumnoRepository,
            PsiquiatraRepository psiquiatraRepository) {
        this.citaRepository = citaRepository;
        this.alumnoRepository = alumnoRepository;
        this.psiquiatraRepository = psiquiatraRepository;
    }

    // Agregamos un metodo para crear una cita
    public Cita createCita(Cita cita, String matricula) {
        // Integer idAlu = Integer.valueOf(idLong.intValue()); // Pasa de Long a Integer
        String numT = cita.getNumTrabajador();
        if (cita != null) {
            // System.out.println("Buscamos al alumno con el ID: " + i);
            // Optional<Alumno> optionalAlumno = alumnoRepository.findById(i);
            if (buscaID(matricula, numT)) {
                Optional<Alumno> optionalAlumno = alumnoRepository.findById(matricula);
                Alumno alumno = optionalAlumno.get(); // Obtiene el objeto Alumno de Optional si está presente
                Optional<Psiquiatra> optionalPsiquiatra = psiquiatraRepository.findById(numT);
                Psiquiatra psiquiatra = optionalPsiquiatra.get();
                // Realiza operaciones con el objeto Alumno
                System.out.println("\nAl parecer se encontro al Alumno con el id:  " + alumno.getMatricula());
                System.out.println("\n El alumno tiene nombre: " + alumno.getNombre());
                cita.setAlumno(alumno); // Se almacena la referencia del alumno
                cita.setPsiquiatra(psiquiatra); // Se almacena la referencia del Psiquiatra
                System.out.println("Corroborandp la info, en cita se tiene: " + cita.getAlumno().getNombre() +
                        "\n Ese es el nombre del alumno");
                return this.citaRepository.save(cita);
            } else {
                // No se encontró un Alumno con el ID proporcionado
                return null;
            }
        } else {
            return null;
        }
    }

    public List<Cita> getAllCita() {
        List<Cita> citasList = citaRepository.findAll();
        List<Cita> listaFinal = new ArrayList<>();
        for (Cita cita : citasList) {
            Cita varcitas = new Cita();
            varcitas.setId(cita.getId());
            varcitas.setFecha(cita.getFecha());
            varcitas.setHora(cita.getHora());
            varcitas.setNumTrabajador(cita.getNumTrabajador());
            varcitas.setMotivoCita(cita.getMotivoCita());
            varcitas.setDiscapacidad(cita.getDiscapacidad());
            varcitas.setComunidadIndigena(cita.getComunidadIndigena());
            varcitas.setMigrante(cita.getMigrante());
            listaFinal.add(varcitas);
        }
        return listaFinal;
    }

    // Verifico si existe el alumno con el ID
    public boolean buscaID(String matricula, String NumTrabajador) {
        System.out.println("Buscamos al alumno con el ID: " + matricula);
        Optional<Alumno> optionalAlumno = alumnoRepository.findById(matricula);
        Optional<Psiquiatra> optionalPsiquiatra = psiquiatraRepository.findById(NumTrabajador);
        if (optionalAlumno.isPresent() && optionalPsiquiatra.isPresent()) {
            return true;
        } else {
            System.out.println("No se encontro al alumno o al Psiquiatra con dicho ID");
            return false;
        }
    }

    public boolean deleteCita(String matricula, long idCita) {
        List<Cita> citasList = citaRepository.findAll();
        for (Cita cita : citasList) {
            if (cita.getAlumno().getMatricula().compareTo(matricula) == 0
                    && cita.getId().compareTo(idCita) == 0) {
                System.out.println("Se encontro al alumno con matricula " +
                        matricula + " cuyo id de cita a eliminar es: " + idCita);
                citaRepository.deleteById(idCita); // Elimina la cita
                return true;
            }
        }
        return false;
    }

    // Obtiene una cita dada una matricula y su ID de cita.
    public Cita getCitaById(String matricula, Long idCita) {
        List<Cita> citasList = citaRepository.findAll();
        Cita res = new Cita();
        for (Cita cita : citasList) {
            if (cita.getAlumno().getMatricula().compareTo(matricula) == 0
                    && cita.getId().compareTo(idCita) == 0) {
                return res = cita; // La cita encontrada es igual al alumno.
            }
        }
        return null;
    }

    public Cita updateByCita(Cita citaEdit, String matricula, Long idCita) {
        List<Cita> citasList = citaRepository.findAll();
        for (Cita cita : citasList) {
            if (cita.getAlumno().getMatricula().compareTo(matricula) == 0
                    && cita.getId().compareTo(idCita) == 0) {
                citaRepository.save(citaEdit); // El problema es que no se accede al ID y crea otro.
                return citaEdit;

            }
        }
        return null;
    }
}
