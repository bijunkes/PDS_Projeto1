package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.CadastroController;
import controller.Frame;
import controller.LoginController;
import model.Usuario;
import model.UsuarioDAO;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class Login extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldNome;
	private JTextField textFieldCpf;
	private LoginController controller;

	
	public Login(Frame frame) {
		
		controller = new LoginController();
		
		Color corFundo = new Color(0x25, 0x4D, 0x32);
		Color verdeClaro = new Color(208, 219, 151);
		Color verdeClaroTransparente = new Color(122, 148, 101);
		
		setBackground(corFundo);
		setPreferredSize(new Dimension(900, 600));
		setLayout(null);
		
		JLabel labelLogin = new JLabel("LOGIN");
		labelLogin.setBounds(276, 130, 344, 47);
		labelLogin.setForeground(verdeClaro);
		labelLogin.setFont(new Font("Arial", Font.BOLD, 40));
		labelLogin.setHorizontalAlignment(SwingConstants.CENTER);
		add(labelLogin);
		
		JButton buttonEntrar = new JButton("ENTRAR");
		

		buttonEntrar.setFont(new Font("Arial", Font.BOLD, 20));
		buttonEntrar.setBounds(350, 430, 200, 50);
		buttonEntrar.setBackground(verdeClaro);
		buttonEntrar.setForeground(corFundo); 
		buttonEntrar.setOpaque(true);
		buttonEntrar.setBorderPainted(false);
		add(buttonEntrar);
		
		textFieldNome = new JTextField();
		textFieldNome.setBorder(new EmptyBorder(10, 10, 10, 10));
		textFieldNome.setToolTipText("NOME");
		textFieldNome.setForeground(verdeClaro);
		textFieldNome.setFont(new Font("Arial", Font.BOLD, 16));
		textFieldNome.setBackground(verdeClaroTransparente);
		textFieldNome.setBounds(200, 230, 500, 50);
		add(textFieldNome);
		textFieldNome.setColumns(10);
		
		try {
			MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
			textFieldCpf = new JFormattedTextField(cpfMask);
			textFieldCpf.setText("");
			textFieldCpf.setBorder(new EmptyBorder(10, 10, 10, 10));
			textFieldCpf.setToolTipText("CPF");
			textFieldCpf.setForeground(verdeClaro);
			textFieldCpf.setFont(new Font("Arial", Font.BOLD, 16));
			textFieldCpf.setBackground(verdeClaroTransparente);
			textFieldCpf.setColumns(10);
			textFieldCpf.setBounds(200, 310, 500, 50);
			add(textFieldCpf);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/voltar.png"));
		JButton buttonVoltar = new JButton(icon);
		buttonVoltar.setBounds(820, 530, 38, 40);
		buttonVoltar.setBackground(corFundo);
		buttonVoltar.setForeground(verdeClaro);
		buttonVoltar.setOpaque(true);
		buttonVoltar.setBorderPainted(false);
		
		buttonVoltar.addActionListener(e -> {
		    frame.mostrarInicio();
		});
		
		buttonEntrar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String nome = textFieldNome.getText().trim();
		        String cpf = textFieldCpf.getText().trim();

		        UsuarioDAO dao = new UsuarioDAO();
		        Usuario usuario = dao.logarUsuario(nome, cpf);

		        if (usuario != null) {
		            if (usuario.isAdmin()) {
		                frame.mostrarCadastroProdutos();
		            } else {
		                frame.mostrarCompras();
		            }
		        } else {
		            javax.swing.JOptionPane.showMessageDialog(Login.this,
		                "Usuário ou CPF inválido.",
		                "Erro de Login",
		                javax.swing.JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});


		add(buttonVoltar);
		
		JLabel lblNome = new JLabel("NOME");
		lblNome.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setForeground(new Color(208, 219, 151));
		lblNome.setFont(new Font("Arial", Font.BOLD, 16));
		lblNome.setBounds(200, 208, 108, 19);
		add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCpf.setHorizontalAlignment(SwingConstants.LEFT);
		lblCpf.setForeground(new Color(208, 219, 151));
		lblCpf.setFont(new Font("Arial", Font.BOLD, 16));
		lblCpf.setBounds(200, 290, 108, 19);
		add(lblCpf);
	}
}
