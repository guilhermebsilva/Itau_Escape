package br.com.api.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.api.models.ContaCorrente;
import br.com.api.producers.ContaCorrenteProducer;

@RestController
@RequestMapping(value = "/conta/corrente")
public class ContaCorrenteController {
    
    private final ContaCorrenteProducer contaCorrenteProducer;

    public ContaCorrenteController(ContaCorrenteProducer contaCorrenteProducer) {
        this.contaCorrenteProducer = contaCorrenteProducer;
    }

    @RequestMapping(path = "/cadastrar", method = RequestMethod.POST)
    public @ResponseBody ContaCorrente register(@RequestBody ContaCorrente conta) {
        return conta;
    }
}