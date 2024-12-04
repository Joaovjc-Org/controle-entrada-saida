package br.pucrs.poo.view.folha;
import br.pucrs.poo.controller.FolhaController;
import br.pucrs.poo.dto.FolhaDTO;
import br.pucrs.poo.dto.GastoTotalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static br.pucrs.poo.view.utils.PopupUtils.envolverEmCampoDeTexto;
import static java.awt.Color.BLACK;
import static java.awt.Color.ORANGE;
import static java.util.concurrent.TimeUnit.SECONDS;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

@Component
public class FolhaCard extends JPanel {
    @Autowired
    private FolhaController folhaController;
    private Optional<FolhaDTO> folhaMostradaEmTela;
    private JPanel containerTelaFolha;
    private JPanel containerComandasDaFolha;
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
        JButton abrirFolha = new JButton("Abrir Folha");
        abrirFolha.addActionListener(e -> JOptionPane.showMessageDialog(getRootPane().getComponent(1),
                "Nao foi implementado Ainda",
                "pesquisa", ERROR_MESSAGE));
        JButton fecharFolha = new JButton("Fechar Folha");
        fecharFolha.addActionListener(e -> JOptionPane.showMessageDialog(getRootPane().getComponent(1),
                "Nao foi Implementado Ainda",
                "pesquisa", ERROR_MESSAGE));
        fecharFolha.setEnabled(false);
        JButton gerarBalancete = new JButton("Gerar Balancete");
        gerarBalancete.addActionListener(e -> gerarBalancete().ifPresent(this::criarTelaComandasDaFolha));
        gerarBalancete.setEnabled(false);
        JButton buttonRecuperarFolhaAtual = new JButton("Recuperar folha Atual");
        buttonRecuperarFolhaAtual.addActionListener(e -> {
            if(folhaMostradaEmTela.isEmpty()) {
                Optional<FolhaDTO> folhaDTO = recuperarFolhaDoDia();
                if (folhaDTO.isPresent()) {
                    folhaMostradaEmTela = folhaDTO;
                    criarTelaFolha(folhaDTO.get());
                    abrirFolha.setEnabled(false);
                    gerarBalancete.setEnabled(true);
                    fecharFolha.setEnabled(false);
                }
            }
        });
        java.util.List<JButton> jButtons = List.of(buttonRecuperarFolhaAtual, abrirFolha, fecharFolha,
                gerarBalancete);
        jButtons.forEach(opcoesMenuLateral::add);

        JPanel containerGrid = new JPanel();
        containerGrid.setBorder(new LineBorder(BLACK,3));
        containerGrid.setLayout(new GridLayout(1,2,6,8));
        containerTelaFolha = new JPanel();
        containerTelaFolha.setBorder(new LineBorder(BLACK,3));
        containerTelaFolha.setLayout(new BorderLayout(8,6));
        containerTelaFolha.setBackground(Color.CYAN);

