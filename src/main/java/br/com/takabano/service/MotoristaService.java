package br.com.takabano.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.takabano.entity.Motorista;
import br.com.takabano.helper.MotoristaHelper;
import br.com.takabano.repository.MotoristaRepository;

@Service
public class MotoristaService {
	@Autowired
	MotoristaRepository repository;
	@Autowired
	MotoristaHelper motoristaHelper;

	public void save(MultipartFile file) {
		try {
			List<Motorista> motoristas = motoristaHelper.excelToMotorista(file.getInputStream());
			for (Motorista motorista : motoristas) {
				try {
					if(!motorista.getNome().isEmpty()) {
						repository.save(motorista);						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("Erro ao salvar dados na base: " + e.getMessage());
		}
	}

	public boolean salvar(Motorista motorista) {
		try {
			repository.save(motorista);
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

	public boolean editar(Long id, Motorista motorista) {
		try {
			motorista.setId(id);
			salvar(motorista);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Motorista> getMotoristas() {
		return repository.findAll();
	}

}
