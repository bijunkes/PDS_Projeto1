package controller;

import model.Usuario;
import model.UsuarioDAO;

public class CadastroController {
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	public boolean cadastrarUsuario(String nome, String cpf) {
		
		if (nome == null || nome.trim().isEmpty()) {
			return false;
		}
		if (cpf == null || !cpf.matches("\\d{11}")) {
			return false;
		}
		
		Usuario usuario = new Usuario(nome, cpf);
		return usuarioDAO.adicionarUsuario(usuario);
	}
}
