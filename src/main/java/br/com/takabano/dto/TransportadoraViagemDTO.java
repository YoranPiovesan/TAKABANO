package br.com.takabano.dto;

public class TransportadoraViagemDTO {

	private String transportadora;
	private Long qtd_viagem;
	public String getTransportadora() {
		return transportadora;
	}
	public void setTransportadora(String transportadora) {
		this.transportadora = transportadora;
	}
	public Long getQtd_viagem() {
		return qtd_viagem;
	}
	public void setQtd_viagem(Long qtd_viagem) {
		this.qtd_viagem = qtd_viagem;
	}
	
	public TransportadoraViagemDTO() {			
	}
	
	public TransportadoraViagemDTO(String transportadora, Long qtd_viagem) {
		super();
		this.transportadora = transportadora;
		this.qtd_viagem = qtd_viagem;
	}
	
	
		
}
