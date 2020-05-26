package br.com.api.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.models.ContaCorrente;

@Repository
// Se eu sobe-se explicava
public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Integer> {
    
}