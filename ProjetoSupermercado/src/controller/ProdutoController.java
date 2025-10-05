package controller;

import java.util.List;

import model.Produto;
import model.ProdutoDAO;

public class ProdutoController {
    
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    
    public boolean cadastrarProduto(String nome, double preco, int qtde) {
        if (nome == null || nome.trim().isEmpty() || preco <= 0 || qtde < 0) {
            return false;
        }
        Produto produto = new Produto(0, nome, preco, qtde);
        return produtoDAO.cadastrarProduto(produto);
    }
    
    public boolean atualizarProduto(int id, String nome, double preco, int qtde) {
        if (id <= 0 || nome == null || nome.trim().isEmpty() || preco <= 0 || qtde < 0) {
            return false;
        }
        Produto produto = new Produto(id, nome, preco, qtde);
        return produtoDAO.atualizarProduto(produto);
    }
    
    public Produto buscarProdutoPorId(int id) {
        if (id <= 0) return null;
        return produtoDAO.buscarProdutoPorId(id);
    }
    
    public boolean excluirProduto(int id) {
        if (id <= 0) return false;
        return produtoDAO.excluirProduto(id);
    }
    
    public List<Produto> listarProdutos() {
        return produtoDAO.listarProdutos();
    }
    
    public boolean adicionarEstoqueProduto(int idProduto, int qtde) {
        return produtoDAO.adicionarEstoque(idProduto, qtde);
    }
    
    public boolean removerEstoqueProduto(int idProduto, int qtde) {
        return produtoDAO.removerEstoque(idProduto, qtde);
    }

}