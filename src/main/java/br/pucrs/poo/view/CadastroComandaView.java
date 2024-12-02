import java.util.Scanner;
import br.pucrs.poo.dto.ComandaDTO;

public class CadastroComandaView {
    private Scanner scanner = new Scanner(System.in);

    public void criarComanda() {
        System.out.println("\n=== Cadastro de Comanda ===");

        System.out.print("Digite o ID do cliente: ");
        Long clienteId = scanner.nextLong();

        System.out.print("Digite o ID da folha associada: ");
        Long folhaId = scanner.nextLong();

        ComandaDTO novaComanda = new ComandaDTO(null, null, null, null, null, clienteId, folhaId);

        try {
            ComandaDTO comandaCriada = comandaService.criarComanda(novaComanda);
            System.out.println("Comanda criada com sucesso!");
            System.out.println("Código da Comanda: " + comandaCriada.getCodigoComanda());
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void adicionarDebito() {
        System.out.println("\n=== Adicionar Débito à Comanda ===");

        System.out.print("Digite o código da comanda: ");
        String codigoComanda = scanner.next();

        System.out.print("Digite a descrição do débito: ");
        String descricao = scanner.next();

        System.out.print("Digite o valor do débito: ");
        double valor = scanner.nextDouble();

        Gasto novoGasto = new Gasto(descricao, valor);

        try {
            comandaService.adicionarDebito(codigoComanda, novoGasto);
            System.out.println("Débito adicionado com sucesso!");
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
