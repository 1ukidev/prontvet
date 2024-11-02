package prontvet.dao;

import static prontvet.Constants.DB_PASSWORD;
import static prontvet.Constants.DB_URL;
import static prontvet.Constants.DB_USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import prontvet.table.TutorTable;

public class TutorDAO implements DAO<TutorTable> {
    public TutorTable save(TutorTable tutor) {
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

    public List<TutorTable> findAll() {
        List<TutorTable> tutores = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql = "SELECT * FROM tutores";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                TutorTable tutor = new TutorTable();
                tutor.setId(rs.getInt("id"));
                tutor.setNome(rs.getString("nome"));
                tutor.setTelefone(rs.getString("telefone"));
                tutor.setEndereco(rs.getString("endereco"));
                tutores.add(tutor);
            }

            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tutores;
    }

    // Singleton
    private TutorDAO() {}

    private static TutorDAO instance = new TutorDAO();

    public static TutorDAO getInstance() {
        return instance;
    }
}
