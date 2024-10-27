package prontvet.dao;

import static prontvet.Constants.DB_PASSWORD;
import static prontvet.Constants.DB_URL;
import static prontvet.Constants.DB_USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import prontvet.table.TutorTable;

public class TutorDAO {
    public static TutorTable save(TutorTable tutor) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql = "INSERT INTO tutores (nome, telefone, endereco) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int i = 0;
            pstmt.setString(++i, tutor.getNome());
            pstmt.setString(++i, tutor.getTelefone());
            pstmt.setString(++i, tutor.getEndereco());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                tutor.setId(rs.getInt(1));
            }

            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tutor;
    }
}
