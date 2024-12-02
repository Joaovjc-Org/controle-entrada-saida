package br.pucrs.poo.view.cliente;
import br.pucrs.poo.controller.ClienteController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;
import static java.awt.Color.BLACK;
import static java.awt.Color.ORANGE;
@Component
public class ClienteCard extends JPanel {
    @Autowired
    private ClienteController controller;
    public ClienteCard() {
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

        List<JButton> jButtons = List.of(new JButton("pesquisar"), new JButton("criar Comanda"));
        jButtons.forEach(opcoesMenuLateral::add);

        JLabel telaAtual = new JLabel("PesquisaUsuario", SwingConstants.CENTER);
        telaAtual.setOpaque(true);
        telaAtual.setBorder(new LineBorder(BLACK,3));

        painelLateral.add(opcoesMenuLateral);
        this.add(painelLateral, BorderLayout.WEST);
        this.add(telaAtual);
    }
    public JPanel pesquisaCliente() {
        return new JPanel();
    }
}