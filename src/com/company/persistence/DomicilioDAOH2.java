package com.company.persistence;

import com.company.persistence.util.GeneradorDeSentencias;
import com.company.model.Domicilio;
import com.company.persistence.util.*;

import java.sql.*;
import java.util.List;

public class DomicilioDAOH2 implements IDAO<Domicilio> {
    private static final ConfiguracionJDBC jdbc = new ConfiguracionJDBC();
    private static final String TABLE = "Domicilio";
    private static final List<String> campos = List.of("calle", "numero", "localidad", "provincia");


    @Override
    public Domicilio crear(Domicilio domicilio) {
        jdbc.cargarElControlador();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = jdbc.conectarConBaseDeDatos();
            ps = con.prepareStatement(
                    GeneradorDeSentencias.generarInsert(TABLE, campos), Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, domicilio.getCalle());
            ps.setInt(2, domicilio.getNumero());
            ps.setString(3, domicilio.getLocalidad());
            ps.setString(4, domicilio.getProvincia());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            while (rs.next()) {
                domicilio.setId(rs.getLong("id"));
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

        return domicilio;
    }

    @Override
    public void eliminar(int id) {

    }

    @Override
    public Domicilio buscarPorId(int id) {
        return null;
    }

    @Override
    public List<Domicilio> buscarTodos() {
        return null;
    }
}
