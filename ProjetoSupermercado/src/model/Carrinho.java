package model;

public class Carrinho {
	
	private int id;
	private int idUsuario;
	private int idProduto;
	private int qtde;
	private double preco;
	
	public Carrinho(int id, int idUsuario, int idProduto, int qtde, double preco) {
		this.id = id;
		this.idUsuario = idUsuario;
		this.idProduto = idProduto;
		this.qtde = qtde;
		this.preco = preco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public int getQtde() {
		return qtde;
	}

	public void setQtde(int qtde) {
		this.qtde = qtde;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
}
