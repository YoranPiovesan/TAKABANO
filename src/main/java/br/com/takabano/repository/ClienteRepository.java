package br.com.takabano.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.takabano.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> { 
	
}