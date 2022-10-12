package br.com.takabano.dto;

public class TransporteDestinoDTO {

	private String destino;
	private Long quantidade;
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	
	public TransporteDestinoDTO() {		
	}
	
	public TransporteDestinoDTO(String destino, Long quantidade) {
		super();
		this.destino = destino;
		this.quantidade = quantidade;
	}
	
	

}