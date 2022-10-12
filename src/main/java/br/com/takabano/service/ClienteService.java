package br.com.takabano.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.takabano.entity.Cliente;
import br.com.takabano.helper.ClienteHelper;
import br.com.takabano.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository repository;
	@Autowired
	ClienteHelper clienteHelper;

	public void save(MultipartFile file) {
		try {
			List<Cliente> clientes = clienteHelper.excelToCliente(file.getInputStream());
			for (Cliente cliente : clientes) {
				try {
					if(!cliente.getNome().isEmpty()) {
						repository.save(cliente);						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("Erro ao salvar dados na base: " + e.getMessage());
		}
	}

	public boolean salvar(Cliente cliente) {
		try {
			repository.save(cliente);
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

	public boolean editar(Long id, Cliente cliente) {
		try {
			cliente.setId(id);
			salvar(cliente);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Cliente> getClientes() {
		return repository.findAll();
	}

}
