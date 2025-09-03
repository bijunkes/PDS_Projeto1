package supermercado;

public class Produto {
	private int id;
	private String produto;
	private int qtde;
	
	public Produto(int id, String produto, int qtde) {
		this.id = id;
		this.produto = produto;
		this.qtde = qtde;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public int getQtde() {
		return qtde;
	}

	public void setQtde(int qtde) {
		this.qtde = qtde;
	}

}
