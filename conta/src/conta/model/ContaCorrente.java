package conta.model;

public class ContaCorrente extends Conta{
	
	private float limite;

	public ContaCorrente(int numero, int agencia, int tipo, String titular, float saldo, float limite) {
		super(numero, agencia, tipo, titular, saldo);
		this.limite = limite;
	}

	public float getLimite() {
		return limite;
	}

	public void setLimite(float limite) {
		this.limite = limite;
	}
	
	/** Realiza um saque na conta, considerando o limite de crédito e o saldo disponível.
	 * @param valor: quantia a ser sacada
	 * @return true se a operação for bem sucedida;
	 * ou false se o saldo+limite for insuficiente para completar a operação.
	 */
	@Override
	public boolean sacar(float valor) { 
		
		if(this.getSaldo() + this.getLimite() < valor) {
			System.out.println("\n Saldo Insuficiente!");
			return false;
		}
		
		this.setSaldo(this.getSaldo() - valor);
		return true;
		
	}
	
	/**Exibe os dados da conta e o limite de crédito.*/
    @Override
	public void visualizar() {
		super.visualizar();
		System.out.println("Limite de Crédito: " + this.limite);
	}

}
