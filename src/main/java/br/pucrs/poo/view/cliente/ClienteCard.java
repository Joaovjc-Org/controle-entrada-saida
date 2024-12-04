package br.pucrs.poo.view.cliente;
import br.pucrs.poo.controller.ClienteController;
import br.pucrs.poo.controller.ComandaController;
import br.pucrs.poo.dto.ClienteDTO;
import br.pucrs.poo.dto.ComandaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
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
    private final JButton criarComanda;
    private Optional<ClienteDTO> clienteMostradoEmTela;
    @Autowired
    private ClienteController clienteController;
    @Autowired
    private ComandaController comandaController;

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
        criarComanda = new JButton("criar Comanda");
        criarComanda.addActionListener(e -> {
            clienteMostradoEmTela.ifPresentOrElse(
                    this::criarPopUpDeComanda,
                    ()->JOptionPane.showMessageDialog(getRootPane().getComponent(1),
                            "Nenhum Cliente Selecionado",
                            "pesquisa", ERROR_MESSAGE)
            );
        });
        criarComanda.setEnabled(false);
        JButton pesquisar = new JButton("Pesquisar Cliente");
        pesquisar.setEnabled(false);
        textoPesquisa.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                pesquisar.setEnabled(true);
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                if (e.getDocument().getLength()==0) pesquisar.setEnabled(false);
            }
            @Override public void changedUpdate(DocumentEvent e) {}
        });
        pesquisar.addActionListener(e -> {
            criarTelaClienteVazia();
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
        criarPessoa.addActionListener(e -> criarTelaCadastroCliente());
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
        containerTelaCliente.setLayout(new BorderLayout(8,6));
        containerTelaCliente.setBackground(Color.CYAN);
        telaAtual.add(containerTelaCliente);
        painelLateral.add(opcoesMenuLateral);
        criarTelaClienteVazia();
        this.add(painelLateral, BorderLayout.WEST);
        this.add(telaAtual,SwingConstants.CENTER);
    }
    private void criarPopUpDeComanda(ClienteDTO clienteDTO) {
        criarComanda(clienteDTO)
                .ifPresentOrElse(
                        str -> JOptionPane.showMessageDialog(getRootPane().getComponent(1),
                                envolverEmCampoDeTexto("Codigo comanda: "+ str),
                                "comanda", JOptionPane.INFORMATION_MESSAGE),
                        () -> JOptionPane.showMessageDialog(getRootPane().getComponent(1),
                                "nao foi possivel gerar comanda",
                                "comanda", ERROR_MESSAGE)
                );
    }

    private JScrollPane envolverEmCampoDeTexto(String s) {
        JTextArea ta = new JTextArea(10, 10);
        ta.setText(s);
        ta.setWrapStyleWord(true);
        ta.setLineWrap(true);
        ta.setCaretPosition(0);
        ta.setEditable(false);
        return new JScrollPane(ta);
    }

    private Optional<String> criarComanda(ClienteDTO clienteDTO) {
        SwingWorker<Optional<String>, Void> sw = new SwingWorker<>() {
            @Override
            protected Optional<String> doInBackground() {
                return Optional.of(comandaController.criarComanda(clienteDTO.cpf()))
                        .map(ComandaDTO::codigoComanda);
            }
        };
        sw.run();
        try {
            return sw.get(15L, SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            JOptionPane.showMessageDialog(getRootPane().getComponent(1),
                    envolverEmCampoDeTexto(e.getMessage()),
                    "pesquisa", ERROR_MESSAGE);
            return Optional.empty();
        }
    }

    private boolean validarCpfValido(String text) {
        return text.matches("[0-9]+") && text.length() == 11;
    }
    public void criarTelaCliente(ClienteDTO cliente){
        containerTelaCliente.removeAll();
        JPanel painel = new JPanel();
        painel.setBorder(new LineBorder(BLACK,3));
        painel.setLayout(new GridLayout(2,4,6,8));
        JTextField labelNome = new JTextField("nome: ");
        labelNome.setEditable(false);
        painel.add(labelNome);
        JTextField nome = new JTextField(cliente.nome());
        nome.setEditable(false);
        painel.add(nome);
        JTextField labelCpf = new JTextField("cpf: ");
        labelCpf.setEditable(false);
        painel.add(labelCpf);
        JTextField cpf = new JTextField(cliente.cpf());
        cpf.setEditable(false);
        painel.add(cpf);
        JTextField labelTelefone = new JTextField("telefone: ");
        labelTelefone.setEditable(false);
        painel.add(labelTelefone);
        JTextField telefone = new JTextField(cliente.cpf());
        telefone.setEditable(false);
        painel.add(telefone);
        JTextField labelEmail = new JTextField("email: ");
        labelEmail.setEditable(false);
        painel.add(labelEmail);
        JTextField email = new JTextField(cliente.cpf());
        email.setEditable(false);
        painel.add(email);
        painel.setBackground(Color.PINK);
        clienteMostradoEmTela = Optional.of(cliente);
        criarComanda.setEnabled(true);
        containerTelaCliente.add(painel);
        containerTelaCliente.validate();
        containerTelaCliente.repaint();
    }
    public void criarTelaClienteVazia(){
        criarComanda.setEnabled(false);
        containerTelaCliente.removeAll();
        JPanel comp = new JPanel();
        comp.setBorder(new LineBorder(BLACK,3));
        comp.setLayout(new FlowLayout(FlowLayout.TRAILING));
        comp.setBackground(Color.DARK_GRAY);
        containerTelaCliente.add(comp);
        containerTelaCliente.validate();
        containerTelaCliente.repaint();
    }
    public void criarTelaCadastroCliente(){
        containerTelaCliente.removeAll();
        JPanel painel = new JPanel();
        painel.setBorder(new LineBorder(BLACK,3));
        painel.setLayout(new GridLayout(2,4,6,8));
        JTextField labelNome = new JTextField("nome: ");
        labelNome.setEditable(false);
        painel.add(labelNome);
        JTextField nome = new JTextField();
        painel.add(nome);
        JTextField labelCpf = new JTextField("cpf: ");
        labelCpf.setEditable(false);
        painel.add(labelCpf);
        JTextField cpf = new JTextField();
        painel.add(cpf);
        JTextField labelTelefone = new JTextField("telefone: ");
        labelTelefone.setEditable(false);
        painel.add(labelTelefone);
        JTextField telefone = new JTextField();
        painel.add(telefone);
        JTextField labelEmail = new JTextField("email: ");
        labelEmail.setEditable(false);
        painel.add(labelEmail);
        JTextField email = new JTextField();
        painel.add(email);
        painel.setBackground(Color.PINK);
        JPanel footer = new JPanel();
        footer.setBorder(new LineBorder(BLACK,3));
        footer.setLayout(new FlowLayout(FlowLayout.TRAILING));
        JButton buttonSalvar = new JButton("Salvar");
        buttonSalvar.addActionListener(e -> {
            List<String> strings = retornarCamposVazios(nome, cpf, telefone, email);
            if (!strings.isEmpty()){
                JOptionPane.showMessageDialog(getRootPane().getComponent(1),
                        "preencha o(s) campo(s) vazio(s): "+ String.join(", ", strings),
                        "pesquisa", ERROR_MESSAGE);
            }else {
                ClienteDTO clienteDTO = criarCliente(nome.getText(), cpf.getText(), telefone.getText(), email.getText());
                if (clienteDTO != null){
                    criarComanda.setEnabled(true);
                    clienteMostradoEmTela = Optional.of(clienteDTO);
                    criarTelaCliente(clienteDTO);
                }else {
                    criarTelaClienteVazia();
                }
            }
        });
        footer.add(buttonSalvar);
        JButton buttonCancelar = new JButton("Cancelar");
        buttonCancelar.addActionListener(e -> criarTelaClienteVazia());
        footer.add(buttonCancelar);
        containerTelaCliente.add(footer, BorderLayout.SOUTH);
        containerTelaCliente.add(painel);
        containerTelaCliente.validate();
        containerTelaCliente.repaint();
    }

    private List<String> retornarCamposVazios(JTextField nome, JTextField cpf, JTextField telefone, JTextField email) {
        List<String> list = new ArrayList<>(4);
        if (nome.getText().isBlank()) list.add("nome");
        if (cpf.getText().isBlank()) list.add("cpf");
        if (telefone.getText().isBlank()) list.add("telefone");
        if (email.getText().isBlank()) list.add("nome");
        return list;
    }

    public Optional<ClienteDTO> pesquisaCliente(String cpf) {
        SwingWorker<Optional<ClienteDTO>, Void> sw = new SwingWorker<>() {
            @Override
            protected Optional<ClienteDTO> doInBackground() throws Exception {
                return clienteController.buscarClientesPorCPF(cpf);
            }
        };
        sw.run();
        try {
            return sw.get(15L, SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            JOptionPane.showMessageDialog(getRootPane().getComponent(1),
                    envolverEmCampoDeTexto(e.getMessage()),
                    "pesquisa", ERROR_MESSAGE);
            return Optional.empty();
        }
    }
    public ClienteDTO criarCliente(String nome, String cpf, String telefone, String email) {
        SwingWorker<ClienteDTO, Void> sw = new SwingWorker<>() {
            @Override
            protected ClienteDTO doInBackground() {
                return clienteController.cadastrarCliente(nome,cpf,telefone,email);
            }
        };
        sw.run();
        try {
            return sw.get(15L, SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            JOptionPane.showMessageDialog(getRootPane().getComponent(1),
                    envolverEmCampoDeTexto(e.getMessage()),
                    "pesquisa", ERROR_MESSAGE);
            return null;
        }
    }
}