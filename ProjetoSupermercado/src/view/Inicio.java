package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.Frame;
import net.miginfocom.swing.MigLayout;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.SpringLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Inicio extends JPanel {

	private static final long serialVersionUID = 1L;

	public Inicio(Frame frame) {
		Color corFundo = new Color(0x25, 0x4D, 0x32);
		Color verdeClaro = new Color(208, 219, 151);
		
		setBackground(corFundo);
		setPreferredSize(new Dimension(900, 600));
		setLayout(null);
		
		JLabel labelSupermercado = new JLabel("SUPERMERCADO");
		labelSupermercado.setBounds(284, 150, 344, 47);
		labelSupermercado.setForeground(verdeClaro);
		labelSupermercado.setFont(new Font("Arial", Font.BOLD, 40));
		labelSupermercado.setHorizontalAlignment(SwingConstants.CENTER);
		add(labelSupermercado);
		
		JButton buttonCadastrar = new JButton("CADASTRAR");
		buttonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.mostrarCadastro();
			}
		});
		buttonCadastrar.setFont(new Font("Arial", Font.BOLD, 20));
		buttonCadastrar.setBounds(250, 300, 400, 50);
		buttonCadastrar.setBackground(verdeClaro);
		buttonCadastrar.setForeground(corFundo); 
		buttonCadastrar.setOpaque(true);
		buttonCadastrar.setBorderPainted(false);
		add(buttonCadastrar);
		
		JButton buttonEntrar = new JButton("ENTRAR");
		buttonEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.mostrarLogin();
			}
		});
		buttonEntrar.setFont(new Font("Arial", Font.BOLD, 20));
		buttonEntrar.setBounds(250, 400, 400, 50);
		buttonEntrar.setBackground(verdeClaro);
		buttonEntrar.setForeground(corFundo); 
		buttonEntrar.setOpaque(true);
		buttonEntrar.setBorderPainted(false);
		add(buttonEntrar);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/logout.png"));
		JButton buttonLogout = new JButton(icon);
		buttonLogout.setBounds(820, 530, 38, 40);
		buttonLogout.setBackground(corFundo);
		buttonLogout.setForeground(verdeClaro);
		buttonLogout.setOpaque(true);
		buttonLogout.setBorderPainted(false);
		
		buttonLogout.addActionListener(e -> {
		    frame.dispose();
		});

		add(buttonLogout);
	}
}
