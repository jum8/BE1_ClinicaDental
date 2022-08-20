package com.company.test;

import com.company.persistence.DomicilioDAOH2;
import com.company.persistence.IDAO;
import com.company.persistence.PacienteDAOH2;
import com.company.persistence.util.GeneradorDeSentencias;
import com.company.model.Domicilio;
import com.company.model.Paciente;
import com.company.service.PacienteService;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import com.company.persistence.util.*;

class PacienteServiceTest {

    @Test
    void verificarQueHayaGuardadoElRegistroEnLaBaseDeDatos() {
        // Arrange
        Domicilio domicilio = new Domicilio("25 de Mayo", 2710, "Virasoro", "Corrientes");
        Paciente paciente = new Paciente("Perez", "Pedro", 28389043, LocalDate.parse("2018-11-10"),domicilio);
        IDAO<Paciente> pacienteDAO = new PacienteDAOH2();
        IDAO<Domicilio> domicilioDAO = new DomicilioDAOH2();
        PacienteService service = new PacienteService(pacienteDAO, domicilioDAO);
        String query = GeneradorDeSentencias.generarSelectPorId("Paciente");
        ConfiguracionJDBC jdbc = new ConfiguracionJDBC();
        jdbc.cargarElControlador();
        Connection con = jdbc.conectarConBaseDeDatos();


        // Act
        Paciente pacienteGuardado = service.guardar(paciente);

        try(PreparedStatement ps = con.prepareStatement(query)) {
            ps.setLong(1, pacienteGuardado.getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(String.format("%d %s, %s dni: %s fecha de ingreso: %s",
                        rs.getInt(1),rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getDate(5).toLocalDate()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Assert
    }
}