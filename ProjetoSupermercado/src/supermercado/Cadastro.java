package supermercado;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class Cadastro extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldNome;
	private JTextField textFieldCpf;

	public Cadastro() {
		Color corFundo = new Color(0x25, 0x4D, 0x32);
		Color verdeClaro = new Color(208, 219, 151);
		Color verdeClaroTransparente = new Color(208, 219, 151, 128);
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
		buttonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonCadastrar.setFont(new Font("Arial", Font.BOLD, 20));
		buttonCadastrar.setBounds(350, 454, 200, 50);
		buttonCadastrar.setBackground(verdeClaro);
		buttonCadastrar.setForeground(corFundo); 
		buttonCadastrar.setOpaque(true);
		buttonCadastrar.setBorderPainted(false);
		add(buttonCadastrar);
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Arial", Font.BOLD, 16));
		textFieldNome.setBackground(verdeClaroTransparente);
		textFieldNome.setBounds(200, 194, 500, 50);
		add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldCpf = new JTextField();
		textFieldCpf.setFont(new Font("Arial", Font.BOLD, 16));
		textFieldCpf.setBackground(verdeClaroTransparente);
		textFieldCpf.setColumns(10);
		textFieldCpf.setBounds(200, 274, 500, 50);
		add(textFieldCpf);
		
		JRadioButton radioButtonAdm = new JRadioButton("ADMINISTRADOR");
		radioButtonAdm.setBackground(verdeClaroTransparente);
		radioButtonAdm.setForeground(corFundo); 
		radioButtonAdm.setFont(new Font("Arial", Font.BOLD, 16));
		radioButtonAdm.setBounds(200, 356, 500, 50);
		add(radioButtonAdm);
	}
}
