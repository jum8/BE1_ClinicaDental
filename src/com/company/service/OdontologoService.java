package com.company.service;

import com.company.model.Odontologo;
import com.company.persistence.IDAO;

import java.util.List;


public class OdontologoService {
    private IDAO<Odontologo> odontologoDAO;

    public OdontologoService(IDAO<Odontologo> odontologoDAO) {
        this.odontologoDAO = odontologoDAO;
    }

    public Odontologo registrar(Odontologo odontologo) {
        return odontologoDAO.crear(odontologo);
    }

    public List<Odontologo> buscarTodos() {
        List<Odontologo> odontologos = odontologoDAO.buscarTodos();
//        odontologos.forEach(odontologo -> System.out.println(odontologo));
        return odontologos;
    }

    public Odontologo buscar(int id) {
        return odontologoDAO.buscarPorId(id);
    }
}
