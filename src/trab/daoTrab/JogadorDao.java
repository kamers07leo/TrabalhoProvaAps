package trab.daoTrab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import db.ConexBanco;
import trab.modelTrab.Jogador;

public class JogadorDao extends ConexBanco {

	final String SQL_INSERT_JOGADOR = "INSERT INTO JOGADOR(CPF, NOME_JOGADOR, EMAIL, TIME) VALUES(?, ?, ?, ?)";
	final String SQL_SELECT_JOGADOR = "SELECT * FROM JOGADOR";
	final String SQL_SELECT_JOGADOR_CPF = "SELECT * FROM JOGADOR WHERE CPF = ?";
	final String SQL_UPDATE_JOGADOR_CPF = "UPDATE JOGADOR SET CPF = ?, NOME_JOGADOR = ?, EMAIL = ?, TIME = ? WHERE CPF = ?";
	final String SQL_DELETE_JOGADOR = "DELETE FROM JOGADOR WHERE CPF = ?";

	public int inserir(Jogador jogador) {

		int quant = 0;

		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_INSERT_JOGADOR);) {

			pst.setString(1, jogador.getCpf());
			pst.setString(2, jogador.getNome());
			pst.setString(3, jogador.getEmail());
			pst.setString(4, jogador.getTime());

			quant = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quant;
	}

	public List<Jogador> listarTodos() {
		List<Jogador> listaJogador = new ArrayList<Jogador>();

		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_JOGADOR);) {

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Jogador jogador = new Jogador();

				jogador.setCpf(rs.getString("CPF"));
				jogador.setNome(rs.getString("NOME_JOGADOR"));
				jogador.setEmail(rs.getString("EMAIL"));
				jogador.setTime(rs.getString("TIME"));

				listaJogador.add(jogador);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaJogador;

	}

	public Jogador findByCPF(String CPF) {

		Jogador jogador = null;

		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_JOGADOR_CPF);) {

			pst.setString(1, CPF);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				jogador = new Jogador();

				jogador.setCpf(rs.getString("CPF"));
				jogador.setNome(rs.getString("NOME_JOGADOR"));
				jogador.setEmail(rs.getString("EMAIL"));
				jogador.setTime(rs.getString("TIME"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jogador;
	}

	public int alterar(Jogador jogador) {
		int quant = 0;

		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_UPDATE_JOGADOR_CPF);) {

			pst.setString(1, jogador.getCpf());
			pst.setString(2, jogador.getNome());
			pst.setString(3, jogador.getEmail());
			pst.setString(4, jogador.getTime());
			pst.setString(5, jogador.getCpf());

			quant = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quant;

	}

	public void excluir(String CPF) {
		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_DELETE_JOGADOR);) {

			pst.setString(1, CPF);
			pst.execute();
			System.out.println("Jogador removido!!");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
