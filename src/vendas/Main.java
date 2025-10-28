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

        // MUDANÇA AQUI: Adicionando a chamada para os novos testes de exceção
        System.out.println("-----------------------------------------");
        executarTestesDeExcecao(produtoService, clienteService);
        // FIM DA MUDANÇA

        System.out.println("\n=========================================");
        System.out.println("   SISTEMA 2 AMIGOS ENCERRADO");
        System.out.println("=========================================");
    }

    private static void executarRotinasDoGestor(IProdutoService produtoService, IClienteService clienteService) {
        System.out.println("\n--- ACESSANDO PAINEL DE GESTÃO ---");

        System.out.println("\n[GESTOR] 1. Cadastrando produtos no inventário...");

        // Rotinas de cadastro agora podem falhar, então usamos try/catch
        try {
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
        } catch (Exception e) {
            System.out.println("  ERRO INESPERADO no cadastro de produtos: " + e.getMessage());
        }

        System.out.println("\n[GESTOR] 2. Listando inventário (pós-cadastro)...");
        for (Produto p : produtoService.listarTodosProdutos()) {
            System.out.printf("  [ID %d] %-15s | R$ %5.2f | Estoque: %d\n",
                    p.getId(), p.getNome(), p.getPrecoUnitario(), p.getQuantidadeEmEstoque());
        }

        System.out.println("\n[GESTOR] 3. Consultando produto ID 3...");
        Produto produtoConsultado = null;
        try {
            produtoConsultado = produtoService.buscarProdutoPorId(3);
            System.out.println("  Consulta: Produto '" + produtoConsultado.getNome() + "', Preço: R$ " + produtoConsultado.getPrecoUnitario());
        } catch (Exception e) {
            System.out.println("  ERRO INESPERADO ao consultar produto: " + e.getMessage());
        }


        System.out.println("\n[GESTOR] 4. Editando produtos...");
        // Rotinas de edição agora podem falhar, então usamos try/catch
        try {
            if (produtoConsultado != null) {
                System.out.println("  Editando: '" + produtoConsultado.getNome() + "' (ID 3), Novo Preço: R$ 5.50");
                produtoConsultado.setPrecoUnitario(5.50);
            }
            System.out.println("  Editando: 'Litro de Leite' (ID 3), Adicionando +30 ao estoque.");
            produtoService.adicionarEstoque(3, 30);
        } catch (Exception e) {
            System.out.println("  ERRO AO EDITAR PRODUTO: " + e.getMessage());
        }


        System.out.println("\n[GESTOR] 5. Verificando alterações no ID 3...");
        try {
            Produto produtoEditado = produtoService.buscarProdutoPorId(3);
            System.out.printf("  OK: '%s', Preço: R$ %.2f, Estoque: %d\n",
                    produtoEditado.getNome(), produtoEditado.getPrecoUnitario(), produtoEditado.getQuantidadeEmEstoque());
        } catch (Exception e) {
            System.out.println("  ERRO AO VERIFICAR EDIÇÃO: " + e.getMessage());
        }


        System.out.println("\n[GESTOR] 6. Registrando clientes (Fidelidade)...");
        try {
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
        } catch (Exception e) {
            System.out.println("  ERRO INESPERADO no cadastro de clientes: " + e.getMessage());
        }


        System.out.println("\n[GESTOR] 7. Listando clientes (pós-cadastro)...");
        for (Cliente c : clienteService.listarClientes()) {
            System.out.printf("  [ID %d] %-28s | Categoria: %s\n",
                    c.getId(), c.getNome(), c.getCategoria());
        }

        System.out.println("\n[GESTOR] 8. Consultando cliente ID 1...");
        try {
            Cliente clienteConsultado = clienteService.consultarCliente(1);
            System.out.println("  Consulta: " + clienteConsultado.getNome() + " (Telefone: " + clienteConsultado.getTelefone() + ")");
        } catch (Exception e) {
            System.out.println("  ERRO AO CONSULTAR CLIENTE: " + e.getMessage());
        }


        System.out.println("\n[GESTOR] 9. Editando cliente (Promovendo ID 3)...");
        try {
            Cliente clienteParaEditar = clienteService.consultarCliente(3); // Busca a Maria
            System.out.println("  Promovendo: '" + clienteParaEditar.getNome() +
                    "' de " + clienteParaEditar.getCategoria() +
                    " para " + Categoria.CLIENTEOURO);
            clienteParaEditar.setCategoria(Categoria.CLIENTEOURO);
        } catch (Exception e) {
            System.out.println("  ERRO AO EDITAR CLIENTE: " + e.getMessage());
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

        try {
            Cliente clienteBreno = clienteService.consultarCliente(1);
            Venda venda1 = new Venda(clienteBreno);
            System.out.println("Venda #" + venda1.getId() + " aberta. Cliente: " + venda1.getCliente().getNome());

            Produto pArroz = produtoService.buscarProdutoPorId(1);
            int qtdArroz = 2;

            // MUDANÇA AQUI: darBaixaEstoque agora está em um try/catch
            try {
                produtoService.darBaixaEstoque(pArroz.getId(), qtdArroz); // Lança exceção se falhar
                venda1.adicionarItem(new ItensVenda(pArroz.getId(), pArroz.getNome(), qtdArroz, pArroz.getPrecoUnitario()));
                System.out.println("  Item adicionado: 2x " + pArroz.getNome());
                System.out.println("  Estoque atualizado: " + pArroz.getNome() + " | Restante: " + pArroz.getQuantidadeEmEstoque());
            } catch (Exception e) {
                System.out.println("  FALHA AO ADICIONAR ITEM (Arroz): " + e.getMessage());
            }
            // FIM DA MUDANÇA

            Produto pLeite = produtoService.buscarProdutoPorId(3);
            int qtdLeite = 5;

            // MUDANÇA AQUI: darBaixaEstoque agora está em um try/catch
            try {
                produtoService.darBaixaEstoque(pLeite.getId(), qtdLeite); // Lança exceção se falhar
                venda1.adicionarItem(new ItensVenda(pLeite.getId(), pLeite.getNome(), qtdLeite, pLeite.getPrecoUnitario()));
                System.out.println("  Item adicionado: 5x " + pLeite.getNome());
                System.out.println("  Estoque atualizado: " + pLeite.getNome() + " | Restante: " + pLeite.getQuantidadeEmEstoque());
            } catch (Exception e) {
                System.out.println("  FALHA AO ADICIONAR ITEM (Leite): " + e.getMessage());
            }
            // FIM DA MUDANÇA

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

        } catch (Exception e) {
            System.out.println("ERRO CRÍTICO NA VENDA 1: " + e.getMessage());
        }


        System.out.println("\n[PDV] Iniciando Venda 2...");
        try {
            Venda venda2 = new Venda(null);
            System.out.println("Venda #" + venda2.getId() + " aberta. Cliente: (Não informado)");

            Produto pSabonete = produtoService.buscarProdutoPorId(4);
            int qtdSabonete = 1;

            // MUDANÇA AQUI: darBaixaEstoque agora está em um try/catch
            try {
                produtoService.darBaixaEstoque(pSabonete.getId(), qtdSabonete); // Lança exceção se falhar
                venda2.adicionarItem(new ItensVenda(pSabonete.getId(), pSabonete.getNome(), qtdSabonete, pSabonete.getPrecoUnitario()));
                System.out.println("  Item adicionado: 1x " + pSabonete.getNome());
                System.out.println("  Estoque atualizado: " + pSabonete.getNome() + " | Restante: " + pSabonete.getQuantidadeEmEstoque());
            } catch (Exception e) {
                System.out.println("  FALHA AO ADICIONAR ITEM (Sabonete): " + e.getMessage());
            }
            // FIM DA MUDANÇA

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

        } catch (Exception e) {
            System.out.println("ERRO CRÍTICO NA VENDA 2: " + e.getMessage());
        }

        System.out.println("\n--- MODO PONTO DE VENDA (PDV) ENCERRADO ---");
    }

    // ===================================================================
    // ===================================================================
    // AQUI COMEÇA A NOVA SEÇÃO DE TESTES DE EXCEÇÃO
    // ===================================================================
    // ===================================================================
    private static void executarTestesDeExcecao(IProdutoService produtoService, IClienteService clienteService) {
        System.out.println("\n--- ACESSANDO MODO DE TESTES DE EXCEÇÃO ---");

        // --- TESTES DE PRODUTO ---
        System.out.println("\n--- Testes de Produto ---");

        // Cadastrar: Dados incompletos (nome vazio)
        System.out.println("\n[TESTE] Cadastrar Produto: Dados incompletos (nome vazio)");
        try {
            Produto p = new Produto(5, "", 10.0, 10);
            produtoService.cadastrarProduto(p);
            System.out.println("  FALHA: Sistema aceitou produto com nome vazio.");
        } catch (Exception e) {
            System.out.println("  SUCESSO: Exceção capturada. Mensagem: " + e.getMessage());
        }

        // Cadastrar: Dados em tipo inválido (Estoque negativo)
        System.out.println("\n[TESTE] Cadastrar Produto: Estoque negativo");
        try {
            Produto p = new Produto(5, "Produto Teste", 10.0, -10);
            produtoService.cadastrarProduto(p);
            System.out.println("  FALHA: Sistema aceitou produto com estoque negativo.");
        } catch (Exception e) {
            System.out.println("  SUCESSO: Exceção capturada. Mensagem: " + e.getMessage());
        }

        // Cadastrar: Dados em tipo inválido (Preço negativo)
        System.out.println("\n[TESTE] Cadastrar Produto: Preço negativo");
        try {
            Produto p = new Produto(5, "Produto Teste", -10.0, 10);
            produtoService.cadastrarProduto(p);
            System.out.println("  FALHA: Sistema aceitou produto com preço negativo.");
        } catch (Exception e) {
            System.out.println("  SUCESSO: Exceção capturada. Mensagem: " + e.getMessage());
        }

        // Consultar: Produto Inexistente
        System.out.println("\n[TESTE] Consultar Produto: Inexistente (ID 999)");
        try {
            produtoService.buscarProdutoPorId(999);
            System.out.println("  FALHA: Sistema não lançou exceção para produto inexistente.");
        } catch (Exception e) {
            System.out.println("  SUCESSO: Exceção capturada. Mensagem: " + e.getMessage());
        }

        // Editar: Dados em tipo inválido (Estoque negativo)
        System.out.println("\n[TESTE] Editar Produto: Estoque negativo");
        try {
            Produto p = produtoService.buscarProdutoPorId(1); // Pega o Arroz
            p.setQuantidadeEmEstoque(-5);
            System.out.println("  FALHA: Sistema aceitou edição para estoque negativo.");
        } catch (Exception e) {
            System.out.println("  SUCESSO: Exceção capturada. Mensagem: " + e.getMessage());
        }

        // Alterar Estoque: Estoque negativo (adicionar negativo)
        System.out.println("\n[TESTE] Alterar Estoque: Adicionar quantidade negativa");
        try {
            produtoService.adicionarEstoque(1, -10);
            System.out.println("  FALHA: Sistema aceitou adicionar estoque negativo.");
        } catch (Exception e) {
            System.out.println("  SUCESSO: Exceção capturada. Mensagem: " + e.getMessage());
        }

        // --- TESTES DE CLIENTE ---
        System.out.println("\n--- Testes de Cliente ---");

        // Cadastrar: Dados incompletos (nome vazio)
        System.out.println("\n[TESTE] Cadastrar Cliente: Dados incompletos (nome vazio)");
        try {
            Cliente c = new Cliente(4, "", "1234-5678", Categoria.CLIENTECOMUM);
            clienteService.cadastrarCliente(c);
            System.out.println("  FALHA: Sistema aceitou cliente com nome vazio.");
        } catch (Exception e) {
            System.out.println("  SUCESSO: Exceção capturada. Mensagem: " + e.getMessage());
        }

        // Consultar: Cliente Inexistente
        System.out.println("\n[TESTE] Consultar Cliente: Inexistente (ID 999)");
        try {
            clienteService.consultarCliente(999);
            System.out.println("  FALHA: Sistema não lançou exceção para cliente inexistente.");
        } catch (Exception e) {
            System.out.println("  SUCESSO: Exceção capturada. Mensagem: " + e.getMessage());
        }

        // Editar: Dados incompletos (nome vazio)
        System.out.println("\n[TESTE] Editar Cliente: Nome vazio");
        try {
            Cliente c = clienteService.consultarCliente(1); // Pega o Breno
            c.setNome("");
            System.out.println("  FALHA: Sistema aceitou edição para nome vazio.");
        } catch (Exception e) {
            System.out.println("  SUCESSO: Exceção capturada. Mensagem: " + e.getMessage());
        }

        // --- TESTES DE VENDA (via Serviço de Produto) ---
        System.out.println("\n--- Testes de Venda ---");

        // Venda: Quantidade superior ao estoque
        System.out.println("\n[TESTE] Venda: Quantidade superior ao estoque");
        try {
            // Arroz (ID 1) tem 48 em estoque (50 - 2 da Venda 1)
            produtoService.darBaixaEstoque(1, 1000); // Tenta comprar 1000
            System.out.println("  FALHA: Sistema permitiu baixa de estoque superior ao disponível.");
        } catch (Exception e) {
            System.out.println("  SUCESSO: Exceção capturada. Mensagem: " + e.getMessage());
        }

        // Venda: Quantidade zerada ou negativa
        System.out.println("\n[TESTE] Venda: Quantidade zerada");
        try {
            produtoService.darBaixaEstoque(1, 0);
            System.out.println("  FALHA: Sistema permitiu baixa de estoque zerada.");
        } catch (Exception e) {
            System.out.println("  SUCESSO: Exceção capturada. Mensagem: " + e.getMessage());
        }

        System.out.println("\n[TESTE] Venda: Quantidade negativa");
        try {
            produtoService.darBaixaEstoque(1, -1);
            System.out.println("  FALHA: Sistema permitiu baixa de estoque negativa.");
        } catch (Exception e) {
            System.out.println("  SUCESSO: Exceção capturada. Mensagem: " + e.getMessage());
        }

        // Venda: Produto inexistente (Já coberto pelo teste de Produto)
        System.out.println("\n[TESTE] Venda: Produto inexistente (ID 999)");
        try {
            produtoService.darBaixaEstoque(999, 1);
            System.out.println("  FALHA: Sistema não lançou exceção para produto inexistente na venda.");
        } catch (Exception e) {
            System.out.println("  SUCESSO: Exceção capturada. Mensagem: " + e.getMessage());
        }

        System.out.println("\n--- TESTES DE EXCEÇÃO ENCERRADOS ---");
    }
}