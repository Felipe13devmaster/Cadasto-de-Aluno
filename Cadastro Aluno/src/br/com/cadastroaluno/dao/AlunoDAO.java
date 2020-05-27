package br.com.cadastroaluno.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import br.com.cadastroaluno.model.Aluno;
import br.com.cadastroaluno.util.ConnectionFactory;

//DAO = OBLETO DE ACESSO A DADOS =>CONTEM O CRUD
public class AlunoDAO {
	private Aluno aluno;
	private Connection connection;//CONECTA COM O BANCO
	private PreparedStatement ps;//EXECUTA COMANDO NO SQL
	private ResultSet rs;//TABELA
	//CONSTRUTOR
	public AlunoDAO() throws Exception {//THROWS EXCEPTION SIGNIFICA QUE ERROS NAO SAO TRATADOS NESTE METODO
		try {
			connection = ConnectionFactory.getConnection();
		} catch(Exception e) {
			throw new Exception("Erro: "+ e.getMessage());//ESTE TRECHOAPENAS SERVE PARA O DEV DEBUGAR
		}
	}
	
	public void salvarAluno(Aluno aluno) throws Exception {
		try {
			String sql = "INSERT INTO TB_ALUNO (RGM, NOME, NASCIMENTO, CPF, EMAIL, ENDERECO,"
					+ "MUNICIPIO, UF, CELULAR, CURSO, CAMPUS, PERIODO )"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			
			ps = connection.prepareStatement(sql);
			ps.setInt(1, aluno.getRgm());//1 PORQUE VAI GRAVAR NO PRIMEIRO CAMPO DE VALUES (?)
			ps.setString(2,aluno.getNome());
			ps.setString(3, aluno.getNascimento());
			ps.setString(4, aluno.getCpf());
			ps.setString(5, aluno.getEmail());
			ps.setString(6, aluno.getEndereco());
			ps.setString(7, aluno.getMunicipio());
			ps.setString(8, aluno.getUf());
			ps.setString(9, aluno.getCelular());
			ps.setString(10, aluno.getCurso());
			ps.setString(11, aluno.getCampus());
			ps.setString(12, aluno.getPeriodo());
			ps.executeUpdate();//USAR AO ISERIR,ALTERAR E EXCLUIR
			
			JOptionPane.showMessageDialog(null, "Sucesso ao salvar aluno.");
			
		} catch(Exception e) {
			throw new Exception("Erro ao salvar aluno: "+ e.getMessage());
		}
	}
	
	public void alterarAluno(Aluno aluno) throws Exception {
		try {
			String sql = "UPDATE TB_ALUNO SET NOME=?, NASCIMENTO=?, CPF=?, EMAIL=?, ENDERECO=?,"
					+ "MUNICIPIO=?, UF=?, CELULAR=?, CURSO=?, CAMPUS=?, PERIODO=? "
					+ "WHERE RGM=?";
			
			ps = connection.prepareStatement(sql);
			ps.setString(1,aluno.getNome());//1 PORQUE VAI GRAVAR NO PRIMEIRO CAMPO DE VALUES (?)
			ps.setString(2, aluno.getNascimento());
			ps.setString(3, aluno.getCpf());
			ps.setString(4, aluno.getEmail());
			ps.setString(5, aluno.getEndereco());
			ps.setString(6, aluno.getMunicipio());
			ps.setString(7, aluno.getUf());
			ps.setString(8, aluno.getCelular());
			ps.setString(9, aluno.getCurso());
			ps.setString(10, aluno.getCampus());
			ps.setString(11, aluno.getPeriodo());
			ps.setInt(12, aluno.getRgm());
			ps.executeUpdate();//USAR AO ISERIR,ALTERAR E EXCLUIR
			
			JOptionPane.showMessageDialog(null, "Sucesso ao alterar aluno.");
			
		} catch(Exception e) {
			throw new Exception("Erro ao alterar: "+ e.getMessage());
		}
	}
	
	public void excluirAluno(int rgm) throws Exception {
		try {
			String sql = "DELETE FROM TB_ALUNO WHERE RGM=?";
			
			ps = connection.prepareStatement(sql);
			ps.setInt(1, rgm);
			ps.executeUpdate();//USAR AO ISERIR,ALTERAR E EXCLUIR
			
			JOptionPane.showMessageDialog(null, "Sucesso ao excluir aluno.");
			
		} catch(Exception e) {
			throw new Exception("Erro ao excluir aluno: "+ e.getMessage());
		}
	}
	
	public List<Aluno> ListarAlunos() throws Exception {
		List<Aluno> lista = new ArrayList<Aluno>();
		String sql = "SELECT * FROM TB_ALUNO";
		
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();//USAR NO SELECT
			
			while(rs.next()) {
				int rgm = rs.getInt("RGM");
				String nome = rs.getString("NOME");
				String nascimento = rs.getString("NASCIMENTO");
				String cpf = rs.getString("CPF");
				String email = rs.getString("EMAIL");
				String endereco = rs.getString("ENDERECO");
				String municipio = rs.getString("MUNICIPIO");
				String uf = rs.getString("UF");
				String celular = rs.getString("CELULAR");
				String curso = rs.getString("CURSO");
				String campus = rs.getString("CAMPUS");
				String periodo = rs.getString("PERIODO");
				aluno = new Aluno(rgm, nome, nascimento, cpf, email, endereco, 
						municipio, uf, celular, curso, campus, periodo);
				lista.add(aluno);
				
			}
			return lista;
			
		} catch(Exception e) {
			throw new Exception("Erro na lista de alunos: "+ e.getMessage());
		}
	}
	
	public Aluno consultarAluno(int rgm) throws Exception {
		String sql = "SELECT * FROM TB_ALUNO WHERE RGM=?";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, rgm);
			rs = ps.executeQuery();//USAR NO SELECT
			
			if(rs.next()) {
				String nome = rs.getString("NOME");
				String nascimento = rs.getString("NASCIMENTO");
				String cpf = rs.getString("CPF");
				String email = rs.getString("EMAIL");
				String endereco = rs.getString("ENDERECO");
				String municipio = rs.getString("MUNICIPIO");
				String uf = rs.getString("UF");
				String celular = rs.getString("CELULAR");
				String curso = rs.getString("CURSO");
				String campus = rs.getString("CAMPUS");
				String periodo = rs.getString("PERIODO");
				
				aluno = new Aluno(rgm, nome, nascimento, cpf, email, endereco, 
						municipio, uf, celular, curso, campus, periodo);
				
				JOptionPane.showMessageDialog(null, "Consulta de alunos concluida com sucesso.");
			}
			return aluno;
			
		} catch(Exception e) {
			throw new Exception("Erro ao listar alunos: "+ e.getMessage());
		}
	}
	
}
