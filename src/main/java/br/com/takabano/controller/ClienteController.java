package br.com.takabano.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import br.com.takabano.entity.Cliente;
import br.com.takabano.helper.TransporteHelper;
import br.com.takabano.message.ResponseMessage;
import br.com.takabano.service.ClienteService;

@Controller
@RequestMapping("/takabano/cliente")
public class ClienteController {

	@Autowired
	ClienteService service;
	
	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";

		if (TransporteHelper.hasExcelFormat(file)) {
			try {
				service.save(file);
				message = "Upload realizado com sucesso: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "Erro ao inserir: " + file.getOriginalFilename() + "!";
				System.out.println("error Message:" + e);
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}
		message = "O arquivo deve ser do tipo Excel!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}

	@PostMapping("/inserir")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<ResponseMessage> inserir(@RequestBody Cliente cliente) {
		String message = "";
		try {
			if (service.salvar(cliente)) {
				message = "inserido com sucesso!: " + cliente.getNome();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} else {
				message = "Por favor, insira um cliente válido: " + cliente.getNome();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
			}
		} catch (Exception e) {
			message = "Erro ao inserir: " + cliente.getNome();
			System.out.println(e.getStackTrace());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}
	@GetMapping("/clientes")
	public ResponseEntity<List<Cliente>> getMotoristas() {
		try {
			List<Cliente> clientes = service.getClientes();

			if (clientes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(clientes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<ResponseMessage> delete(@PathVariable("id") Long id) {
		String message = "";
		try {
			if (service.excluir(id)) {
				message = "Cliente excluída com sucesso: " + id;
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} else {
				message = "Cliente não encontrada: " + id;
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(message));
			}
		} catch (Exception e) {
			message = "Erro ao excluir transporte" + id;
			System.out.println(e.getStackTrace());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(message));
		}
	}

	@PostMapping("/editar/{id}")
	public ResponseEntity<ResponseMessage> editar(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
		String message = "";
		try {
			if (service.editar(id, cliente)) {
				message = "Editado com sucesso: " + "id";
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} else {
				message = "Não encontrado" + "id";
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
			}
		} catch (Exception e) {
			message = "Erro ao editar" + id;
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(message));
		}
	}
}
