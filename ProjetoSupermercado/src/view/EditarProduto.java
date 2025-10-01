package view;

import javax.swing.*;

import controller.ProdutoController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Produto;

public class EditarProduto extends JDialog {

    private JTextField campoNome;
    private JTextField campoPreco;
    private JTextField campoQtde;

    private Produto produto;
    private Runnable callbackAtualizarTabela;
    
    private ProdutoController controller;


    public EditarProduto(JFrame parent, Produto produtoSelecionado, Runnable callbackAtualizar, ProdutoController controller) {
    	super(parent, "Editar Produto", true);
        this.controller = controller;
        this.produto = produtoSelecionado;
        this.callbackAtualizarTabela = callbackAtualizar;
        
        Color corFundo = new Color(0x25, 0x4D, 0x32);
		Color verdeClaro = new Color(208, 219, 151);
		Color verdeClaroTransparente = new Color(122, 148, 101);
		
        getContentPane().setBackground(corFundo);
        this.produto = produtoSelecionado;
        this.callbackAtualizarTabela = callbackAtualizar;
        setSize(500, 400);
        setLocationRelativeTo(parent);
        
        Font novaFonte = new Font("Arial", Font.BOLD, 18); 

        campoNome = new JTextField(produto.getProduto());
        campoNome.setFont(novaFonte);
        campoNome.setForeground(corFundo);
        campoNome.setBackground(verdeClaroTransparente);
        campoNome.setBounds(100, 110, 300, 40);
        campoNome.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        
        campoPreco = new JTextField(String.valueOf(produto.getPreco()));
        campoPreco.setFont(novaFonte);
        campoPreco.setForeground(corFundo);
        campoPreco.setBackground(verdeClaroTransparente);
        campoPreco.setBounds(100, 180, 300, 40);
        campoPreco.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        
        campoQtde = new JTextField(String.valueOf(produto.getQtde()));
        campoQtde.setFont(novaFonte);
        campoQtde.setForeground(corFundo);
        campoQtde.setBackground(verdeClaroTransparente);
        campoQtde.setBounds(100, 250, 300, 40);
        campoQtde.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        
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

            if (controller.atualizarProduto(produto)) {
                JOptionPane.showMessageDialog(this, "Produto atualizado!");
                callbackAtualizarTabela.run();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar produto.");
            }


        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Preço ou quantidade inválidos.");
        }
    }
}
