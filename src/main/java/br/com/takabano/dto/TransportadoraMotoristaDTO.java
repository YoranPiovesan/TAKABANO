package br.com.takabano.dto;

public class TransportadoraMotoristaDTO {
	private String nomeMotorista;
	private Long quantidade;
	
	public TransportadoraMotoristaDTO(String nomeMotorista, Long quantidade) {
		super();
		this.nomeMotorista = nomeMotorista;
		this.quantidade = quantidade;
	}
	
	public TransportadoraMotoristaDTO() {
		super();
	}
	
	public TransportadoraMotoristaDTO(Long quantidade) {
		super();
		this.quantidade = quantidade;
	}

	public TransportadoraMotoristaDTO(String nomeMotorista) {
		super();
		this.nomeMotorista = nomeMotorista;
	}

	public String getNomeMotorista() {
		return nomeMotorista;
	}
	public void setNomeMotorista(String nomeMotorista) {
		this.nomeMotorista = nomeMotorista;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	
}
