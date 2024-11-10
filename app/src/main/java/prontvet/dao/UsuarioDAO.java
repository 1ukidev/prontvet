package prontvet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import prontvet.table.UsuarioEntity;

public class UsuarioDAO implements DAO<UsuarioEntity> {
    public UsuarioEntity save(UsuarioEntity usuario) {
        try {
            Connection conn = ConnectionDAO.create();

            String sql = "INSERT INTO usuarios (email, senha) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int i = 0;
            pstmt.setString(++i, usuario.getEmail());
            pstmt.setString(++i, usuario.getSenha());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                usuario.setId(rs.getInt(1));
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public List<UsuarioEntity> findAll() {
        List<UsuarioEntity> usuarios = new ArrayList<>();

        try {
            Connection conn = ConnectionDAO.create();

            String sql = "SELECT * FROM usuarios";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                UsuarioEntity usuario = new UsuarioEntity();
                usuario.setId(rs.getInt("id"))
                       .setEmail(rs.getString("email"))
                       .setSenha(rs.getString("senha"));
                usuarios.add(usuario);
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public boolean update(UsuarioEntity usuario) {
        try {
            Connection conn = ConnectionDAO.create();

            String sql = "UPDATE usuarios SET email = ?, senha = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            int i = 0;
            pstmt.setString(++i, usuario.getEmail());
            pstmt.setString(++i, usuario.getSenha());
            pstmt.setInt(++i, usuario.getId());
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

    public boolean delete(UsuarioEntity usuario) {
        try {
            Connection conn = ConnectionDAO.create();

            String sql = "DELETE FROM usuarios WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, usuario.getId());
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

    public boolean find(UsuarioEntity usuario) {
        try {
            Connection conn = ConnectionDAO.create();

            String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            int i = 0;
            pstmt.setString(++i, usuario.getEmail());
            pstmt.setString(++i, usuario.getSenha());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
                return true;
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Singleton
    private UsuarioDAO() {}

    private static UsuarioDAO instance = new UsuarioDAO();

    public static UsuarioDAO getInstance() {
        return instance;
    }
}
