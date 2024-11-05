package prontvet.dao;

import java.sql.Connection;
import java.sql.SQLException;

import prontvet.Log;
import prontvet.Util;

public class OthersDAO {
    public static void test() {
        try {
            Connection conn = ConnectionDAO.create();
            if (conn == null) {
                showErrorMsg();
            } else {
                Log.debug("Banco de dados detectado.");
            }
            conn.close();
        } catch (SQLException e) {
            showErrorMsg();
        }
    }

    private static void showErrorMsg() {
        Util.showError("Não foi possível se conectar ao banco de dados.");
        Log.error("Não foi possível se conectar ao banco de dados.");
    }
}
