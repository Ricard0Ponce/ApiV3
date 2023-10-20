package io.swagger.api;

import io.swagger.model.Alumno;
import io.swagger.model.AlumnoDTOLogin;
import io.swagger.model.AlumnoDTOid;
import io.swagger.model.AlumnosLoginBody;
import io.swagger.model.Cita;
import io.swagger.model.CitaDTO;
import io.swagger.model.Error204Alumno;
import io.swagger.model.Error204Cita;
import io.swagger.model.Error204Psiquiatra;
import io.swagger.model.Error403AlumnoID;
import io.swagger.model.Error404AlumnoID;
import io.swagger.model.Error404Cita;
import io.swagger.model.Error404CitaID;
import io.swagger.model.Error404Psiquiatra;
import io.swagger.model.Error500Alumno;
import io.swagger.model.Error500Cita;
import io.swagger.model.Error500Psiquiatra;
import io.swagger.model.Model204CitaDelete;
import io.swagger.model.Psiquiatra;
import io.swagger.model.PsiquiatraDTO;
import io.swagger.model.PsiquiatrasLoginBody;
import io.swagger.service.AlumnoService;
import io.swagger.service.CitaService;
import io.swagger.service.PsiquiatraService;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-10-03T21:32:08.208259185Z[GMT]")
@RestController
public class ApiApiController implements ApiApi {

    private static final Logger log = LoggerFactory.getLogger(ApiApiController.class);
    // Se reliza la inyeccion de dependencias
    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final AlumnoService alumnoService;

    private final CitaService citaService;

    private final PsiquiatraService psiquiatraService;

    @org.springframework.beans.factory.annotation.Autowired
    public ApiApiController(ObjectMapper objectMapper, HttpServletRequest request, AlumnoService alumnoService,
            CitaService citaService, PsiquiatraService psiquiatraService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.alumnoService = alumnoService;
        this.citaService = citaService;
        this.psiquiatraService = psiquiatraService;
    }

