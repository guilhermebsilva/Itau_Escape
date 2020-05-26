package br.com.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.models.ContaCorrente;
import br.com.api.repositorys.ContaCorrenteRepository;

// Classe que contém os métodos para manipulação do banco
@Service
public class ContaCorrenteService {

    @Autowired
    private ContaCorrenteRepository repository;

    // Grava uma nova conta no banco
    public boolean register(ContaCorrente conta) {
        try {
            if (!contaExist(conta.getConta())) {
                // Salva no banco
                repository.save(conta);
            }
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    // Verifica se existe uma conta no banco
    public Boolean contaExist(Integer conta) {
        // Pega conta pelo número da conta e depois verifica se existe
        return repository.findById(conta).isPresent();
    }

    // Retorna o saldo da conta
    public Double getSaldo(Integer conta) {
        // Pega conta pelo número da conta e depois retorna o saldo
        return repository.findById(conta).get().getSaldo();
    }

    // Aplica um debito na conta(Subitrai um valor do saldo)
    public void debito(Integer conta, Double value) {
        if (!contaExist(conta)) {
            return;
        }

        ContaCorrente contaCorrente = repository.findById(conta).get();
        if (contaCorrente.setSaldo(-value)) {
            repository.save(contaCorrente);
        }
    }

    // Aplica um crédito na conta(Soma um valor no saldo)
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