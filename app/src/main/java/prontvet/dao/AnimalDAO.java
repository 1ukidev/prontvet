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

import prontvet.table.AnimalTable;

public class AnimalDAO implements DAO<AnimalTable> {
    public AnimalTable save(AnimalTable animal) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql = "INSERT INTO animais (nome, raca, sexo, idade, peso) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int i = 0;
            pstmt.setString(++i, animal.getNome());
            pstmt.setString(++i, animal.getRaca());
            pstmt.setString(++i, animal.getSexo().toString());
            pstmt.setInt(++i, animal.getIdade());
            pstmt.setDouble(++i, animal.getPeso());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                animal.setId(rs.getInt(1));
            }

            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return animal;
    }

    public List<AnimalTable> findAll() {
        List<AnimalTable> animais = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql = "SELECT * FROM animais";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                AnimalTable animal = new AnimalTable();
                animal.setId(rs.getInt("id"));
                animal.setNome(rs.getString("nome"));
                animal.setRaca(rs.getString("raca"));
                animal.setSexo(rs.getString("sexo").charAt(0));
                animal.setIdade(rs.getInt("idade"));
                animal.setPeso(rs.getDouble("peso"));
                animais.add(animal);
            }

            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return animais;
    }

    // Singleton
    private AnimalDAO() {}

    private static AnimalDAO instance = new AnimalDAO();

    public static AnimalDAO getInstance() {
        return instance;
    }
}
