package prontvet.dao;

import static prontvet.Constants.DB_PASSWORD;
import static prontvet.Constants.DB_URL;
import static prontvet.Constants.DB_USER;

import java.sql.Connection;
import java.sql.DriverManager;

import prontvet.Log;
import prontvet.Util;

public class OthersDAO {
    public static void test() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            if (conn == null) {
                showErrorMsg();
            } else {
                Log.debug("Banco de dados detectado.");
            }
            conn.close();
        } catch (Exception e) {
            showErrorMsg();
        }
    }

    private static void showErrorMsg() {
        Util.showError("Não foi possível se conectar ao banco de dados.");
        Log.error("Não foi possível se conectar ao banco de dados.");
    }
}