    public ResponseEntity<?> createAlumno(
            @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody Alumno body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try { // Si es nulo se puede crear, si no es nulo no se puede crear
                AlumnoDTOid alum = new AlumnoDTOid();
                alum = alumnoService.getAlumnoById(body.getMatricula());
                if (alum == null) { // Si es null no se encontro la matricula, entonces se puede almacenar
                    Alumno alumno = alumnoService.createAlumno(body);
                    if (alumno == null) {
                        System.out.println("El usuario no pudo ser creado correctamente. ");
                        Error204Alumno err = new Error204Alumno(); // Aqui va error403AlumnoID
                        err.description("El alumno no ha podido ser creado exitosamente.");
                        return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
                    } else {
                        AlumnoDTOLogin alumnoDTOLogin = new AlumnoDTOLogin();
                        alumnoDTOLogin.setApellidoPaterno(body.getApellidoPaterno());
                        alumnoDTOLogin.setNombres(body.getNombres());
                        alumnoDTOLogin.setApellidoMaterno(body.getApellidoMaterno());
                        alumnoDTOLogin.setMatricula(body.getMatricula());
                        System.out.println("Se almaceno el alumno con nombre: " + alumno.getNombres());
                        return new ResponseEntity<>(alumnoDTOLogin, HttpStatus.CREATED);
                    }
                } else {
                    System.out.println("\nLa matricula ya se encuentra en uso. ");
                    Error204Alumno err = new Error204Alumno();
                    err.description("Error: La matricula especificada ya esta en uso.");
                    return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
                }
            } catch (Exception e) {
                System.out.println("A sucedido un error");
                Error500Alumno err = new Error500Alumno();
                err.description("Ha sucedido un error en el servidor.");
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<AlumnoDTOLogin>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<?> createCita(
            @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("matricula") String matricula,
            @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody Cita body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                // Pasa de Long a Integer
                String numTrabajador = body.getNumTrabajador(); // Manda el dato del alumno
                if (citaService.buscaID(matricula, numTrabajador)) {
                    System.out.println("Se encontro la matricula y el num de Trabajador.");
                    if (citaService.validaFechaCita(body.getFecha())) {
                        System.out.println("Se ingreso una fecha valida");
                        if (citaService.dispoibilidadFechaCita(body.getFecha(), body.getHora())) {
                            if (body.getMotivoCita().length() > 230) {
                                Error404Cita err = new Error404Cita();
                                err.description("Error: El tamaño de la cadena de motivo cita es muy grande.");
                                return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
                            } else {
                                Cita citaf = new Cita(); //
                                citaf = citaService.createCita(body, matricula);
                                if (citaf != null) {
                                    System.out.println("Se almaceno la cita con el ID: " + matricula);
                                    return new ResponseEntity<>(citaf, HttpStatus.CREATED);
                                } else {
                                    Error404Cita err = new Error404Cita();
                                    err.description("La cita no ha podido ser creada exitosamente.");
                                    return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
                                }
                            }
                        } else {
                            Error404Cita err = new Error404Cita();
                            err.description("Error: La hora especificada ya tiene una cita registrada previamente.");
                            return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
                        }
                    } else {
                        Error404Cita err = new Error404Cita();
                        err.description("Error: La fecha que eligio ya paso.");
                        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
                    }
                } else {
                    Error404Cita err = new Error404Cita();
                    err.description("Error: La matricula o el numero de trabajador son incorrectos.");
                    return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                System.out.println("A sucedido un error");
                log.error("Couldn't serialize response for content type application/json", e);
                Error500Cita err = new Error500Cita();
                err.description("Ha sucedido un error en el servidor");
                return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Cita>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<?> createPsiquiatra(
            @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody Psiquiatra body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                if (body.getNumTrabajador().length() != 10) {
                    Error404Psiquiatra err = new Error404Psiquiatra();
                    err.setDescription("Error: El numero de trabajador contiene especificamente 10 digitos.");
                    return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
                } else {
                    PsiquiatraDTO valNum = psiquiatraService.getPsiquiatraByNumTrabajador(body.getNumTrabajador());
                    if (valNum == null) {
                        PsiquiatraDTO psi = psiquiatraService.createPsiquiatra(body);
                        if (psi == null) { // Si es null quiere decir que no se encontro el NumT en la BD
                            Error204Psiquiatra err = new Error204Psiquiatra();
                            err.setDescription("Error: La cuenta del psiquiatra no ha podido ser creada.");
                            return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
                        } else {
                            return new ResponseEntity<>(psi, HttpStatus.CREATED);
                        }
                    } else {
                        Error204Psiquiatra err = new Error204Psiquiatra();
                        err.setDescription("Error: El numero de trabajador que indico ya se encuentra registrado.");
                        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
                    }
                }

            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                Error500Psiquiatra err = new Error500Psiquiatra();
                err.description("Ha sucedido un error en el servidor");
                return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PsiquiatraDTO>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<?> deleteCitaById(
            @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("matricula") String matricula,
            @PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                if (matricula.length() != 10) {
                    Error404Cita err = new Error404Cita();
                    err.description("Error: La matricula debe contener exactamente 10 digitos.");
                    return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
                }
                boolean find = citaService.deleteCita(matricula, id);
                if (find) {
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    Error404Cita err = new Error404Cita();
                    err.description("La cita no ha podido ser eliminado debido a que no ha sido encontrado.");
                    return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                Error500Cita err = new Error500Cita();
                err.description("Ha sucedido un error en el servidor.");
                return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Model204CitaDelete>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<?> getAllAlumnos() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                List<AlumnoDTOid> alumnoList = alumnoService.getAllAlumnos();
                if (alumnoService.getAllAlumnos().isEmpty()) {
                    Error204Alumno err = new Error204Alumno();
                    System.out.println("No se cargo la lista de Alumnos");
                    err.description("La solicitud fue correcta, sin embargo no se encontraron alumnos registrados.");
                    return new ResponseEntity<>(err, HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(alumnoList, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                Error500Alumno err = new Error500Alumno();
                err.description("Ha sucedido un error en el servidor");
                return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<AlumnoDTOid>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<?> getAllCita() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                List<CitaDTO> citaList = citaService.getAllCita();
                if (citaList.isEmpty()) {
                    Error204Cita err = new Error204Cita();
                    err.description("La solicitud fue correcta, sin embargo no se encontraron citas registradas.");
                    return new ResponseEntity<>(err, HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(citaList, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                Error500Cita err = new Error500Cita();
                err.description("Ha sucedido un error en el servidor.");
                return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Cita>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<?> getAllPsiquiatras() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                List<PsiquiatraDTO> psiList = psiquiatraService.getAllPsiquiatras();
                if (psiList.isEmpty()) {
                    Error204Psiquiatra err = new Error204Psiquiatra();
                    err.description(
                            "La solicitud se proceso de manera correcta, sin embargo no se encontraron Psiquiatras registrados.");
                    return new ResponseEntity<>(err, HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(psiList, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                Error500Psiquiatra err = new Error500Psiquiatra();
                err.description("Ha sucedido un error en el servidor.");
                return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<PsiquiatraDTO>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<?> getAlumnoByMatricula(@PathVariable("matricula") String matricula) {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            try {
                if (matricula.length() != 10) {
                    Error404AlumnoID err = new Error404AlumnoID();
                    err.description("Error: La matricula debe contener exactamente 10 digitos.");
                    return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
                }
                AlumnoDTOid alumnoDTO = alumnoService.getAlumnoById(matricula);
                if (alumnoDTO != null) {
                    return new ResponseEntity<>(alumnoDTO, HttpStatus.OK);
                } else {
                    Error404AlumnoID err = new Error404AlumnoID();
                    err.description("No se ha encontrado al alumno con esa matricula.");
                    return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                log.error("An error occurred", e);
                Error500Alumno err = new Error500Alumno();
                err.description("Ha sucedido un error con el servidor.");
                return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<?> getCitaById(
            @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("matricula") String matricula,
            @PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                if (matricula.length() != 10) {
                    Error404Cita err = new Error404Cita();
                    err.description("Error: La matricula debe llevar exactamente 10 digitos.");
                    return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
                }
                Cita cita = citaService.getCitaById(matricula, id);
                if (cita != null) {
                    CitaDTO citadto = new CitaDTO();
                    citadto.setId(cita.getId());
                    citadto.setNumTrabajador(cita.getNumTrabajador());
                    citadto.setFecha(cita.getFecha());
                    citadto.setHora(cita.getHora());
                    citadto.setMotivoCita(cita.getMotivoCita());
                    citadto.setMatriculaAlumno(matricula);
                    citadto.setComunidadIndigena(cita.getComunidadIndigena());
                    citadto.setMigrante(cita.getMigrante());
                    citadto.setDiscapacidad(cita.getDiscapacidad());
                    return new ResponseEntity<>(citadto, HttpStatus.OK);
                } else {
                    Error404Cita err = new Error404Cita();
                    err.description("No se ha encontrado la cita con ese ID.");
                    return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                Error500Cita err = new Error500Cita();
                err.description("Ha sucedido un error en el servidor");
                return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Cita>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<?> getLoginPsicologo(
            @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody PsiquiatrasLoginBody body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                if (body.getNumTrabajador().length() != 10) {
                    Error404Psiquiatra err = new Error404Psiquiatra();
                    err.description("Error: El numero de trabajador debe llevar exactamente 10 digitos.");
                    return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
                } else {
                    PsiquiatraDTO psiDTO = psiquiatraService.getPsiquiatraByNumTrabajador(body.getNumTrabajador());
                    if (psiDTO == null) { // Doble condicion
                        Error404Psiquiatra err = new Error404Psiquiatra();
                        err.description("Error: No se encontro el numero de trabajador.");
                        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
                    } else {
                        PsiquiatraDTO psi;
                        psi = psiquiatraService.loginPsiquiatra(body);
                        if (psi != null) {
                            return new ResponseEntity<>(psi, HttpStatus.OK);
                        } else {
                            Error404Psiquiatra err = new Error404Psiquiatra();
                            err.description(
                                    "Error: Se encontro el numero de trabajador pero el password es incorrecto.");
                            return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
                        }
                    }
                }
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                Error500Psiquiatra err = new Error500Psiquiatra();
                err.description("Ha sucedido un error en el servidor.");
                return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PsiquiatraDTO>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<?> getPsiquiatraByNumTrabajador(
            @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("NumTrabajador") String NumTrabajador) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                if (NumTrabajador.length() != 10) {
                    Error404AlumnoID err = new Error404AlumnoID();
                    err.description("Error: El numero de trabajador lleva exactamente 10 digitos.");
                    return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
                }
                PsiquiatraDTO psiquiatraDTO = psiquiatraService.getPsiquiatraByNumTrabajador(NumTrabajador);

                if (psiquiatraDTO != null) {
                    return new ResponseEntity<>(psiquiatraDTO, HttpStatus.OK);
                } else {
                    Error404AlumnoID err = new Error404AlumnoID();
                    err.description("No se ha encontrado al Psiquiatra");
                    return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                Error500Psiquiatra err = new Error500Psiquiatra();
                err.description("Ha sucedido un error en el servidor.");
                return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PsiquiatraDTO>(HttpStatus.NOT_IMPLEMENTED);
    }

    // Permite acceder a un alumno al sistema con password y matricula
    public ResponseEntity<?> loginAlumno(
            @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody AlumnosLoginBody body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                if (body.getMatricula().length() != 10) {
                    Error404AlumnoID err = new Error404AlumnoID();
                    err.description("Error: La matricula debe llevar 10 digitos.");
                    return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
                } else {
                    AlumnoDTOid al = alumnoService.getAlumnoById(body.getMatricula());
                    if (al == null) {
                        Error404AlumnoID err = new Error404AlumnoID();
                        err.description("Error: No se encontro la matricula.");
                        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
                    } else {
                        AlumnoDTOLogin alumno;
                        alumno = alumnoService.loginAlumno(body);
                        if (alumno != null) {
                            System.out.println("Nombre del alumno: " + alumno.getNombres());
                            return new ResponseEntity<>(alumno, HttpStatus.OK);
                        }
                        Error404AlumnoID err = new Error404AlumnoID();
                        err.description("Error: Se encontro la matricula pero el password es incorrecto.");
                        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
                    }
                }

            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                Error500Alumno err = new Error500Alumno();
                err.description("Ha sucedido un error en el servidor.");
                return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<AlumnoDTOLogin>(HttpStatus.NOT_IMPLEMENTED);
    }

    // Actualiza una CITA por medio del ID
    public ResponseEntity<?> updateCitaById(
            @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("matricula") String matricula,
            @PathVariable("id") Long id,
            @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody Cita body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Cita resp = citaService.updateByCita(body, matricula, id);
                if (resp != null) {
                    return new ResponseEntity<>(resp, HttpStatus.OK);
                } else {
                    Error404CitaID err = new Error404CitaID();
                    err.description("No se han podido actualizar los datos, verifica tus datos.");
                    return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                Error500Cita err = new Error500Cita();
                err.description("Ha sucedido un error en el servidor.");
                return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Cita>(HttpStatus.NOT_IMPLEMENTED);
    }

}
