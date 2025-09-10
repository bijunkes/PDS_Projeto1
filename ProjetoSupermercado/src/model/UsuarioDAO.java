package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.BancoDeDados;

public class UsuarioDAO {
	
	public boolean adicionarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, cpf) VALUES (?, ?)";
        
        try (Connection conexao = BancoDeDados.conectar();
        	PreparedStatement pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        		
        	pstm.setString(1, usuario.getNome());
        	pstm.setString(2, usuario.getCpf());
        	int linhas = pstm.executeUpdate();
        		
        	if(linhas == 0) {
        		return false;
        	}
        		
        	try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
        		if(generatedKeys.next()) {
        			usuario.setId(generatedKeys.getInt(1));
        		}
        	}
        		
        	return true;
        		
        } catch (SQLException e) {
        	e.printStackTrace();
        	return false;
        }
    }
	
	public List<Usuario> listarUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		String sql = "SELECT id, nome, cpf FROM usuarios";
		
		try (Connection conexao = BancoDeDados.conectar();
				PreparedStatement pstm = conexao.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery()) {
				
				while (rs.next()) {
					Usuario u = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"));
					usuarios.add(u);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuarios;
	}

}
