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

import br.com.takabano.dto.TransportadoraClienteDTO;
import br.com.takabano.dto.TransportadoraMotoristaDTO;
import br.com.takabano.dto.TransportadoraTipoCargaDTO;
import br.com.takabano.dto.TransportadoraValorCarga;
import br.com.takabano.dto.TransportadoraViagemDTO;
import br.com.takabano.dto.TransporteDestinoDTO;
import br.com.takabano.entity.Transporte;
import br.com.takabano.helper.TransporteHelper;
import br.com.takabano.message.ResponseMessage;
import br.com.takabano.service.TransporteService;

@Controller
@RequestMapping("/takabano/transporte")
public class TransporteController {

	@Autowired
	TransporteService service;

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

	@GetMapping("/destinos")
	public ResponseEntity<List<TransporteDestinoDTO>> listaDestinos() {

		List<TransporteDestinoDTO> destinos = service.getDestinos();

		return ResponseEntity.ok().body(destinos);
	}

	@GetMapping("/viagens")
	public ResponseEntity<List<TransportadoraViagemDTO>> listaViagens() {

		List<TransportadoraViagemDTO> viagens = service.getViagens();

		return ResponseEntity.ok().body(viagens);
	}

	@GetMapping("/tiposCarga")
	public ResponseEntity<List<TransportadoraTipoCargaDTO>> getTipoCarga() {

		List<TransportadoraTipoCargaDTO> tiposCarga = service.getTipoCarga();

		return ResponseEntity.ok().body(tiposCarga);
	}

	@GetMapping("/valorCarga")
	public ResponseEntity<List<TransportadoraValorCarga>> getValorCarga() {

		List<TransportadoraValorCarga> valorCarga = service.getValorCarga();

		return ResponseEntity.ok().body(valorCarga);
	}
	
	@GetMapping("/listaMotoristas")
	public ResponseEntity<List<TransportadoraMotoristaDTO>> getListaMotoristas() {
		
		List<TransportadoraMotoristaDTO> valorCarga = service.getQtdMotorista();
		
		return ResponseEntity.ok().body(valorCarga);
	}
	@GetMapping("/listaClientes")
	public ResponseEntity<List<TransportadoraClienteDTO>> getListaClientes() {
		
		List<TransportadoraClienteDTO> valorCarga = service.getQtdCliente();
		
		return ResponseEntity.ok().body(valorCarga);
	}

	@GetMapping("/transportes")
	public ResponseEntity<List<Transporte>> getAllTransportes() {
		try {
			List<Transporte> transportes = service.getAllTransportes();

			if (transportes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(transportes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/inserir")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<ResponseMessage> inserir(@RequestBody Transporte transporte) {
		String message = "";
		try {
			if (service.salvar(transporte)) {
				message = "Transporte inserido com sucesso!: " + transporte.getTransportadora();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} else {
				message = "Por favor, insira um transpote válido: " + transporte.getTransportadora();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
			}
		} catch (Exception e) {
			message = "Erro ao inserir: " + transporte.getTransportadora();
			System.out.println(e.getStackTrace());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<ResponseMessage> delete(@PathVariable("id") Long id) {
		String message = "";
		try {
			if (service.excluir(id)) {
				message = "Transporte excluída com sucesso: " + id;
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} else {
				message = "Transporte não encontrada: " + id;
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(message));
			}
		} catch (Exception e) {
			message = "Erro ao excluir transporte" + id;
			System.out.println(e.getStackTrace());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(message));
		}
	}

	@PostMapping("/editar/{id}")
	public ResponseEntity<ResponseMessage> editar(@PathVariable("id") Long id, @RequestBody Transporte transporte) {
		String message = "";
		try {
			if (service.editar(id, transporte)) {
				message = "Transporte editado com sucesso: " + "id";
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} else {
				message = "Transporte não encontrado" + "id";
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
			}
		} catch (Exception e) {
			message = "Erro ao editar transporte" + id;
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(message));
		}
	}
	
	
}
