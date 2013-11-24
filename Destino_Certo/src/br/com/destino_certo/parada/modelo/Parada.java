package br.com.destino_certo.parada.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import br.com.destino_certo.onibus.modelo.Onibus;

@Entity
@Table(name="tbl_parada")
public class Parada implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(generator = "increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "numero")
	private Long numero;
	
	@Column(name="local")
	private String local;
	
	@Column(name="bairro")
	private String bairro;
	
	@Column(name="referencia")
	private String referencia;
	
	@Column(name="latitude",nullable = true)
    private double latitude;
	
    @Column(name="longitude", nullable = true)
    private double longitude;
    
    public Parada(){
    	
	}
    
    public Parada(Long numero, String local, String bairro, String referencia,
			double latitude, double longitude) {
		super();
		this.numero = numero;
		this.local = local;
		this.bairro = bairro;
		this.referencia = referencia;
		this.latitude = latitude;
		this.longitude = longitude;
	}
    
	public Parada(Long numero, String local, String bairro, String referencia,
			double latitude, double longitude, List<Onibus> onibus) {
		super();
		this.numero = numero;
		this.local = local;
		this.bairro = bairro;
		this.referencia = referencia;
		this.latitude = latitude;
		this.longitude = longitude;
		this.onibus = onibus;
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

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Onibus> onibus;

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
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

	public List<Onibus> getOnibus() {
		return onibus;
	}

	public void setOnibus(List<Onibus> onibus) {
		this.onibus = onibus;
	}

	@Override
	public String toString() {
		return "Parada [numero=" + numero + ", local=" + local + ", bairro="
				+ bairro + ", referencia=" + referencia + ", latitude="
				+ latitude + ", longitude=" + longitude + ", onibus=" + onibus
				+ "]";
	}	

	
	
}
