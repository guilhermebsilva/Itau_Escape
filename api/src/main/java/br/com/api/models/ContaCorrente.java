package br.com.api.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ContaCorrente {
    
    private Integer agencia;

    @Id
    private Integer conta;
    private Integer dac;
    private Double saldo = 0.0;

    public ContaCorrente() {
        //
    }

    public ContaCorrente(Integer agencia, Integer conta, Integer dac) {
        this.agencia = agencia;
        this.conta = conta;
        this.dac = dac;
        this.saldo = 0.0;
    }

    public ContaCorrente(Integer agencia, Integer conta, Integer dac, Double saldo) {
        this.agencia = agencia;
        this.conta = conta;
        this.dac = dac;
        this.saldo = saldo;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public Integer getConta() {
        return conta;
    }

    public Integer getDac() {
        return dac;
    }

    public Double getSaldo() {
        return saldo;
    }

    public Boolean setSaldo(Double value) {
        if (saldo + value < 0) {
            return false;
        }
        saldo += value;
        return true;
    }

    @Override
    public String toString() {
        return agencia + "&" + conta + "&" + dac + "&" + saldo;
    }

    public static ContaCorrente stringToObject(String seria) {
        String[] attrs = seria.split("&");

        if (attrs.length == 3) {
            return new ContaCorrente(
                Integer.parseInt(attrs[0]),
                Integer.parseInt(attrs[1]),
                Integer.parseInt(attrs[2])
            );
        }
        return new ContaCorrente(
            Integer.parseInt(attrs[0]),
            Integer.parseInt(attrs[1]),
            Integer.parseInt(attrs[2]),
            Double.parseDouble(attrs[3])
        );
        
    }

    public String lineSeparation() {
        return conta + "," + agencia + "," + dac + "," + saldo;
    }

}