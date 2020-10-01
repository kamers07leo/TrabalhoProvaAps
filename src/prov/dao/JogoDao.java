package prov.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import prov.model.Jogo;
import db.ConexBanco;

public class JogoDao extends ConexBanco {

	final String SQL_INSERT_JOGO = "INSERT INTO JOGO(NOME_JOGO, GENERO_JOGO, DATA_LANCAMENTO, VALOR_JOGO)VALUES ( ?, ?, ?, ? )";
	final String SQL_SELECT_JOGO = "SELECT * FROM JOGO";
	final String SQL_SELECT_JOGO_ID = "SELECT * FROM JOGO WHERE ID = ?";
	final String SQL_UPDATE_JOGO = "UPDATE JOGO SET NOME_JOGO = ?, GENERO_JOGO = ?, DATA_LANCAMENTO = ?, VALOR_JOGO = ? WHERE ID = ?";
	final String SQL_DELETE_JOGO = "DELETE FROM JOGO WHERE ID = ?";

	public int inserir(Jogo jogo) {

		int quanti = 0;

		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_INSERT_JOGO);) {
			
			pst.setString(1, jogo.getNome_jogo());
			pst.setString(2, jogo.getGenero_jogo());
			pst.setDate(3, new java.sql.Date(jogo.getData_lancamento().getTime()));
			pst.setFloat(4, jogo.getValor_jogo());

			quanti = pst.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return quanti;
	}

	public List<Jogo> listAll() {
		List<Jogo> listaJogo = new ArrayList<Jogo>();

		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_JOGO);) {

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Jogo jogo = new Jogo();

				jogo.setId(rs.getInt("ID"));
				jogo.setNome_jogo(rs.getNString("NOME_JOGO"));
				jogo.setGenero_jogo(rs.getString("GENERO_JOGO"));
				jogo.setData_lancamento(rs.getDate("DATA_LANCAMENTO"));
				jogo.setValor_jogo(rs.getFloat("VALOR_JOGO"));

				listaJogo.add(jogo);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return listaJogo;

	}

	public Jogo findByID(int ID) {

		Jogo jogo = null;

		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_JOGO_ID);) {

			pst.setInt(1, ID);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				jogo = new Jogo();

				jogo.setId(rs.getInt("ID"));
				jogo.setNome_jogo(rs.getNString("NOME_JOGO"));
				jogo.setGenero_jogo(rs.getString("GENERO_JOGO"));
				jogo.setData_lancamento(rs.getDate("DATA_LANCAMENTO"));
				jogo.setValor_jogo(rs.getFloat("VALOR_JOGO"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jogo;
	}

	public int alterar(Jogo jogo) {
		int quant = 0;

		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_UPDATE_JOGO);) {

			
			pst.setString(1, jogo.getNome_jogo());
			pst.setString(2, jogo.getGenero_jogo());
			pst.setDate(3,  java.sql.Date.valueOf(jogo.getData_lancamento().toString()));
			pst.setFloat(4, jogo.getValor_jogo());
			pst.setInt(5, jogo.getId());

			quant = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quant;

	}

	public void excluir(int id) {
	    try(Connection connection = this.conectar();
	            PreparedStatement pst = connection.prepareStatement(SQL_DELETE_JOGO);){

	        pst.setInt(1, id);
	        pst.execute();
	        

	    }catch(SQLException e) {
	        e.printStackTrace();
	    }

	}
}
