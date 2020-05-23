
public class TestaGetESet {
	
	public static void main(String[] args) {
		Conta conta = new Conta(1337, 24226);
		System.out.println(conta.getNumero());
		
		cliente Paulo = new cliente();
		//conta.titular = Paulo;
		Paulo.setNome("Paulo Silveira");
		
		conta.setTitular(Paulo);
		
		System.out.println(conta.getTitular().getNome());
		
		conta.getTitular().setProfissao("programador");
		}

}
