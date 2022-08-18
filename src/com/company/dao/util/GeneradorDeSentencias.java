package com.company.dao.util;

import java.util.List;


public class GeneradorDeSentencias {

    public static String generarInsert(String tabla, List<String> campos) {
        if (campos.size() < 1){
            return null;
        }

        String cantidadCampos = sacarUltimoCaracter("?,".repeat(campos.size()));

        StringBuilder sentencia = new StringBuilder("INSERT INTO " + tabla + " (");

        for (String campo : campos) {
            sentencia.append(campo + ",");
        }

        return sacarUltimoCaracter(sentencia.toString()) + ") VALUES (" + cantidadCampos + ");";
    }

    public static String generarSelectAll(String tabla){
        return "SELECT * FROM " + tabla;
    }

    public static String generarSelectPorId(String tabla){
        return "SELECT * FROM " + tabla + " WHERE id = ?";
    }

    public static String generarSelectPorCampo(String tabla){
        return "SELECT * FROM " + tabla + " WHERE ? = ?";
    }

    public static String generarDeletePorCampo(String tabla) {
        return "DELETE FROM " + tabla + " WHERE ? = ?";
    }

    /**
     * Este método se encarga de generar un UPDATE SQL para PreparedStatement.
     * @param tabla
     * @param campos
     * @return
     * @throws Exception
     */
    public static String generarUpdatePorId(String tabla, List<String> campos) throws Exception {
        if (campos.size() < 1) throw new Exception("No se puede generar un update sin campos");
        StringBuilder sentencia = new StringBuilder("UPDATE " + tabla + " SET ");
        for (String campo : campos) {
            sentencia.append(campo + "= ?,");
        }
        return sacarUltimoCaracter(sentencia.toString()) + " WHERE id = ?;";
    }

    private static String sacarUltimoCaracter(String str) {
        return str.substring(0, str.length() - 1);
    }
}
