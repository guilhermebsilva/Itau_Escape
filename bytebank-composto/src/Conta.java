

public class Conta {

	double saldo = 100;
	int agencia;
	int numero;
    cliente titular;
    
    
    public void deposita(double valor) {
    	this.saldo += valor;  
    	
    }

	public boolean saca(double valor) {
		if(this.saldo >= valor) {
			this.saldo -= valor;
			return true;
		} else {
			return false;
			
		}
		// TODO Auto-generated method stub
		
	}
	
	public boolean transfere(double valor, Conta destino) {
		if(this .saldo >= valor) {
			this.saldo -= valor;
			destino.deposita(valor);
			return true;
		} else {
			
		return false;
		}
		public double getSaldo() {
			return this.saldo;
			
			}
	
		
		}
}
