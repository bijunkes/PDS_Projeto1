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
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.CarrinhoController;
import controller.Frame;
import controller.ProdutoController;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JTable;

public class Compras extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField tfProduto;
	private JTextField tfQtde;
	private JTextField tfTotalPagar;
	private JTable table;
	private CarrinhoController controller;
	
	DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Produto", "Preço", "Qtde"}, 0);

	public Compras(Frame frame) {
		
		controller = new CarrinhoController();
		
		Color corFundo = new Color(0x25, 0x4D, 0x32);
		Color verdeClaro = new Color(208, 219, 151);
		
		setBackground(corFundo);
		setPreferredSize(new Dimension(900, 600));
		setLayout(null);
		
		JLabel lblCatalogo = new JLabel("CATÁLOGO");
		lblCatalogo.setVerticalAlignment(SwingConstants.TOP);
		lblCatalogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCatalogo.setForeground(new Color(208, 219, 151));
		lblCatalogo.setFont(new Font("Arial", Font.BOLD, 30));
		lblCatalogo.setBounds(37, 50, 353, 29);
		add(lblCatalogo);
		
		JLabel labelDeProdutos = new JLabel("DE PRODUTOS");
		labelDeProdutos.setVerticalAlignment(SwingConstants.TOP);
		labelDeProdutos.setHorizontalAlignment(SwingConstants.CENTER);
		labelDeProdutos.setForeground(new Color(208, 219, 151));
		labelDeProdutos.setFont(new Font("Arial", Font.BOLD, 30));
		labelDeProdutos.setBounds(37, 80, 353, 29);
		add(labelDeProdutos);
		
		JLabel lblProdutoTabela = new JLabel("PRODUTO");
		lblProdutoTabela.setOpaque(true);
		lblProdutoTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblProdutoTabela.setForeground(new Color(37, 77, 50));
		lblProdutoTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblProdutoTabela.setBackground(new Color(208, 219, 151));
		lblProdutoTabela.setBounds(40, 130, 120, 30);
		add(lblProdutoTabela);
		
		JLabel lblPrecoTabela = new JLabel("PREÇO");
		lblPrecoTabela.setOpaque(true);
		lblPrecoTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecoTabela.setForeground(new Color(37, 77, 50));
		lblPrecoTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblPrecoTabela.setBackground(new Color(208, 219, 151));
		lblPrecoTabela.setBounds(154, 130, 120, 30);
		add(lblPrecoTabela);
		
		JLabel lblQtdeTabela = new JLabel("QTDE");
		lblQtdeTabela.setOpaque(true);
		lblQtdeTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblQtdeTabela.setForeground(new Color(37, 77, 50));
		lblQtdeTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblQtdeTabela.setBackground(new Color(208, 219, 151));
		lblQtdeTabela.setBounds(270, 130, 120, 30);
		add(lblQtdeTabela);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(30);
		table.setForeground(new Color(37, 77, 50));
		table.setFont(new Font("Arial", Font.BOLD, 18));
		table.setBackground(new Color(122, 148, 101));
		table.setBounds(40, 160, 350, 410);
		
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, centralizado);
		
		String[] colunas = {"ID", "Produto", "Preço", "Qtde"};
		modelo.setColumnIdentifiers(colunas);
		table.setModel(new DefaultTableModel(colunas, 0) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		});
		table.setModel(modelo);
		
		table.getSelectionModel().addListSelectionListener(e -> {
		    int linha = table.getSelectedRow();
		    if (linha != -1) {
		        tfProduto.setText((String) table.getModel().getValueAt(linha, 1));
		        tfQtde.setText("1");
		        atualizarTotal();
		    }
		});
		
		table.removeColumn(table.getColumnModel().getColumn(0));
		add(table);
		
		JLabel labelInformacoes = new JLabel("INFORMAÇÕES");
		labelInformacoes.setVerticalAlignment(SwingConstants.BOTTOM);
		labelInformacoes.setHorizontalAlignment(SwingConstants.LEFT);
		labelInformacoes.setForeground(new Color(208, 219, 151));
		labelInformacoes.setFont(new Font("Arial", Font.BOLD, 22));
		labelInformacoes.setBounds(450, 85, 408, 29);
		add(labelInformacoes);
		
		JLabel lblProduto = new JLabel("PRODUTO");
		lblProduto.setVerticalAlignment(SwingConstants.BOTTOM);
		lblProduto.setHorizontalAlignment(SwingConstants.LEFT);
		lblProduto.setForeground(new Color(208, 219, 151));
		lblProduto.setFont(new Font("Arial", Font.BOLD, 16));
		lblProduto.setBounds(450, 130, 408, 29);
		add(lblProduto);
		
		tfProduto = new JTextField();
		tfProduto.setToolTipText("PRODUTO");
		tfProduto.setForeground(new Color(37, 77, 50));
		tfProduto.setFont(new Font("Arial", Font.BOLD, 16));
		tfProduto.setColumns(10);
		tfProduto.setBorder(new EmptyBorder(10, 10, 10, 10));
		tfProduto.setBackground(new Color(122, 148, 101));
		tfProduto.setBounds(450, 160, 408, 50);
		add(tfProduto);
		
		JLabel lblQtde = new JLabel("QUANTIDADE");
		lblQtde.setVerticalAlignment(SwingConstants.BOTTOM);
		lblQtde.setHorizontalAlignment(SwingConstants.LEFT);
		lblQtde.setForeground(new Color(208, 219, 151));
		lblQtde.setFont(new Font("Arial", Font.BOLD, 16));
		lblQtde.setBounds(450, 220, 408, 29);
		add(lblQtde);
		
		tfQtde = new JTextField();
		tfQtde.setToolTipText("PREÇO");
		tfQtde.setForeground(new Color(37, 77, 50));
		tfQtde.setFont(new Font("Arial", Font.BOLD, 16));
		tfQtde.setColumns(10);
		tfQtde.setBorder(new EmptyBorder(10, 10, 10, 10));
		tfQtde.setBackground(new Color(122, 148, 101));
		tfQtde.setBounds(450, 250, 408, 50);
		
		tfQtde.getDocument().addDocumentListener(new DocumentListener() {
		    private void update() {
		        atualizarTotal();
		    }
		    @Override public void insertUpdate(DocumentEvent e) { update(); }
		    @Override public void removeUpdate(DocumentEvent e) { update(); }
		    @Override public void changedUpdate(DocumentEvent e) { update(); }
		});
		add(tfQtde);
		
		JButton buttonAdicionar = new JButton("ADICIONAR");
		buttonAdicionar.setOpaque(true);
		buttonAdicionar.setForeground(new Color(37, 77, 50));
		buttonAdicionar.setFont(new Font("Arial", Font.BOLD, 20));
		buttonAdicionar.setBorderPainted(false);
		buttonAdicionar.setBackground(new Color(208, 219, 151));
		buttonAdicionar.setBounds(678, 320, 180, 50);
		
		buttonAdicionar.addActionListener(e -> {
		    int linha = table.getSelectedRow();
		    if(linha != -1) {
		        try {
		            int idProduto = (int) table.getModel().getValueAt(linha, 0);
		            String nome = tfProduto.getText();
		            
		            Object precoObj = table.getModel().getValueAt(linha, 2);
		            double preco = precoObj instanceof Double ? (Double) precoObj : Double.parseDouble(precoObj.toString());
		            
		            int qtde = Integer.parseInt(tfQtde.getText());
		            if (qtde <= 0) {
		                JOptionPane.showMessageDialog(this, "Quantidade inválida.");
		                return;
		            }

		            int idUsuario = frame.getUsuarioLogado().getId();

		            boolean sucesso = controller.adicionarProduto(idUsuario, idProduto, qtde, preco);
		            if(sucesso) {
		                JOptionPane.showMessageDialog(this, "Produto adicionado ao carrinho.");
		                carregarProdutos();
		            } else {
		                JOptionPane.showMessageDialog(this, "Erro ao adicionar ao carrinho.");
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(this, "Quantidade inválida.");
		        }
		    } else {
		        JOptionPane.showMessageDialog(this, "Selecione um produto.");
		    }
		});
		add(buttonAdicionar);
		
		JLabel lblTotal = new JLabel("TOTAL A PAGAR (R$) / PRODUTO");
		lblTotal.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setForeground(new Color(208, 219, 151));
		lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
		lblTotal.setBounds(450, 440, 408, 29);
		add(lblTotal);
		
		tfTotalPagar = new JTextField();
		tfTotalPagar.setToolTipText("QUANTIDADE");
		tfTotalPagar.setForeground(corFundo);
		tfTotalPagar.setFont(new Font("Arial", Font.BOLD, 16));
		tfTotalPagar.setColumns(10);
		tfTotalPagar.setBorder(new EmptyBorder(10, 10, 10, 10));
		tfTotalPagar.setBackground(new Color(122, 148, 101));
		tfTotalPagar.setBounds(450, 470, 408, 50);
		add(tfTotalPagar);
		
		JButton buttonCarrinho = new JButton(new ImageIcon(Compras.class.getResource("/icons/carrinho.png")));
		buttonCarrinho.setOpaque(true);
		buttonCarrinho.setForeground(new Color(208, 219, 151));
		buttonCarrinho.setBorderPainted(false);
		buttonCarrinho.setBackground(new Color(37, 77, 50));
		buttonCarrinho.setBounds(820, 50, 38, 40);
		
		buttonCarrinho.addActionListener(e -> {
		    frame.mostrarCarrinho();
		});
		add(buttonCarrinho);
		
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
		
		carregarProdutos();
	}
	
	private void carregarProdutos() {
	    ProdutoController produtoController = new ProdutoController();
	    modelo.setRowCount(0);
	    
	    produtoController.listarProdutos().forEach(p -> {
	        modelo.addRow(new Object[]{
	            p.getId(),
	            p.getProduto(),
	            p.getPreco(),
	            p.getQtde()
	        });
	    });
	}
	
	private void atualizarTotal() {
	    int linha = table.getSelectedRow();
	    if (linha != -1) {
	        try {
	            Object precoObj = table.getModel().getValueAt(linha, 2);
	            double preco;
	            if (precoObj instanceof Double) {
	                preco = (Double) precoObj;
	            } else if (precoObj instanceof Integer) {
	                preco = ((Integer) precoObj).doubleValue();
	            } else {
	                preco = Double.parseDouble(precoObj.toString());
	            }

	            int qtde = Integer.parseInt(tfQtde.getText());
	            tfTotalPagar.setText(String.format("%.2f", preco * qtde));
	        } catch (NumberFormatException ex) {
	            tfTotalPagar.setText("0.00");
	        }
	    }
	}

}
