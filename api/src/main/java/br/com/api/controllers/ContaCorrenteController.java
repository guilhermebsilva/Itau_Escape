package br.com.api.controllers;

import org.springframework.beans.factory.annotation.Value;
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

    private String topicName = "contacorrente";
    
    private final ContaCorrenteProducer contaCorrenteProducer;

    public ContaCorrenteController(ContaCorrenteProducer contaCorrenteProducer) {
        this.contaCorrenteProducer = contaCorrenteProducer;
    }

    @RequestMapping(path = "/cadastrar", method = RequestMethod.POST)
    public @ResponseBody Boolean register(@RequestBody ContaCorrente conta) {
        try {
            contaCorrenteProducer.send(topicName + "register", conta);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}