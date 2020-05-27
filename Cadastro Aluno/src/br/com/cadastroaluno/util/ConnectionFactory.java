package br.com.cadastroaluno.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	public static Connection getConnection() throws Exception{
		String driver = "com.mysql.jdbc.Driver";
		
		try {
			Class.forName(driver);
			String login = "root";
			String senha = "Root";
			String servidor = "jdbc:mysql://localhost:3306/cadastrodealuno?serverTimezone=UTC";
			return DriverManager.getConnection(servidor, login, senha);
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/*
	public static void main(String[] args) {
		try {Connection connection = ConnectionFactory.getConnection();
		JOptionPane.showMessageDialog(null, "Banco conectado");		
	} catch(Exception e) {
		e.printStackTrace();
	}
	}*/
}