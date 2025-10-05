package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.CarrinhoController;
import controller.Frame;
import controller.ProdutoController;
import model.Usuario;

import javax.swing.JTextField;

import javax.swing.JTable;

public class Carrinho extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfProduto;
	private JTextField tfQtde;
	private JTextField lblTotalAPagar;
	private JTable table;
	private CarrinhoController controllerCarrinho;
	private ProdutoController controllerProduto;

	private boolean atualizandoCampo = false;

	private Frame frame;
	public Carrinho(Frame frame) {
		
		this.frame = frame;
		controllerCarrinho = new CarrinhoController();
		controllerProduto = new ProdutoController();
		
		Color corFundo = new Color(0x25, 0x4D, 0x32);
		Color verdeClaro = new Color(208, 219, 151);
		Color verdeClaroTransparente = new Color(122, 148, 101);
		
		setBackground(corFundo);
		setPreferredSize(new Dimension(900, 600));
		setLayout(null);
		
		JLabel lblCarrinho = new JLabel("CARRINHO");
		lblCarrinho.setVerticalAlignment(SwingConstants.TOP);
		lblCarrinho.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarrinho.setForeground(new Color(208, 219, 151));
		lblCarrinho.setFont(new Font("Arial", Font.BOLD, 30));
		lblCarrinho.setBounds(37, 55, 353, 29);
		add(lblCarrinho);
		
		JLabel labelDeProdutos = new JLabel("DE PRODUTOS");
		labelDeProdutos.setVerticalAlignment(SwingConstants.TOP);
		labelDeProdutos.setHorizontalAlignment(SwingConstants.CENTER);
		labelDeProdutos.setForeground(new Color(208, 219, 151));
		labelDeProdutos.setFont(new Font("Arial", Font.BOLD, 30));
		labelDeProdutos.setBounds(37, 85, 353, 29);
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
		table.setRowHeight(30);
		table.setForeground(new Color(37, 77, 50));
		table.setFont(new Font("Arial", Font.BOLD, 18));
		table.setBackground(new Color(122, 148, 101));
		table.setBounds(40, 160, 350, 410);
				
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, centralizado);
		
		String[] colunas = {"ID", "Produto", "Preço", "Qtde", "ID_PRODUTO"};
		DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		table.setModel(modelo);
		
		table.getSelectionModel().addListSelectionListener(event -> {
		    if (!event.getValueIsAdjusting()) {
		        int linha = table.getSelectedRow();
		        if (linha != -1) {
		            atualizarCamposComLinhaSelecionada(linha);
		        } else {
		            tfProduto.setText("");
		            tfQtde.setText("");
		            atualizarTotalCarrinho();
		        }
		    }
		});
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    private int linhaSelecionadaAnterior = -1;

		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent e) {
		        int linhaAtual = table.rowAtPoint(e.getPoint());

		        if (linhaAtual == linhaSelecionadaAnterior) {
		            table.clearSelection();
		            linhaSelecionadaAnterior = -1;
		        } else {
		            linhaSelecionadaAnterior = linhaAtual;
		        }
		    }
		});

		table.removeColumn(table.getColumnModel().getColumn(4));
		table.removeColumn(table.getColumnModel().getColumn(0));
		add(table);
		
		JLabel labelInformacoes = new JLabel("INFORMAÇÕES");
		labelInformacoes.setVerticalAlignment(SwingConstants.BOTTOM);
		labelInformacoes.setHorizontalAlignment(SwingConstants.LEFT);
		labelInformacoes.setForeground(verdeClaro);
		labelInformacoes.setFont(new Font("Arial", Font.BOLD, 22));
		labelInformacoes.setBounds(450, 85, 408, 29);
		add(labelInformacoes);
		
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
		
		JLabel lblQtde = new JLabel("QUANTIDADE");
		lblQtde.setVerticalAlignment(SwingConstants.BOTTOM);
		lblQtde.setHorizontalAlignment(SwingConstants.LEFT);
		lblQtde.setForeground(new Color(208, 219, 151));
		lblQtde.setFont(new Font("Arial", Font.BOLD, 16));
		lblQtde.setBounds(450, 220, 408, 29);
		add(lblQtde);
		
		tfQtde = new JTextField();
		tfQtde.setToolTipText("QUANTIDADE");
		tfQtde.setForeground(new Color(37, 77, 50));
		tfQtde.setFont(new Font("Arial", Font.BOLD, 16));
		tfQtde.setColumns(10);
		tfQtde.setBorder(new EmptyBorder(10, 10, 10, 10));
		tfQtde.setBackground(verdeClaroTransparente);
		tfQtde.setBounds(450, 250, 408, 50);
		tfQtde.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		add(tfQtde);
		
		JLabel lblTotal = new JLabel("TOTAL A PAGAR (R$)");
		lblTotal.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setForeground(new Color(208, 219, 151));
		lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
		lblTotal.setBounds(450, 400, 408, 29);
		add(lblTotal);
		
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
		
		JButton buttonSalvar = new JButton("SALVAR");
		buttonSalvar.setOpaque(true);
		buttonSalvar.setForeground(new Color(37, 77, 50));
		buttonSalvar.setFont(new Font("Arial", Font.BOLD, 20));
		buttonSalvar.setBorderPainted(false);
		buttonSalvar.setBackground(new Color(208, 219, 151));
		buttonSalvar.setBounds(708, 320, 150, 50);
		
		buttonSalvar.addActionListener(e -> {
		    int linha = table.getSelectedRow();
		    if (linha == -1) {
		        JOptionPane.showMessageDialog(this, "Selecione um produto para atualizar.");
		        return;
		    }

		    DefaultTableModel modelo1 = (DefaultTableModel) table.getModel();
		    int idUsuario = frame.getUsuarioLogado().getId();
		    int idProduto = Integer.parseInt(modelo1.getValueAt(linha, 4).toString());
		    int qtdeAtualCarrinho = Integer.parseInt(modelo1.getValueAt(linha, 3).toString());

		    String qtdeStr = tfQtde.getText().trim();
		    if (qtdeStr.isEmpty()) {
		        JOptionPane.showMessageDialog(this, "Informe a nova quantidade.");
		        return;
		    }

		    int novaQtde;
		    try {
		        novaQtde = Integer.parseInt(qtdeStr);
		    } catch (NumberFormatException ex) {
		        JOptionPane.showMessageDialog(this, "Digite um número válido.");
		        return;
		    }

		    if (novaQtde <= 0) {
		        JOptionPane.showMessageDialog(this, "A quantidade deve ser maior que 0.");
		        return;
		    }

		    int diferenca = novaQtde - qtdeAtualCarrinho;

		    List<model.Carrinho> entradas = controllerCarrinho.listarProdutos(idUsuario);
		    boolean erro = false;
		    int restante = Math.abs(diferenca);

		    for (model.Carrinho c : entradas) {
		        if (c.getIdProduto() != idProduto) continue;
		        if (restante <= 0) break;

		        int idCarrinho = c.getId();
		        int qtde = c.getQtde();

		        if (diferenca > 0) {
		            boolean ok = controllerCarrinho.atualizarQuantidade(idCarrinho, qtde + restante);
		            if (!ok) erro = true;
		            break;
		        } else if (diferenca < 0) {
		            if (qtde > restante) {
		                boolean ok = controllerCarrinho.atualizarQuantidade(idCarrinho, qtde - restante);
		                if (!ok) erro = true;
		                break;
		            } else {
		                boolean ok = controllerCarrinho.removerProduto(idCarrinho);
		                if (!ok) erro = true;
		                restante -= qtde;
		            }
		        }
		    }

		    if (erro) {
		        JOptionPane.showMessageDialog(this, "Erro ao atualizar o carrinho. Tente novamente.");
		        return;
		    }
		    
		    boolean sucessoEstoque;
		    if (diferenca > 0) {
		        sucessoEstoque = controllerProduto.removerEstoqueProduto(idProduto, diferenca);
		    } else if (diferenca < 0) {
		        sucessoEstoque = controllerProduto.adicionarEstoqueProduto(idProduto, Math.abs(diferenca));
		    } else {
		        sucessoEstoque = true;
		    }

		    if (!sucessoEstoque) {
		        JOptionPane.showMessageDialog(this, "Erro ao atualizar o estoque. Verifique o sistema.");
		        return;
		    }

		    carregarCarrinho();
		    JOptionPane.showMessageDialog(this, "Quantidade atualizada.");
		});

		add(buttonSalvar);
		
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
		    Usuario usuario = frame.getUsuarioLogado();
		    List<model.Carrinho> produtos = controllerCarrinho.listarProdutos(usuario.getId());

		    new NotaFiscal(frame, usuario, produtos, controllerProduto);
		    
		    for (model.Carrinho c : produtos) {
		        controllerCarrinho.removerProduto(c.getId());
		    }

		    carregarCarrinho();
		});
		add(buttonNotaFiscal);
		
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
		
		carregarCarrinho();
	}
	
	private void carregarCarrinho() {
	    int idUsuario = frame.getUsuarioLogado().getId();
	    DefaultTableModel modelo = (DefaultTableModel) table.getModel();
	    modelo.setRowCount(0);

	    Map<Integer, Object[]> mapa = new HashMap<>();

	    controllerCarrinho.listarProdutos(idUsuario).forEach(c -> {
	        if (mapa.containsKey(c.getIdProduto())) {
	            Object[] item = mapa.get(c.getIdProduto());
	            int qtdeAtual = (int) item[3];
	            item[3] = qtdeAtual + c.getQtde();
	        } else {
	            String nomeProduto = controllerProduto.buscarProdutoPorId(c.getIdProduto()).getProduto();
	            mapa.put(c.getIdProduto(), new Object[] {
	            	    c.getId(), nomeProduto, c.getPreco(), c.getQtde(), c.getIdProduto()
	            	});
	        }
	    });
	    for (Object[] item : mapa.values()) {
	        modelo.addRow(item);
	    }
	    atualizarTotalCarrinho();
	}
	
	private void atualizarCamposComLinhaSelecionada(int linha) {
	    atualizandoCampo = true;
	    try {
	        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
	        String nomeProduto = modelo.getValueAt(linha, 1).toString();
	        tfProduto.setText(nomeProduto);

	        String qtde = modelo.getValueAt(linha, 3).toString();
	        tfQtde.setText(qtde);

	        double preco = Double.parseDouble(modelo.getValueAt(linha, 2).toString());
	        lblTotalAPagar.setText(String.format("%.2f", preco * Integer.parseInt(qtde)));
	    } finally {
	        atualizandoCampo = false;
	    }
	}
	
	private void atualizarTotalCarrinho() {
	    DefaultTableModel modelo = (DefaultTableModel) table.getModel();
	    double total = 0.0;
	    for (int i = 0; i < modelo.getRowCount(); i++) {
	        double preco = Double.parseDouble(modelo.getValueAt(i, 2).toString());
	        int qtde = Integer.parseInt(modelo.getValueAt(i, 3).toString());
	        total += preco * qtde;
	    }
	    lblTotalAPagar.setText(String.format("%.2f", total));
	}
	
	
}
