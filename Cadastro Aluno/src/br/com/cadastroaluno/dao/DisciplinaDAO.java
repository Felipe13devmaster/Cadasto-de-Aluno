package br.com.cadastroaluno.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.cadastroaluno.model.Disciplina;
import br.com.cadastroaluno.util.ConnectionFactory;

public class DisciplinaDAO {
	private Disciplina disciplina;
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public DisciplinaDAO() throws Exception {
		try {
			connection = ConnectionFactory.getConnection();
		} catch(Exception e) {
			throw new Exception("Erro: "+ e.getMessage());
		}
	}
	
	public void salvarDisciplina (Disciplina disciplina) throws Exception {
		try {
			String sql = "INSERT INTO TB_DISCIPLINA (RGM, NOME_DISCIPLINA, SEMESTRE, NOTA, FALTAS)"
					+ "VALUES (?,?,?,?,?)";
			
			ps = connection.prepareStatement(sql);
			ps.setInt(1, disciplina.getRgm());
			ps.setString(2, disciplina.getNome());
			ps.setString(3, disciplina.getSemestre());
			ps.setString(4, disciplina.getNota());
			ps.setString(5, disciplina.getFaltas());
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Disciplina gravada com sucesso.");
			
		} catch (Exception e) {
			throw new Exception("Erro ao salvar disciplina: "+ e.getMessage());
		}
	}
	
	public void alterarDisciplina(Disciplina disciplina) throws Exception {
		try {
			String sql = "UPDATE TB_DISCIPLINA SET NOME_DISCIPLINA=?, SEMESTRE=?, NOTA=?, FALTAS=?"
					+ "WHERE RGM=?";
			
			ps = connection.prepareStatement(sql);
			ps.setString(1, disciplina.getNome());
			ps.setString(2, disciplina.getSemestre());
			ps.setString(3, disciplina.getNota());
			ps.setString(4, disciplina.getFaltas());
			ps.setInt(5, disciplina.getRgm());
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Disciplina alterada com sucesso.");
			
		} catch(Exception e) {
			throw new Exception("Erro ao alterar disciplina: "+ e.getMessage());
		}
	}
	
	public void excluirDisciplina(int rgm) throws Exception {
		try {
			String sql = "DELETE FROM TB_DISCIPLINA WHERE RGM=?";
			
			ps = connection.prepareStatement(sql);
			ps.setInt(1, rgm);
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Disciplina excluida com sucesso.");
			
		} catch(Exception e) {
			throw new Exception("Erro ao excluir disciplina: "+ e.getMessage());
		}
	}
	
	public List<Disciplina> ListarDisciplina(int rgm) throws Exception {
		List<Disciplina> lista = new ArrayList<Disciplina>();
		String sql = "SELECT * FROM TB_DISCIPLINA WHERE RGM=?";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, rgm);
			rs = ps.executeQuery();//USAR NO SELECT
			
			while(rs.next()) {
				String nomeDisciplina = rs.getString("NOME_DISCIPLINA");
				String semestre = rs.getString("SEMESTRE");
				String nota = rs.getString("NOTA");
				String faltas = rs.getString("FALTAS");
				
				disciplina = new Disciplina(rgm, nomeDisciplina, semestre, nota, faltas);
				lista.add(disciplina);
			}
			return lista;
			
		} catch(Exception e) {
			throw new Exception("Erro na lista de disciplina: "+ e.getMessage());
		}
	}
	
	public Disciplina consultarDisciplina(int rgm) throws Exception {
		String sql = "SELECT * FROM TB_DISCIPLINA WHERE RGM=?";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, rgm);
			rs = ps.executeQuery();//USAR NO SELECT
			
			if(rs.next()) {
				String nomeDisciplina = rs.getString("NOME_DISCIPLINA");
				String semestre = rs.getString("SEMESTRE");
				String nota = rs.getString("NOTA");
				String faltas = rs.getString("FALTAS");
				
				disciplina = new Disciplina(rgm, nomeDisciplina, semestre, nota, faltas);
				
				JOptionPane.showMessageDialog(null, "Disciplina consultada com sucesso.");
			}
			return disciplina;
			
		} catch(Exception e) {
			throw new Exception("Erro ao listar disciplina: "+ e.getMessage());
		}
		
	}
}
