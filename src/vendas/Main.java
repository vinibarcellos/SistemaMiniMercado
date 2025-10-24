package vendas;

import vendas.clientes.*;
import vendas.produtos.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println(" BEM-VINDO AO SISTEMA - MINI MERCADO 2 AMIGOS");
        System.out.println("=========================================");
        System.out.println("Carregando serviços...");

        IProdutoService produtoService = new ProdutoService();
        IClienteService clienteService = new ClienteService();
        IVendasService vendasService = new VendasService();

        System.out.println("Serviços prontos. Executando rotinas...");
        System.out.println("-----------------------------------------");

        executarRotinasDoGestor(produtoService, clienteService);

        System.out.println("-----------------------------------------");

        executarRotinasDeVenda(produtoService, clienteService, vendasService);

        System.out.println("\n=========================================");
        System.out.println("   SISTEMA 2 AMIGOS ENCERRADO");
        System.out.println("=========================================");
    }

    private static void executarRotinasDoGestor(IProdutoService produtoService, IClienteService clienteService) {
        System.out.println("\n--- ACESSANDO PAINEL DE GESTÃO ---");

        System.out.println("\n[GESTOR] 1. Cadastrando produtos no inventário...");

        Produto p1 = new Produto(1, "Arroz 5kg", 25.00, 50);
        produtoService.cadastrarProduto(p1);
        System.out.println("  Produto cadastrado: " + p1.getNome() + " (ID: " + p1.getId() + ")");

        Produto p2 = new Produto(2, "Feijão 1kg", 8.00, 50);
        produtoService.cadastrarProduto(p2);
        System.out.println("  Produto cadastrado: " + p2.getNome() + " (ID: " + p2.getId() + ")");

        Produto p3 = new Produto(3, "Litro de Leite", 5.00, 100);
        produtoService.cadastrarProduto(p3);
        System.out.println("  Produto cadastrado: " + p3.getNome() + " (ID: " + p3.getId() + ")");

        Produto p4 = new Produto(4, "Sabonete", 2.00, 200);
        produtoService.cadastrarProduto(p4);
        System.out.println("  Produto cadastrado: " + p4.getNome() + " (ID: " + p4.getId() + ")");

        System.out.println("\n[GESTOR] 2. Listando inventário (pós-cadastro)...");
        for (Produto p : produtoService.listarTodosProdutos()) {
            System.out.printf("  [ID %d] %-15s | R$ %5.2f | Estoque: %d\n",
                    p.getId(), p.getNome(), p.getPrecoUnitario(), p.getQuantidadeEmEstoque());
        }

        System.out.println("\n[GESTOR] 3. Consultando produto ID 3...");
        Produto produtoConsultado = produtoService.buscarProdutoPorId(3);
        if (produtoConsultado != null) {
            System.out.println("  Consulta: Produto '" + produtoConsultado.getNome() + "', Preço: R$ " + produtoConsultado.getPrecoUnitario());
        }

        System.out.println("\n[GESTOR] 4. Editando produtos...");
        if (produtoConsultado != null) {
            System.out.println("  Editando: '" + produtoConsultado.getNome() + "' (ID 3), Novo Preço: R$ 5.50");
            produtoConsultado.setPrecoUnitario(5.50);
        }
        System.out.println("  Editando: 'Litro de Leite' (ID 3), Adicionando +30 ao estoque.");
        produtoService.adicionarEstoque(3, 30);

        System.out.println("\n[GESTOR] 5. Verificando alterações no ID 3...");
        Produto produtoEditado = produtoService.buscarProdutoPorId(3);
        if (produtoEditado != null) {
            System.out.printf("  OK: '%s', Preço: R$ %.2f, Estoque: %d\n",
                    produtoEditado.getNome(), produtoEditado.getPrecoUnitario(), produtoEditado.getQuantidadeEmEstoque());
        }

        System.out.println("\n[GESTOR] 6. Registrando clientes (Fidelidade)...");


        Cliente c1 = new ClientePessoaFisica(
                1, "Breno (Cliente Ouro)", "9999-8888", Categoria.CLIENTEOURO, "111.222.333-44"
        );
        clienteService.cadastrarCliente(c1);
        System.out.println("  Cliente registrado: " + c1.getNome());

        Cliente c2 = new ClientePessoaJuridica(
                2, "Padaria São João (Platina)", "3333-4444", Categoria.CLIENTEPLATINA, "12.345.678/0001-99"
        );
        clienteService.cadastrarCliente(c2);
        System.out.println("  Cliente registrado: " + c2.getNome());


        Cliente c3 = new ClientePessoaFisica(
                3, "Maria (Cliente Comum)", "5555-1111", Categoria.CLIENTECOMUM, "555.666.777-88"
        );
        clienteService.cadastrarCliente(c3);
        System.out.println("  Cliente registrado: " + c3.getNome());

        System.out.println("\n[GESTOR] 7. Listando clientes (pós-cadastro)...");
        for (Cliente c : clienteService.listarClientes()) {
            System.out.printf("  [ID %d] %-28s | Categoria: %s\n",
                    c.getId(), c.getNome(), c.getCategoria());
        }

        System.out.println("\n[GESTOR] 8. Consultando cliente ID 1...");
        Cliente clienteConsultado = clienteService.consultarCliente(1);
        if (clienteConsultado != null) {
            System.out.println("  Consulta: " + clienteConsultado.getNome() + " (Telefone: " + clienteConsultado.getTelefone() + ")");
        }

        System.out.println("\n[GESTOR] 9. Editando cliente (Promovendo ID 3)...");
        Cliente clienteParaEditar = clienteService.consultarCliente(3); // Busca a Maria
        if (clienteParaEditar != null) {
            System.out.println("  Promovendo: '" + clienteParaEditar.getNome() +
                    "' de " + clienteParaEditar.getCategoria() +
                    " para " + Categoria.CLIENTEOURO);
            clienteParaEditar.setCategoria(Categoria.CLIENTEOURO);
        }

        System.out.println("\n[GESTOR] 10. Listando clientes (pós-edição)...");
        for (Cliente c : clienteService.listarClientes()) {
            System.out.printf("  [ID %d] %-28s | Categoria: %s\n",
                    c.getId(), c.getNome(), c.getCategoria());
        }

        System.out.println("--- PAINEL DE GESTÃO ENCERRADO ---");
    }


    private static void executarRotinasDeVenda(IProdutoService produtoService, IClienteService clienteService, IVendasService vendasService) {
        System.out.println("\n--- ACESSANDO MODO PONTO DE VENDA (PDV) ---");


        System.out.println("\n[PDV] Iniciando Venda 1...");

        Cliente clienteBreno = clienteService.consultarCliente(1);
        Venda venda1 = new Venda(clienteBreno);
        System.out.println("Venda #" + venda1.getId() + " aberta. Cliente: " + venda1.getCliente().getNome());

        Produto pArroz = produtoService.buscarProdutoPorId(1);
        int qtdArroz = 2;
        if (pArroz != null && produtoService.darBaixaEstoque(pArroz.getId(), qtdArroz)) {
            venda1.adicionarItem(new ItensVenda(pArroz.getId(), pArroz.getNome(), qtdArroz, pArroz.getPrecoUnitario()));
            System.out.println("  Item adicionado: 2x " + pArroz.getNome());
            System.out.println("  Estoque atualizado: " + pArroz.getNome() + " | Restante: " + pArroz.getQuantidadeEmEstoque());
        }

        Produto pLeite = produtoService.buscarProdutoPorId(3);
        int qtdLeite = 5;
        if (pLeite != null && produtoService.darBaixaEstoque(pLeite.getId(), qtdLeite)) {
            venda1.adicionarItem(new ItensVenda(pLeite.getId(), pLeite.getNome(), qtdLeite, pLeite.getPrecoUnitario()));
            System.out.println("  Item adicionado: 5x " + pLeite.getNome());
            System.out.println("  Estoque atualizado: " + pLeite.getNome() + " | Restante: " + pLeite.getQuantidadeEmEstoque());
        }

        System.out.println("[PDV] Finalizando Venda #" + venda1.getId() + "...");
        double subtotalV1 = venda1.getSubTotal();
        double precoFinalV1 = DescontoFidelidade.calcularPrecoFinal(venda1.getCliente(), subtotalV1);
        double descontoV1 = subtotalV1 - precoFinalV1;

        venda1.setDesconto(descontoV1);
        vendasService.registrarVenda(venda1);


        System.out.println("\n    --- RECIBO (Venda #" + venda1.getId() + ") ---");
        System.out.println("    Cliente: " + venda1.getCliente().getNome());
        System.out.println("    Categoria: " + venda1.getCliente().getCategoria());
        System.out.println("    ------------------------");
        System.out.printf("    Subtotal:           R$ %5.2f\n", subtotalV1);
        System.out.printf("    Desconto Fidelidade: R$ -%5.2f\n", descontoV1);
        System.out.printf("    TOTAL A PAGAR:      R$ %5.2f\n", venda1.getValorTotal());
        System.out.println("    ------------------------");


        System.out.println("\n[PDV] Iniciando Venda 2...");

        Venda venda2 = new Venda(null);
        System.out.println("Venda #" + venda2.getId() + " aberta. Cliente: (Não informado)");


        Produto pSabonete = produtoService.buscarProdutoPorId(4);
        int qtdSabonete = 1;
        if (pSabonete != null && produtoService.darBaixaEstoque(pSabonete.getId(), qtdSabonete)) {
            venda2.adicionarItem(new ItensVenda(pSabonete.getId(), pSabonete.getNome(), qtdSabonete, pSabonete.getPrecoUnitario()));
            System.out.println("  Item adicionado: 1x " + pSabonete.getNome());
            System.out.println("  Estoque atualizado: " + pSabonete.getNome() + " | Restante: " + pSabonete.getQuantidadeEmEstoque());
        }


        System.out.println("[PDV] Finalizando Venda #" + venda2.getId() + "...");
        double subtotalV2 = venda2.getSubTotal();
        double precoFinalV2 = DescontoFidelidade.calcularPrecoFinal(venda2.getCliente(), subtotalV2);
        double descontoV2 = subtotalV2 - precoFinalV2;

        venda2.setDesconto(descontoV2);
        vendasService.registrarVenda(venda2);


        System.out.println("\n    --- RECIBO (Venda #" + venda2.getId() + ") ---");
        System.out.println("    Cliente: (Não informado)");
        System.out.println("    ------------------------");
        System.out.printf("    Subtotal:           R$ %5.2f\n", subtotalV2);
        System.out.printf("    Desconto Fidelidade: R$ -%5.2f\n", descontoV2);
        System.out.printf("    TOTAL A PAGAR:      R$ %5.2f\n", venda2.getValorTotal());
        System.out.println("    ------------------------");

        System.out.println("\n--- MODO PONTO DE VENDA (PDV) ENCERRADO ---");
    }
}