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

import prontvet.table.UsuarioEntity;

public class UsuarioDAO implements DAO<UsuarioEntity> {
    public UsuarioEntity save(UsuarioEntity usuario) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public List<UsuarioEntity> findAll() {
        List<UsuarioEntity> usuarios = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql = "SELECT * FROM usuarios";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                UsuarioEntity usuario = new UsuarioEntity();
                usuario.setId(rs.getInt("id"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
            }

            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public boolean find(UsuarioEntity usuario) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

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
        } catch (Exception e) {
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
