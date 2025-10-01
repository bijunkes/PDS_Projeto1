package controller;

import model.Produto;
import model.ProdutoDAO;

import java.util.List;

public class ProdutoController {

    private ProdutoDAO produtoDAO;

    public ProdutoController() {
        produtoDAO = new ProdutoDAO();
    }

    public boolean cadastrarProduto(String nome, double preco, int qtde) {
        if (nome == null || nome.trim().isEmpty() || preco < 0 || qtde < 0) {
            return false;
        }
        Produto produto = new Produto(0, nome, preco, qtde);
        return produtoDAO.cadastrarProduto(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoDAO.listarProdutos();
    }

    public boolean excluirProduto(int id) {
        return produtoDAO.excluirProduto(id);
    }

    public boolean atualizarProduto(Produto produto) {
        if (produto == null || produto.getId() <= 0) return false;
        return produtoDAO.atualizarProduto(produto);
    }

    public Produto buscarPorId(int id) {
        return produtoDAO.buscarPorId(id);
    }
}
