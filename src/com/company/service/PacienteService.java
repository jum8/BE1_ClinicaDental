package com.company.service;

import com.company.persistence.IDAO;
import com.company.model.Domicilio;
import com.company.model.Paciente;

public class PacienteService {
    private IDAO<Paciente> pacienteDAO;
    private IDAO<Domicilio> domicilioDAO;

    public PacienteService(IDAO<Paciente> pacienteDAO, IDAO<Domicilio> domicilioDAO) {
        this.pacienteDAO = pacienteDAO;
        this.domicilioDAO = domicilioDAO;
    }

    public Paciente guardar(Paciente paciente) {
        domicilioDAO.crear(paciente.getDomicilio());
        return pacienteDAO.crear(paciente);
    }
}
