package conta;

import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;

import conta.util.Cores;
import conta.controller.ContaController;
import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;

public class Menu {

	public static void main(String[] args) {
		
		ContaController contas = new ContaController();
		
		Scanner sc = new Scanner(System.in);
		
		int opcao, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor;
		
		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João da Silva", 1000f, 100.0f);
		contas.cadastrar(cc1);
			
		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria da Silva", 2000f, 100.0f);
		contas.cadastrar(cc2);
		
		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Mariana dos Santos", 4000f, 12);
		contas.cadastrar(cp1);
		
		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Juliana Ramos", 8000f, 15);
		contas.cadastrar(cp2);
		
		contas.listarTodas();
	
		while (true) {
	
			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND + " *************************************************");
			System.out.println("                                                 ");
			System.out.println("                BANCO DO BRAZIL COM Z            ");
			System.out.println("                                                 ");
			System.out.println("*************************************************");
			System.out.println("                                                 ");
			System.out.println("            1 - Criar Conta                      ");
			System.out.println("            2 - Listar todas as Contas           ");
			System.out.println("            3 - Buscar Conta por Numero          ");
			System.out.println("            4 - Atualizar Dados da Conta         ");
			System.out.println("            5 - Apagar Conta                     ");
			System.out.println("            6 - Sacar                            ");
			System.out.println("            7 - Depositar                        ");
			System.out.println("            8 - Transferir valores entre Contas  ");
			System.out.println("            9 - Sair                             ");
			System.out.println("                                                 ");
			System.out.println("*************************************************");
			System.out.println("Entre com a opção desejada: " + Cores.TEXT_RESET);
			
			
			try {
				opcao = sc.nextInt();
			} catch(InputMismatchException e) {
				System.out.println("\nDigite valores inteiros!"); 
				sc.nextLine(); 
				opcao=0;
			}
			
			if (opcao==9) {
				System.out.println(Cores.TEXT_WHITE_BOLD+"\nBanco do Brazil com Z - O seu futuro começa aqui.");
				sobre();
				sc.close();
				System.exit(0);
			}
			
			switch (opcao) {
			case 1:
				System.out.println(Cores.TEXT_BLUE +"Criar Conta\n\n");
				System.out.println("Digite o número da agência: ");
				agencia = sc.nextInt();
				System.out.println("Digite o nome do titular: ");
				sc.skip("\\R?");
				titular = sc.nextLine();
				
				do {
					System.out.println("Digite o tipo da conta (1-CC ou 2-CP): ");
					tipo = sc.nextInt();
				} while (tipo < 1 && tipo > 2);
				System.out.println("Digite o saldo da conta: R$ ");
				saldo = sc.nextFloat();
				
				switch(tipo) {
					case 1 -> {
						System.out.println("Digite o limite de crédito: ");
						limite = sc.nextFloat();
						contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
					}
					case 2 -> {
						System.out.println("Digite o dia do aniversario da conta: ");
						aniversario = sc.nextInt();
						contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
					}
				}
				
				keyPress();
				break;
			case 2:
				System.out.println(Cores.TEXT_BLUE+"Listar todas as Contas\n\n");
				contas.listarTodas();
				keyPress();
				break;
				
			case 3:
				System.out.println(Cores.TEXT_BLUE+"Consultar dados da Conta - por número\n\n");
				System.out.println("Digite o número da conta: ");
				numero = sc.nextInt();
				contas.procurarPorNumero(numero);
				keyPress();
				break;
				
			case 4:
				System.out.println(Cores.TEXT_BLUE + "Atualizar dados da Conta\n\n");
				
				System.out.println("Digite o número da conta: ");
				numero = sc.nextInt();
				
				var buscaConta = contas.buscarNaCollection(numero);

				if (buscaConta != null) {
					
					System.out.println("Digite o Numero da Agência: ");
					agencia = sc.nextInt();
					System.out.println("Digite o Nome do Titular: ");
					sc.skip("\\R?");
					titular = sc.nextLine();
						
					System.out.println("Digite o Saldo da Conta (R$): ");
					saldo = sc.nextFloat();
					
					tipo = buscaConta.getTipo();
					
					switch(tipo) {
						case 1 -> {
							System.out.println("Digite o Limite de Crédito (R$): ");
							limite = sc.nextFloat();
							contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
						}
						case 2 -> {
							System.out.println("Digite o dia do Aniversario da Conta: ");
							aniversario = sc.nextInt();
							contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
						}
						default ->{
							System.out.println("Tipo de conta inválido!");
						}
					}
					
				}else {
					System.out.println("\nConta não encontrada!");
				}				
				keyPress();
				break;
			case 5:
				System.out.println(Cores.TEXT_BLUE+"Apagar a Conta\n\n");
				System.out.println("Digite o número da conta: ");
				numero = sc.nextInt();
				contas.deletar(numero);
				keyPress();
				break;
			case 6:
				System.out.println(Cores.TEXT_BLUE+"Saque\n\n");
				System.out.println("Digite o número da conta: ");
				numero = sc.nextInt();
				do {
					System.out.println("Digite o valor do saque: ");
					valor = sc.nextFloat();
				} while (valor <= 0);
				contas.sacar(numero, valor);
				keyPress();
				break;
			case 7:
				System.out.println(Cores.TEXT_BLUE+"Depósito\n\n");
				System.out.println("Digite o número da conta: ");
				numero = sc.nextInt();
				do {
					System.out.println("Digite o valor do depósito: ");
					valor = sc.nextFloat();
				} while (valor <= 0);
				contas.depositar(numero, valor);
				keyPress();
				break;
			case 8:
				System.out.println(Cores.TEXT_BLUE+"Transferência entre Contas\n\n");
				System.out.println("Digite o número da conta de origem: ");
				numero = sc.nextInt();
				System.out.println("Digite o número da conta de destino: ");
				numeroDestino = sc.nextInt();
				
				do {
					System.out.println("Digite o valor da transferência: ");
					valor = sc.nextFloat();
				} while (valor <= 0);
				contas.transferir(numero, numeroDestino, valor);
				keyPress();
				break;
			default:
				System.out.println(Cores.TEXT_RED_BOLD +"\nOpção Inválida!\n"+ Cores.TEXT_RESET);
				keyPress();
				break;
			}
		}
	}

	/**
	* Função que exibe as informações a respeito do desenvolvedor do projeto.
	*/
	public static void sobre() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por: ");
		System.out.println("Laura Azevedo - lauraa@genstudents.org / azevedolaura02@gmail.com");
		System.out.println("github.com/laura-azevedo");
		System.out.println("*********************************************************");
	}
	
	public static void keyPress() {
		try {
			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();
		} catch (IOException e) {
			System.out.println("Você pressionou uma tecla diferente de enter!");
		}
	}
}
