//Não é acessivel para qualquer um, somente gerentes/administradores
// Define uma senha (2222)


public class SistemaInterno {
	
	private int senha = 2222;
	
	public void autentica(Autenticavel fa) {
		boolean autenticou = fa.autentica(this.senha);
			if(autenticou) {
				System.out.println("Pode entrar no sistema");
			} else {
				System.out.println("Acesso Negado");
				
			}
			

	}
	}
