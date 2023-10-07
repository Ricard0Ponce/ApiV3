package io.swagger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import io.swagger.data.repository.PsiquiatraRepository;
import io.swagger.model.Psiquiatra;
import io.swagger.model.PsiquiatraDTO;
import io.swagger.model.PsiquiatrasLoginBody;

@Service
public class PsiquiatraService {
    // Inyeccion de deendencias.
    @Autowired
    private final PsiquiatraRepository psiquiatraRepository;

    public PsiquiatraService(PsiquiatraRepository psiquiatraRepository) {
        this.psiquiatraRepository = psiquiatraRepository;
    }

    // Creamos un alumno, faltan agregar condicionales
    public PsiquiatraDTO createPsiquiatra(Psiquiatra psiquiatra) {
        PsiquiatraDTO psiRes = new PsiquiatraDTO();
        psiRes.setNombres(psiquiatra.getNombres());
        psiRes.setApellidoPaterno(psiquiatra.getApellidoPaterno());
        psiRes.setApellidoMaterno(psiquiatra.getApellidoMaterno());
        psiRes.setNumTrabajador(psiquiatra.getNumTrabajador());
        psiquiatraRepository.save(psiquiatra);
        return psiRes;
    }

    // Permite a un Psiquiatra iniciar sesion.
    public PsiquiatraDTO loginPsiquiatra(PsiquiatrasLoginBody loginBody) {
        boolean find = false;
        PsiquiatraDTO psiquiatraLogeado = new PsiquiatraDTO();
        List<Psiquiatra> psiList = psiquiatraRepository.findAll();
        for (Psiquiatra psiquiatra : psiList) {
            if (psiquiatra.getNumTrabajador().compareTo(loginBody.getNumTrabajador()) == 0
                    && psiquiatra.getPassword().compareTo(loginBody.getPassword()) == 0) {
                psiquiatraLogeado.setNombres(psiquiatra.getNombres());
                psiquiatraLogeado.setApellidoPaterno(psiquiatra.getApellidoPaterno());
                psiquiatraLogeado.setApellidoMaterno(psiquiatra.getApellidoMaterno());
                psiquiatraLogeado.setNumTrabajador(psiquiatra.getNumTrabajador());
                find = true;
            }
        }
        if (find == true) {
            return psiquiatraLogeado;
        } else {
            return null;
        }
    } // Termina metodo loginPsiquiatra
}// Termina clase PisquiatrService
