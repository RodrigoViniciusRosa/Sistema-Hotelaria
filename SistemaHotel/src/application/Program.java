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
		List<Cliente> clienteCadastrados = new ArrayList<>();

		Cliente cliente = null;
		Cliente clienteReserva = null;
		Quarto novoQuarto = null;
		Quarto quartoReserva = null;
		Reserva novaReserva = null;
		Quarto quartoParaEditar = null;
		Cliente clienteParaEditar = null;

		Scanner input = new Scanner(System.in);

		boolean clienteCadastrado;
		boolean quartoCadastrado;
		int numeroEditQuarto;
		int menu;
		int numQuarto;
		final int cadQuarto = 1;
		final int listQuartos = 2;
		final int editQuartos = 3;
		final int cadCliente = 4;
		final int listClientes = 5;
		final int editClientes = 6;
		final int cadReserva = 7;
		final int listReserva = 8;
		final int canReserva = 9;
		final int sair = 10;
		int tipoQuarto;
		int id = 1;
		int editTipoQuarto;

		// Lista de opções de quartos disponíveis.

		String[] tiposQuarto = { "", "Solteiro Standard", "Solteiro Master", "Solteiro Deluxe", "Casal Standard",
				"Casal Master", "Casal Deluxe" };

		// Lista de opções do menu.

		String[] dadosMenu = { "", "Digite 1 para cadastrar um quarto", "Digite 2 para listar os quartos cadastrados",
				"Digite 3 para editar um quarto cadastrado", "Digite 4 para cadastrar um cliente",
				"Digite 5 para listar os clientes cadastrados", "Digite 6 para editar um cliente cadastrado",
				"Digite 7 para realizar uma reserva", "Digite 8 para listar as reservas",
				"Digite 9 para cancelar uma reserva", "Digite 10 para finalizar o programa" };

		// Menu com as opções para o usuário.

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

			// Opção do menu 1 - cadastro de quartos, cadastra um novo quarto, com número,
			// tipo (com opção em uma lista), e valor cobrado pelo quarto.

			switch (menu) {
			case (cadQuarto):
				System.out.println("Cadastro de quartos");
				System.out.println("-------------------");

				do {

					quartoCadastrado = false;

					System.out.print("Digite o número do quarto para cadastro: ");
					numQuarto = input.nextInt();
					System.out.println();
					for (Quarto q : quartosCadastrados) {
						if (numQuarto == q.getNumero()) {
							quartoCadastrado = true;
							System.out
									.println("Número de quarto já cadastrado, por favor verifique o número digitado!");
							System.out.println();
						}
					}
				} while (quartoCadastrado == true);

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

			// Edição de quartos cadastrados

			case (editQuartos):
				do {

					quartoCadastrado = false;

					System.out.println("Quartos cadastrados");
					System.out.println("-------------------");
					for (Quarto q : quartosCadastrados) {
						System.out.println(q.toString());
					}
					System.out.println();
					System.out.print("Escolha o número do quarto a ser editado:");

					numeroEditQuarto = input.nextInt();

					for (Quarto q : quartosCadastrados) {
						if (numeroEditQuarto == q.getNumero()) {
							quartoCadastrado = true;
							quartoParaEditar = q;
						}
					}

					if (quartoCadastrado == false) {
						System.out.println();
						System.out.println("Quarto não cadastrado, verifique por favor!");
						System.out.println();

					} else {
						do {
							System.out.println();
							System.out.println("Escolha um novo tipo de quarto para cadastro");
							System.out.println("--------------------------------------");
							for (int i = 1; i < tiposQuarto.length; i++) {
								System.out.println(i + " - " + tiposQuarto[i]);
							}
							editTipoQuarto = input.nextInt();
							if (editTipoQuarto < 1 || editTipoQuarto > 6) {
								System.out.println("Escolha um tipo de 1 a 6");
								System.out.println();
							}
						} while (editTipoQuarto < 1 || editTipoQuarto > 6);

						quartoParaEditar.setTipo(tiposQuarto[editTipoQuarto]);

						System.out.print("Digite uma nova descrição para o quarto:");
						input.nextLine();
						String editDescricaoQuarto = input.nextLine();

						quartoParaEditar.setDescricao(editDescricaoQuarto);

						System.out.print("Digite um novo preço para o quarto:");
						double editPrecoQuarto = input.nextDouble();

						quartoParaEditar.setPreco(editPrecoQuarto);

						System.out.println("Quarto: " + quartoParaEditar.getNumero() + ", editado com sucesso!");

					}

				} while (quartoCadastrado == false);
				break;

			// Cadastro de clientes.

			case (cadCliente):
				System.out.println("Cadastro de clientes");
				System.out.println("--------------------");

				for (Cliente c : clienteCadastrados) {
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
				clienteCadastrados.add(cliente);
				System.out.println();
				System.out.println("Cliente: " + cliente.getNomeCliente() + ", cadastrado com sucesso!");
				System.out.println();
				break;

			// Listagem dos clientes cadastrados.

			case (listClientes):
				System.out.println("Clientes cadastrados");
				System.out.println("--------------------");
				for (Cliente c : clienteCadastrados) {
					System.out.println(c.toString());
				}
				System.out.println();
				break;

			// Edição de clientes cadastrados

			case (editClientes):
				do {
					System.out.println("Clientes cadastrados");
					System.out.println("--------------------");
					for (Cliente c : clienteCadastrados) {
						System.out.println(c.toString());
					}
					System.out.println();

					clienteCadastrado = false;
					System.out.print("Digite o Id do cliente a ser editado: ");
					int idEditCliente = input.nextInt();
					for (Cliente c : clienteCadastrados) {
						if (idEditCliente == c.getIdCliente()) {
							clienteCadastrado = true;
							clienteParaEditar = c;
							
						}
					}
					if (clienteCadastrado == false) {
						System.out.println();
						System.out.println("Id do cliente não cadastrado, verifique por favor!");
						System.out.println();
					}

				} while (clienteCadastrado == false);

				System.out.print("Digite um novo nome para cadastro: ");
				input.nextLine();
				String editNomeCliente = input.nextLine();
				
				System.out.println();

				clienteParaEditar.setNomeCliente(editNomeCliente);

				System.out.print("Digite um novo telefone para cadastro: ");
				String edtiTelCliente = input.nextLine();
				
				System.out.println();

				clienteParaEditar.setTelCliente(edtiTelCliente);

				System.out.println("Cliente: " + clienteParaEditar.getNomeCliente() + ", cadastrado com sucesso!");
				System.out.println();
				break;

				// Reserva dos quartos.

			case (cadReserva):
				System.out.println("Reserva de quartos");
				System.out.println("------------------");
				System.out.println("Clientes cadastrados");
				System.out.println("--------------------");
				for (Cliente c : clienteCadastrados) {
					System.out.println(c.toString());
				}
				System.out.println();
				System.out.print("Digite o Id do cliente para reserva: ");

				int buscaId = input.nextInt();
				for (Cliente c : clienteCadastrados) {
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
				System.out.println("Reserva: " + clienteReserva.getNomeCliente() + ", Quarto: "
						+ quartoReserva.getNumero() + ", cadastrada com sucesso!");
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

		} while (menu != 10);

		input.close();
	}
}
