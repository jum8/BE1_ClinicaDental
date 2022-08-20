package com.company.test;

import com.company.model.Odontologo;
import com.company.persistence.OdontologoDAOH2;
import com.company.service.OdontologoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class OdontologoServiceTest {
    OdontologoService odontologoService = new OdontologoService(new OdontologoDAOH2());

    @Test
    public void registrarOdontologo() {
        Odontologo odontologo = new Odontologo( 101, "Juan", "Perez");

        odontologoService.registrar(odontologo);

        Assertions.assertNotNull(odontologoService.buscar(odontologo.getId()));
    }

    @Test
    public void buscarTodosLosOdontologos() {
        List<Odontologo> odontologos = odontologoService.buscarTodos();

        Assertions.assertNotNull(odontologos);
    }
}