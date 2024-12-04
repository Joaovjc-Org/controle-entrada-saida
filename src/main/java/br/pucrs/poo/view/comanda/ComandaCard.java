package br.pucrs.poo.view.comanda;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static java.awt.Color.BLACK;
import static java.awt.Color.ORANGE;
@Component
public class ComandaCard extends JPanel {
    public ComandaCard() {
        this.setLayout(new BorderLayout(8,6));
        this.setBackground(ORANGE);
        JLabel telaAtual = new JLabel("Comandas", SwingConstants.CENTER);
        telaAtual.setOpaque(true);
        telaAtual.setBorder(new LineBorder(BLACK,3));

        this.add(telaAtual);
    }
}
