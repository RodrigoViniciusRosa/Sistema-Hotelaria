package entities;

public class Quarto {

	private int numero;
	private String tipo;
	private String descricao;
	private double preco;

	Cliente cliente;

	public Quarto() {

	}

	public Quarto(int numero, String tipo, String descricao, double preco) {
		this.numero = numero;
		this.tipo = tipo;
		this.descricao = descricao;
		this.preco = preco;
	}

	public Quarto(int numero, String tipo, String descricao, double preco, Cliente cliente) {
		super();
		this.numero = numero;
		this.tipo = tipo;
		this.descricao = descricao;
		this.preco = preco;
		this.cliente = cliente;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String toString() {
		return "Numero: " + numero + ", Tipo: " + tipo + ", Descrição: " + descricao + ", Preço: R$ " + preco;
	}
}