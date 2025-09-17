package controller;

import model.Usuario;
import model.UsuarioDAO;


public class CadastroController {
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	public boolean cadastrarUsuario(String nome, String cpf, boolean admin) {
		
		if (nome == null || nome.trim().isEmpty()) {
			return false;
		}
		if (cpf == null) {
			return false;
		}
		
		if (cpf.length() != 14) {
	        return false;
	    }
		
		Usuario usuario = new Usuario(0, nome, cpf, admin);
		return usuarioDAO.cadastrarUsuario(usuario);
	}
}
