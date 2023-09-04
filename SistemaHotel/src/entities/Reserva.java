package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Reserva {

	String dataInicial;
	String dataFinal;

	Quarto quarto;
	Cliente cliente;

	int numReserva;
	LocalDate inicioReserva;
	LocalDate finalReserva;

	private List<Reserva> reservaEfetuada = new ArrayList<Reserva>();

	public Reserva() {

	}

	public Reserva(int numReserva, String dataInicial, String dataFinal, Quarto quarto, Cliente cliente) {
		super();
		this.numReserva = numReserva;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.quarto = quarto;
		this.cliente = cliente;
		this.inicioReserva = LocalDate.parse(dataInicial, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.finalReserva = LocalDate.parse(dataFinal, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getInicioReserva() {
		return inicioReserva;
	}

	public void setInicioReserva(LocalDate inicioReserva) {
		this.inicioReserva = inicioReserva;
	}

	public LocalDate getFinalReserva() {
		return finalReserva;
	}

	public void setFinalReserva(LocalDate finalReserva) {
		this.finalReserva = finalReserva;
	}

	public void addReserva(Reserva add) {
		reservaEfetuada.add(add);
	}
	
	public void delReserva(Reserva del) {
		reservaEfetuada.remove(del);
	}

	public List<Reserva> getReservaEfetuada() {
		return reservaEfetuada;
	}

	public int getNumReserva() {
		return numReserva;
	}

	public void setNumReserva(int numReserva) {
		this.numReserva = numReserva;
	}

	public String toString() {
		return "Reserva número: " + numReserva + ", Quarto: " +  quarto.getNumero() + ", Cliente: " + cliente.getNomeCliente() + ", Check in: "
				+ dataInicial + ", Check out: " + dataFinal;

	}

	public void listReservas() {
		for (Reserva r : reservaEfetuada) {
			System.out.println(r.toString());
		}
	}
	
	public long diasReserva() {
		return ChronoUnit.DAYS.between(inicioReserva, finalReserva);
	}
	
	public double calculoReserva() {
		return diasReserva() * quarto.getPreco();
	}

}