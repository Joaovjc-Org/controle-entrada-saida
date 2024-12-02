package br.pucrs.poo.view;

import javax.swing.*;
import javax.swing.border.LineBorder;

import org.springframework.stereotype.Component;

import java.awt.*;

import static java.awt.Color.*;
@Component
public class PlaceholderCard extends JPanel {
    public PlaceholderCard() {
        this.setLayout(new BorderLayout(8,6));
        this.setBackground(ORANGE);
        JPanel left = new JPanel();
        left.setBorder(new LineBorder(BLACK,3));
        left.setLayout(new FlowLayout(FlowLayout.LEADING,6,6));
        left.setBackground(Color.RED);

        JPanel opcoesMenuLateral = new JPanel();
        opcoesMenuLateral.setLayout(new GridLayout(10,1,5,5));
        opcoesMenuLateral.setBorder(new LineBorder(BLACK,3));
        opcoesMenuLateral.setBackground(Color.RED);

        JButton button = new JButton("Desativado");
        button.setEnabled(false);
        opcoesMenuLateral.add(button);

        JLabel telaAtual = new JLabel("Placeholder", SwingConstants.CENTER);
        telaAtual.setOpaque(true);
        telaAtual.setBorder(new LineBorder(BLACK,3));

        left.add(opcoesMenuLateral);
        this.add(left, BorderLayout.WEST);
        this.add(telaAtual);
    }
}
