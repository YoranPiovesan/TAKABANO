package br.com.takabano.helper;

import br.com.takabano.entity.Transporte;

public class VerificaObjeto {
	
	public boolean verificaPlanilha(Transporte planilha) {
		if(planilha.getCaminhao() == null || planilha.getCaminhao() == "") {
			return false;
		}
		if(planilha.getTipoCarga() == null || planilha.getTipoCarga()  == "") {
			return false;
		}
		if(planilha.getTransportadora()== null || planilha.getTransportadora() == "") {
			return false;
		}
		return true;
	}

}
