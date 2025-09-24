package model;

import db.BancoDeDados;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public boolean cadastrarProduto(Produto produto) {
        String sql = "INSERT INTO produtos (nome, preco, qtde) VALUES (?, ?, ?)";
        
        try (Connection conexao = BancoDeDados.conectar();
             PreparedStatement pstm = conexao.prepareStatement(sql)) {

            pstm.setString(1, produto.getProduto());
            pstm.setDouble(2, produto.getPreco());
            pstm.setInt(3, produto.getQtde());

            pstm.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection conexao = BancoDeDados.conectar();
             PreparedStatement pstm = conexao.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDouble("preco"),
                    rs.getInt("qtde")
                );
                produtos.add(produto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }
    
    public boolean excluirProduto(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        
        try (Connection conexao = BancoDeDados.conectar();
             PreparedStatement pstm = conexao.prepareStatement(sql)) {
            pstm.setInt(1, id);
            int linhasAfetadas = pstm.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean atualizarProduto(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, preco = ?, qtde = ? WHERE id = ?";

        try (Connection conexao = BancoDeDados.conectar();
             PreparedStatement pstm = conexao.prepareStatement(sql)) {

            pstm.setString(1, produto.getProduto());
            pstm.setDouble(2, produto.getPreco());
            pstm.setInt(3, produto.getQtde());
            pstm.setInt(4, produto.getId());

            return pstm.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
