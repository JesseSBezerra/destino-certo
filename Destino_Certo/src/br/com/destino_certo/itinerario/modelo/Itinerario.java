package br.com.destino_certo.itinerario.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import br.com.destino_certo.endereco.modelo.Endereco;
import br.com.destino_certo.onibus.modelo.Onibus;

@Entity
@Table(name="tbl_itinerario")
public class Itinerario implements Serializable {

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
	
	@Column(name="ativo")
	private boolean ativo;
	
	@ManyToOne(optional=true)
	@JoinColumn
	private Endereco origem;
	
	@ManyToOne(optional=true)
	@JoinColumn
	private Endereco destino;
	
	@ManyToOne(optional=true)
	@JoinColumn
	private Onibus onibus;

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

	public Onibus getOnibus() {
		return onibus;
	}

	public void setOnibus(Onibus onibus) {
		this.onibus = onibus;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Endereco getOrigem() {
		return origem;
	}

	public void setOrigem(Endereco origem) {
		this.origem = origem;
	}

	public Endereco getDestino() {
		return destino;
	}

	public void setDestino(Endereco destino) {
		this.destino = destino;
	}
		

}
