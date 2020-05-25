package br.com.api.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.api.models.ContaCorrente;
import br.com.api.producers.ContaCorrenteProducer;
import br.com.api.requests.DebitoRequest;
import br.com.api.services.ContaCorrenteService;

@RestController
@RequestMapping(value = "/conta/corrente")
public class ContaCorrenteController {

    private String topicName = "contacorrente";
    
    private final ContaCorrenteProducer contaCorrenteProducer;
    
    @Autowired
    private ContaCorrenteService contaCorrenteService;

    public ContaCorrenteController(ContaCorrenteProducer contaCorrenteProducer) {
        this.contaCorrenteProducer = contaCorrenteProducer;
    }

    @RequestMapping(path = "/cadastrar", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Boolean> register(@RequestBody ContaCorrente conta) {
        try {
            contaCorrenteProducer.send(topicName + "register", conta);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(path = "/saldo/{conta}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Double> getSaldo(@PathVariable Integer conta) {
        if (!contaCorrenteService.contaExist(conta)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contaCorrenteService.getSaldo(conta));
    }

    @RequestMapping(value="/debito", method=RequestMethod.PUT)
    public @ResponseBody ResponseEntity<Boolean> debito
          (@RequestBody DebitoRequest request) {
        try {
            Map<String, Number> data = new HashMap<>();
            data.put("conta", request.getConta());
            data.put("value", request.getValue());
            contaCorrenteProducer.send(topicName + "debito", data);
            return ResponseEntity.ok(true);
        } catch (Exception exception) {
            return ResponseEntity.status(500).build();
        }
    }

    @RequestMapping(value="/credito", method=RequestMethod.PUT)
    public @ResponseBody ResponseEntity<Boolean> credito
          (@RequestBody DebitoRequest request) {
        try {
            Map<String, Number> data = new HashMap<>();
            data.put("conta", request.getConta());
            data.put("value", request.getValue());
            contaCorrenteProducer.send(topicName + "credito", data);
            return ResponseEntity.ok(true);
        } catch (Exception exception) {
            return ResponseEntity.status(500).build();
        }
    }
    
}