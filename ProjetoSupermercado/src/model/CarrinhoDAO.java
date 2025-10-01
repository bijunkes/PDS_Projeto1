package model;

import db.BancoDeDados;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoDAO {

	public boolean adicionarAoCarrinho(Carrinho carrinho) {
	    String sql = "INSERT INTO carrinho (id_usuario, id_produto, qtde, preco) VALUES (?, ?, ?, ?)";
	    try (Connection conexao = BancoDeDados.conectar();
	         PreparedStatement pstm = conexao.prepareStatement(sql)) {

	        pstm.setInt(1, carrinho.getIdUsuario());
	        pstm.setInt(2, carrinho.getIdProduto());
	        pstm.setInt(3, carrinho.getQtde());
	        pstm.setDouble(4, carrinho.getPreco());

	        int linhasAfetadas = pstm.executeUpdate();

	        return linhasAfetadas > 0;
	    } catch (SQLException e) {
	        System.out.println("Erro SQL: " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
	}


	public List<Carrinho> listarCarrinhoPorUsuario(int idUsuario) {
	    List<Carrinho> itens = new ArrayList<>();
	    String sql = "SELECT * FROM carrinho WHERE id_usuario = ?";
	    try (Connection conexao = BancoDeDados.conectar();
	         PreparedStatement pstm = conexao.prepareStatement(sql)) {

	        pstm.setInt(1, idUsuario);
	        ResultSet rs = pstm.executeQuery();

	        while (rs.next()) {
	            Carrinho c = new Carrinho(
	                rs.getInt("id"),
	                rs.getInt("id_usuario"),
	                rs.getInt("id_produto"),
	                rs.getInt("qtde"),
	                rs.getDouble("preco")
	            );
	            itens.add(c);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return itens;
	}

    public boolean excluirItem(int id) {
        String sql = "DELETE FROM carrinho WHERE id = ?";
        try (Connection conexao = BancoDeDados.conectar();
             PreparedStatement pstm = conexao.prepareStatement(sql)) {
            
            pstm.setInt(1, id);
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean atualizarQuantidade(int idUsuario, int idProduto, int novaQtde) {
        String sql = "UPDATE carrinho SET qtde = ? WHERE id_usuario = ? AND id_produto = ?";
        try (Connection conexao = BancoDeDados.conectar();
             PreparedStatement pstm = conexao.prepareStatement(sql)) {

            pstm.setInt(1, novaQtde);
            pstm.setInt(2, idUsuario);
            pstm.setInt(3, idProduto);

            return pstm.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Carrinho buscarProdutoNoCarrinho(int idUsuario, int idProduto) {
        String sql = "SELECT * FROM carrinho WHERE id_usuario = ? AND id_produto = ?";
        try (Connection conexao = BancoDeDados.conectar();
             PreparedStatement pstm = conexao.prepareStatement(sql)) {

            pstm.setInt(1, idUsuario);
            pstm.setInt(2, idProduto);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                return new Carrinho(
                    rs.getInt("id"),
                    rs.getInt("id_usuario"),
                    rs.getInt("id_produto"),
                    rs.getInt("qtde"),
                    rs.getDouble("preco")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean limparCarrinho(int idUsuario) {
        String sql = "DELETE FROM carrinho WHERE id_usuario = ?";
        try (Connection conn = BancoDeDados.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, idUsuario);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int buscarProdutoIdPorCarrinhoId(int idCarrinho) {
        String sql = "SELECT id_produto FROM carrinho WHERE id = ?";
        try (Connection conexao = BancoDeDados.conectar();
             PreparedStatement pstm = conexao.prepareStatement(sql)) {

            pstm.setInt(1, idCarrinho);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_produto");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public List<Object[]> listarItensComProduto(int idUsuario) {
        List<Object[]> lista = new ArrayList<>();
        List<Carrinho> carrinhoItens = listarCarrinhoPorUsuario(idUsuario);
        ProdutoDAO produtoDAO = new ProdutoDAO();

        for (Carrinho c : carrinhoItens) {
            Produto p = produtoDAO.buscarPorId(c.getIdProduto());
            if (p != null) {
                lista.add(new Object[]{c, p});
            }
        }
        return lista;
    }
    
    public List<String> finalizarCompra(int idUsuario, List<Carrinho> itensCarrinho) {
        ProdutoDAO produtoDao = new ProdutoDAO();
        List<String> notaFiscal = new ArrayList<>();
        double totalPago = 0.0;

        for (Carrinho c : itensCarrinho) {
            Produto p = produtoDao.buscarPorId(c.getIdProduto());
            if (p == null) continue;

            int novoEstoque = p.getQtde() - c.getQtde();
            produtoDao.atualizarEstoque(p.getId(), novoEstoque);

            double subtotal = c.getPreco() * c.getQtde();
            totalPago += subtotal;

            notaFiscal.add(p.getProduto() + " - " + c.getQtde() + " x R$" + c.getPreco() + " = R$" + subtotal);
        }

        for (Carrinho c : itensCarrinho) {
            excluirItem(c.getId());
        }

        notaFiscal.add("TOTAL PAGO: R$" + totalPago);

        return notaFiscal;
    }
    
}
