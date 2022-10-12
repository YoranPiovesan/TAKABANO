package br.com.takabano.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.takabano.entity.Motorista;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
	
	public boolean existsById(Long id); 
	
}