package br.com.destino_certo.util.teste;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="parada")
public class ParadaUtil {
	
	@Id
	private Integer idparada;
	private String local;
	private String bairro;
	private String referencia;
	private String latitude;
	private String longitude;
	
	
	public Integer getIdparada() {
		return idparada;
	}
	public void setIdparada(Integer idparada) {
		this.idparada = idparada;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	

}
