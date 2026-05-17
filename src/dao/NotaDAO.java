package dao;

import model.Nota;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class NotaDAO {

    public void inserir(Nota n) {
        String sql = "INSERT INTO nota (rgm, disciplina, semestre, nota, faltas) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, n.getRgm());
            stmt.setString(2, n.getDisciplina());
            stmt.setString(3, n.getSemestre());
            stmt.setDouble(4, n.getNota());
            stmt.setInt(5, n.getFaltas());
            stmt.execute();
            System.out.println("Nota salva!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    public java.util.List<Nota> listarPorAluno(String rgm) {
        java.util.List<Nota> lista = new java.util.ArrayList<>();
        String sql = "SELECT * FROM nota WHERE rgm = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, rgm);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                Nota n = new Nota();
                n.setDisciplina(rs.getString("disciplina"));
                n.setSemestre(rs.getString("semestre"));
                n.setNota(rs.getDouble("nota"));
                n.setFaltas(rs.getInt("faltas"));
                lista.add(n);
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return lista;
    }
    
    public void excluir(String rgm, String disciplina, String semestre) {
        String sql = "DELETE FROM nota WHERE rgm = ? AND disciplina = ? AND semestre = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, rgm);
            stmt.setString(2, disciplina);
            stmt.setString(3, semestre);
            stmt.execute();
            System.out.println("Nota excluída!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}