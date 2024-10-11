package prontvet.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import prontvet.Config;
import prontvet.entities.UsuarioEntity;

public class UsuarioRepository implements Repository<UsuarioEntity> {
    public UsuarioEntity save(UsuarioEntity entity) {
        try {
            Connection conn = DriverManager.getConnection(Config.dbUrl, Config.dbUser, Config.dbPassword);

            String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int i = 0;
            pstmt.setString(++i, entity.getNome());
            pstmt.setString(++i, entity.getEmail());
            pstmt.setString(++i, entity.getSenha());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                entity.setId(rs.getInt(1));
            }

            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entity;
    }

    private UsuarioRepository() {}

    public static final UsuarioRepository instance = new UsuarioRepository();

    public static UsuarioRepository getInstance() {
        return instance;
    }
}
