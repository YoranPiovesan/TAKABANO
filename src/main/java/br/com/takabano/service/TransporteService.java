package br.com.takabano.service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.takabano.dto.TransportadoraClienteDTO;
import br.com.takabano.dto.TransportadoraMotoristaDTO;
import br.com.takabano.dto.TransportadoraTipoCargaDTO;
import br.com.takabano.dto.TransportadoraValorCarga;
import br.com.takabano.dto.TransportadoraViagemDTO;
import br.com.takabano.dto.TransporteDestinoDTO;
import br.com.takabano.entity.Transporte;
import br.com.takabano.helper.TransporteHelper;
import br.com.takabano.repository.TransporteRepository;

@Service
public class TransporteService {
	@Autowired
	TransporteRepository repository;
	@Autowired
	TransporteHelper transporteHelper;

	public void save(MultipartFile file) {
		try {
			List<Transporte> transportes = transporteHelper.excelToTransporte(file.getInputStream());
			for (Transporte transporte : transportes) {
				if(Objects.nonNull(transporte.getMotorista()) && Objects.nonNull(transporte.getCliente())) {
					try {
						repository.save(transporte);
					} catch (Exception e) {
						e.printStackTrace();
					}									
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("Erro ao salvar dados na base: " + e.getMessage());
		}
	}

	public List<Transporte> getAllTransportes() {
		return repository.findAll();
	}
	
	
	public List<TransporteDestinoDTO> getDestinos() {
		return repository.listaDestino();
	}
	
	public List<TransportadoraViagemDTO> getViagens() {
		return repository.listaViagem();
	}
	
	public List<TransportadoraTipoCargaDTO> getTipoCarga() {
		return repository.getTipoCarga();
	}
	
	public List<TransportadoraValorCarga> getValorCarga() {
		return repository.getValorCarga();
	}
	
	public List<TransportadoraMotoristaDTO> getQtdMotorista() {
		return repository.listaMotorista();
	}
	
	public List<TransportadoraClienteDTO> getQtdCliente() {
		return repository.listaCliente();
	}
	
	public boolean salvar(Transporte transporte) {
		try {
			repository.save(transporte);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean excluir(Long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean editar(Long id, Transporte transporte) {
		try {
			transporte.setId(id);
			salvar(transporte);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
