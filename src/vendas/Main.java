package vendas;

// Importando todos os nossos componentes
// (Usando '*' para importar tudo dos pacotes)
import vendas.clientes.*;
import vendas.produtos.*;

/*
 * CLASSE PRINCIPAL (Entry Point)
 *
 * A responsabilidade desta classe (no nosso MVP) é:
 * 1. Inicializar todos os serviços (ProdutoService, ClienteService, VendasService).
 * 2. Simular o "Modo Gestor" (cadastrando e gerenciando produtos e clientes).
 * 3. Simular o "Modo Cliente" (realizando vendas para testar o sistema).
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== INICIANDO SISTEMA DO MINI MERCADO ===");

        // --- 1. INICIALIZAÇÃO DOS SERVIÇOS ---
        // Criamos as instâncias que gerenciam nossos "bancos de dados" em memória.
        IProdutoService produtoService = new ProdutoService();
        IClienteService clienteService = new ClienteService();
        IVendasService vendasService = new VendasService();

        // --- 2. SIMULAÇÃO DO MODO GESTOR ---
        // O gestor cadastra produtos, clientes, edita, lista e consulta.
        simularModoGestor(produtoService, clienteService);

        // --- 3. SIMULAÇÃO DO MODO CLIENTE ---
        // O cliente realiza as compras (testando baixa de estoque e descontos).
        simularModoCliente(produtoService, clienteService, vendasService);

        System.out.println("\n=== SISTEMA ENCERRADO ===");
    }

    /**
     * Simula as ações de um Gestor (Joana ou João).
     * Inclui agora o CRUD completo (simulado) de Produtos e Clientes.
     */
    private static void simularModoGestor(IProdutoService produtoService, IClienteService clienteService) {
        System.out.println("\n--- MODO GESTOR ATIVADO ---");

        // --- SEÇÃO DE PRODUTOS ---

        System.out.println("\n[GESTOR] 1. Cadastrando produtos...");
        produtoService.cadastrarProduto(new Produto(1, "Arroz 5kg", 25.00, 50));
        produtoService.cadastrarProduto(new Produto(2, "Feijão 1kg", 8.00, 50));
        produtoService.cadastrarProduto(new Produto(3, "Litro de Leite", 5.00, 100));
        produtoService.cadastrarProduto(new Produto(4, "Sabonete", 2.00, 200));

        System.out.println("\n[GESTOR] 2. Listando todos os produtos cadastrados...");
        for (Produto p : produtoService.listarTodosProdutos()) {
            System.out.printf("  ID: %d | Produto: %-15s | Preço: R$ %.2f | Estoque: %d\n",
                    p.getId(), p.getNome(), p.getPrecoUnitario(), p.getQuantidadeEmEstoque());
        }

        System.out.println("\n[GESTOR] 3. Consultando produto individual (ID 3)...");
        Produto produtoConsultado = produtoService.buscarProdutoPorId(3);
        if (produtoConsultado != null) {
            System.out.println("  Produto encontrado: " + produtoConsultado.getNome() + " (Preço: R$ " + produtoConsultado.getPrecoUnitario() + ")");
        } else {
            System.out.println("  Produto com ID 3 não encontrado.");
        }

        System.out.println("\n[GESTOR] 4. Editando produtos...");
        if (produtoConsultado != null) {
            System.out.println("  ...Alterando preço do Leite (ID 3) de R$ 5.00 para R$ 5.50");
            produtoConsultado.setPrecoUnitario(5.50);
        }
        System.out.println("  ...Adicionando +30 unidades ao Leite (ID 3)");
        produtoService.adicionarEstoque(3, 30);

        System.out.println("\n[GESTOR] 5. Verificando as edições (consultando ID 3 novamente)...");
        Produto produtoEditado = produtoService.buscarProdutoPorId(3);
        if (produtoEditado != null) {
            System.out.printf("  Produto: %s | Novo Preço: R$ %.2f | Novo Estoque: %d\n",
                    produtoEditado.getNome(), produtoEditado.getPrecoUnitario(), produtoEditado.getQuantidadeEmEstoque());
        } else {
            System.out.println("  [ERRO] Produto com ID 3 não encontrado.");
        }

        // --- SEÇÃO DE CLIENTES (FIDELIDADE) ---

        System.out.println("\n[GESTOR] 6. Cadastrando clientes no programa de fidelidade...");
        clienteService.cadastrarCliente(new ClientePessoaFisica(
                1, "Breno (Cliente Ouro)", "9999-8888", Categoria.CLIENTEOURO, "111.222.333-44"
        ));
        clienteService.cadastrarCliente(new ClientePessoaJuridica(
                2, "Padaria da Esquina (Platina)", "3333-4444", Categoria.CLIENTEPLATINA, "12.345.678/0001-99"
        ));
        clienteService.cadastrarCliente(new ClientePessoaFisica(
                3, "Joana (Cliente Comum)", "5555-1111", Categoria.CLIENTECOMUM, "555.666.777-88"
        ));

        System.out.println("\n[GESTOR] 7. Listando todos os clientes cadastrados...");
        // Usamos o 'listarClientes()' que retorna 'Cliente[]'
        for (Cliente c : clienteService.listarClientes()) {
            System.out.printf("  ID: %d | Nome: %-28s | Categoria: %s\n",
                    c.getId(), c.getNome(), c.getCategoria());
        }

        System.out.println("\n[GESTOR] 8. Consultando cliente individual (ID 1)...");
        Cliente clienteConsultado = clienteService.consultarCliente(1);
        if (clienteConsultado != null) {
            System.out.println("  Cliente encontrado: " + clienteConsultado.getNome() + " (Telefone: " + clienteConsultado.getTelefone() + ")");
        } else {
            System.out.println("  Cliente com ID 1 não encontrado.");
        }

        System.out.println("\n[GESTOR] 9. Editando cliente (Promovendo Joana ID 3 para OURO)...");
        Cliente clienteParaEditar = clienteService.consultarCliente(3); // Busca a Joana
        if (clienteParaEditar != null) {
            System.out.println("  ...Alterando categoria de " + clienteParaEditar.getNome() +
                    " de " + clienteParaEditar.getCategoria() +
                    " para " + Categoria.CLIENTEOURO);
            // Usamos o 'setter' da própria entidade Cliente
            clienteParaEditar.setCategoria(Categoria.CLIENTEOURO);
            // (Assim como no produto, a alteração é refletida no repositório)
        } else {
            System.out.println("  Cliente com ID 3 não encontrado para edição.");
        }

        System.out.println("\n[GESTOR] 10. Verificando a edição (listando clientes novamente)...");
        for (Cliente c : clienteService.listarClientes()) {
            System.out.printf("  ID: %d | Nome: %-28s | Categoria: %s\n",
                    c.getId(), c.getNome(), c.getCategoria());
        }

        System.out.println("--- FIM MODO GESTOR ---");
    }

    /**
     * Simula as ações de um Cliente (realizando compras).
     * (Este método testa Venda, Baixa de Estoque e Desconto)
     */
    private static void simularModoCliente(IProdutoService produtoService, IClienteService clienteService, IVendasService vendasService) {
        System.out.println("\n--- MODO CLIENTE ATIVADO ---");

        // ***************************************************************
        // SIMULAÇÃO 1: Venda para Cliente Cadastrado (com Desconto)
        // ***************************************************************
        System.out.println("\n[CLIENTE] Simulação 1: Venda para Breno (Ouro)");

        Cliente clienteBreno = clienteService.consultarCliente(1);

        // Usando o construtor 'Venda(Cliente c)' que gera o ID automático
        Venda venda1 = new Venda(clienteBreno);
        System.out.println("Venda #" + venda1.getId() + " iniciada para: " + venda1.getCliente().getNome());

        // Adicionando 2x Arroz (ID 1)
        Produto pArroz = produtoService.buscarProdutoPorId(1);
        int qtdArroz = 2;
        if (pArroz != null && produtoService.darBaixaEstoque(pArroz.getId(), qtdArroz)) {
            venda1.adicionarItem(new ItensVenda(pArroz.getId(), pArroz.getNome(), qtdArroz, pArroz.getPrecoUnitario()));
            System.out.println("Item adicionado: 2x " + pArroz.getNome());
        }

        // Adicionando 5x Leite (ID 3) - (Preço já deve estar R$ 5.50)
        Produto pLeite = produtoService.buscarProdutoPorId(3);
        int qtdLeite = 5;
        if (pLeite != null && produtoService.darBaixaEstoque(pLeite.getId(), qtdLeite)) {
            venda1.adicionarItem(new ItensVenda(pLeite.getId(), pLeite.getNome(), qtdLeite, pLeite.getPrecoUnitario()));
            System.out.println("Item adicionado: 5x " + pLeite.getNome());
        }

        // Finalizar a Venda
        double subtotalV1 = venda1.getSubTotal();

        // Calcular Desconto (Usando nossa classe 'DescontoFidelidade')
        double precoFinalV1 = DescontoFidelidade.calcularPrecoFinal(venda1.getCliente(), subtotalV1);
        double descontoV1 = subtotalV1 - precoFinalV1;
        venda1.setDesconto(descontoV1); // Armazena o desconto na Venda

        // Registrar a Venda no Serviço
        vendasService.registrarVenda(venda1);

        // Imprimir Recibo
        System.out.println("--- RECIBO (Venda #" + venda1.getId() + ") ---");
        System.out.println("Cliente: " + venda1.getCliente().getNome() + " (Categoria: " + venda1.getCliente().getCategoria() + ")");
        System.out.printf("Subtotal: R$ %.2f\n", subtotalV1);
        System.out.printf("Desconto Fidelidade: R$ -%.2f\n", descontoV1);
        System.out.printf("TOTAL A PAGAR: R$ %.2f\n", venda1.getValorTotal());

        // ***************************************************************
        // SIMULAÇÃO 2: Venda Anônima (Sem Desconto)
        // ***************************************************************
        System.out.println("\n[CLIENTE] Simulação 2: Venda Anônima");

        // Regra: "CPF/CNPJ opcional". Passamos 'null' como cliente.
        Venda venda2 = new Venda(null);
        System.out.println("Venda #" + venda2.getId() + " iniciada para: (Cliente não informado)");

        // Adiciona 1x Sabonete (ID 4)
        Produto pSabonete = produtoService.buscarProdutoPorId(4);
        int qtdSabonete = 1;
        if (pSabonete != null && produtoService.darBaixaEstoque(pSabonete.getId(), qtdSabonete)) {
            venda2.adicionarItem(new ItensVenda(pSabonete.getId(), pSabonete.getNome(), qtdSabonete, pSabonete.getPrecoUnitario()));
            System.out.println("Item adicionado: 1x " + pSabonete.getNome());
        }

        // Finalizar Venda
        double subtotalV2 = venda2.getSubTotal();
        double precoFinalV2 = DescontoFidelidade.calcularPrecoFinal(venda2.getCliente(), subtotalV2);
        double descontoV2 = subtotalV2 - precoFinalV2; // (Será 0.0)
        venda2.setDesconto(descontoV2);
        vendasService.registrarVenda(venda2);

        // Imprimir Recibo
        System.out.println("--- RECIBO (Venda #" + venda2.getId() + ") ---");
        System.out.println("Cliente: (Cliente não informado)");
        System.out.printf("Subtotal: R$ %.2f\n", subtotalV2);
        System.out.printf("Desconto Fidelidade: R$ -%.2f\n", descontoV2);
        System.out.printf("TOTAL A PAGAR: R$ %.2f\n", venda2.getValorTotal());

        System.out.println("\n--- FIM MODO CLIENTE ---");
    }
}