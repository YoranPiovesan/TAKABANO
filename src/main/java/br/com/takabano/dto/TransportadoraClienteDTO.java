package br.com.takabano.dto;

public class TransportadoraClienteDTO {
	private String nomeCliente;
	private Long quantidade;
	
	public TransportadoraClienteDTO(String nomeCliente, Long quantidade) {
		super();
		this.nomeCliente = nomeCliente;
		this.quantidade = quantidade;
	}
	
	public TransportadoraClienteDTO() {
		super();
	}
	
	public TransportadoraClienteDTO(Long quantidade) {
		super();
		this.quantidade = quantidade;
	}

	public TransportadoraClienteDTO(String nomeCliente) {
		super();
		this.nomeCliente = nomeCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	

	
}
