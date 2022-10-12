package br.com.takabano.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "Transporte")
public class Transporte {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "caminhao")
	private String caminhao;

	@OneToOne(cascade = { CascadeType.MERGE})
	@JoinColumn(name = "motorista")
	private Motorista motorista;
	
	@OneToOne(cascade = { CascadeType.MERGE})
	@JoinColumn(name = "cliente")
	private Cliente cliente;

	@Column(name = "tipo_carga")
	private String tipoCarga;

	@Column(name = "transportadora")
	private String transportadora;

	@Column(name = "qtd_itens_carga")
	private double qtdItensCarga;

	@Column(name = "valor_un")
	private double valorUn;

	@Column(name = "origem")
	private String origem;

	@Column(name = "destino")
	private String destino;

	@Column(name = "vlr_total_carga")
	private double vlrTotalCarga;

	@Column(name = "vlr_frete")
	private double vlrFrete;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "previsao_entrega")
	private LocalDate previsaoEntrega;

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public Transporte(long id, String caminhao, Motorista motorista, Cliente cliente, String tipoCarga,
			String transportadora, double qtdItensCarga, double valorUn, String origem, String destino,
			double vlrTotalCarga, double vlrFrete, LocalDate previsaoEntrega) {
		super();
		this.id = id;
		this.caminhao = caminhao;
		this.motorista = motorista;
		this.cliente = cliente;
		this.tipoCarga = tipoCarga;
		this.transportadora = transportadora;
		this.qtdItensCarga = qtdItensCarga;
		this.valorUn = valorUn;
		this.origem = origem;
		this.destino = destino;
		this.vlrTotalCarga = vlrTotalCarga;
		this.vlrFrete = vlrFrete;
		this.previsaoEntrega = previsaoEntrega;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getPrevisaoEntrega() {
		return previsaoEntrega;
	}

	public void setPrevisaoEntrega(LocalDate previsaoEntrega) {
		this.previsaoEntrega = previsaoEntrega;
	}

	public Transporte() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCaminhao() {
		return caminhao;
	}

	public void setCaminhao(String caminhao) {
		this.caminhao = caminhao;
	}

	public String getTipoCarga() {
		return tipoCarga;
	}

	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
	}

	public String getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(String transportadora) {
		this.transportadora = transportadora;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public double getQtdItensCarga() {
		return qtdItensCarga;
	}

	public void setQtdItensCarga(double qtdItensCarga) {
		this.qtdItensCarga = qtdItensCarga;
	}

	public double getValorUn() {
		return valorUn;
	}

	public void setValorUn(double valorUn) {
		this.valorUn = valorUn;
	}

	public double getVlrTotalCarga() {
		return vlrTotalCarga;
	}

	public void setVlrTotalCarga(double vlrTotalCarga) {
		this.vlrTotalCarga = vlrTotalCarga;
	}

	public double getVlrFrete() {
		return vlrFrete;
	}

	public void setVlrFrete(double vlrFrete) {
		this.vlrFrete = vlrFrete;
	}

}
