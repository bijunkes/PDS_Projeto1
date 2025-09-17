package controller;

import java.util.List;

import model.Usuario;
import model.UsuarioDAO;

public class LoginController {
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	public boolean autenticar(String nome, String cpf) {
		List<Usuario> usuarios = usuarioDAO.listarUsuarios();
		for(Usuario u : usuarios) {
			if (u.getNome().equalsIgnoreCase(nome) && u.getCpf().equals(cpf)) {
				return true;
			}
		}
		return false;
	}
}
