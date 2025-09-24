package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Produto;
import model.ProdutoDAO;

public class EditarProduto extends JDialog {

    private JTextField campoNome;
    private JTextField campoPreco;
    private JTextField campoQtde;

    private Produto produto;
    private Runnable callbackAtualizarTabela;

    public EditarProduto(JFrame parent, Produto produtoSelecionado, Runnable callbackAtualizar) {
        super(parent, "Editar Produto", true);
        
        Color corFundo = new Color(0x25, 0x4D, 0x32);
		Color verdeClaro = new Color(208, 219, 151);
		Color verdeClaroTransparente = new Color(122, 148, 101);
		
        getContentPane().setBackground(corFundo);
        this.produto = produtoSelecionado;
        this.callbackAtualizarTabela = callbackAtualizar;
        setSize(500, 400);
        setLocationRelativeTo(parent);

        campoNome = new JTextField(produto.getProduto());
        campoNome.setForeground(verdeClaro);
        campoNome.setBackground(verdeClaroTransparente);
        campoNome.setBounds(100, 110, 300, 40);
        campoPreco = new JTextField(String.valueOf(produto.getPreco()));
        campoPreco.setForeground(verdeClaro);
        campoPreco.setBackground(verdeClaroTransparente);
        campoPreco.setBounds(100, 180, 300, 40);
        campoQtde = new JTextField(String.valueOf(produto.getQtde()));
        campoQtde.setForeground(verdeClaro);
        campoQtde.setBackground(verdeClaroTransparente);
        campoQtde.setBounds(100, 250, 300, 40);
        getContentPane().setLayout(null);
        getContentPane().add(campoNome);
        getContentPane().add(campoPreco);
        getContentPane().add(campoQtde);
        
        JButton buttonSALVAR = new JButton("SALVAR");
        buttonSALVAR.setOpaque(true);
        buttonSALVAR.setForeground(new Color(37, 77, 50));
        buttonSALVAR.setFont(new Font("Arial", Font.BOLD, 18));
        buttonSALVAR.setBorderPainted(false);
        buttonSALVAR.setBackground(new Color(208, 219, 151));
        buttonSALVAR.setBounds(173, 305, 150, 40);
        getContentPane().add(buttonSALVAR);
        
        JLabel labelCadastro_1_1 = new JLabel("PREÇO (R$)");
        labelCadastro_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
        labelCadastro_1_1.setHorizontalAlignment(SwingConstants.LEFT);
        labelCadastro_1_1.setForeground(new Color(208, 219, 151));
        labelCadastro_1_1.setFont(new Font("Arial", Font.BOLD, 16));
        labelCadastro_1_1.setBounds(100, 150, 220, 29);
        getContentPane().add(labelCadastro_1_1);
        
        JLabel lblEditarProduto = new JLabel("EDITAR PRODUTO");
        lblEditarProduto.setVerticalAlignment(SwingConstants.TOP);
        lblEditarProduto.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditarProduto.setForeground(new Color(208, 219, 151));
        lblEditarProduto.setFont(new Font("Arial", Font.BOLD, 30));
        lblEditarProduto.setBounds(100, 35, 300, 29);
        getContentPane().add(lblEditarProduto);
        
        JLabel labelCadastro_1_1_1 = new JLabel("PRODUTO");
        labelCadastro_1_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
        labelCadastro_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
        labelCadastro_1_1_1.setForeground(new Color(208, 219, 151));
        labelCadastro_1_1_1.setFont(new Font("Arial", Font.BOLD, 16));
        labelCadastro_1_1_1.setBounds(100, 80, 220, 29);
        getContentPane().add(labelCadastro_1_1_1);
        
        JLabel labelCadastro_1_1_2 = new JLabel("QUANTIDADE");
        labelCadastro_1_1_2.setVerticalAlignment(SwingConstants.BOTTOM);
        labelCadastro_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
        labelCadastro_1_1_2.setForeground(new Color(208, 219, 151));
        labelCadastro_1_1_2.setFont(new Font("Arial", Font.BOLD, 16));
        labelCadastro_1_1_2.setBounds(100, 220, 220, 29);
        getContentPane().add(labelCadastro_1_1_2);
        buttonSALVAR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarEdicao();
            }
        });
    }

    private void salvarEdicao() {
        String nome = campoNome.getText().trim();
        String precoStr = campoPreco.getText().trim().replace(",", ".");
        String qtdeStr = campoQtde.getText().trim();

        if (nome.isEmpty() || precoStr.isEmpty() || qtdeStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        try {
            double preco = Double.parseDouble(precoStr);
            int qtde = Integer.parseInt(qtdeStr);

            produto.setProduto(nome);
            produto.setPreco(preco);
            produto.setQtde(qtde);

            ProdutoDAO dao = new ProdutoDAO();
            if (dao.atualizarProduto(produto)) {
                JOptionPane.showMessageDialog(this, "Produto atualizado!");
                callbackAtualizarTabela.run(); // Atualiza a tabela no painel principal
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar produto.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Preço ou quantidade inválidos.");
        }
    }
}
