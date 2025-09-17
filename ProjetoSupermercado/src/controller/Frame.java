package controller;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.Cadastro;
import view.CadastroProdutos;
import view.Inicio;
import view.Login;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(1, 1, 1, 1));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);

		Inicio inicio = new Inicio(this);
		contentPane.add(inicio, BorderLayout.CENTER);
		
		pack();
	}
	
	public void mostrarInicio() {
		contentPane.removeAll();
		contentPane.add(new Inicio(this), BorderLayout.CENTER);
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	public void mostrarCadastro() {
		contentPane.removeAll();
		contentPane.add(new Cadastro(this), BorderLayout.CENTER);
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	public void mostrarLogin() {
		contentPane.removeAll();
		contentPane.add(new Login(this), BorderLayout.CENTER);
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	public void mostrarCadastroProdutos() {
		contentPane.removeAll();
		contentPane.add(new CadastroProdutos(this), BorderLayout.CENTER);
		contentPane.revalidate();
		contentPane.repaint();
	}

}
