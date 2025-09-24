package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.CadastroController;
import controller.Frame;
import model.Produto;
import model.ProdutoDAO;
import model.Usuario;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
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
	private java.util.List<Produto> listaProdutos = new java.util.ArrayList<>();
	
	private void atualizarTabela() {
	    ProdutoDAO dao = new ProdutoDAO();
	    java.util.List<Produto> produtos = dao.listarProdutos();

	    listaProdutos.clear();
	    listaProdutos.addAll(produtos);

	    DefaultTableModel model = new DefaultTableModel();
	    model.addColumn("Produto");
	    model.addColumn("Preço");
	    model.addColumn("Qtde");

	    for (Produto p : produtos) {
	        model.addRow(new Object[] {
	            p.getProduto(),
	            String.format("R$ %.2f", p.getPreco()),
	            p.getQtde()
	        });
	    }

	    table.setModel(model);
	}

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
		textFieldProduto.setBorder(new EmptyBorder(10, 10, 10, 10));
		textFieldProduto.setToolTipText("PRODUTO");
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
		    frame.mostrarInicio();
		});

		add(buttonVoltar);
		
		textFieldPreco = new JTextField();
		textFieldPreco.setToolTipText("PREÇO");
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
		textFieldQtde.setToolTipText("QUANTIDADE");
		textFieldQtde.setForeground(new Color(208, 219, 151));
		textFieldQtde.setFont(new Font("Arial", Font.BOLD, 16));
		textFieldQtde.setColumns(10);
		textFieldQtde.setBorder(new EmptyBorder(10, 10, 10, 10));
		textFieldQtde.setBackground(new Color(122, 148, 101));
		textFieldQtde.setBounds(95, 375, 300, 50);
		add(textFieldQtde);
		
		table = new JTable();
		table.setFont(new Font("Arial", Font.BOLD, 18));
		table.setForeground(verdeClaro);
		table.setBackground(verdeClaroTransparente);
		table.setBounds(500, 85, 350, 430);
		add(table);
		
		JButton buttonEditar = new JButton("EDITAR");
		buttonEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonEditar.setOpaque(true);
		buttonEditar.setForeground(new Color(37, 77, 50));
		buttonEditar.setFont(new Font("Arial", Font.BOLD, 18));
		buttonEditar.setBorderPainted(false);
		buttonEditar.setBackground(new Color(208, 219, 151));
		buttonEditar.setBounds(500, 530, 115, 40);
		add(buttonEditar);
		
		JButton buttonExcluir = new JButton("EXCLUIR");
		buttonExcluir.setOpaque(true);
		buttonExcluir.setForeground(new Color(37, 77, 50));
		buttonExcluir.setFont(new Font("Arial", Font.BOLD, 18));
		buttonExcluir.setBorderPainted(false);
		buttonExcluir.setBackground(verdeClaroTransparente);
		buttonExcluir.setBounds(630, 530, 115, 40);
		add(buttonExcluir);
		
		JLabel lblProduto = new JLabel("PRODUTO");
		lblProduto.setVerticalAlignment(SwingConstants.BOTTOM);
		lblProduto.setHorizontalAlignment(SwingConstants.LEFT);
		lblProduto.setForeground(new Color(208, 219, 151));
		lblProduto.setFont(new Font("Arial", Font.BOLD, 16));
		lblProduto.setBounds(95, 165, 220, 29);
		add(lblProduto);
		
		JLabel labelCadastro_1_1 = new JLabel("PREÇO (R$)");
		labelCadastro_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
		labelCadastro_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		labelCadastro_1_1.setForeground(new Color(208, 219, 151));
		labelCadastro_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		labelCadastro_1_1.setBounds(95, 255, 220, 29);
		add(labelCadastro_1_1);
		
		JLabel labelCadastro_1_1_1 = new JLabel("QUANTIDADE");
		labelCadastro_1_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
		labelCadastro_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		labelCadastro_1_1_1.setForeground(new Color(208, 219, 151));
		labelCadastro_1_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		labelCadastro_1_1_1.setBounds(95, 345, 220, 29);
		add(labelCadastro_1_1_1);
		
		buttonCadastrar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String nome = textFieldProduto.getText().trim();
		        String precoStr = textFieldPreco.getText().trim().replace(",", ".");
		        String qtdeStr = textFieldQtde.getText().trim();

		        if (nome.isEmpty() || precoStr.isEmpty() || qtdeStr.isEmpty()) {
		            javax.swing.JOptionPane.showMessageDialog(null, "Preencha todos os campos");
		            return;
		        }

		        try {
		            double preco = Double.parseDouble(precoStr);
		            int qtde = Integer.parseInt(qtdeStr);

		            Produto produto = new Produto(0, nome, preco, qtde);  // id = 0 pois será gerado no banco
		            ProdutoDAO dao = new ProdutoDAO();

		            if (dao.cadastrarProduto(produto)) {
		                javax.swing.JOptionPane.showMessageDialog(null, "Produto cadastrado!");
		                atualizarTabela();
		                textFieldProduto.setText("");
		                textFieldPreco.setText("");
		                textFieldQtde.setText("");
		            } else {
		                javax.swing.JOptionPane.showMessageDialog(null, "Erro ao cadastrar.");
		            }


		        } catch (NumberFormatException ex) {
		            javax.swing.JOptionPane.showMessageDialog(null, "Preço ou quantidade inválidos.");
		        }
		    }
		});
		
		buttonExcluir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();

		        if (selectedRow == -1) {
		            javax.swing.JOptionPane.showMessageDialog(null, "Selecione um produto para excluir.");
		            return;
		        }

		        Produto produtoSelecionado = listaProdutos.get(selectedRow);

		        int confirm = javax.swing.JOptionPane.showConfirmDialog(null, 
		            "Deseja realmente excluir o produto \"" + produtoSelecionado.getProduto() + "\"?",
		            "Confirmação", javax.swing.JOptionPane.YES_NO_OPTION);

		        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
		            ProdutoDAO dao = new ProdutoDAO();
		            if (dao.excluirProduto(produtoSelecionado.getId())) {
		                javax.swing.JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
		                atualizarTabela();
		            } else {
		                javax.swing.JOptionPane.showMessageDialog(null, "Erro ao excluir produto.");
		            }
		        }
		    }
		});
		
		buttonEditar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int linhaSelecionada = table.getSelectedRow();

		        if (linhaSelecionada == -1) {
		            JOptionPane.showMessageDialog(null, "Selecione um produto para editar.");
		            return;
		        }

		        Produto produtoSelecionado = listaProdutos.get(linhaSelecionada);

		        EditarProduto dialog = new EditarProduto(
		            SwingUtilities.getWindowAncestor(CadastroProdutos.this) instanceof JFrame 
		                ? (JFrame) SwingUtilities.getWindowAncestor(CadastroProdutos.this)
		                : null,
		            produtoSelecionado,
		            () -> atualizarTabela()
		        );
		        dialog.setVisible(true);
		    }
		});

		
		atualizarTabela();

	}
}
