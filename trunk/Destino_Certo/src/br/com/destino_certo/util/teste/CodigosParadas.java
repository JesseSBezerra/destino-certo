package br.com.destino_certo.util.teste;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_codigos_parada")
public class CodigosParadas {
	
	@Id
	@Column(name="codigo")
	private int codigo;
	
	@Column(name="local")
	private String local;
	
	@Column(name="codigo_parada")
	private int codigoParada;
	

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public int getCodigoParada() {
		return codigoParada;
	}

	public void setCodigoParada(int codigoParada) {
		this.codigoParada = codigoParada;
	}
	
	public CodigosParadas() {
		// TODO Auto-generated constructor stub
	}

	public CodigosParadas(int codigo, String local, int codigoParada) {
		super();
		this.codigo = codigo;
		this.local = local;
		this.codigoParada = codigoParada;
	}

	@Override
	public String toString() {
		return "CodigosParadas [codigo=" + codigo + ", local=" + local
				+ ", codigoParada=" + codigoParada + "]";
	}

	
		
	
	

}
