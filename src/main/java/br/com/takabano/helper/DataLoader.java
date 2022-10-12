package br.com.takabano.helper;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.takabano.entity.Cliente;
import br.com.takabano.entity.Motorista;
import br.com.takabano.repository.ClienteRepository;
import br.com.takabano.repository.MotoristaRepository;

@Component
public class DataLoader implements ApplicationRunner {

    private MotoristaRepository motoristaRepository;
    private ClienteRepository clienteRepository;

    
    public DataLoader(MotoristaRepository motoristaRepository, ClienteRepository clienteRepository) {
        this.motoristaRepository = motoristaRepository;
        this.clienteRepository = clienteRepository;
    }

    public void run(ApplicationArguments args) {
        motoristaRepository.save(new Motorista("Yoran"));
        motoristaRepository.save(new Motorista("Josney"));
        clienteRepository.save(new Cliente("Coca-cola", "11015533914111"));
        clienteRepository.save(new Cliente("Pepsi", "11015533915222"));
    }
}
