package br.com.destino_certo.taxi.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name="tbl_taxi")
public class Taxi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "numero")
	private Long numero;
		
	@Column(name="nome")
	private String nome;
	
	@Column(name="latitude",nullable = true)
    private double latitude;
	
    @Column(name="longitude", nullable = true)
    private double longitude;
	
	@Column(name="tarifa")
	private Double tarifa;
	
	@Column(name="tarifaCorrida")
	private Double tarifaCorrida;


	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getTarifa() {
		return tarifa;
	}

	public void setTarifa(Double tarifa) {
		this.tarifa = tarifa;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Double getTarifaCorrida() {
		return tarifaCorrida;
	}

	public void setTarifaCorrida(Double tarifaCorrida) {
		this.tarifaCorrida = tarifaCorrida;
	}
		

}
