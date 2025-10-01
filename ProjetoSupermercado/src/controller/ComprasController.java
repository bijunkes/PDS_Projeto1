package controller;

import model.Carrinho;
import model.CarrinhoDAO;
import model.Produto;
import model.ProdutoDAO;

import java.util.List;

public class ComprasController {

    private ProdutoDAO produtoDao;
    private CarrinhoDAO carrinhoDao;

    public ComprasController() {
        produtoDao = new ProdutoDAO();
        carrinhoDao = new CarrinhoDAO();
    }

    public List<Produto> listarProdutos() {
        return produtoDao.listarProdutos();
    }

    public Carrinho buscarProdutoNoCarrinho(int idUsuario, int idProduto) {
        return carrinhoDao.buscarProdutoNoCarrinho(idUsuario, idProduto);
    }

    public boolean adicionarAoCarrinho(Carrinho c) {
        return carrinhoDao.adicionarAoCarrinho(c);
    }

    public boolean atualizarQuantidadeNoCarrinho(int idUsuario, int idProduto, int novaQtde) {
        return carrinhoDao.atualizarQuantidade(idUsuario, idProduto, novaQtde);
    }

    public void retornarEstoqueAoRemover(Produto produto, int qtdeRemovida) {
        produto.setQtde(produto.getQtde() + qtdeRemovida);
    }
}
