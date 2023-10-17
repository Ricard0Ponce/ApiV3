package io.swagger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.data.repository.AlumnoRepository;
import io.swagger.data.repository.CitaRepository;
import io.swagger.data.repository.PsiquiatraRepository;
import io.swagger.model.Alumno;
import io.swagger.model.Cita;
import io.swagger.model.CitaDTO;
import io.swagger.model.Psiquiatra;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;

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
        LocalDate fechaBody = cita.getFecha();
        if (cita != null) {
            if (buscaID(matricula, numT) && validaFechaCita(fechaBody)) {
                Optional<Alumno> optionalAlumno = alumnoRepository.findById(matricula);
                Alumno alumno = optionalAlumno.get(); // Obtiene el objeto Alumno de Optional si está presente
                Optional<Psiquiatra> optionalPsiquiatra = psiquiatraRepository.findById(numT);
                Psiquiatra psiquiatra = optionalPsiquiatra.get();
                cita.setAlumno(alumno); // Se almacena la referencia del alumno
                cita.setPsiquiatra(psiquiatra); // Se almacena la referencia del Psiquiatra
                System.out.println("La cita se registro al alumno con matricula: " + cita.getAlumno().getMatricula());
                return this.citaRepository.save(cita);
            } else {
                // No se encontró un Alumno con el ID proporcionado
                return null;
            }
        }
        return null;
    }

    public List<CitaDTO> getAllCita() {
        List<Cita> citasList = citaRepository.findAll();
        List<CitaDTO> listaFinal = new ArrayList<>();
        for (Cita cita : citasList) {
            CitaDTO varcitas = new CitaDTO();
            varcitas.setId(cita.getId());
            varcitas.setFecha(cita.getFecha());
            varcitas.setHora(cita.getHora());
            varcitas.setNumTrabajador(cita.getNumTrabajador());
            varcitas.setMotivoCita(cita.getMotivoCita());
            varcitas.setDiscapacidad(cita.getDiscapacidad());
            varcitas.setComunidadIndigena(cita.getComunidadIndigena());
            varcitas.setMigrante(cita.getMigrante());
            varcitas.setMatriculaAlumno(cita.getAlumno().getMatricula());
            listaFinal.add(varcitas);
        }
        return listaFinal;
    }

    // Verifico si existe el alumno con el ID
    public boolean buscaID(String matricula, String NumTrabajador) {
        // Obtén la fecha actual
        Date fechaActual = new Date();
        // Crea un objeto SimpleDateFormat con el formato deseado
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        // Formatea la fecha actual utilizando el formato
        String fechaFormateada = formato.format(fechaActual);
        // Imprime la fecha formateada
        System.out.println("La fecha del dia de hoy es: " + fechaFormateada);
        /////////////////////////////////////////////////////////////
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
        for (Cita cita : citasList) {
            if (cita.getAlumno().getMatricula().compareTo(matricula) == 0
                    && cita.getId().compareTo(idCita) == 0) {
                return cita; // La cita encontrada es igual al alumno.
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

    // Validamos que la fecha
    public boolean validaFechaCita(LocalDate fechaBody) {
        try {
            // Creamos un elemento con la fecha actual
            LocalDate fechaActual = LocalDate.now();
            System.out.println("La fecha del dia de hoy es: " + fechaActual);
            if (fechaBody.isBefore(fechaActual)) {
                System.out.println("Se dio una fecha que invalida, debido a que ese dia ya paso");
                return false;
            } else {
                if (fechaDisponible(fechaBody)) {
                    System.out.println("Se dio una fecha valida.");
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("A sucedido un error");
        }
        return true;
    }

    public boolean fechaDisponible(LocalDate fechaBody) {
        for (Cita cita : citaRepository.findAll()) {
            if (cita.getFecha().compareTo(fechaBody) == 0) {
                System.out.println("Fecha disponible");
                return true;
            }
        }
        return false;
    }

    public boolean dispoibilidadFechaCita(LocalDate fecha, LocalTime hora) {
        // Busca si ya hay una cita en esa fecha.
        Cita cita = citaRepository.findByFechaAndHora(fecha, hora);
        if (cita == null) { // Si la cita es null, quiere decir que si se puede agendar ese dia
            return true;
        } else {
            return false;
        }
    }
}
