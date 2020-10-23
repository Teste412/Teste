package br.com.fuulltime.fullarm.painel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public Connection gerarConexao(){
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/Fullarm?useTimezone=true&serverTimezone=UTC", "root", "root1234");
		} catch (SQLException e) {
			System.out.println("Falha na conexão com o banco de dados");
		}
		return null;
	}
	
}
