package prov.model;

import java.util.*;

public class Jogo {

	
	private int id;
	private String nome_jogo;
	private String genero_jogo;
	private Date data_lancamento;
	private float valor_jogo;
	
	public Jogo() {
		
	}
	
	
	public Jogo( String nome_jogo, String genero_jogo, Date data_lancamento, float valor_jogo) {
		super();
	
		this.nome_jogo = nome_jogo;
		this.genero_jogo = genero_jogo;
		this.data_lancamento = data_lancamento;
		this.valor_jogo = valor_jogo;
	}
	
	public Jogo(int id, String nome_jogo, String genero_jogo, Date data_lancamento, float valor_jogo) {
		super();
		this.id = id;
		this.nome_jogo = nome_jogo;
		this.genero_jogo = genero_jogo;
		this.data_lancamento = data_lancamento;
		this.valor_jogo = valor_jogo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome_jogo() {
		return nome_jogo;
	}
	public void setNome_jogo(String nome_jogo) {
		this.nome_jogo = nome_jogo;
	}
	public String getGenero_jogo() {
		return genero_jogo;
	}
	public void setGenero_jogo(String genero_jogo) {
		this.genero_jogo = genero_jogo;
	}
	public Date getData_lancamento() {
		return data_lancamento;
	}
	public void setData_lancamento(Date data_lancamento) {
		this.data_lancamento = data_lancamento;
	}
	public java.sql.Date getData_lancamento_sql() {
		return new java.sql.Date(data_lancamento.getTime());
	}
	
	public float getValor_jogo() {
		return valor_jogo;
	}
	public void setValor_jogo(float valor_jogo) {
		this.valor_jogo = valor_jogo;
	}
	
	@Override
	public String toString() {
		return "Jogo [id=" + id + ", nome_jogo=" + nome_jogo + ", genero_jogo=" + genero_jogo + ", data_lancamento="
				+ data_lancamento + ", valor_jogo=" + valor_jogo + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data_lancamento == null) ? 0 : data_lancamento.hashCode());
		result = prime * result + ((genero_jogo == null) ? 0 : genero_jogo.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome_jogo == null) ? 0 : nome_jogo.hashCode());
		result = prime * result + Float.floatToIntBits(valor_jogo);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jogo other = (Jogo) obj;
		if (data_lancamento == null) {
			if (other.data_lancamento != null)
				return false;
		} else if (!data_lancamento.equals(other.data_lancamento))
			return false;
		if (genero_jogo == null) {
			if (other.genero_jogo != null)
				return false;
		} else if (!genero_jogo.equals(other.genero_jogo))
			return false;
		if (id != other.id)
			return false;
		if (nome_jogo == null) {
			if (other.nome_jogo != null)
				return false;
		} else if (!nome_jogo.equals(other.nome_jogo))
			return false;
		if (Float.floatToIntBits(valor_jogo) != Float.floatToIntBits(other.valor_jogo))
			return false;
		return true;
	}
	

	
	
	
	
	
}
