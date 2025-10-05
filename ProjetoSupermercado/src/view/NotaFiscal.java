package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.ProdutoController;
import model.Usuario;

import java.awt.*;

public class NotaFiscal extends JDialog {

    private JTextField textFieldNome;
    private JTextField textFieldCPF;
    private JTextField textFieldTotal;
    private JTable table;

    public NotaFiscal(JFrame parent, Usuario usuario, java.util.List<model.Carrinho> produtos, ProdutoController produtoController) {
    	
        Color corFundo = new Color(0x25, 0x4D, 0x32);
		Color verdeClaro = new Color(208, 219, 151);
		Color verdeClaroTransparente = new Color(122, 148, 101);
		
        getContentPane().setBackground(corFundo);
        setSize(500, 600);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(null);
        
        JLabel lblNotaFiscal = new JLabel("NOTA FISCAL");
        lblNotaFiscal.setVerticalAlignment(SwingConstants.TOP);
        lblNotaFiscal.setHorizontalAlignment(SwingConstants.CENTER);
        lblNotaFiscal.setForeground(new Color(208, 219, 151));
        lblNotaFiscal.setFont(new Font("Arial", Font.BOLD, 30));
        lblNotaFiscal.setBounds(100, 35, 300, 29);
        getContentPane().add(lblNotaFiscal);
        
        JLabel lblNome = new JLabel("NOME");
        lblNome.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNome.setHorizontalAlignment(SwingConstants.LEFT);
        lblNome.setForeground(new Color(208, 219, 151));
        lblNome.setFont(new Font("Arial", Font.BOLD, 16));
        lblNome.setBounds(75, 70, 220, 29);
        getContentPane().add(lblNome);

        textFieldNome = new JTextField(usuario.getNome());
        textFieldNome.setEditable(false);
        textFieldNome.setFont(new Font("Arial", Font.BOLD, 18));
        textFieldNome.setForeground(corFundo);
        textFieldNome.setBackground(verdeClaroTransparente);
        textFieldNome.setBounds(75, 100, 350, 40);
        textFieldNome.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        getContentPane().add(textFieldNome);
        
        JLabel lblCPF = new JLabel("CPF");
        lblCPF.setVerticalAlignment(SwingConstants.BOTTOM);
        lblCPF.setHorizontalAlignment(SwingConstants.LEFT);
        lblCPF.setForeground(new Color(208, 219, 151));
        lblCPF.setFont(new Font("Arial", Font.BOLD, 16));
        lblCPF.setBounds(75, 140, 220, 29);
        getContentPane().add(lblCPF);
        
        textFieldCPF = new JTextField(usuario.getCpf());
        textFieldCPF.setFont(new Font("Arial", Font.BOLD, 18));
        textFieldCPF.setForeground(corFundo);
        textFieldCPF.setBackground(verdeClaroTransparente);
        textFieldCPF.setBounds(75, 170, 350, 40);
        textFieldCPF.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        getContentPane().add(textFieldCPF);
        
        JLabel lblProdutos = new JLabel("PRODUTOS");
        lblProdutos.setVerticalAlignment(SwingConstants.BOTTOM);
        lblProdutos.setHorizontalAlignment(SwingConstants.LEFT);
        lblProdutos.setForeground(new Color(208, 219, 151));
        lblProdutos.setFont(new Font("Arial", Font.BOLD, 16));
        lblProdutos.setBounds(75, 210, 220, 29);
        getContentPane().add(lblProdutos);
        
        JLabel lblProdutoTabela = new JLabel("PRODUTO");
		lblProdutoTabela.setOpaque(true);
		lblProdutoTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblProdutoTabela.setForeground(new Color(37, 77, 50));
		lblProdutoTabela.setFont(new Font("Arial", Font.BOLD, 18));
		lblProdutoTabela.setBackground(new Color(208, 219, 151));
		lblProdutoTabela.setBounds(75, 240, 120, 30);
		getContentPane().add(lblProdutoTabela);
        
        JLabel lblQtdeTabela = new JLabel("QTDE");
        lblQtdeTabela.setOpaque(true);
        lblQtdeTabela.setHorizontalAlignment(SwingConstants.CENTER);
        lblQtdeTabela.setForeground(new Color(37, 77, 50));
        lblQtdeTabela.setFont(new Font("Arial", Font.BOLD, 18));
        lblQtdeTabela.setBackground(new Color(208, 219, 151));
        lblQtdeTabela.setBounds(305, 240, 120, 30);
        getContentPane().add(lblQtdeTabela);
        
        JLabel lblPrecoTabela = new JLabel("PREÇO");
        lblPrecoTabela.setOpaque(true);
        lblPrecoTabela.setHorizontalAlignment(SwingConstants.CENTER);
        lblPrecoTabela.setForeground(new Color(37, 77, 50));
        lblPrecoTabela.setFont(new Font("Arial", Font.BOLD, 18));
        lblPrecoTabela.setBackground(new Color(208, 219, 151));
        lblPrecoTabela.setBounds(190, 240, 120, 30);
        getContentPane().add(lblPrecoTabela);
        
        table = new JTable();
        table.setRowHeight(30);
        table.setForeground(new Color(37, 77, 50));
		table.setFont(new Font("Arial", Font.BOLD, 18));
		table.setBackground(new Color(122, 148, 101));
        table.setBounds(75, 270, 350, 190);
        
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, centralizado);
		
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Produto", "Preço (R$)", "Qtde"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(modelo);
        
        double totalCompra = 0.0;
        java.util.Map<String, Object[]> mapaProdutos = new java.util.LinkedHashMap<>();

        for (model.Carrinho c : produtos) {
            var produto = produtoController.buscarProdutoPorId(c.getIdProduto());
            String nomeProduto = produto.getProduto();
            double preco = c.getPreco();
            int qtde = c.getQtde();

            if (mapaProdutos.containsKey(nomeProduto)) {
                Object[] dados = mapaProdutos.get(nomeProduto);
                int qtdeAtual = (int) dados[2];
                dados[2] = qtdeAtual + qtde;
                mapaProdutos.put(nomeProduto, dados);
            } else {
                mapaProdutos.put(nomeProduto, new Object[]{nomeProduto, String.format("%.2f", preco), qtde});
            }

            totalCompra += preco * qtde;
        }

        for (Object[] linha : mapaProdutos.values()) {
            modelo.addRow(linha);
        }

        getContentPane().add(table);
        
        JLabel lblTotal = new JLabel("TOTAL (R$)");
        lblTotal.setVerticalAlignment(SwingConstants.BOTTOM);
        lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
        lblTotal.setForeground(new Color(208, 219, 151));
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotal.setBounds(75, 460, 220, 29);
        getContentPane().add(lblTotal);
        
        textFieldTotal = new JTextField(String.format("%.2f", totalCompra));
        textFieldTotal.setEditable(false);
        textFieldTotal.setFont(new Font("Arial", Font.BOLD, 18));
        textFieldTotal.setForeground(corFundo);
        textFieldTotal.setBackground(verdeClaroTransparente);
        textFieldTotal.setBounds(75, 490, 350, 40);
        textFieldTotal.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        getContentPane().add(textFieldTotal);
        
        setModal(true);
        setVisible(true);
    }
}
