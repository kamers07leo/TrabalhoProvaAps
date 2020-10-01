package trab.testTrab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConexBanco;



public class TestJogador {

	public static void main(String[] args) {
	
		ConexBanco conn = new ConexBanco();
		
		Connection connection = conn.conectar();
		
		
		
		

		
		
		
final String SQL_INSERT_JOGADOR = "INSERT INTO JOGADOR(CPF, NOME_JOGADOR, EMAIL, TIME) VALUES(?, ?, ?, ?)";
		
		PreparedStatement pstc;
		try {
			pstc = connection.prepareStatement(SQL_INSERT_JOGADOR);
			pstc.setString(1, "09888997955");
			pstc.setString(2, "LEONARDO");
			pstc.setString(3, "LEO@FASFA");
			pstc.setString(4, "TESTE");
			int qtdec = pstc.executeUpdate();
			System.out.println("Num Clientes: " + qtdec);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		final String SQL_SELECT_JOGADOR = "SELECT * FROM JOGADOR";
		
		try {
			pstc = connection.prepareStatement(SQL_SELECT_JOGADOR);
			ResultSet rs = pstc.executeQuery();
			
			while(rs.next()) {
				String cpf = rs.getString("CPF");
				String nome = rs.getString("NOME_JOGADOR");
				String email = rs.getString("EMAIL");
				String time = rs.getString("TIME");

				System.out.println(cpf +" "+ nome +" "+ email +" "+ time);
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}



	}
	


