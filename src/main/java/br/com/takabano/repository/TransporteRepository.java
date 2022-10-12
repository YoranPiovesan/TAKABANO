package br.com.takabano.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.takabano.dto.TransportadoraClienteDTO;
import br.com.takabano.dto.TransportadoraMotoristaDTO;
import br.com.takabano.dto.TransportadoraTipoCargaDTO;
import br.com.takabano.dto.TransportadoraValorCarga;
import br.com.takabano.dto.TransportadoraViagemDTO;
import br.com.takabano.dto.TransporteDestinoDTO;
import br.com.takabano.entity.Transporte;

@Repository
public interface TransporteRepository extends JpaRepository<Transporte, Long> {
	
	@Query(value = "select new br.com.takabano.dto.TransporteDestinoDTO(t.destino, count(t.destino)) from Transporte t group by t.destino")
	public List<TransporteDestinoDTO> listaDestino();
		
	@Query(value = "select new br.com.takabano.dto.TransportadoraViagemDTO(v.transportadora, count(v.transportadora)) from Transporte v group by v.transportadora")
	public List<TransportadoraViagemDTO> listaViagem();
	
	@Query(value = "select new br.com.takabano.dto.TransportadoraTipoCargaDTO(v.tipoCarga, count(v.tipoCarga)) from Transporte v group by v.transportadora")
	public List<TransportadoraTipoCargaDTO> getTipoCarga();
	
	@Query(value = "select new br.com.takabano.dto.TransportadoraValorCarga(a.transportadora, sum(a.vlrTotalCarga) as valorCarga) from Transporte a group by a.transportadora")
	public List<TransportadoraValorCarga> getValorCarga();
	
	@Query(value = "select new br.com.takabano.dto.TransportadoraMotoristaDTO(t.motorista.nome, count(t.motorista)) from Transporte t group by t.motorista.nome")
	public List<TransportadoraMotoristaDTO> listaMotorista();
	
	@Query(value = "select new br.com.takabano.dto.TransportadoraClienteDTO(t.cliente.nome, count(t.cliente)) from Transporte t group by t.cliente.nome")
	public List<TransportadoraClienteDTO> listaCliente();
	
}