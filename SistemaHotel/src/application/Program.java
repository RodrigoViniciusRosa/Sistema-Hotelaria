package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import entities.Cliente;
import entities.Quarto;
import entities.Reserva;
import model.exceptions.TextoInvalidoException;

public class Program {

	public static void main(String[] args) {

		Reserva gerenciadorReservas = new Reserva();
		Reserva acessReservaEfetuada = new Reserva();

		List<Cliente> clienteCadastrados = new ArrayList<>();
		List<Quarto> quartosCadastrados = new ArrayList<>();
		List<Reserva> reservaEfetuada = acessReservaEfetuada.getReservaEfetuada();

		Scanner input = new Scanner(System.in);

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
		boolean clienteCadastrado = false;
		boolean quartoCadastrado = false;
		int menu = 0;

		String[] tiposQuarto = { "", "Solteiro Standard", "Solteiro Master", "Solteiro Deluxe", "Casal Standard",
				"Casal Master", "Casal Deluxe" };

		do {

			switch (menu(input, menu)) {

			// Chama o método cadastrarQuartos().

			case (cadQuarto):
				cadastrarQuartos(input, quartoCadastrado, quartosCadastrados, tiposQuarto);
				break;

			// Chama o método listarQuartos().

			case (listQuartos):
				listarQuartos(quartosCadastrados);
				break;

			// Chama o método editarQuartos().

			case (editQuartos):
				editarQuartos(input, quartoCadastrado, quartosCadastrados, tiposQuarto);
				break;

			// Chama o método cadastrarClientes().

			case (cadCliente):
				cadastrarClientes(input, clienteCadastrados);
				break;

			// Chama o método listarClientes().

			case (listClientes):
				listarClientes(clienteCadastrados);
				break;

			// Chama o método editarClientes().

			case (editClientes):
				editarClientes(input, clienteCadastrados, clienteCadastrado);
				break;

			// Chama o método cadastrarReserva().

			case (cadReserva):
				cadastrarReserva(input, clienteCadastrados, clienteCadastrado, gerenciadorReservas, quartoCadastrado,
						quartosCadastrados);
				break;

			// Chama o método listarReserva().

			case (listReserva):
				listarReserva(gerenciadorReservas);
				break;

			// Chama o método cancelarReserva().

			case (canReserva):
				cancelarReserva(input, gerenciadorReservas, reservaEfetuada);
				break;

			// Chama o método finalizarPrograma().

			case (sair):
				finalizarPrograma(input);
				break;

			}

		} while (menu != sair);

		input.close();

	}

	public static int menu(Scanner input, int menu) {

		String[] dadosMenu = { "", "Opção: 1 para cadastrar um quarto", "Opção: 2 para listar os quartos cadastrados",
				"Opção: 3 para editar um quarto cadastrado", "Opção: 4 para cadastrar um cliente",
				"Opção: 5 para listar os clientes cadastrados", "Opção: 6 para editar um cliente cadastrado",
				"Opção: 7 para realizar uma reserva", "Opção: 8 para listar as reservas",
				"Opção: 9 para cancelar uma reserva", "Opção: 10 para finalizar o programa" };

		System.out.println("Sistema hoteleiro");
		System.out.println("-----------------");
		System.out.println("Menu");
		System.out.println("----");
		for (int i = 1; i < dadosMenu.length; i++)
			System.out.println(dadosMenu[i]);
		menu = input.nextInt();

		if (menu > 10) {
			System.out.println("Escolha uma opção entre 1 e 10");
			System.out.println();
		}
		return menu;
	}

	/**
	 * Método para cadastrar quartos.
	 *
	 * @param input              Scanner para entrada do usuário.
	 * @param quartoCadastrado   Flag para verificar se o quarto já está cadastrado.
	 * @param quartosCadastrados Lista de quartos já cadastrados.
	 * @param tiposQuarto        Array com os tipos de quartos disponíveis.
	 */

