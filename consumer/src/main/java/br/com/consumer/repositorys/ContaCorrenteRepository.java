package br.com.consumer.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.consumer.models.ContaCorrente;

@Repository
public interface ContaCorrenteRepository extends CrudRepository<ContaCorrente, Integer> {
    
}