package conta;

import java.util.Scanner;

public class Menu {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		int opcao;
	
		while (true) {
	
			System.out.println("*************************************************");
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
			System.out.println("Entre com a opção desejada: ");
			
			opcao = verificaInteiro(sc);
			
			if (opcao==9) {
				System.out.println("\nBanco do Brazil com Z - O seu futuro começa aqui.");
				sobre();
				sc.close();
				System.exit(0);
			}
			
			switch (opcao) {
			case 1:
				System.out.println("Criar Conta\n\n");
				break;
			case 2:
				System.out.println("Listar todas as Contas\n\n");
				break;
			case 3:
				System.out.println("Consultar dados da Conta - por número\n\n");
				break;
			case 4:
				System.out.println("Atualizar dados da Conta\n\n");
				break;
			case 5:
				System.out.println("Apagar a Conta\n\n");
				break;
			case 6:
				System.out.println("Saque\n\n");
				break;
			case 7:
				System.out.println("Depósito\n\n");
				break;
			case 8:
				System.out.println("Transferência entre Contas\n\n");
				break;
			default:
				System.out.println("\nOpção Inválida!\n");
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
	
	/**
	 * Função que verifica se o valor digitado pelo usuário é um número ou não.
	 * @param
	 * @return Caso seja um valor válido, retorna a opção selecionada pelo usuário. 
	 * Do contrário, exibe a mensagem de entrada inválida. 
	 */
	private static int verificaInteiro(Scanner scanner) {
	    while (true) {
	        if (scanner.hasNextInt()) {
	        	int opcao = scanner.nextInt();
	            scanner.nextLine();
	            return opcao;
	        }
	        System.out.println("Entrada inválida. Digite um número válido entre as opções do menu.");
	        scanner.nextLine();
	    }
	}
}
