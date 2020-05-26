package br.com.api.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.api.Response;
import br.com.api.models.ContaCorrente;
import br.com.api.producers.ContaCorrenteProducer;
import br.com.api.requests.DebitoRequest;
import br.com.api.services.ContaCorrenteService;

// Recebe as requições do usuário
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

    // Cadastra uma nova conta
    @RequestMapping(path = "/cadastrar", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Response<ContaCorrente>> register(@RequestBody ContaCorrente conta) {
        try {
            contaCorrenteProducer.send(topicName + "register", conta);

            return ResponseEntity.ok(new Response<ContaCorrente>("Conta cadastrada", conta));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Pega o saldo da conta
    @RequestMapping(path = "/saldo/{conta}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Response<ContaCorrente>> getSaldo(@PathVariable Integer conta) {
        if (!contaCorrenteService.contaExist(conta)) {
            return ResponseEntity.notFound().build();
        }

        ContaCorrente contaCorrente = new ContaCorrente(conta, contaCorrenteService.getSaldo(conta));
        return ResponseEntity.ok(
            new Response<ContaCorrente>("Cliente encontrado e saldo resgatado", contaCorrente)
        );
    }

    // Retira um valor do saldo
    @RequestMapping(value="/debito", method=RequestMethod.PUT)
    public @ResponseBody ResponseEntity<Response<Map<String, Number>>> debito
          (@RequestBody DebitoRequest request) {
        try {

            Map<String, Number> data = new HashMap<>();
            data.put("conta", request.getConta());
            data.put("value", request.getValue());
            
            contaCorrenteProducer.send(topicName + "debito", data);
            String message = "Dinheiro retirado";

            if (contaCorrenteService.getSaldo(request.getConta()) - request.getValue() < 0) {
                message = "Saldo Indisponivel";
            }

            return ResponseEntity.ok(
                new Response<Map<String, Number>>(message, data)
            );
        } catch (Exception exception) {
            return ResponseEntity.status(500).build();
        }
    }

    // Coloca um valor somatorio no saldo
    @RequestMapping(value="/credito", method=RequestMethod.PUT)
    public @ResponseBody ResponseEntity<Response<Map<String, Number>>> credito
          (@RequestBody DebitoRequest request) {
        try {

            Map<String, Number> data = new HashMap<>();
            data.put("conta", request.getConta());
            data.put("value", request.getValue());

            contaCorrenteProducer.send(topicName + "credito", data);
            String message = "Tranferencia realizada com sucesso";
            
            return ResponseEntity.ok(
                new Response<Map<String, Number>>(message, data)
            );
        } catch (Exception exception) {
            return ResponseEntity.status(500).build();
        }
    }
    
}