        containerComandasDaFolha = new JPanel();
        containerComandasDaFolha.setBorder(new LineBorder(BLACK,3));
        containerComandasDaFolha.setLayout(new BorderLayout(8,6));
        containerComandasDaFolha.setBackground(Color.CYAN);
        containerGrid.add(containerTelaFolha, BorderLayout.WEST);
        containerGrid.add(containerComandasDaFolha, BorderLayout.EAST);
        criarTelaFolhaVazia();
        criarTelaComandasDaFolhaVazia();
        painelLateral.add(opcoesMenuLateral);
        this.add(painelLateral, BorderLayout.WEST);
        this.add(containerGrid,BorderLayout.CENTER);
    }
    public void criarTelaFolhaVazia(){
        folhaMostradaEmTela = Optional.empty();
        containerTelaFolha.removeAll();
        JPanel comp = new JPanel();
        comp.setBorder(new LineBorder(BLACK,3));
        comp.setLayout(new FlowLayout(FlowLayout.TRAILING));
        comp.setBackground(Color.DARK_GRAY);
        containerTelaFolha.add(comp);
        containerTelaFolha.validate();
        containerTelaFolha.repaint();
    }
    public void criarTelaComandasDaFolhaVazia(){
        containerComandasDaFolha.removeAll();
        JPanel comp = new JPanel();
        comp.setBorder(new LineBorder(BLACK,3));
        comp.setLayout(new FlowLayout(FlowLayout.TRAILING));
        comp.setBackground(Color.DARK_GRAY);
        JLabel semComandas = new JLabel("Sem comandas");
        semComandas.setOpaque(true);
        semComandas.setBorder(new LineBorder(BLACK,3));
        comp.add(semComandas);
        containerComandasDaFolha.add(comp);
        containerComandasDaFolha.validate();
        containerComandasDaFolha.repaint();
    }
    public void criarTelaFolha(FolhaDTO folha){
        containerTelaFolha.removeAll();
        JPanel painel = new JPanel();
        painel.setBorder(new LineBorder(BLACK,3));
        painel.setLayout(new GridLayout(2,1,6,8));
        JTextField labelNome = new JTextField("Data abertura Folha: ");
        labelNome.setEditable(false);
        painel.add(labelNome);
        JTextField nome = new JTextField(folha.dataAbertura());
        nome.setEditable(false);
        painel.add(nome);
        folhaMostradaEmTela = Optional.of(folha);
        containerTelaFolha.add(painel);
        containerTelaFolha.validate();
        containerTelaFolha.repaint();
    }
    public void criarTelaComandasDaFolha(List<GastoTotalDTO> gastos){
        containerComandasDaFolha.removeAll();
        JPanel painel = new JPanel();
        painel.setBorder(new LineBorder(BLACK,3));
        painel.setLayout(new BorderLayout(8,6));
        JPanel header = new JPanel();
        header.setBorder(new LineBorder(Color.BLACK, 3));
        header.setBackground(Color.ORANGE);
        header.setLayout(new FlowLayout(FlowLayout.CENTER));
        JTextField text = new JTextField("Comandas: ");
        text.setEditable(false);
        header.add(text);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout(8,6));
        jPanel.setBorder(new LineBorder(BLACK,3));
        Vector<Vector<?>> collect = gastos.stream()
                .map(gasto -> Stream.of((Object)gasto.nomeCliente(),gasto.codigoComanda(),gasto.gastoTotal(),gasto.fechada()?"FECHADA":"EM ABERTO").collect(Collectors.toCollection(Vector::new)))
                .collect(Collectors.toCollection(Vector::new));
        JTable table = new JTable(collect, (Vector<?>) Stream.of("Nome Cliente","Codigo Comanda","Gasto total","Estado da Comanda").collect(Collectors.toCollection(Vector::new)));
        JScrollPane scrollPane = new JScrollPane();
        table.setFillsViewportHeight(true);
        scrollPane.setViewportView(table);
        jPanel.add(scrollPane);
        painel.add(header,BorderLayout.NORTH);
        painel.add(jPanel);
        containerComandasDaFolha.add(painel);
        containerComandasDaFolha.validate();
        containerComandasDaFolha.repaint();
    }
    private Optional<FolhaDTO> recuperarFolhaDoDia() {
        SwingWorker<FolhaDTO, Void> sw = new SwingWorker<>() {
            @Override
            protected FolhaDTO doInBackground() {
                return folhaController.recuperarFolhaDoDia();
            }
        };
        sw.run();
        try {
            return Optional.of(sw.get(15L, SECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            JOptionPane.showMessageDialog(getRootPane().getComponent(1),
                    envolverEmCampoDeTexto(e.getMessage()),
                    "pesquisa", ERROR_MESSAGE);
            return Optional.empty();
        }
    }
    private Optional<List<GastoTotalDTO>> gerarBalancete() {
        SwingWorker<List<GastoTotalDTO>, Void> sw = new SwingWorker<>() {
            @Override
            protected List<GastoTotalDTO> doInBackground() {
                return folhaController.gerarBalanceteDiario();
            }
        };
        sw.run();
        try {
            return Optional.of(sw.get(20L, SECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            JOptionPane.showMessageDialog(getRootPane().getComponent(1),
                    envolverEmCampoDeTexto(e.getMessage()),
                    "pesquisa", ERROR_MESSAGE);
            return Optional.empty();
        }
    }
}
