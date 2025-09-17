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
import model.Usuario;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import javax.swing.JTable;

public class CadastroProdutos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldProduto;
	private CadastroController controller;
	private JTextField textFieldPreco;
	private JTextField textFieldQtde;
	private JTable table;

	public CadastroProdutos(Frame frame) {
		
		controller = new CadastroController();
		
		Color corFundo = new Color(0x25, 0x4D, 0x32);
		Color verdeClaro = new Color(208, 219, 151);
		Color verdeClaroTransparente = new Color(122, 148, 101);
		
		setBackground(corFundo);
		setPreferredSize(new Dimension(900, 600));
		setLayout(null);
		
		JLabel labelCadastro = new JLabel("CADASTRO");
		labelCadastro.setVerticalAlignment(SwingConstants.TOP);
		labelCadastro.setBounds(140, 85, 220, 29);
		labelCadastro.setForeground(verdeClaro);
		labelCadastro.setFont(new Font("Arial", Font.BOLD, 30));
		labelCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		add(labelCadastro);
		
		JButton buttonCadastrar = new JButton("CADASTRAR");
		buttonCadastrar.setFont(new Font("Arial", Font.BOLD, 20));
		buttonCadastrar.setBounds(145, 465, 200, 50);
		buttonCadastrar.setBackground(verdeClaro);
		buttonCadastrar.setForeground(corFundo); 
		buttonCadastrar.setOpaque(true);
		buttonCadastrar.setBorderPainted(false);
		add(buttonCadastrar);
		
		textFieldProduto = new JTextField();
		textFieldProduto.setText("PRODUTO");
		textFieldProduto.setBorder(new EmptyBorder(10, 10, 10, 10));
		textFieldProduto.setToolTipText("NOME");
		textFieldProduto.setForeground(verdeClaro);
		textFieldProduto.setFont(new Font("Arial", Font.BOLD, 16));
		textFieldProduto.setBackground(verdeClaroTransparente);
		textFieldProduto.setBounds(95, 200, 300, 50);
		add(textFieldProduto);
		textFieldProduto.setColumns(10);
		
		try {
			MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
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
		    frame.mostrarLogin();
		});
		
		buttonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		add(buttonVoltar);
		
		textFieldPreco = new JTextField();
		textFieldPreco.setText("PREÇO (R$)");
		textFieldPreco.setToolTipText("NOME");
		textFieldPreco.setForeground(new Color(208, 219, 151));
		textFieldPreco.setFont(new Font("Arial", Font.BOLD, 16));
		textFieldPreco.setColumns(10);
		textFieldPreco.setBorder(new EmptyBorder(10, 10, 10, 10));
		textFieldPreco.setBackground(new Color(122, 148, 101));
		textFieldPreco.setBounds(95, 285, 300, 50);
		add(textFieldPreco);
		
		JLabel labelDeProdutos = new JLabel("DE PRODUTOS");
		labelDeProdutos.setVerticalAlignment(SwingConstants.TOP);
		labelDeProdutos.setHorizontalAlignment(SwingConstants.CENTER);
		labelDeProdutos.setForeground(new Color(208, 219, 151));
		labelDeProdutos.setFont(new Font("Arial", Font.BOLD, 30));
		labelDeProdutos.setBounds(140, 119, 220, 29);
		add(labelDeProdutos);
		
		textFieldQtde = new JTextField();
		textFieldQtde.setToolTipText("NOME");
		textFieldQtde.setText("QUANTIDADE");
		textFieldQtde.setForeground(new Color(208, 219, 151));
		textFieldQtde.setFont(new Font("Arial", Font.BOLD, 16));
		textFieldQtde.setColumns(10);
		textFieldQtde.setBorder(new EmptyBorder(10, 10, 10, 10));
		textFieldQtde.setBackground(new Color(122, 148, 101));
		textFieldQtde.setBounds(95, 375, 300, 50);
		add(textFieldQtde);
		
		table = new JTable();
		table.setBackground(verdeClaroTransparente);
		table.setBounds(500, 85, 350, 430);
		add(table);
	}
}
