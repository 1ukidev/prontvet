package prontvet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import prontvet.table.TutorEntity;

public class TutorDAO implements DAO<TutorEntity> {
    public TutorEntity save(TutorEntity tutor) {
        try {
            Connection conn = ConnectionDAO.create();

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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tutor;
    }

    public List<TutorEntity> findAll() {
        List<TutorEntity> tutores = new ArrayList<>();

        try {
            Connection conn = ConnectionDAO.create();

            String sql = "SELECT * FROM tutores";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                TutorEntity tutor = new TutorEntity();
                tutor.setId(rs.getInt("id"));
                tutor.setNome(rs.getString("nome"));
                tutor.setTelefone(rs.getString("telefone"));
                tutor.setEndereco(rs.getString("endereco"));
                tutores.add(tutor);
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tutores;
    }

    public boolean update(TutorEntity tutor) {
        try {
            Connection conn = ConnectionDAO.create();

            String sql = "UPDATE tutores SET nome = ?, telefone = ?, endereco = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            int i = 0;
            pstmt.setString(++i, tutor.getNome());
            pstmt.setString(++i, tutor.getTelefone());
            pstmt.setString(++i, tutor.getEndereco());
            pstmt.setInt(++i, tutor.getId());
            pstmt.executeUpdate();

            if (pstmt.getUpdateCount() == 0) {
                return false;
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean delete(TutorEntity tutor) {
        try {
            Connection conn = ConnectionDAO.create();

            String sql = "DELETE FROM tutores WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, tutor.getId());
            pstmt.executeUpdate();

            if (pstmt.getUpdateCount() == 0) {
                return false;
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }

    public TutorEntity findById(Integer id) {
        try {
            Connection conn = ConnectionDAO.create();

            String sql = "SELECT * FROM tutores WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                TutorEntity tutor = new TutorEntity();
                tutor.setId(rs.getInt("id"));
                tutor.setNome(rs.getString("nome"));
                tutor.setTelefone(rs.getString("telefone"));
                tutor.setEndereco(rs.getString("endereco"));
                return tutor;
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Singleton
    private TutorDAO() {}

    private static TutorDAO instance = new TutorDAO();

    public static TutorDAO getInstance() {
        return instance;
    }
}
