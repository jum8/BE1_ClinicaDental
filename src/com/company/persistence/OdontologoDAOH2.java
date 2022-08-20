package com.company.persistence;

import com.company.model.Odontologo;
import com.company.persistence.util.ConfiguracionJDBC;
import com.company.persistence.util.GeneradorDeSentencias;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OdontologoDAOH2 implements IDAO<Odontologo> {

    private final static Logger logger = Logger.getLogger(OdontologoDAOH2.class);
    private final static ConfiguracionJDBC jdbc = new ConfiguracionJDBC();
    private final static String TABLE = "Odontologo";

    @Override
    public Odontologo crear(Odontologo odontologo) {
        jdbc.cargarElControlador();
        String sqlStmt = GeneradorDeSentencias.generarInsert(TABLE, List.of("numero_matricula", "nombre", "apellido"));

        try(Connection con = jdbc.conectarConBaseDeDatos();
            PreparedStatement stmt = con.prepareStatement(sqlStmt, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, odontologo.getNumeroMatricula());
            stmt.setString(2, odontologo.getNombre());
            stmt.setString(3, odontologo.getApellido());

            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();

            if(keys.next()) {
                odontologo.setId(keys.getInt(1));
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
        logger.info(odontologo);
        return odontologo;
    }

    @Override
    public void eliminar(int id) {

    }

    @Override
    public Odontologo buscarPorId(int id) {
        jdbc.cargarElControlador();
        Odontologo odontologo = null;
        String sqlStmt = GeneradorDeSentencias.generarSelectPorId(TABLE);

        try(Connection con = jdbc.conectarConBaseDeDatos();
            PreparedStatement stmt = con.prepareStatement(sqlStmt)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                odontologo = new Odontologo(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4));
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
        logger.info(odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        jdbc.cargarElControlador();
        Odontologo odontologo = null;
        List<Odontologo> odontologos = new ArrayList<>();
        String sqlStmt = GeneradorDeSentencias.generarSelectAll(TABLE);

        try(Connection con = jdbc.conectarConBaseDeDatos();
            PreparedStatement stmt = con.prepareStatement(sqlStmt)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                odontologo = new Odontologo(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4));
                odontologos.add(odontologo);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
        logger.info(odontologos);
        return odontologos;
    }
}
