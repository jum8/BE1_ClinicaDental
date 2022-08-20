package com.company.persistence;

import com.company.persistence.util.*;
import com.company.model.Paciente;

import java.sql.*;
import java.util.List;

public class PacienteDAOH2 implements IDAO<Paciente> {

    private static final ConfiguracionJDBC jdbc = new ConfiguracionJDBC();
    private static final String TABLE = "Paciente";
    private static final List<String> campos = List.of("apellido", "nombre", "dni", "fecha_ingreso", "domicilio_id");

    @Override
    public Paciente crear(Paciente paciente) {
        jdbc.cargarElControlador();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = con.prepareStatement(
                    GeneradorDeSentencias.generarInsert(TABLE, campos), Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, paciente.getApellido());
            ps.setString(2, paciente.getNombre());
            ps.setInt(3, paciente.getDni());
            ps.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            ps.setLong(5, paciente.getDomicilio().getId());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            while (rs.next()) {
                paciente.setId(rs.getLong("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return paciente;
    }

    @Override
    public void eliminar(int id) {

    }

    @Override
    public Paciente buscarPorId(int id) {
        return null;
    }

    @Override
    public List<Paciente> buscarTodos() {
        return null;
    }
}
