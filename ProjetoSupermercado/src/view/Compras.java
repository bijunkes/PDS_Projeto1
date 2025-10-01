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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.ComprasController;
import controller.Frame;
import model.Carrinho;
import model.Produto;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.JTable;

public class Compras extends JPanel {

	private static final long serialVersionUID = 1L;

	private List<Produto> produtos;
	private JTextField tfProduto;
	private JTextField tfQtde;
	private JTextField lblTotalAPagar;
	private JTable table;
	
	private ComprasController controller;
	private Frame frame;
	
	DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Produto", "Preço", "Qtde"}, 0);
	NumberFormat formatoReal = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

	public Compras(Frame frame) {
		
		this.controller = new ComprasController();
		produtos = controller.listarProdutos();
		this.frame = frame;
		
		Color corFundo = new Color(0x25, 0x4D, 0x32);
		Color verdeClaro = new Color(208, 219, 151);
		
		produtos = controller.listarProdutos();
        for (Produto p : produtos) {
            modelo.addRow(new Object[]{
                    p.getProduto(),
                    formatoReal.format(p.getPreco()),
                    p.getQtde()
            });
        }
		
		setBackground(corFundo);
		setPreferredSize(new Dimension(900, 600));
		setLayout(null);
		
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
		
		JLabel labelInformacoes = new JLabel("INFORMAÇÕES");
		labelInformacoes.setVerticalAlignment(SwingConstants.BOTTOM);
		labelInformacoes.setHorizontalAlignment(SwingConstants.LEFT);
		labelInformacoes.setForeground(new Color(208, 219, 151));
		labelInformacoes.setFont(new Font("Arial", Font.BOLD, 22));
		labelInformacoes.setBounds(450, 85, 408, 29);
		add(labelInformacoes);
		
		tfQtde = new JTextField();
		tfQtde.setToolTipText("PREÇO");
		tfQtde.setForeground(new Color(37, 77, 50));
		tfQtde.setFont(new Font("Arial", Font.BOLD, 16));
		tfQtde.setColumns(10);
		tfQtde.setBorder(new EmptyBorder(10, 10, 10, 10));
		tfQtde.setBackground(new Color(122, 148, 101));
		tfQtde.setBounds(450, 250, 408, 50);
		tfQtde.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
		    public void update() {
		        int linha = table.getSelectedRow();
		        if (linha >= 0) {
		            Produto p = produtos.get(linha);
		            try {
		                int qtde = Integer.parseInt(tfQtde.getText());

		                if (qtde > p.getQtde()) {
		                    JOptionPane.showMessageDialog(Compras.this,
		                        "Quantidade solicitada maior que o estoque disponível (" + p.getQtde() + ")!",
		                        "Atenção", JOptionPane.WARNING_MESSAGE);
		                    qtde = p.getQtde();
		                    tfQtde.setText(String.valueOf(qtde));
		                } else if (qtde < 1) {
		                    qtde = 1;
		                    tfQtde.setText(String.valueOf(qtde));
		                }

		                double total = qtde * p.getPreco();
		                lblTotalAPagar.setText(formatoReal.format(total));

		            } catch (NumberFormatException ex) {
		                lblTotalAPagar.setText(formatoReal.format(0));
		            }
		        } else {
		            lblTotalAPagar.setText(formatoReal.format(0));
		        }
		    }

		    @Override
		    public void insertUpdate(javax.swing.event.DocumentEvent e) { update(); }
		    @Override
		    public void removeUpdate(javax.swing.event.DocumentEvent e) { update(); }
		    @Override
		    public void changedUpdate(javax.swing.event.DocumentEvent e) { update(); }
		});



		add(tfQtde);
		
		lblTotalAPagar = new JTextField();
		lblTotalAPagar.setToolTipText("QUANTIDADE");
		lblTotalAPagar.setForeground(corFundo);
		lblTotalAPagar.setFont(new Font("Arial", Font.BOLD, 16));
		lblTotalAPagar.setColumns(10);
		lblTotalAPagar.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblTotalAPagar.setBackground(new Color(122, 148, 101));
		lblTotalAPagar.setBounds(450, 470, 408, 50);
		add(lblTotalAPagar);
		
		JButton buttonAdicionar = new JButton("ADICIONAR");

		buttonAdicionar.setOpaque(true);
		buttonAdicionar.setForeground(new Color(37, 77, 50));
		buttonAdicionar.setFont(new Font("Arial", Font.BOLD, 20));
		buttonAdicionar.setBorderPainted(false);
		buttonAdicionar.setBackground(new Color(208, 219, 151));
		buttonAdicionar.setBounds(678, 320, 180, 50);
		buttonAdicionar.addActionListener(e -> {
		    adicionarProdutoSelecionado();
		});

		add(buttonAdicionar);
		
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
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(30);
		table.setForeground(new Color(37, 77, 50));
		table.setFont(new Font("Arial", Font.BOLD, 18));
		table.setBackground(new Color(122, 148, 101));
		table.setBounds(40, 160, 350, 410);
		table.setModel(modelo);
		
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < table.getColumnCount(); i++) {
		    table.getColumnModel().getColumn(i).setCellRenderer(centralizado);
		}
		
		add(table);
		
		table.getSelectionModel().addListSelectionListener(event -> {
		    if (!event.getValueIsAdjusting()) {
		        int linha = table.getSelectedRow();
		        if (linha >= 0) {
		            Produto p = produtos.get(linha);
		            tfProduto.setText(p.getProduto());
		            tfQtde.setText("1");
		            double total = 1 * p.getPreco();
		            lblTotalAPagar.setText(formatoReal.format(total));
		        }
		    }
		});
	
		JLabel labelCadastro_1_1_1_1 = new JLabel("QUANTIDADE");
		labelCadastro_1_1_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
		labelCadastro_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		labelCadastro_1_1_1_1.setForeground(new Color(208, 219, 151));
		labelCadastro_1_1_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		labelCadastro_1_1_1_1.setBounds(450, 220, 408, 29);
		add(labelCadastro_1_1_1_1);
		
		JLabel lblQtdeTabela = new JLabel("QTDE");
		lblQtdeTabela.setOpaque(true);
		lblQtdeTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblQtdeTabela.setForeground(new Color(37, 77, 50));
		lblQtdeTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblQtdeTabela.setBackground(new Color(208, 219, 151));
		lblQtdeTabela.setBounds(270, 130, 120, 30);

		add(lblQtdeTabela);
		
		JLabel lblTotal = new JLabel("TOTAL A PAGAR (R$) / PRODUTO");
		lblTotal.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setForeground(new Color(208, 219, 151));
		lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
		lblTotal.setBounds(450, 440, 408, 29);
		add(lblTotal);
		
		JButton buttonCarrinho = new JButton(new ImageIcon(Compras.class.getResource("/icons/carrinho.png")));
		buttonCarrinho.setOpaque(true);
		buttonCarrinho.setForeground(new Color(208, 219, 151));
		buttonCarrinho.setBorderPainted(false);
		buttonCarrinho.setBackground(new Color(37, 77, 50));
		buttonCarrinho.setBounds(820, 50, 38, 40);
		add(buttonCarrinho);
		buttonCarrinho.addActionListener(e -> {
		    frame.mostrarCarrinho();
		});
	}
	
	public void retornarEstoqueAoRemover(int idProduto, int qtdeRemovida) {
	    Produto produto = produtos.stream()
	        .filter(p -> p.getId() == idProduto)
	        .findFirst()
	        .orElse(null);

	    if (produto != null) {
	        produto.setQtde(produto.getQtde() + qtdeRemovida);

	        int linha = produtos.indexOf(produto);
	        modelo.setValueAt(produto.getQtde(), linha, 2);
	    }
	}
	
	private void adicionarProdutoSelecionado() {
        int linha = table.getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um produto!");
            return;
        }

        if (frame.getUsuarioLogado() == null) {
            JOptionPane.showMessageDialog(this, "Nenhum usuário logado!");
            return;
        }

        Produto selecionado = produtos.get(linha);
        int qtde;

        try {
            qtde = Integer.parseInt(tfQtde.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantidade inválida!");
            return;
        }

        if (qtde > selecionado.getQtde()) {
            JOptionPane.showMessageDialog(this,
                    "Quantidade solicitada maior que o estoque disponível (" + selecionado.getQtde() + ")!",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Carrinho existente = controller.buscarProdutoNoCarrinho(frame.getUsuarioLogado().getId(), selecionado.getId());

        if (existente != null) {
            int novaQtde = existente.getQtde() + qtde;
            controller.atualizarQuantidadeNoCarrinho(frame.getUsuarioLogado().getId(), selecionado.getId(), novaQtde);
            JOptionPane.showMessageDialog(this, "Quantidade atualizada no carrinho!");
        } else {
            Carrinho c = new Carrinho(
                    0,
                    frame.getUsuarioLogado().getId(),
                    selecionado.getId(),
                    qtde,
                    selecionado.getPreco()
            );
            if (controller.adicionarAoCarrinho(c)) {
                JOptionPane.showMessageDialog(this, "Produto adicionado ao carrinho!");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar produto!");
                return;
            }
        }

        selecionado.setQtde(selecionado.getQtde() - qtde);
        modelo.setValueAt(selecionado.getQtde(), linha, 2);
    }
	
}
