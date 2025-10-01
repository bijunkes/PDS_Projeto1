package controller;

import model.Carrinho;
import model.CarrinhoDAO;

import java.util.List;

public class CarrinhoController {

    private CarrinhoDAO carrinhoDAO;

    public CarrinhoController() {
        this.carrinhoDAO = new CarrinhoDAO();
    }

    public boolean adicionarAoCarrinho(Carrinho carrinho) {
        if (carrinho == null || carrinho.getQtde() <= 0) {
            return false;
        }
        return carrinhoDAO.adicionarAoCarrinho(carrinho);
    }

    public List<Carrinho> listarCarrinhoPorUsuario(int idUsuario) {
        if (idUsuario <= 0) {
            throw new IllegalArgumentException("ID de usuário inválido");
        }
        return carrinhoDAO.listarCarrinhoPorUsuario(idUsuario);
    }

    public boolean excluirItem(int idCarrinho) {
        if (idCarrinho <= 0) {
            return false;
        }
        return carrinhoDAO.excluirItem(idCarrinho);
    }

    public boolean atualizarQuantidade(int idUsuario, int idProduto, int novaQtde) {
        if (idUsuario <= 0 || idProduto <= 0 || novaQtde < 0) {
            return false;
        }
        return carrinhoDAO.atualizarQuantidade(idUsuario, idProduto, novaQtde);
    }

    public Carrinho buscarProdutoNoCarrinho(int idUsuario, int idProduto) {
        if (idUsuario <= 0 || idProduto <= 0) {
            return null;
        }
        return carrinhoDAO.buscarProdutoNoCarrinho(idUsuario, idProduto);
    }

    public boolean limparCarrinho(int idUsuario) {
        if (idUsuario <= 0) {
            return false;
        }
        return carrinhoDAO.limparCarrinho(idUsuario);
    }

    public int buscarProdutoIdPorCarrinhoId(int idCarrinho) {
        if (idCarrinho <= 0) {
            return -1;
        }
        return carrinhoDAO.buscarProdutoIdPorCarrinhoId(idCarrinho);
    }
    
    public List<Object[]> listarItensDoCarrinhoComProduto(int idUsuario) {
        return carrinhoDAO.listarItensComProduto(idUsuario);
    }
    
    public List<String> finalizarCompra(int idUsuario, List<Carrinho> itensCarrinho) {
        return carrinhoDAO.finalizarCompra(idUsuario, itensCarrinho);
    }
}
