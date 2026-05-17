package dao;

import model.Aluno;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AlunoDAO {

	public void inserir(Aluno aluno) {
		String sql = "INSERT INTO aluno (rgm, nome, cpf, celular, data_nascimento, email, endereco, municipio, uf) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, aluno.getRgm());
			stmt.setString(2, aluno.getNome());
			stmt.setString(3, aluno.getCpf());
			stmt.setString(4, aluno.getCelular());
			stmt.setString(5, aluno.getDataNascimento());
			stmt.setString(6, aluno.getEmail());
			stmt.setString(7, aluno.getEndereco());
			stmt.setString(8, aluno.getMunicipio());
			stmt.setString(9, aluno.getUf());
			stmt.execute();
			System.out.println("Aluno cadastrado!");
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public void excluir(String rgm) {
		String sql = "DELETE FROM aluno WHERE rgm = ?";
		try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, rgm);
			stmt.execute();
			System.out.println("Aluno excluído!");
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public Aluno buscar(String rgm) {
	    String sql = "SELECT a.*, c.nome AS nome_curso FROM aluno a " +
	                 "LEFT JOIN curso c ON a.curso_id = c.id WHERE a.rgm = ?";
	    try (Connection conn = Conexao.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, rgm);
	        var rs = stmt.executeQuery();
	        if (rs.next()) {
	            Aluno a = new Aluno();
	            a.setRgm(rs.getString("rgm"));
	            a.setNome(rs.getString("nome"));
	            a.setCpf(rs.getString("cpf"));
	            a.setCelular(rs.getString("celular"));
	            a.setDataNascimento(rs.getString("data_nascimento"));
	            a.setEmail(rs.getString("email"));
	            a.setEndereco(rs.getString("endereco"));
	            a.setMunicipio(rs.getString("municipio"));
	            a.setUf(rs.getString("uf"));
	            a.setNomeCurso(rs.getString("nome_curso"));
	            return a;
	        }
	    } catch (Exception e) {
	        System.out.println("Erro: " + e.getMessage());
	    }
	    return null;
	}
	
	public void atualizarCurso(String rgm, String nomeCurso, String campus, String periodo) {
	    int cursoId = new CursoDAO().buscarId(nomeCurso, campus, periodo);
	    if (cursoId == -1) {
	        javax.swing.JOptionPane.showMessageDialog(null, "Curso não encontrado no banco!");
	        return;
	    }
	    String sql = "UPDATE aluno SET curso_id = ? WHERE rgm = ?";
	    try (Connection conn = Conexao.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, cursoId);
	        stmt.setString(2, rgm);
	        stmt.execute();
	        System.out.println("Curso atualizado!");
	    } catch (Exception e) {
	        System.out.println("Erro: " + e.getMessage());
	    }
	}
}