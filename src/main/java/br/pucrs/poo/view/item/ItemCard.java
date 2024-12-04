package br.pucrs.poo.view.item;

import br.pucrs.poo.dto.ItemDTO;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static br.pucrs.poo.view.utils.PopupUtils.envolverEmCampoDeTexto;
import static java.awt.Color.BLACK;
import static java.awt.Color.ORANGE;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

@Component
public class ItemCard extends JPanel {
    private final ItemWorker itemWorker;
    public ItemCard(ItemWorker itemWorker) {
        this.itemWorker = itemWorker;
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

        JButton buttonItem = new JButton("Itens");
        buttonItem.addActionListener(e -> {
            try{
                this.itemWorker.run();
                List<ItemDTO> itens = this.itemWorker.get(15, TimeUnit.SECONDS);
                JPanel jPanel = new JPanel();
                jPanel.setLayout(new BorderLayout(8,6));
                Vector<Vector<Object>> collect = itens.stream()
                        .map(item -> Stream.of(item.nome(),(Object) item.preco().toString()).collect(Collectors.toCollection(Vector::new)))
                        .collect(Collectors.toCollection(Vector::new));
                JTable table = new JTable(collect, (Vector<?>) Stream.of("Nome item","Preço").collect(Collectors.toCollection(Vector::new)));
                JScrollPane scrollPane = new JScrollPane();
                table.setFillsViewportHeight(true);
                scrollPane.setViewportView(table);
                jPanel.add(scrollPane);
                JOptionPane.showMessageDialog(getRootPane().getComponent(1),
                        jPanel,
                        "itens", INFORMATION_MESSAGE);
            } catch (ExecutionException | InterruptedException | TimeoutException ex) {
                JOptionPane.showMessageDialog(getRootPane().getComponent(1),
                        envolverEmCampoDeTexto("nao foi possivel recuperar os itens cadastrados no sistema, erro: "
                                + ex.getMessage()),
                        "item", ERROR_MESSAGE);
            }

        });
        opcoesMenuLateral.add(buttonItem);

        JPanel telaAtual = new JPanel(new BorderLayout(6,8));
        telaAtual.setOpaque(true);
        telaAtual.setBorder(new LineBorder(BLACK,3));

        painelLateral.add(opcoesMenuLateral);
        this.add(painelLateral, BorderLayout.WEST);
        this.add(telaAtual);
    }
}