	public static void cadastrarQuartos(Scanner input, boolean quartoCadastrado, List<Quarto> quartosCadastrados,
			String[] tiposQuarto) {

		int tipoQuarto = 0;
		int numQuarto = 0;
		double preco = 0.0;
		String descricao = null;
		boolean numeroInvalido = false;
		boolean descricaoValida = false;

		Quarto novoQuarto = null;

		System.out.println("Cadastro de quartos");
		System.out.println("-------------------");

		do {

			quartoCadastrado = false;

			try {
				System.out.print("Digite o número do quarto para cadastro: ");
				numQuarto = input.nextInt();
				System.out.println();

				// Verifica se o número do quarto já está cadastrado
				for (Quarto q : quartosCadastrados) {
					if (numQuarto == q.getNumero()) {
						quartoCadastrado = true;
						System.out.println("Número de quarto já cadastrado, por favor verifique o número digitado!");
						System.out.println();
						break;
					}
				}

				if (!quartoCadastrado) {
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("Entrada inválida! Digite um número para cadastro.");
				System.out.println();
				input.nextLine();
			}

		} while (true);

		do {

			try {
				System.out.println("Escolha o tipo do quarto para cadastro");
				System.out.println("--------------------------------------");

				// Exibe opções de tipos de quartos
				for (int i = 1; i < tiposQuarto.length; i++) {
					System.out.println(i + " - " + tiposQuarto[i]);
				}
				tipoQuarto = input.nextInt();
				if (tipoQuarto < 1 || tipoQuarto > 6) {
					System.out.println("Escolha um tipo de 1 a 6");
					System.out.println();
				}
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("Entrada inválida! Digite um tipo de quarto válido.");
				System.out.println();
				input.nextLine();
			}
		} while (tipoQuarto < 1 || tipoQuarto > 6);

		do {
			try {
				System.out.print("Digite a descrição do quarto para cadastro: ");

				descricao = input.next();

				// Verifica a validade da descrição usando uma exceção personalizada
				TextoInvalidoException.verificarTexto(descricao);
				descricaoValida = true;
				if (descricaoValida == true) {
					break;
				}
			} catch (TextoInvalidoException e) {
				System.out.println();
				System.out.println("Erro: " + e.getMessage());
				System.out.println();

			}

		} while (!descricaoValida);

		do {
			try {
				System.out.print("Digite o valor cobrado pelo quarto para cadastro: ");
				preco = input.nextDouble();
				numeroInvalido = true;
				System.out.println();
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("Entrada inválida! Digite um valor válido para cadastro.");
				System.out.println();
				input.nextLine();
			}
		} while (!numeroInvalido);

		// Cria um novo quarto e o adiciona à lista de quartos cadastrados
		novoQuarto = new Quarto(numQuarto, tiposQuarto[tipoQuarto], descricao, preco);
		quartosCadastrados.add(novoQuarto);
		System.out.println("Quarto " + novoQuarto.getNumero() + " cadastrado com sucesso!");
		System.out.println();
	}

	public static void listarQuartos(List<Quarto> quartosCadastrados) {

		System.out.println("Quartos cadastrados");
		System.out.println("--------------------");
		for (Quarto q : quartosCadastrados) {
			System.out.println(q.toString());
		}
		System.out.println();

	}

	public static void editarQuartos(Scanner input, boolean quartoCadastrado, List<Quarto> quartosCadastrados,
			String[] tiposQuarto) {

		int editTipoQuarto = 0;
		Quarto quartoParaEditar = null;

		do {

			quartoCadastrado = false;

			System.out.println("Quartos cadastrados");
			System.out.println("-------------------");
			for (Quarto q : quartosCadastrados) {
				System.out.println(q.toString());
			}
			System.out.println();
			System.out.print("Escolha o número do quarto a ser editado:");

			int numeroEditQuarto = input.nextInt();

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

	}

	public static void cadastrarClientes(Scanner input, List<Cliente> clienteCadastrados) {

		Cliente cliente = null;
		int id = 1;

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
	}

	public static void listarClientes(List<Cliente> clienteCadastrados) {

		System.out.println("Clientes cadastrados");
		System.out.println("--------------------");
		for (Cliente c : clienteCadastrados) {
			System.out.println(c.toString());
		}
		System.out.println();
	}

	public static void editarClientes(Scanner input, List<Cliente> clienteCadastrados, boolean clienteCadastrado) {

		Cliente clienteParaEditar = null;

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
	}

	public static void cadastrarReserva(Scanner input, List<Cliente> clienteCadastrados, boolean clienteCadastrado,
			Reserva gerenciadorReservas, boolean quartoCadastrado, List<Quarto> quartosCadastrados) {

		Quarto quartoReserva = null;
		Reserva novaReserva = null;
		Cliente clienteReserva = null;

		int numReserva = 1;
		int numeroEditQuarto;

		System.out.println("Reserva de quartos");
		System.out.println("------------------");

		System.out.println();
		System.out.println("Reserva: " + numReserva);

		do {
			System.out.println("Clientes cadastrados");
			System.out.println("--------------------");
			for (Cliente c : clienteCadastrados) {
				System.out.println(c.toString());
			}
			System.out.println();

			clienteCadastrado = false;
			System.out.print("Digite o Id do cliente para reserva: ");
			int idEditCliente = input.nextInt();
			for (Cliente c : clienteCadastrados) {
				if (idEditCliente == c.getIdCliente()) {
					clienteCadastrado = true;
					clienteReserva = c;

				}
			}
			if (clienteCadastrado == false) {
				System.out.println();
				System.out.println("Id do cliente não cadastrado, verifique por favor!");
				System.out.println();
			}

		} while (clienteCadastrado == false);

		do {

			quartoCadastrado = false;

			System.out.println("Quartos cadastrados");
			System.out.println("-------------------");
			for (Quarto q : quartosCadastrados) {
				System.out.println(q.toString());
			}
			System.out.println();
			System.out.print("Escolha o número do quarto a ser reservado:");

			numeroEditQuarto = input.nextInt();

			for (Quarto q : quartosCadastrados) {
				if (numeroEditQuarto == q.getNumero()) {
					quartoCadastrado = true;
					quartoReserva = q;
				}
			}

			if (quartoCadastrado == false) {
				System.out.println();
				System.out.println("Quarto não cadastrado, verifique por favor!");
				System.out.println();
			}
		} while (quartoCadastrado == false);

		System.out.println();
		System.out.print("Digite a data de inicio da reserva (dd/mm/aaaa): ");
		input.nextLine();
		String dataInicial = input.nextLine();
		System.out.println();
		System.out.print("Digite a data final da reserva (dd/mm/aaaa): ");
		String dataFinal = input.nextLine();

		novaReserva = new Reserva(numReserva, dataInicial, dataFinal, quartoReserva, clienteReserva);
		gerenciadorReservas.addReserva(novaReserva);

		numReserva++;

		System.out.println();
		System.out.println(novaReserva.toString() + ", Cadastrada com sucesso!");
		System.out.println();
	}

	public static void listarReserva(Reserva gerenciadorReservas) {

		System.out.println("Reservas cadastradas");
		System.out.println("--------------------");
		gerenciadorReservas.listReservas();
		System.out.println();
	}

	public static void cancelarReserva(Scanner input, Reserva gerenciadorReservas, List<Reserva> reservaEfetuada) {

		boolean reservaEncontrada;

		do {
			System.out.println("Reservas cadastradas");
			System.out.println("--------------------");
			gerenciadorReservas.listReservas();
			System.out.println();
			System.out.print("Digite o número da reserva a ser cancelada:");
			int delReserva = input.nextInt();
			reservaEncontrada = false;
			for (Reserva r : reservaEfetuada) {
				if (delReserva == r.getNumReserva()) {
					reservaEncontrada = true;
					reservaEfetuada.remove(delReserva);
				} else {
					System.out.println("Número de reserva não encontrado, verifique por favor!");
				}
			}
		} while (reservaEncontrada);
	}

	public static void finalizarPrograma(Scanner input) {

		char encerrar = 0;

		System.out.println();
		System.out.println("Deseja mesmo sair do programa (s/n)?");
		encerrar = input.next().charAt(0);
		if (encerrar == 's' || encerrar == 'S') {
			System.out.println("Programa finalizado com sucesso!");
			System.exit(0);
		}
	}

}
