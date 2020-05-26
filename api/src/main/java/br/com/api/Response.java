package br.com.api;

// Classe para formatar a resposta
public class Response<T> {
    
    private String message;
    private T data;

    public Response(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public String toString() {
        return "{" +
                "\"message\": " + message +
                "\"data\": " + data.toString();
    }
}