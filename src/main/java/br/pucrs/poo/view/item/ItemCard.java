package br.pucrs.poo.view.item;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;
import static java.awt.Color.BLACK;
import static java.awt.Color.ORANGE;
@Component
public class ItemCard extends JPanel {
    public ItemCard() {
        this.setLayout(new BorderLayout(8,6));
        this.setBackground(ORANGE);
        JLabel telaAtual = new JLabel("Mostrar Itens", SwingConstants.CENTER);
        telaAtual.setOpaque(true);
        telaAtual.setBorder(new LineBorder(BLACK,3));

        this.add(telaAtual);
    }
}
