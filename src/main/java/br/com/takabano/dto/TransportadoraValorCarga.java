package br.com.takabano.dto;

public class TransportadoraValorCarga {

	private String transportadora;
	private double vlrTotalCarga;
	public String getTransportadora() {
		return transportadora;
	}
	public void setTransportadora(String transportadora) {
		this.transportadora = transportadora;
	}
	public double getVlrTotalCarga() {
		return vlrTotalCarga;
	}
	public void setVlrTotalCarga(double vlrTotalCarga) {
		this.vlrTotalCarga = vlrTotalCarga;
	}
	public TransportadoraValorCarga() {		
	}
	public TransportadoraValorCarga(String transportadora, double vlrTotalCarga) {
		super();
		this.transportadora = transportadora;
		this.vlrTotalCarga = vlrTotalCarga;
	}
	
	
	
}
