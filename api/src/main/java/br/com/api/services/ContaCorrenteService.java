package br.com.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.models.ContaCorrente;
import br.com.api.repositorys.ContaCorrenteRepository;

@Service
public class ContaCorrenteService {

    @Autowired
    private ContaCorrenteRepository repository;

    public boolean register(ContaCorrente conta) {
        try {
            repository.save(conta);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public Boolean contaExist(Integer conta) {
        return repository.findById(conta).isPresent();
    }

    public Double getSaldo(Integer conta) {
        return repository.findById(conta).get().getSaldo();
    }

    public void debito(Integer conta, Double value) {
        if (!contaExist(conta)) {
            return;
        }

        ContaCorrente contaCorrente = repository.findById(conta).get();
        if (contaCorrente.setSaldo(-value)) {
            repository.save(contaCorrente);
        }
    }

    public void credito(Integer conta, Double value) {
        if (!contaExist(conta)) {
            return;
        }

        ContaCorrente contaCorrente = repository.findById(conta).get();
        if (contaCorrente.setSaldo(value)) {
            repository.save(contaCorrente);
        }
    }
    
}