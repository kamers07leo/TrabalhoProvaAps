package prov.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import prov.model.Jogo;

import java.sql.Date;


import db.ConexBanco;

public class TestConection {

	public static void main(String[] args) {

		/*
		 * ConexBanco conn = new ConexBanco();
		 * 
		 * Connection connection = conn.conectar(); System.out.println(connection);
		 */

		ConexBanco conn = new ConexBanco();

		Connection connection = conn.conectar();

		final String SQL_INSERT_JOGO = "INSERT INTO JOGO(NOME_JOGO, GENERO_JOGO, DATA_LANCAMENTO, VALOR_JOGO)VALUES ( ?, ?, ?, ? )";

		
		Jogo jogo = new Jogo("COD", "ACAO", Date.valueOf("2020-12-15"), 10.8f);
		
		PreparedStatement pst;
		try {
			pst = connection.prepareStatement(SQL_INSERT_JOGO);
			pst.setString(1, jogo.getNome_jogo());
			pst.setString(2, jogo.getGenero_jogo());
			// pst.setDate(3, new java.sql.Date (2020-05-04));
			pst.setDate(3, jogo.getData_lancamento_sql());
			pst.setFloat(4, jogo.getValor_jogo());
			int qtde = pst.executeUpdate();
			System.out.println("Info jogo: " + qtde);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		final String SQL_SELECT_JOGO = "SELECT * FROM JOGO";

		try {
			pst = connection.prepareStatement(SQL_SELECT_JOGO);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int idjogo = rs.getInt("ID");
				String nomejogo = rs.getString("NOME_JOGO");
				String generojogo = rs.getString("GENERO_JOGO");
				Date datalancamento = rs.getDate("DATA_LANCAMENTO");
				Float valorjogo = rs.getFloat("VALOR_JOGO");

				System.out.println(idjogo + " " + nomejogo + " " + generojogo + " " + datalancamento + " " + valorjogo);
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
