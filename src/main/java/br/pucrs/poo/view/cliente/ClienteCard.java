package br.pucrs.poo.view.cliente;
import br.pucrs.poo.controller.ClienteController;
import br.pucrs.poo.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.awt.Color.BLACK;
import static java.awt.Color.ORANGE;
import static java.util.concurrent.TimeUnit.SECONDS;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

@Component
public class ClienteCard extends JPanel {
    private final JTextField textoPesquisa;
    private final JPanel containerTelaCliente;
    private Optional<ClienteDTO> clienteMostradoEmTela;
    @Autowired
    private ClienteController controller;

    public ClienteCard() {
        clienteMostradoEmTela = Optional.empty();
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

        textoPesquisa = new JTextField(5);
        textoPesquisa.setEditable(true);
        JButton pesquisar = new JButton("Pesquisar Cliente");
        pesquisar.setEnabled(false);
        textoPesquisa.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                pesquisar.setEnabled(true);
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                if (e.getLength()==0) pesquisar.setEnabled(false);
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                pesquisar.setEnabled(true);
            }
        });
        pesquisar.addActionListener(e -> {

            String text = textoPesquisa.getText();
            if(validarCpfValido(text)) {
                pesquisaCliente(text)
                        .ifPresentOrElse(this::criarTelaCliente,()-> JOptionPane.showMessageDialog(
                                getRootPane().getComponent(1),
                                "CPF nao encontrado",
                                "pesquisa", ERROR_MESSAGE));
            }
            else JOptionPane.showMessageDialog(getRootPane().getComponent(1),
                    "CPF nao e valido, digite somente os numeros",
                    "pesquisa", ERROR_MESSAGE);
        });

        JButton criarPessoa = new JButton("criar Cliente");
        JButton criarComanda = new JButton("criar Comanda");
        criarComanda.setEnabled(false);
        List<JButton> jButtons = List.of(pesquisar, criarPessoa, criarComanda);
        jButtons.forEach(opcoesMenuLateral::add);

        JPanel telaAtual = new JPanel(new BorderLayout(8,6));
        telaAtual.setBackground(Color.MAGENTA);
        telaAtual.setBorder(new LineBorder(BLACK,3));

        JPanel headerPesquisa = new JPanel();
        headerPesquisa.setBorder(new LineBorder(Color.BLACK, 3));
        headerPesquisa.setBackground(Color.GRAY);
        headerPesquisa.setLayout(new FlowLayout(FlowLayout.LEFT));
        headerPesquisa.add(new JLabel("Digite o CPF: "));
        headerPesquisa.add(textoPesquisa);

        telaAtual.add(headerPesquisa, BorderLayout.NORTH);
        containerTelaCliente = new JPanel();
        containerTelaCliente.setBorder(new LineBorder(BLACK,3));
        containerTelaCliente.setLayout(new FlowLayout(FlowLayout.LEADING,6,6));
        containerTelaCliente.setBackground(Color.CYAN);
        JPanel comp = new JPanel();
        comp.setBorder(new LineBorder(BLACK,3));
        comp.setLayout(new FlowLayout(FlowLayout.TRAILING));
        comp.setBackground(Color.DARK_GRAY);
        telaAtual.add(containerTelaCliente);
        painelLateral.add(opcoesMenuLateral);
        criarTelaClienteVazia();
        this.add(painelLateral, BorderLayout.WEST);
        this.add(telaAtual,SwingConstants.CENTER);
    }

    private boolean validarCpfValido(String text) {
        return text.matches("[0-9]+") && text.length() == 11;
    }
    public void criarTelaCliente(ClienteDTO cliente){
        containerTelaCliente.removeAll();
        JPanel painel = new JPanel();
        painel.setBorder(new LineBorder(BLACK,3));
        painel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        painel.setBackground(Color.PINK);
        containerTelaCliente.add(painel);
        containerTelaCliente.validate();
        containerTelaCliente.repaint();
    }
    public void criarTelaClienteVazia(){
        containerTelaCliente.removeAll();
        JPanel comp = new JPanel();
        comp.setBorder(new LineBorder(BLACK,3));
        comp.setLayout(new FlowLayout(FlowLayout.TRAILING));
        comp.setBackground(Color.DARK_GRAY);
        containerTelaCliente.add(comp);
        containerTelaCliente.validate();
        containerTelaCliente.repaint();
    }
    private JPanel telaVaziaCliente() {
        return new JPanel();
    }

    public Optional<ClienteDTO> pesquisaCliente(String cpf) {
        SwingWorker<Optional<ClienteDTO>, Void> sw = new SwingWorker<>() {
            @Override
            protected Optional<ClienteDTO> doInBackground() throws Exception {
                return controller.buscarClientesPorCPF(cpf);
            }
        };
        sw.run();
        try {
            return sw.get(15L, SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            JOptionPane.showMessageDialog(getRootPane().getComponent(1),
                    e.getMessage(),
                    "pesquisa", ERROR_MESSAGE);
            return Optional.empty();
        }
    }
}