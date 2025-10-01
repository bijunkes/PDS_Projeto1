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

import controller.CarrinhoController;
import controller.Frame;
import model.Produto;

import javax.swing.JTextField;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.JTable;

public class Carrinho extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfProduto;
	private JTextField tfQtde;
	private JTextField lblTotalAPagar;
	private JTable table;
	
	private CarrinhoController carrinhoController;
	
	private Frame frame;

	NumberFormat formatoReal = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	DefaultTableModel modelo = new DefaultTableModel(
		    new Object[]{"IDProduto", "Produto", "Preço", "Qtde", "IDCarrinho"}, 0
		) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};

		private void atualizarTotal() {
		    double total = 0.0;

		    for (int i = 0; i < modelo.getRowCount(); i++) {
		        double preco = (Double) modelo.getValueAt(i, 2);
		        int qtde = (Integer) modelo.getValueAt(i, 3);

		        total += preco * qtde;
		    }

		    lblTotalAPagar.setText(formatoReal.format(total));
		}	
		
		private void atualizarTabela() {
		    modelo.setRowCount(0);

		    if (frame.getUsuarioLogado() == null) return;

		    List<Object[]> itens = carrinhoController.listarItensDoCarrinhoComProduto(frame.getUsuarioLogado().getId());

		    for (Object[] obj : itens) {
		        model.Carrinho c = (model.Carrinho) obj[0];
		        Produto p = (Produto) obj[1];

		        modelo.addRow(new Object[]{
		            p.getId(),
		            p.getProduto(),
		            c.getPreco(),
		            c.getQtde(),
		            c.getId()
		        });
		    }

		    table.getColumnModel().getColumn(4).setMinWidth(0);
		    table.getColumnModel().getColumn(4).setMaxWidth(0);
		    table.getColumnModel().getColumn(4).setPreferredWidth(0);

		    tfProduto.setText("");
		    tfQtde.setText("");
		    atualizarTotal();
		}

	public Carrinho(Frame frame, Compras compras) {
		
		this.frame = frame;
		this.carrinhoController = new CarrinhoController();
		
		Color corFundo = new Color(0x25, 0x4D, 0x32);
		Color verdeClaro = new Color(208, 219, 151);
		Color verdeClaroTransparente = new Color(122, 148, 101);
		
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
		    frame.mostrarCompras();
		});
		
		add(buttonVoltar);
		
		JLabel lblCarrinho = new JLabel("CATÁLOGO");
		lblCarrinho.setVerticalAlignment(SwingConstants.TOP);
		lblCarrinho.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarrinho.setForeground(verdeClaro);
		lblCarrinho.setFont(new Font("Arial", Font.BOLD, 30));
		lblCarrinho.setBounds(37, 70, 353, 29);
		add(lblCarrinho);
		
		JLabel lblProduto = new JLabel("PRODUTO");
		lblProduto.setVerticalAlignment(SwingConstants.BOTTOM);
		lblProduto.setHorizontalAlignment(SwingConstants.LEFT);
		lblProduto.setForeground(verdeClaro);
		lblProduto.setFont(new Font("Arial", Font.BOLD, 16));
		lblProduto.setBounds(450, 130, 408, 29);
		add(lblProduto);
		
		tfProduto = new JTextField();
		tfProduto.setEditable(false);
		tfProduto.setToolTipText("PRODUTO");
		tfProduto.setForeground(corFundo);
		tfProduto.setFont(new Font("Arial", Font.BOLD, 16));
		tfProduto.setColumns(10);
		tfProduto.setBorder(new EmptyBorder(10, 10, 10, 10));
		tfProduto.setBackground(verdeClaroTransparente);
		tfProduto.setBounds(450, 160, 408, 50);
		add(tfProduto);
		
		JLabel labelInformacoes = new JLabel("INFORMAÇÕES");
		labelInformacoes.setVerticalAlignment(SwingConstants.BOTTOM);
		labelInformacoes.setHorizontalAlignment(SwingConstants.LEFT);
		labelInformacoes.setForeground(verdeClaro);
		labelInformacoes.setFont(new Font("Arial", Font.BOLD, 22));
		labelInformacoes.setBounds(450, 85, 408, 29);
		add(labelInformacoes);
		
		tfQtde = new JTextField();
		tfQtde.setEditable(false);
		tfQtde.setToolTipText("QUANTIDADE");
		tfQtde.setForeground(new Color(37, 77, 50));
		tfQtde.setFont(new Font("Arial", Font.BOLD, 16));
		tfQtde.setColumns(10);
		tfQtde.setBorder(new EmptyBorder(10, 10, 10, 10));
		tfQtde.setBackground(verdeClaroTransparente);
		tfQtde.setBounds(450, 250, 408, 50);
		add(tfQtde);
		
		lblTotalAPagar = new JTextField();
		lblTotalAPagar.setEditable(false);
		lblTotalAPagar.setToolTipText("QUANTIDADE");
		lblTotalAPagar.setForeground(new Color(208, 219, 151));
		lblTotalAPagar.setFont(new Font("Arial", Font.BOLD, 16));
		lblTotalAPagar.setColumns(10);
		lblTotalAPagar.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblTotalAPagar.setBackground(verdeClaroTransparente);
		lblTotalAPagar.setBounds(450, 430, 408, 50);
		add(lblTotalAPagar);
		
		JButton buttonExcluir = new JButton("EXCLUIR");
		buttonExcluir.addActionListener(e -> {
		    int linha = table.getSelectedRow();
		    if (linha < 0) {
		        JOptionPane.showMessageDialog(Carrinho.this, "Selecione um item para excluir!");
		        return;
		    }

		    int idCarrinho = (Integer) modelo.getValueAt(linha, 4);
		    int idProduto = (Integer) modelo.getValueAt(linha, 0);
		    int qtdeRemovida = (Integer) modelo.getValueAt(linha, 3);

		    if (carrinhoController.excluirItem(idCarrinho)) {
		        if (compras != null) {
		            compras.retornarEstoqueAoRemover(idProduto, qtdeRemovida);
		        }
		        atualizarTabela();
		        JOptionPane.showMessageDialog(Carrinho.this, "Item excluído!");
		    } else {
		        JOptionPane.showMessageDialog(Carrinho.this, "Erro ao excluir o item!");
		    }

		});

		buttonExcluir.setOpaque(true);
		buttonExcluir.setForeground(new Color(37, 77, 50));
		buttonExcluir.setFont(new Font("Arial", Font.BOLD, 20));
		buttonExcluir.setBorderPainted(false);
		buttonExcluir.setBackground(new Color(208, 219, 151));
		buttonExcluir.setBounds(708, 320, 150, 50);
		
		add(buttonExcluir);
		
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
		
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		
		table.getColumnModel().getColumn(4).setMinWidth(0);
		table.getColumnModel().getColumn(4).setMaxWidth(0);
		table.getColumnModel().getColumn(4).setPreferredWidth(0);


		table.getSelectionModel().addListSelectionListener(e -> {
		    if (!e.getValueIsAdjusting() && table.getSelectedRow() >= 0) {
		        int linha = table.getSelectedRow();

		        Object objProduto = table.getValueAt(linha, 1);
		        Object objQtde = table.getValueAt(linha, 3); 
		        
		        tfProduto.setText(objProduto != null ? objProduto.toString() : "");
		        tfQtde.setText(objQtde != null ? objQtde.toString() : "0");
		    }
		});

		add(table);
		
		if (frame.getUsuarioLogado() != null) {
		    atualizarTabela();
		}
		
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
		lblTotal.setBounds(450, 400, 408, 29);
		add(lblTotal);
		
		JButton buttonCarrinho = new JButton(new ImageIcon(Carrinho.class.getResource("/icons/carrinho.png")));
		buttonCarrinho.setOpaque(true);
		buttonCarrinho.setForeground(new Color(208, 219, 151));
		buttonCarrinho.setBorderPainted(false);
		buttonCarrinho.setBackground(new Color(37, 77, 50));
		buttonCarrinho.setBounds(820, 50, 38, 40);
		add(buttonCarrinho);
		
		JButton buttonNotaFiscal = new JButton("NOTA FISCAL");
		buttonNotaFiscal.setOpaque(true);
		buttonNotaFiscal.setForeground(new Color(37, 77, 50));
		buttonNotaFiscal.setFont(new Font("Arial", Font.BOLD, 20));
		buttonNotaFiscal.setBorderPainted(false);
		buttonNotaFiscal.setBackground(new Color(208, 219, 151));
		buttonNotaFiscal.setBounds(450, 520, 180, 50);
		buttonNotaFiscal.addActionListener(e -> {
		    if (frame.getUsuarioLogado() == null) {
		        JOptionPane.showMessageDialog(Carrinho.this, "Nenhum usuário logado!");
		        return;
		    }

		    int idUsuario = frame.getUsuarioLogado().getId();
		    List<model.Carrinho> itensCarrinho = carrinhoController.listarCarrinhoPorUsuario(idUsuario);

		    List<String> nota = carrinhoController.finalizarCompra(idUsuario, itensCarrinho);

		    System.out.println("========== NOTA FISCAL ==========");
		    System.out.println("Nome: " + frame.getUsuarioLogado().getNome());
		    System.out.println("CPF: " + frame.getUsuarioLogado().getCpf());
		    System.out.println("---------------------------------");
		    for (String linha : nota) {
		        System.out.println(linha);
		    }

		    modelo.setRowCount(0);
		    tfProduto.setText("");
		    tfQtde.setText("");
		    lblTotalAPagar.setText("R$0,00");

		    JOptionPane.showMessageDialog(Carrinho.this, "Compra concluída com sucesso!");
		});

		add(buttonNotaFiscal);
	}
	
	
}
