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
		
		int opcao, numero, agencia, tipo, aniversario;
		String titular;
		float saldo, limite;
		
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
				System.out.println(Cores.TEXT_WHITE+"Criar Conta\n\n");
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
				System.out.println(Cores.TEXT_WHITE+"Listar todas as Contas\n\n");
				contas.listarTodas();
				keyPress();
				break;
			case 3:
				System.out.println(Cores.TEXT_WHITE+"Consultar dados da Conta - por número\n\n");
				keyPress();
				break;
			case 4:
				System.out.println(Cores.TEXT_WHITE+"Atualizar dados da Conta\n\n");
				keyPress();
				break;
			case 5:
				System.out.println(Cores.TEXT_WHITE+"Apagar a Conta\n\n");
				keyPress();
				break;
			case 6:
				System.out.println(Cores.TEXT_WHITE+"Saque\n\n");
				keyPress();
				break;
			case 7:
				System.out.println(Cores.TEXT_WHITE+"Depósito\n\n");
				keyPress();
				break;
			case 8:
				System.out.println(Cores.TEXT_WHITE+"Transferência entre Contas\n\n");
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
