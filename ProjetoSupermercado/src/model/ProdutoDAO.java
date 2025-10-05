package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.BancoDeDados;

public class ProdutoDAO {
	
	public boolean cadastrarProduto(Produto produto) {
		String sql = "INSERT INTO produtos (nome, preco, qtde) VALUES (?, ?, ?)";
			try (Connection conexao = BancoDeDados.conectar();
				PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	            
	            stmt.setString(1, produto.getProduto());
	            stmt.setDouble(2, produto.getPreco());
	            stmt.setInt(3, produto.getQtde());
	            
	            int linhas = stmt.executeUpdate();
	            if (linhas > 0) {
	                try (ResultSet rs = stmt.getGeneratedKeys()) {
	                    if (rs.next()) {
	                        produto.setId(rs.getInt(1));
	                    }
	                }
	                return true;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	     return false;
	   }
	
	public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        
        try (Connection conexao = BancoDeDados.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
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

	public boolean atualizarProduto(Produto produto) {
	    String sql = "UPDATE produtos SET nome = ?, preco = ?, qtde = ? WHERE id = ?";
	    try (Connection conexao = BancoDeDados.conectar();
	         PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        
	        stmt.setString(1, produto.getProduto());
	        stmt.setDouble(2, produto.getPreco());
	        stmt.setInt(3, produto.getQtde());
	        stmt.setInt(4, produto.getId());
	        
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	public boolean excluirProduto(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conexao = BancoDeDados.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
	public Produto buscarProdutoPorId(int id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        try (Connection conexao = BancoDeDados.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("qtde")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public boolean adicionarEstoque(int idProduto, int qtde) {
	    String sql = "UPDATE produtos SET qtde = qtde + ? WHERE id = ?";
	    try (Connection conexao = BancoDeDados.conectar();
	         PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setInt(1, qtde);
	        stmt.setInt(2, idProduto);
	        return stmt.executeUpdate() > 0;
	    } catch(SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean removerEstoque(int idProduto, int qtde) {
	    String sql = "UPDATE produtos SET qtde = qtde - ? WHERE id = ? AND qtde >= ?";
	    try (Connection conexao = BancoDeDados.conectar();
	         PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setInt(1, qtde);
	        stmt.setInt(2, idProduto);
	        stmt.setInt(3, qtde);
	        return stmt.executeUpdate() > 0;
	    } catch(SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}