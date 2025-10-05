package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.Frame;
import controller.ProdutoController;
import model.Produto;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;

public class CadastroProdutos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldProduto;
	private JTextField textFieldPreco;
	private JTextField textFieldQtde;
	private JTable table;
	private ProdutoController controller;
	private DefaultTableModel tableModel;
	
	public CadastroProdutos(Frame frame) {
		
		controller = new ProdutoController();
		
		Color corFundo = new Color(0x25, 0x4D, 0x32);
		Color verdeClaro = new Color(208, 219, 151);
		Color verdeClaroTransparente = new Color(122, 148, 101);
		
		setBackground(corFundo);
		setPreferredSize(new Dimension(900, 600));
		setLayout(null);
		
		JLabel labelCadastro = new JLabel("CADASTRO");
		labelCadastro.setVerticalAlignment(SwingConstants.TOP);
		labelCadastro.setBounds(95, 85, 300, 29);
		labelCadastro.setForeground(verdeClaro);
		labelCadastro.setFont(new Font("Arial", Font.BOLD, 30));
		labelCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		add(labelCadastro);
		
		JLabel labelDeProdutos = new JLabel("DE PRODUTOS");
		labelDeProdutos.setVerticalAlignment(SwingConstants.TOP);
		labelDeProdutos.setHorizontalAlignment(SwingConstants.CENTER);
		labelDeProdutos.setForeground(new Color(208, 219, 151));
		labelDeProdutos.setFont(new Font("Arial", Font.BOLD, 30));
		labelDeProdutos.setBounds(95, 113, 300, 29);
		add(labelDeProdutos);
		
		JLabel lblProduto = new JLabel("PRODUTO");
		lblProduto.setVerticalAlignment(SwingConstants.BOTTOM);
		lblProduto.setHorizontalAlignment(SwingConstants.LEFT);
		lblProduto.setForeground(new Color(208, 219, 151));
		lblProduto.setFont(new Font("Arial", Font.BOLD, 16));
		lblProduto.setBounds(95, 170, 220, 29);
		add(lblProduto);
		
		textFieldProduto = new JTextField();
		textFieldProduto.setBorder(new EmptyBorder(10, 10, 10, 10));
		textFieldProduto.setToolTipText("PRODUTO");
		textFieldProduto.setForeground(corFundo);
		textFieldProduto.setFont(new Font("Arial", Font.BOLD, 16));
		textFieldProduto.setBackground(verdeClaroTransparente);
		textFieldProduto.setBounds(95, 200, 300, 50);
		add(textFieldProduto);
		textFieldProduto.setColumns(10);
		
		JLabel lblPreco = new JLabel("PREÇO (R$)");
		lblPreco.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPreco.setHorizontalAlignment(SwingConstants.LEFT);
		lblPreco.setForeground(new Color(208, 219, 151));
		lblPreco.setFont(new Font("Arial", Font.BOLD, 16));
		lblPreco.setBounds(95, 255, 220, 29);
		add(lblPreco);
		
		textFieldPreco = new JTextField();
		textFieldPreco.setToolTipText("PREÇO");
		textFieldPreco.setForeground(corFundo);
		textFieldPreco.setFont(new Font("Arial", Font.BOLD, 16));
		textFieldPreco.setColumns(10);
		textFieldPreco.setBorder(new EmptyBorder(10, 10, 10, 10));
		textFieldPreco.setBackground(new Color(122, 148, 101));
		textFieldPreco.setBounds(95, 285, 300, 50);
		add(textFieldPreco);
		
		JLabel lblQtde = new JLabel("QUANTIDADE");
		lblQtde.setVerticalAlignment(SwingConstants.BOTTOM);
		lblQtde.setHorizontalAlignment(SwingConstants.LEFT);
		lblQtde.setForeground(new Color(208, 219, 151));
		lblQtde.setFont(new Font("Arial", Font.BOLD, 16));
		lblQtde.setBounds(95, 345, 220, 29);
		add(lblQtde);
		
		textFieldQtde = new JTextField();
		textFieldQtde.setToolTipText("QUANTIDADE");
		textFieldQtde.setForeground(corFundo);
		textFieldQtde.setFont(new Font("Arial", Font.BOLD, 16));
		textFieldQtde.setColumns(10);
		textFieldQtde.setBorder(new EmptyBorder(10, 10, 10, 10));
		textFieldQtde.setBackground(new Color(122, 148, 101));
		textFieldQtde.setBounds(95, 375, 300, 50);
		add(textFieldQtde);
		
		JButton buttonCadastrar = new JButton("CADASTRAR");
		buttonCadastrar.setFont(new Font("Arial", Font.BOLD, 20));
		buttonCadastrar.setBounds(145, 465, 200, 50);
		buttonCadastrar.setBackground(verdeClaro);
		buttonCadastrar.setForeground(corFundo); 
		buttonCadastrar.setOpaque(true);
		buttonCadastrar.setBorderPainted(false);
		
		buttonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = textFieldProduto.getText().trim();
			    double preco = Double.parseDouble(textFieldPreco.getText().trim());
			    int qtde = Integer.parseInt(textFieldQtde.getText().trim());

			    if(controller.cadastrarProduto(nome, preco, qtde)) {
			        JOptionPane.showMessageDialog(CadastroProdutos.this, "Produto cadastrado.");
			        atualizarTabela();
			        textFieldProduto.setText("");
			        textFieldPreco.setText("");
			        textFieldQtde.setText("");
			    } else {
			        JOptionPane.showMessageDialog(CadastroProdutos.this, "Erro ao cadastrar produto.");
			    }
			}
		});
		add(buttonCadastrar);
		
		JLabel lblProdutoTabela = new JLabel("PRODUTO");
		lblProdutoTabela.setForeground(corFundo);
		lblProdutoTabela.setBackground(verdeClaro);
		lblProdutoTabela.setOpaque(true);
		lblProdutoTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblProdutoTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblProdutoTabela.setBounds(500, 55, 120, 30);
		add(lblProdutoTabela);
		
		JLabel lblPrecoTabela = new JLabel("PREÇO");
		lblPrecoTabela.setOpaque(true);
		lblPrecoTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecoTabela.setForeground(new Color(37, 77, 50));
		lblPrecoTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblPrecoTabela.setBackground(new Color(208, 219, 151));
		lblPrecoTabela.setBounds(614, 55, 120, 30);
		add(lblPrecoTabela);
		
		JLabel lblQtdeTabela = new JLabel("QTDE");
		lblQtdeTabela.setOpaque(true);
		lblQtdeTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblQtdeTabela.setForeground(new Color(37, 77, 50));
		lblQtdeTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblQtdeTabela.setBackground(new Color(208, 219, 151));
		lblQtdeTabela.setBounds(730, 55, 120, 30);
		add(lblQtdeTabela);
		
		table = new JTable();
		table.setFont(new Font("Arial", Font.BOLD, 18));
		table.setForeground(corFundo);
		table.setBackground(verdeClaroTransparente);
		table.setRowHeight(30);
		table.setBounds(500, 85, 350, 430);
		
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, centralizado);
		
		String[] colunas = {"ID", "PRODUTO", "PREÇO", "QTDE"};
		tableModel = new DefaultTableModel(colunas, 0) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		table.setModel(tableModel);
		table.removeColumn(table.getColumnModel().getColumn(0));
		add(table);
		
		JButton buttonEditar = new JButton("EDITAR");
		buttonEditar.setOpaque(true);
		buttonEditar.setForeground(new Color(37, 77, 50));
		buttonEditar.setFont(new Font("Arial", Font.BOLD, 18));
		buttonEditar.setBorderPainted(false);
		buttonEditar.setBackground(new Color(208, 219, 151));
		buttonEditar.setBounds(500, 530, 115, 40);
		
		buttonEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();
			    if(linha == -1) {
			        JOptionPane.showMessageDialog(CadastroProdutos.this, "Selecione um produto.");
			        return;
			    }

			    int id = (int) tableModel.getValueAt(linha, 0);
			    String nome = (String) tableModel.getValueAt(linha, 1);
			    double preco = (double) tableModel.getValueAt(linha, 2);
			    int qtde = (int) tableModel.getValueAt(linha, 3);

			    Produto produto = new Produto(id, nome, preco, qtde);
			    EditarProduto modal = new EditarProduto((JFrame) SwingUtilities.getWindowAncestor(CadastroProdutos.this), produto, controller);
			    modal.setVisible(true);

			    atualizarTabela();
			}
		});
		add(buttonEditar);
		
		JButton buttonExcluir = new JButton("EXCLUIR");
		buttonExcluir.setOpaque(true);
		buttonExcluir.setForeground(new Color(37, 77, 50));
		buttonExcluir.setFont(new Font("Arial", Font.BOLD, 18));
		buttonExcluir.setBorderPainted(false);
		buttonExcluir.setBackground(verdeClaroTransparente);
		buttonExcluir.setBounds(630, 530, 115, 40);
		
		buttonExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();
			    if(linha == -1) {
			        JOptionPane.showMessageDialog(CadastroProdutos.this, "Selecione um produto.");
			        return;
			    }
			    int id = (int) tableModel.getValueAt(linha, 0);
			    int opcao = JOptionPane.showConfirmDialog(CadastroProdutos.this, "Deseja excluir o produto?", "Confirmação", JOptionPane.YES_NO_OPTION);
			    if(opcao == JOptionPane.YES_OPTION) {
			        if(controller.excluirProduto(id)) {
			            JOptionPane.showMessageDialog(CadastroProdutos.this, "Produto excluído.");
			            atualizarTabela();
			        } else {
			            JOptionPane.showMessageDialog(CadastroProdutos.this, "Erro ao excluir produto.");
			        }
			    }
			}
		});
		add(buttonExcluir);
		
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
		
		atualizarTabela();
	}
	
	private void atualizarTabela() {
	    tableModel.setRowCount(0);
	    for (Produto p : controller.listarProdutos()) {
	        tableModel.addRow(new Object[]{p.getId(), p.getProduto(), p.getPreco(), p.getQtde()});
	    }
	}
}
