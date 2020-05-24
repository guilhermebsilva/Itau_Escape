package br.com.api.models;

public class ContaCorrente {
    
    private Integer agencia;
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

    @Override
    public String toString() {
        return "{'agencia': " + agencia + ", 'conta': " + conta + ", 'dac': " + dac +", 'saldo': " + saldo + "}";
    }

}