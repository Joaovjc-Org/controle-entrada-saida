package br.pucrs.poo.view.folha;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;
import static java.awt.Color.BLACK;
import static java.awt.Color.ORANGE;
public class FolhaCard extends JPanel {
    public FolhaCard() {
        this.setLayout(new BorderLayout(8,6));
        this.setBackground(ORANGE);
        JPanel painelLateral = new JPanel();
        painelLateral.setBorder(new LineBorder(BLACK,3));
        painelLateral.setLayout(new FlowLayout(FlowLayout.LEADING,6,6));
        painelLateral.setBackground(Color.CYAN);

        JPanel opcoesMenuLateral = new JPanel();
        opcoesMenuLateral.setLayout(new GridLayout(20,1,5,5));
        opcoesMenuLateral.setBorder(new LineBorder(BLACK,3));
        opcoesMenuLateral.setBackground(Color.RED);

        java.util.List<JButton> jButtons = List.of(new JButton("Abrir Folha"), new JButton("Fechar Folha"),
                new JButton("Gerar Balancete"));
        jButtons.forEach(opcoesMenuLateral::add);

        JLabel telaAtual = new JLabel("Mostrar Balancete", SwingConstants.CENTER);
        telaAtual.setOpaque(true);
        telaAtual.setBorder(new LineBorder(BLACK,3));

        painelLateral.add(opcoesMenuLateral);
        this.add(painelLateral, BorderLayout.WEST);
        this.add(telaAtual);
    }
}
