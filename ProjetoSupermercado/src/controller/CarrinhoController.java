package controller;

import java.util.List;

import model.Carrinho;
import model.CarrinhoDAO;

public class CarrinhoController {

    private CarrinhoDAO carrinhoDAO = new CarrinhoDAO();

    public boolean adicionarProduto(int idUsuario, int idProduto, int qtde, double preco) {
        if (idUsuario <= 0 || idProduto <= 0 || qtde <= 0 || preco <= 0) {
            return false;
        }
        Carrinho carrinho = new Carrinho(0, idUsuario, idProduto, qtde, preco);
        return carrinhoDAO.adicionarProduto(carrinho);
    }

    public boolean atualizarQuantidade(int idCarrinho, int qtde) {
        if (idCarrinho <= 0 || qtde <= 0) {
            return false;
        }
        return carrinhoDAO.atualizarQuantidade(idCarrinho, qtde);
    }

    public boolean removerProduto(int idCarrinho) {
        if (idCarrinho <= 0) {
            return false;
        }
        return carrinhoDAO.removerProduto(idCarrinho);
    }

    public List<Carrinho> listarProdutos(int idUsuario) {
        if (idUsuario <= 0) {
            return null;
        }
        return carrinhoDAO.listarProdutos(idUsuario);
    }
}
