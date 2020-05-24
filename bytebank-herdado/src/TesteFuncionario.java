
public class TesteFuncionario {

	public static void main(String[] args) {
		
		Cliente cliente = new Cliente();
		
		Gerente guilherme = new Gerente();
		guilherme.setNome("Guilherme B");
		guilherme.setCpf("222.222.222-22");
		guilherme.setSalario(2500.00);
		
		System.out.println(guilherme.getNome());
		System.out.println(guilherme.getBonificacao());

	}

}
