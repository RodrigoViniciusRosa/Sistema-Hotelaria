package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Cliente;
import entities.Quarto;
import entities.Reserva;

public class Program {

	public static void main(String[] args) {

		Reserva gerenciadorReservas = new Reserva();

		List<Quarto> quartosCadastrados = new ArrayList<>();
		List<Cliente> clienteCadastrado = new ArrayList<>();

		Cliente cliente = null;
		Cliente clienteReserva = null;
		Quarto novoQuarto = null;
		Quarto quartoReserva = null;
		Reserva novaReserva = null;

		Scanner input = new Scanner(System.in);
		int menu;
		int numQuarto;
		final int cadQuarto = 1;
		final int listQuartos = 2;
		final int cadCliente = 3;
		final int listClientes = 4;
		final int cadReserva = 5;
		final int listReserva = 6;
		final int canReserva = 7;
		final int sair = 8;
		int tipoQuarto;
		int id = 1;
		int x = 0;

		// Lista de opções de quartos disponíveis.

		String[] tiposQuarto = { "", "Solteiro Standard", "Solteiro Master", "Solteiro Deluxe", "Casal Standard",
				"Casal Master", "Casal Deluxe" };

		// Lista de opções do menu.

		String[] dadosMenu = { "", "Digite 1 para cadastrar um quarto", "Digite 2 para listar os quartos cadastrados",
				"Digite 3 para cadastrar um cliente", "Digite 4 para listar os clientes cadastrados",
				"Digite 5 para realizar uma reserva", "Digite 6 para listar as reservas",
				"Digite 7 para cancelar uma reserva", "Digite 8 para finalizar o programa" };

		do {
			System.out.println("Sistema hoteleiro");
			System.out.println("-----------------");
			System.out.println("Menu");
			System.out.println("----");
			for (int i = 1; i < dadosMenu.length; i++)
				System.out.println(i + " - " + dadosMenu[i]);
			menu = input.nextInt();

			if (menu > 8) {
				System.out.println("Escolha uma opção entre 1 e 8");
				System.out.println();
			}

			// Cadastro de quartos.

			switch (menu) {
			case (cadQuarto):
				System.out.println("Cadastro de quartos");
				System.out.println("-------------------");

				do {
					System.out.print("Digite o número do quarto para cadastro: ");
					numQuarto = input.nextInt();
					System.out.println();
					for (Quarto q : quartosCadastrados) {
						if (numQuarto == q.getNumero()) {
							x = 1;
							System.out.println("Número de quarto já cadastrado, por favor verifique o número digitado!");
							System.out.println();
						} else
							x = 2;
					}
				} while (x == 1);

				do {
					System.out.println("Escolha o tipo do quarto para cadastro");
					System.out.println("--------------------------------------");
					for (int i = 1; i < tiposQuarto.length; i++) {
						System.out.println(i + " - " + tiposQuarto[i]);
					}
					tipoQuarto = input.nextInt();
					if (tipoQuarto < 1 || tipoQuarto > 6) {
						System.out.println("Escolha um tipo de 1 a 6");
						System.out.println();
					}
				} while (tipoQuarto < 1 || tipoQuarto > 6);

				System.out.print("Digite a descrição do quarto para cadastro: ");
				input.nextLine();
				String descricao = input.nextLine();
				System.out.println();
				System.out.print("Digite o valor cobrado pelo quarto para cadastro: ");
				double preco = input.nextDouble();
				System.out.println();

				novoQuarto = new Quarto(numQuarto, tiposQuarto[tipoQuarto], descricao, preco);
				quartosCadastrados.add(novoQuarto);
				System.out.println("Quarto " + novoQuarto.getNumero() + " cadastrado com sucesso!");
				System.out.println();
				break;

			// Listagem dos quartos cadastrados.

			case (listQuartos):
				System.out.println("Quartos cadastrados");
				System.out.println("--------------------");
				for (Quarto q : quartosCadastrados) {
					System.out.println(q.toString());
				}
				System.out.println();
				break;

			// Cadastro de clientes.

			case (cadCliente):
				System.out.println("Cadastro de clientes");
				System.out.println("--------------------");

				for (Cliente c : clienteCadastrado) {
					id = c.getIdCliente();
					id++;
				}

				System.out.println("ID Cliente: " + id);

				System.out.print("Digite o nome do cliente para cadastro: ");
				input.nextLine();
				String nomeCliente = input.nextLine();
				System.out.print("Digite o telefone do cliente para cadastro (DD)00000-0000: ");
				String telCliente = input.nextLine();
				cliente = new Cliente(id, nomeCliente, telCliente);
				clienteCadastrado.add(cliente);
				System.out.println();
				System.out.println("Cliente: " + cliente.getNomeCliente() + ", cadastrado com sucesso!");
				System.out.println();
				break;

			// Listagem dos clientes cadastrados.

			case (listClientes):
				System.out.println("Clientes cadastrados");
				System.out.println("--------------------");
				for (Cliente c : clienteCadastrado) {
					System.out.println(c.toString());
				}
				System.out.println();
				break;

			// Reserva dos quartos.

			case (cadReserva):
				System.out.println("Reserva de quartos");
				System.out.println("------------------");
				System.out.println("Clientes cadastrados");
				System.out.println("--------------------");
				for (Cliente c : clienteCadastrado) {
					System.out.println(c.toString());
				}
				System.out.println();
				System.out.print("Digite o Id do cliente para reserva: ");

				int buscaId = input.nextInt();
				for (Cliente c : clienteCadastrado) {
					if (buscaId == c.getIdCliente()) {
						clienteReserva = c;
						break;

					}
				}

				System.out.println("Quartos cadastrados");
				System.out.println("-------------------");
				for (Quarto q : quartosCadastrados) {
					System.out.println(q.toString());
				}
				System.out.println();
				System.out.print("Escolha o número quarto a ser reservado: ");

				int buscaNumeroQuarto = input.nextInt();
				for (Quarto q : quartosCadastrados) {
					if (buscaNumeroQuarto == q.getNumero()) {
						quartoReserva = q;
						break;
					}
				}

				System.out.println();
				System.out.print("Digite a data de inicio da reserva (dd/mm/aaaa): ");
				input.nextLine();
				String dataInicial = input.nextLine();
				System.out.println();
				System.out.print("Digite a data final da reserva (dd/mm/aaaa): ");
				String dataFinal = input.nextLine();

				novaReserva = new Reserva(dataInicial, dataFinal, quartoReserva, clienteReserva);
				gerenciadorReservas.addReserva(novaReserva);

				System.out.println();
				System.out.println("Reserva: " + cliente.getNomeCliente() + ", cadastrada com sucesso!");
				System.out.println();
				break;

			// Listagem das reservas cadastradas.

			case (listReserva):
				System.out.println("Reservas cadastradas");
				System.out.println("--------------------");
				gerenciadorReservas.listReservas();
				System.out.println();
				break;
			}

		} while (menu != 8);

		input.close();
	}
}