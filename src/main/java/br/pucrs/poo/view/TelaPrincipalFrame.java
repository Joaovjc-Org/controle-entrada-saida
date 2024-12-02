package br.pucrs.poo.view;
import br.pucrs.poo.view.cliente.ClienteCard;
import br.pucrs.poo.view.folha.FolhaCard;
import br.pucrs.poo.view.item.ItemCard;
import org.springframework.stereotype.Component;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static br.pucrs.poo.view.HeaderPanelType.*;
import static java.awt.Color.*;
@Component
public class TelaPrincipalFrame extends JFrame{
    private HeaderPanelType telaAtual;
    public TelaPrincipalFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setWindowSizeDynamic();

        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout(8,6));
        mainContainer.setBackground(YELLOW);

        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.GREEN));

        JPanel cards = new JPanel(new CardLayout(5,5));
        cards.setBackground(BLUE);
        cards.add(new PlaceholderCard(), PLACEHOLDER.name());
        cards.add(new ClienteCard(), CLIENTE.name());
        cards.add(new FolhaCard(), FOLHA.name());
        cards.add(new ItemCard(), ITEM.name());

        JPanel headerOpcoes = new JPanel();
        headerOpcoes.setBorder(new LineBorder(Color.BLACK, 3));
        headerOpcoes.setBackground(Color.ORANGE);
        headerOpcoes.setLayout(new FlowLayout(5));
        headerOpcoes.add(new JLabel("Selecione uma Opcao: "));
        JButton buttonCliente = new JButton("Cliente");
        buttonCliente.addActionListener(e -> {
            if (this.telaAtual != CLIENTE) {
                CardLayout cl = ((CardLayout) cards.getLayout());
                cl.show(cards, CLIENTE.name());
                cards.revalidate();
                cards.repaint();
                telaAtual = CLIENTE;
            }
        });
        headerOpcoes.add(buttonCliente);
        JButton buttonFolha = new JButton("Folha");
        buttonFolha.addActionListener(e -> {
            if (this.telaAtual != FOLHA){
                CardLayout cl = ((CardLayout) cards.getLayout());
                cl.show(cards, FOLHA.name());
                cards.revalidate();
                cards.repaint();
                telaAtual = FOLHA;
            }
        });
        headerOpcoes.add(buttonFolha);
        JButton buttonItem = new JButton("Itens");
        buttonItem.addActionListener(e -> {
            if (this.telaAtual != ITEM){
                CardLayout cl = ((CardLayout) cards.getLayout());
                cl.show(cards, ITEM.name());
                cards.revalidate();
                cards.repaint();
                telaAtual = ITEM;
            }
        });
        headerOpcoes.add(buttonItem);
        mainContainer.add(headerOpcoes, BorderLayout.NORTH);
        mainContainer.add(cards);
    }

    private void setWindowSizeDynamic() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int originalHeight = dimension.height;
        int originalWidth = dimension.width;
        double height = originalHeight * (5D / 6D);
        this.setSize( originalWidth/2 , (int) Math.round(height));
        this.setLocationRelativeTo(null);
    }
    public static void startGUI(String[] args) {
        TelaPrincipalFrame framePrincipal = new TelaPrincipalFrame();
        SwingUtilities.invokeLater(() -> framePrincipal.setVisible(true));
    }
}
