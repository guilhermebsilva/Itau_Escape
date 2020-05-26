package br.com.api.requests;

// Formata a entrada de dados
public class DebitoRequest {
    
    private Integer conta;
    private Double value;

    public DebitoRequest() {
        //
    }

    public DebitoRequest(Integer conta, Double value) {
        this.conta = conta;
        this.value = value;
    }

    public Integer getConta() {
        return conta;
    }

    public void setConta(Integer conta) {
        this.conta = conta;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}