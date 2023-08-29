package entities;

public class Cliente {
	
	private int idCliente;
	private String nomeCliente;
	private String telCliente;
	
	public Cliente() {
		
	}

	public Cliente(int idCliente, String nomeCliente, String telCliente) {
		super();
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.telCliente = telCliente;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getTelCliente() {
		return telCliente;
	}

	public void setTelCliente(String telCliente) {
		this.telCliente = telCliente;
	}
	
	public String toString() {
		return "Cliente Id: "
				+ idCliente
				+ ", Nome: "
				+ nomeCliente
				+ ", Tel: "
				+ telCliente;
	}
	
	

}
