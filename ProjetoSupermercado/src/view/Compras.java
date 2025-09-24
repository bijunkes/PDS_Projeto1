package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.CadastroController;
import controller.Frame;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class Compras extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldNome;
	private JTextField textFieldCpf;
	private CadastroController controller;

	public Compras(Frame frame) {
		
		controller = new CadastroController();
		
		Color corFundo = new Color(0x25, 0x4D, 0x32);
		Color verdeClaro = new Color(208, 219, 151);
		Color verdeClaroTransparente = new Color(122, 148, 101);
		
		setBackground(corFundo);
		setPreferredSize(new Dimension(900, 600));
		setLayout(null);
		
		JLabel labelCadastro = new JLabel("CADASTRO");
		labelCadastro.setBounds(276, 94, 344, 47);
		labelCadastro.setForeground(verdeClaro);
		labelCadastro.setFont(new Font("Arial", Font.BOLD, 40));
		labelCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		add(labelCadastro);
		
		JButton buttonCadastrar = new JButton("CADASTRAR");
		buttonCadastrar.setFont(new Font("Arial", Font.BOLD, 20));
		buttonCadastrar.setBounds(350, 454, 200, 50);
		buttonCadastrar.setBackground(verdeClaro);
		buttonCadastrar.setForeground(corFundo); 
		buttonCadastrar.setOpaque(true);
		buttonCadastrar.setBorderPainted(false);
		add(buttonCadastrar);
		
		textFieldNome = new JTextField();
		textFieldNome.setBorder(new EmptyBorder(10, 10, 10, 10));
		textFieldNome.setToolTipText("NOME");
		textFieldNome.setForeground(verdeClaro);
		textFieldNome.setFont(new Font("Arial", Font.BOLD, 16));
		textFieldNome.setBackground(verdeClaroTransparente);
		textFieldNome.setBounds(200, 194, 500, 50);
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
			textFieldCpf.setBounds(200, 274, 500, 50);
			add(textFieldCpf);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		JRadioButton radioButtonAdm = new JRadioButton("ADMINISTRADOR");
		radioButtonAdm.setBorder(new EmptyBorder(10, 10, 10, 10));
		radioButtonAdm.setBackground(verdeClaroTransparente);
		radioButtonAdm.setForeground(verdeClaro); 
		radioButtonAdm.setFont(new Font("Arial", Font.BOLD, 16));
		radioButtonAdm.setBounds(200, 356, 500, 50);
		add(radioButtonAdm);
		
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
		
		buttonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = textFieldNome.getText();
				String cpf = textFieldCpf.getText();
				boolean admin = radioButtonAdm.isSelected();
				
				boolean ok = controller.cadastrarUsuario(nome, cpf, admin);
				if (ok) {
	                System.out.println("Usuário cadastrado com sucesso!");
	                frame.mostrarInicio();
	            } else {
	                System.out.println("Falha no cadastro. Verifique os dados.");
	            }
			}
		});
		
		add(buttonVoltar);
	}
}
