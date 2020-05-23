
public class TesteFuncionario {

	public static void main(String[] args) {
		
		Gerente nico = new Gerente();
		nico.setNome("Nico Steppat");
		nico.setCpf("222.222.222-22");
		nico.setSalario(2500.80);
		
		System.out.println(nico.getNome());
		System.out.println(nico.getBonificacao());

	}

}
