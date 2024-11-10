package prontvet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import prontvet.table.PacienteEntity;

public class PacienteDAO implements DAO<PacienteEntity> {
    public PacienteEntity save(PacienteEntity paciente) {
        try {
            Connection conn = ConnectionDAO.create();

            String sql = "INSERT INTO pacientes (tutor, nome, raca, sexo, idade, peso, descricao) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int i = 0;
            pstmt.setInt(++i, paciente.getTutor().getId());
            pstmt.setString(++i, paciente.getNome());
            pstmt.setString(++i, paciente.getRaca());
            pstmt.setString(++i, paciente.getSexo().toString());
            pstmt.setInt(++i, paciente.getIdade());
            pstmt.setDouble(++i, paciente.getPeso());
            pstmt.setString(++i, paciente.getDescricao());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                paciente.setId(rs.getInt(1));
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paciente;
    }

    public List<PacienteEntity> findAll() {
        List<PacienteEntity> pacientes = new ArrayList<>();

        try {
            Connection conn = ConnectionDAO.create();

            String sql = "SELECT * FROM pacientes";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                PacienteEntity paciente = new PacienteEntity();
                paciente.setId(rs.getInt("id"));
                paciente.setTutor(TutorDAO.getInstance().findById(rs.getInt("tutor")));
                paciente.setNome(rs.getString("nome"));
                paciente.setRaca(rs.getString("raca"));
                paciente.setSexo(rs.getString("sexo").charAt(0));
                paciente.setIdade(rs.getInt("idade"));
                paciente.setPeso(rs.getDouble("peso"));
                paciente.setDescricao(rs.getString("descricao"));
                pacientes.add(paciente);
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pacientes;
    }

    public boolean update(PacienteEntity paciente) {
        try {
            Connection conn = ConnectionDAO.create();

            String sql = "UPDATE pacientes SET tutor = ?, nome = ?, raca = ?, sexo = ?, idade = ?, peso = ?, descricao = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            int i = 0;
            pstmt.setInt(++i, paciente.getTutor().getId());
            pstmt.setString(++i, paciente.getNome());
            pstmt.setString(++i, paciente.getRaca());
            pstmt.setString(++i, paciente.getSexo().toString());
            pstmt.setInt(++i, paciente.getIdade());
            pstmt.setDouble(++i, paciente.getPeso());
            pstmt.setString(++i, paciente.getDescricao());
            pstmt.setInt(++i, paciente.getId());
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

    public boolean delete(PacienteEntity paciente) {
        try {
            Connection conn = ConnectionDAO.create();

            String sql = "DELETE FROM pacientes WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, paciente.getId());
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

    // Singleton
    private PacienteDAO() {}

    private static PacienteDAO instance = new PacienteDAO();

    public static PacienteDAO getInstance() {
        return instance;
    }
}
