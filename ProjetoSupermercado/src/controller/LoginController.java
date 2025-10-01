package controller;

import model.Usuario;
import model.UsuarioDAO;

public class LoginController {
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario autenticar(String nome, String cpf) {
        return usuarioDAO.logarUsuario(nome, cpf);
    }
}
