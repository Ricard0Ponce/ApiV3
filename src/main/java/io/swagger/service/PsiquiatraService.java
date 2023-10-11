package io.swagger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
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

    // Buscamos a un Psiquiatra mediante tu num. de trabajador
    public PsiquiatraDTO getPsiquiatraByNumTrabajador(String NumTrabajador) {
        Optional<Psiquiatra> buscarPsi = psiquiatraRepository.findById(NumTrabajador);

        if (buscarPsi.isPresent()) {
            Psiquiatra psiB = buscarPsi.get();
            PsiquiatraDTO psiDTO = new PsiquiatraDTO();

            psiDTO.setNumTrabajador(psiB.getNumTrabajador());
            psiDTO.setNombres(psiB.getNombres());
            psiDTO.setApellidoPaterno(psiB.getApellidoPaterno());
            psiDTO.setApellidoMaterno(psiB.getApellidoMaterno());

            return psiDTO;
        }
        return null;
    }

    // Creamos un Psiquiatra, faltan agregar condicionales
    public PsiquiatraDTO createPsiquiatra(Psiquiatra psiquiatra) {
        if (psiquiatraRepository.existsById(psiquiatra.getNumTrabajador())) {
            return null;
        } else {
            PsiquiatraDTO psiRes = new PsiquiatraDTO();
            psiRes.setNombres(psiquiatra.getNombres());
            psiRes.setApellidoPaterno(psiquiatra.getApellidoPaterno());
            psiRes.setApellidoMaterno(psiquiatra.getApellidoMaterno());
            psiRes.setNumTrabajador(psiquiatra.getNumTrabajador());
            psiquiatraRepository.save(psiquiatra);
            return psiRes;
        }
    }

    // Permite regresar la lista de psiquiatras
    public List<PsiquiatraDTO> getAllPsiquiatras() {
        List<Psiquiatra> psiList = psiquiatraRepository.findAll();
        List<PsiquiatraDTO> listaFinal = new ArrayList<>();
        for (Psiquiatra psi : psiList) {
            PsiquiatraDTO varPsi = new PsiquiatraDTO();
            varPsi.setNumTrabajador(psi.getNumTrabajador());
            varPsi.setNombres(psi.getNombres());
            varPsi.setApellidoPaterno(psi.getApellidoPaterno());
            varPsi.setApellidoMaterno(psi.getApellidoMaterno());
            listaFinal.add(varPsi);
        }
        return listaFinal;
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
