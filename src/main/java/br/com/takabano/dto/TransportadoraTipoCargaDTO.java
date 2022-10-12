package br.com.takabano.dto;

public class TransportadoraTipoCargaDTO {

	private String tipo_carga;
	private Long quantidade;
	public String getTipo_carga() {
		return tipo_carga;
	}
	public void setTipo_carga(String tipo_carga) {
		this.tipo_carga = tipo_carga;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	
	public TransportadoraTipoCargaDTO() {		
	}
	
	public TransportadoraTipoCargaDTO(String tipo_carga, Long quantidade) {
		super();
		this.tipo_carga = tipo_carga;
		this.quantidade = quantidade;
	}
	
	
	
}
