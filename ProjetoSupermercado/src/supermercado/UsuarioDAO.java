package supermercado;

import java.sql.*;

public class UsuarioDAO {
	
	public boolean adicionarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, cpf) VALUES (?, ?)";
        Connection conexao = null;
        PreparedStatement pstm = null;

        try {
            conexao = BancoDeDados.conectar();
            pstm = conexao.prepareStatement(sql);
            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getCpf());
            
            int affectedRows = pstm.executeUpdate();

            if (affectedRows == 0) {
                return false;
            }
            
            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
            	if (generatedKeys.next()) {
            		usuario.setId(generatedKeys.getInt(1));
            	}
            }
            
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
        	BancoDeDados.desconectar(conexao);
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
